package process.qlearn;

import java.util.ArrayList;
import java.util.Random;

import data.elements.Hole;
import data.qlearn.*;

public class QLearnCore {
    private static final float MALUS_VALUE = -10f;
    private Grid grid;
    private QTable qTable;
    private int nbIte;
    private float learningRate, explorationRate, discountFactor;

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

    private void generateHole() {
        for (int i = 0; i < grid.getSize() - 1; i++) {
            for (int j = 0; j < grid.getSize() - 1; j++) {
                if ((i != grid.getEndingCell().getCoordinate().coordinateX()
                        && i != grid.getStartingCell().getCoordinate().coordinateX())
                        && (j != grid.getEndingCell().getCoordinate().coordinateY()
                                && j != grid.getEndingCell().getCoordinate().coordinateY())) {
                    if ((float) Math.random() < 0.2) {
                        Cell tmp = grid.getCell(i, j);
                        tmp.setElement(new Hole(tmp.getCoordinate()));
                        tmp.setqValue(MALUS_VALUE);
                    }
                }
            }
        }

    }

    public void doOneIteration() {
        Cell currentCell = grid.getStartingCell();
        int stepCount = 0;
        while (!currentCell.equals(grid.getEndingCell()) && stepCount < grid.getSize() * grid.getSize()) {
            Cell nextCell = null;
            float maxVal = Float.NEGATIVE_INFINITY;
            float random = new Random().nextFloat();
            if (random < explorationRate) {
                for (Cell adjacentCell : grid.getNeighbors(currentCell)) {
                    int x = adjacentCell.getCoordinate().coordinateX();
                    int y = adjacentCell.getCoordinate().coordinateY();
                    float qValue = qTable.getQtab(x, y);
                    if (qValue > maxVal) {
                        maxVal = qValue;
                        nextCell = adjacentCell;
                    }
                }
            } else {
                nextCell = grid.getRandomNeighbors(currentCell);
                maxVal = qTable.getQtab(nextCell.getCoordinate().coordinateX(), nextCell.getCoordinate().coordinateY());
            }
            float reward = nextCell.getqValue();
            currentCell.setqValue(qLearnFormula(currentCell.getqValue(), reward, maxVal, discountFactor));
            stepCount++;
            currentCell = nextCell;
        }
        learningRate = learningRate * 0.99f;
        explorationRate = explorationRate * 0.99f;
        qTable.updateAllValue(grid.generateArrayQValue());
    }

    private float qLearnFormula(float currentVal, float reward, float maxQValue, float discountFactor) {
        float newQValue = currentVal + learningRate * (reward + discountFactor * maxQValue - currentVal);
        if (reward == MALUS_VALUE) {
            newQValue -= learningRate * (reward + discountFactor * maxQValue - currentVal);
        }
        return newQValue;
    }

    public QTable getqTable() {
        return qTable;
    }

    public Grid getGrid() {
        return grid;
    }

    public ArrayList<Cell> bestPath() {
        Cell currentCell = grid.getStartingCell();
        Cell endingCell = grid.getEndingCell();
        ArrayList<Cell> bestPath = new ArrayList<Cell>();
        while (!currentCell.equals(endingCell)) {
            Cell nextCell = null;
            float maxQValue = Float.NEGATIVE_INFINITY;
            for (Cell neighbor : grid.getNeighbors(currentCell)) {
                float qValue = qTable.getQtab(neighbor.getCoordinate().coordinateX(),
                        neighbor.getCoordinate().coordinateY());
                if (qValue > maxQValue) {
                    nextCell = neighbor;
                    maxQValue = qValue;
                }
            }
            bestPath.add(currentCell);
            currentCell = nextCell;
        }
        bestPath.add(currentCell);
        return bestPath;
    }
}