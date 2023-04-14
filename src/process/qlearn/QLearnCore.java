package process.qlearn;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

import data.elements.Hole;
import data.qlearn.*;
import log.LoggerUtility;

public class QLearnCore {
    private Grid grid;
    private QTable qTable;
    private int nbIte;
    private float learningRate, explorationRate, discountFactor;
    private static Logger logger = LoggerUtility.getLogger(QLearnCore.class, "text");

    // DINGUERIE ICI
    public ArrayList<Cell> path;
    public int success = 0;

    public QLearnCore(int gridSize, int nbIteration, float learningRate, float explorationRate, float discountFactor) {
        this.grid = new Grid(gridSize);
        this.qTable = new QTable(grid);
        this.nbIte = nbIteration;
        this.learningRate = learningRate;
        this.explorationRate = explorationRate;
        // Negative value not allowed
        if (learningRate < 0) {
            this.learningRate = 0.01f;
        }
        if (explorationRate < 0) {
            this.explorationRate = 0.01f;
        }
        generateHole();
    }

    /**
     * On génère des trous de manière aléatoire
     */
    private void generateHole() {
        for (int i = 0; i < grid.getSize() - 1; i++) {
            for (int j = 0; j < grid.getSize() - 1; j++) {
                if ((i != grid.getEndingCell().getCoordinate().coordinateX()
                        && i != grid.getStartingCell().getCoordinate().coordinateX())
                        && (j != grid.getEndingCell().getCoordinate().coordinateY()
                                && j != grid.getEndingCell().getCoordinate().coordinateY())) {
                    if ((float) Math.random() < 0.35f) {
                        Cell tmp = grid.getCell(i, j);
                        tmp.setElement(new Hole(tmp.getCoordinate()));
                        for (int k = 0; k < 4; k++) {
                            tmp.setqValue(Grid.MALUS_VALUE, k);
                        }
                        logger.debug(tmp.toString() + "est un trou");
                    }
                }
            }
        }

    }

    public void doOneIteration() {
        path = new ArrayList<Cell>();
        int xRand = new Random().nextInt(grid.getSize() - 1);
        int yRand = new Random().nextInt(grid.getSize() - 1);
        Cell currentCell = grid.getCell(xRand, yRand);
        path.add(currentCell);
        int stepCount = 0;
        while (!currentCell.equals(grid.getEndingCell()) && stepCount < ((grid.getSize() * grid.getSize()) * 2)) {
            Cell nextCell = null;

            nextCell = selectNextCell(currentCell);
            Direction direction = findDirection(currentCell, nextCell);

            float nextCellMaxQValue = nextCell.getqValue()[nextCell.bestDirection()];

            float reward =   nextCell.getqValueFromDirection(Direction.getDirectionFromValue(nextCell.bestDirection()))
                    * 0.8f;
            if (nextCell.equals(grid.getEndingCell())) {
                reward = Grid.ENDING_VALUE;
            }
            reward = (currentCell.getElement() instanceof Hole) ? -10 : reward;
            float newQValue = qLearnFormula(currentCell.getqValueFromDirection(direction), reward, nextCellMaxQValue,
                    discountFactor);
            currentCell.setqValue(newQValue, direction.getValue());
            // qTable.setQValue(currentCell.getCoordinate().coordinateX(),
            // currentCell.getCoordinate().coordinateY(), direction, newQValue);
            path.add(nextCell);

            currentCell = nextCell;
            stepCount++;
        }
        qTable.updateAllValues(grid.generateArrayQValue());
        learningRate = learningRate * 0.99f;
        explorationRate = explorationRate * 0.99f;
    }

    /**
     * Choisis la prochaine case (aléa ou bien le meilleur selon le taux
     * d'exploration)
     * 
     * @param currentCell
     * @return
     */
    private Cell selectNextCell(Cell currentCell) {
        Cell tmp = null;
        float maxVal = Float.NEGATIVE_INFINITY;
        float random = new Random().nextFloat();
        float qValue;
        if (random >= explorationRate) {
            return grid.getRandomNeighbors(currentCell);
        }
        for (Cell adjacentCell : grid.getNeighbors(currentCell)) {
            qValue = adjacentCell.getqValue()[adjacentCell.bestDirection()];
            if (qValue > maxVal) {
                maxVal = qValue;
                tmp = adjacentCell;
            }
        }
        return tmp;
    }

    private Direction findDirection(Cell currentCell, Cell nextCell) {
        try {
            if (grid.getUp(currentCell).equals(nextCell)) {
                return Direction.UP;
            }
        } catch (GridBorderException e) {
        }
        try {
            if (grid.getDown(currentCell).equals(nextCell)) {
                return Direction.DOWN;
            }
        } catch (GridBorderException e) {
        }
        try {
            if (grid.getLeft(currentCell).equals(nextCell)) {
                return Direction.LEFT;
            }
        } catch (GridBorderException e) {
        }
        try {
            if (grid.getRight(currentCell).equals(nextCell)) {
                return Direction.RIGHT;
            }
        } catch (GridBorderException e) {
        }
        return null;
    }

    /**
     * Formule pour calculer la nouvelle valeur de Q(S,a)
     * 
     * @param currentVal
     * @param reward
     * @param maxQValue
     * @param discountFactor
     * @return
     */
    private float qLearnFormula(float currentVal, float reward, float maxQValue, float discountFactor) {
        float newQValue = (1 - learningRate) * currentVal + learningRate * (reward + (discountFactor * maxQValue));
        return newQValue;
    }

    public QTable getqTable() {
        return qTable;
    }

    public Grid getGrid() {
        return grid;
    }

    public int getNbIte() {
        return nbIte;
    }

    /**
     * Le chemin est donné sous forme d'arrayList
     * 
     * @return
     */
    public ArrayList<Cell> bestPath() {
        Cell currentCell = grid.getStartingCell();
        ArrayList<Cell> bestPath = new ArrayList<Cell>();
        int n = grid.getSize();
        int i = 0;
        while (!grid.getEndingCell().equals(currentCell) && i < n * n) {
            Direction direction = Direction.getDirectionFromValue(currentCell.bestDirection());
            Cell nextCell = grid.getCellFromDirection(currentCell, direction);
            bestPath.add(nextCell);
            currentCell = nextCell;
            i++;
        }
        if (grid.getEndingCell().equals(currentCell)) {
            success++;
        }
        if (!bestPath.contains(grid.getEndingCell())) {
            logger.warn("Attention l'agent n'a pas résolu l'environnement.");
        }
        String res = "";
        for (Cell cell : bestPath) {
            res = res + cell.toString() + "->";
        }
        res = res + "END";
        logger.trace(res);
        return bestPath;
    }

    public static void main(String[] args) {
        int gridSize = 10;
        int nbIteration = 100;
        float learningRate = 0.2f;
        float explorationRate = 0.5f;
        float discountFactor = 0.8f;
        QLearnCore qLearn = new QLearnCore(gridSize, nbIteration, learningRate, explorationRate, discountFactor);
        // Run the iterations
        for (int i = 0; i < nbIteration; i++) {
            System.out.println("Iteration numéro : " + i);
            qLearn.doOneIteration();
        }
        logger.info("\n" + qLearn.getqTable().printTable());
        qLearn.bestPath();

        // System.out.println("Grid size = 4");
        // for (int i = 0; i < 30; i++) {
        // Instant start = Instant.now();
        // int counter = 0;
        // for (int n = 1; n < 500; n++) {
        // qLearn = new QLearnCore(4, n, learningRate, explorationRate,
        // discountFactor);
        // for (int k = 0; k < n; k++) {
        // qLearn.doOneIteration();
        // }
        // qLearn.bestPath();
        // counter = counter + qLearn.success;
        // }
        // Instant end = Instant.now();
        // float valueRate = Float.valueOf(counter)/5;
        // System.out.println(Duration.between(start, end).toMillis() + " ms\t Success
        // Rate : " + valueRate);

        // }
    }
}