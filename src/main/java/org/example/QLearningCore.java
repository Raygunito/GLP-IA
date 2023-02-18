package main.java.org.example;

import java.util.Random;

public class QLearningCore {
    private final Grid grid;
    private final QTable qTable;
    private static final int ITERATION = 50;
    private final static double RATIO = 0.9;
    private final static double LEARNING_RATE = 0.1;
    private final static double EXPLORATION_PROBABILITY = 0.4;

    public QLearningCore() {
        grid = new Grid();
        qTable = new QTable();
    }

    public int getMove(Cell cell, double explorationProbability) {
        Random random = new Random();
        if (explorationProbability > random.nextDouble(1)) {
            return random.nextInt(4);
        } else {
            return qTable.getStateByCell(cell).getMax();
        }
    }

    private Cell process(Cell cell) {
        int move = getMove(cell, EXPLORATION_PROBABILITY);
        Cell nextCell;
        try {
            nextCell = grid.move(cell, move);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            nextCell = grid.move(cell, 3 - move);
        }
        int nextMove = getMove(nextCell, 0);
        double propagatingValue = RATIO * qTable.getStateByCell(nextCell).getQValue()[nextMove] - qTable.getStateByCell(cell).getQValue()[move];
        double addedValue = LEARNING_RATE * (nextCell.reward() + propagatingValue);
        qTable.getStateByCell(cell).getQValue()[move] += addedValue;
        return nextCell;
    }

    private void runIteration() {
        Cell cell = grid.getStartingCell();
        while (cell != grid.getEndingCell()) {
            cell = process(cell);
        }
    }

    public void run() {
        for (int i = 0; i < ITERATION; i++) {
            runIteration();
        }
    }
}
