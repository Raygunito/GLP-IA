package main.java.org.example;

import java.util.Random;

public class QLearningCore {
    private Grid grid;
    private QTable qTable;
    private static final int ITERATION = 50;
    private final static double RATIO = 0.9;

    public QLearningCore() {
        grid = new Grid();
        qTable = new QTable();
    }
    public int getMove(Cell cell,double epsilon){
        Random random=new Random();
        if(epsilon> random.nextDouble(1)){
            return random.nextInt(4);
        }else{
            return qTable.getQTable()[cell.getCoordinate().x()][cell.getCoordinate().y()].getMax();
        }
    }
    public Cell run(Cell cell){
                int move = getMove(cell, 0.4);
                    Cell nextCell = grid.move(cell, move);
                    int nextMove = getMove(nextCell, 0);
                    double aza=RATIO * qTable.getQTable()[nextCell.getCoordinate().x()][nextCell.getCoordinate().y()].getQValue()[nextMove] - qTable.getQTable()[cell.getCoordinate().x()][cell.getCoordinate().y()].getQValue()[move];
                    double aeaz = 0.1 * (nextCell.getReward()+aza);
                    qTable.getQTable()[cell.getCoordinate().x()][cell.getCoordinate().y()].getQValue()[move] += aeaz;
                    return nextCell;
            }

    public Grid getGrid() {
        return grid;
    }

    public QTable getqTable() {
        return qTable;
    }
}
