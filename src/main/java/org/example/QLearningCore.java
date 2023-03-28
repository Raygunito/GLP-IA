package main.java.org.example;

import java.util.Random;

public class QLearningCore {
    private final Grid grid;
    private final QTable qTable;
    private static final int ITERATION = 1000000;
    private final static double RATIO = 1;
    private final static double LEARNING_RATE = 0.6;
    private final static double EXPLORATION_PROBABILITY = 0.2;

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
        double propagatingValue = RATIO * (qTable.getStateByCell(nextCell).getQValue()[nextMove] - qTable.getStateByCell(cell).getQValue()[move]);
        double addedValue = LEARNING_RATE * (nextCell.reward() + propagatingValue);
        qTable.getStateByCell(cell).getQValue()[move] += addedValue;
//        qTable.getStateByCell(cell).getQValue()[move]+=updateValue(4,0,0,cell,move);
        return nextCell;
    }
    private double updateValue(int depth,double propagationValue,double addedValue,Cell cell,int move){
        if(depth!=0){
    return addedValue;
        }
        else{
            Cell nextCell;
            try {
                nextCell = grid.move(cell, move);
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                nextCell = grid.move(cell, 3 - move);
            }
            int nextMove=getMove(nextCell,0);
            return LEARNING_RATE*(nextCell.reward()+RATIO * updateValue(depth-1,propagationValue,addedValue,nextCell,nextMove) - qTable.getStateByCell(cell).getQValue()[move]);
        }
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
    public Cell run(Cell cell){
        return process(cell);
    }
    public QTable getqTable(){
        return qTable;
    }
    public Grid getGrid(){
        return grid;
    }
}
