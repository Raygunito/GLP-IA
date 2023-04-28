package process.qlearn;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import data.elements.Hole;
import data.qlearn.*;
import log.LoggerUtility;

public class QLearnCore {
    private QGrid grid;
    private QTable qTable;
    private int nbIte, nbTot;
    private float learningRate, explorationRate, discountFactor;
    private static Logger logger = LoggerUtility.getLogger(QLearnCore.class, "text");

    // DINGUERIE ICI
    public ArrayList<QCell> path;
    public int success = 0;

    /**
     * Constructs an instance of the QLearnCore class with the specified parameters.
     * 
     * @param gridSize        The size of the grid.
     * @param nbIteration     The number of iterations for the agent to learn.
     * @param learningRate    The learning rate.
     * @param explorationRate The exploration rate.
     * @param discountFactor  The discount factor.
     */
    public QLearnCore(int gridSize, int nbIteration, float learningRate, float explorationRate, float discountFactor) {
        this.grid = new QGrid(gridSize);
        this.qTable = new QTable(grid);
        this.nbIte = nbIteration;
        this.nbTot = nbIteration;
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
     * We generate random hole, but there is always one path available (full Right
     * then full Down)
     * <p>
     * The function iterates over all cells in the grid except for the starting cell
     * and the ending cell, and randomly assigns a hole to each cell with a certain
     * probability. The probability is a value of <b>0.35f</b>. The
     * coordinates of the generated holes are logged to the logger with the TRACE
     * level.
     */
    private void generateHole() {
        String res = "";
        for (int i = 0; i < grid.getSize() - 1; i++) {
            for (int j = 0; j < grid.getSize() - 1; j++) {
                if ((i != grid.getEndingCell().getCoordinate().coordinateY()
                        && j != grid.getStartingCell().getCoordinate().coordinateX())) {
                    if ((float) Math.random() < 0.35f) {
                        QCell tmp = grid.getCell(i, j);
                        tmp.setElement(new Hole(tmp.getCoordinate()));
                        res = res + tmp.toString() + ",";
                    }
                }
            }
            res = res + "\n";
        }
        logger.trace(res + "sont des trous");
    }

    /**
     * Performs one iteration of the Q-learning algorithm for the grid world.
     * <p>
     * In one iteration, the function randomly chooses a starting cell, and then
     * repeatedly selects the next cell based on the current cell and the Epsilon
     * greedy policy until either the agent reaches the ending cell or the maximum
     * number of steps is reached. The Q-learning formula is used to update the
     * Q-values of the state-action pairs encountered during the iteration. The
     * learning rate and exploration rate are also updated after each iteration.
     * <p>
     * We limited the amount of movement at 2n^2 which should be enough for the
     * agent to move freely.
     */
    public void doOneIteration() {
        path = new ArrayList<QCell>();
        int xRand = new Random().nextInt(grid.getSize() - 1);
        int yRand = new Random().nextInt(grid.getSize() - 1);
        QCell currentCell = grid.getCell(xRand, yRand);
        path.add(currentCell);
        int stepCount = 0;
        while (!currentCell.equals(grid.getEndingCell()) && stepCount < ((grid.getSize() * grid.getSize()) * 2)) {
            QCell nextCell = null;

            nextCell = selectNextCell(currentCell);
            Direction direction = findDirection(currentCell, nextCell);

            float nextCellMaxQValue = nextCell.getqValue()[nextCell.bestDirection()];

            float reward = nextCell.getqValueFromDirection(Direction.getDirectionFromValue(nextCell.bestDirection()))
                    * 0.8f;
            if (nextCell.equals(grid.getEndingCell())) {
                reward = QGrid.ENDING_VALUE;
            }
            reward = (currentCell.getElement() instanceof Hole) ? QGrid.MALUS_VALUE : reward;
            float newQValue = qLearnFormula(currentCell.getqValueFromDirection(direction), reward, nextCellMaxQValue,
                    discountFactor);
            currentCell.setqValue(newQValue, direction.getValue());
            path.add(nextCell);
            currentCell = nextCell;
            stepCount++;
        }
        nbIte--;
        qTable.updateAllValues(grid.generateArrayQValue());
        learningRate = learningRate * 0.99f;
        explorationRate = explorationRate * 0.99f;
    }

    /**
     * Choisis la prochaine case (aléa ou bien le meilleur selon le taux
     * d'exploration)
     * 
     * @param currentCell
     * @return QCell of the next cell
     */
    private QCell selectNextCell(QCell currentCell) {
        QCell tmp = null;
        float maxVal = Float.NEGATIVE_INFINITY;
        float random = new Random().nextFloat();
        float qValue;
        if (random >= explorationRate) {
            return grid.getRandomNeighbors(currentCell);
        }
        for (QCell adjacentCell : grid.getNeighbors(currentCell)) {
            qValue = adjacentCell.getqValue()[adjacentCell.bestDirection()];
            if (qValue > maxVal) {
                maxVal = qValue;
                tmp = adjacentCell;
            }
        }
        return tmp;
    }

    /**
     * 
     * Returns the direction from one cell to another on the grid.
     * 
     * @param currentCell The starting cell.
     * @param nextCell    The destination cell.
     * @return The direction from currentCell to nextCell, one of UP, DOWN, LEFT, or
     *         RIGHT.
     * @see Direction
     */
    private Direction findDirection(QCell currentCell, QCell nextCell) {
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
        logger.error("Direction not found between " + currentCell.toString() + " and " + nextCell.toString());
        return null;
    }

    /**
     * 
     * Computes the new Q-value using the Q-learning formula.
     * <p>
     * The Q-learning formula computes the new Q-value for a state-action pair
     * using the current Q-value, the reward obtained, the maximum Q-value of
     * the next state, and the discount factor. The learning rate is also
     * taken into account.
     * 
     * @param currentVal     the current Q-value for the state-action pair.
     * @param reward         the reward obtained by taking the action in the state.
     * @param maxQValue      the maximum Q-value of the next state.
     * @param discountFactor the discount factor used to weigh future rewards.
     * @return the new Q-value computed using the Q-learning formula.
     */
    public float qLearnFormula(float currentVal, float reward, float maxQValue, float discountFactor) {
        float newQValue = (1 - learningRate) * currentVal + learningRate * (reward + (discountFactor * maxQValue));
        return newQValue;
    }

    public QTable getqTable() {
        return qTable;
    }

    public QGrid getGrid() {
        return grid;
    }

    public int getNbIte() {
        return nbIte;
    }

    public ArrayList<QCell> getPath() {
        return path;
    }

    public int getSize() {
        return grid.getSize();
    }

    public int getNbTot() {
        return nbTot;
    }

    public float getLearningRate() {
        return learningRate;
    }

    public float getExplorationRate() {
        return explorationRate;
    }

    public float getDiscountFactor() {
        return discountFactor;
    }

    /**
     * Returns the best path as an ArrayList of QCells.
     * The path is computed using the Q-learning algorithm and is represented
     * as an ArrayList of QCells starting from the grid's starting cell and
     * ending at the grid's ending cell.
     * 
     * @return an ArrayList of QCells representing the best path from the
     *         starting cell to the ending cell.
     */
    public ArrayList<QCell> bestPath() {
        QCell currentCell = grid.getStartingCell();
        ArrayList<QCell> bestPath = new ArrayList<QCell>();
        int n = grid.getSize();
        int i = 0;
        bestPath.add(currentCell);
        while (!grid.getEndingCell().equals(currentCell) && i < n * n) {
            Direction direction = Direction.getDirectionFromValue(currentCell.bestDirection());
            QCell nextCell = grid.getCellFromDirection(currentCell, direction);
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
        for (QCell cell : bestPath) {
            res = res + cell.toString() + "->";
        }
        res = res + "END";
        logger.trace(res);
        return bestPath;
    }

    public static void main(String[] args) {
        logger.setLevel(Level.ERROR);
        int gridSize = 10;
        int nbIteration = 200;
        float learningRate = 0.2f;
        float explorationRate = 0.5f;
        float discountFactor = 0.8f;
        QLearnCore qLearn = new QLearnCore(gridSize, nbIteration, learningRate, explorationRate, discountFactor);
        // Run the iterations
        // for (int i = 0; i < nbIteration; i++) {
        // System.out.println("Iteration numéro : " + i);
        // qLearn.doOneIteration();
        // }
        // logger.info("\n" + qLearn.getqTable().printTable());
        // qLearn.bestPath();

        System.out.println("Grid size = 10");
        for (int i = 0; i < 30; i++) {
            Instant start = Instant.now();
            int counter = 0;
            for (int n = 1; n < 500; n++) {
                qLearn = new QLearnCore(10, n, learningRate, explorationRate,
                        discountFactor);
                for (int k = 0; k < n; k++) {
                    qLearn.doOneIteration();
                }
                qLearn.bestPath();
                counter = counter + qLearn.success;
            }
            Instant end = Instant.now();
            float valueRate = Float.valueOf(counter) / 5;
            System.out.println(Duration.between(start, end).toMillis() + " ms\t Success Rate : " + valueRate);

        }
    }

}