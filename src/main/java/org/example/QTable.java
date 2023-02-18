package main.java.org.example;

public class QTable {
    private final State[][] qTable;

    public QTable() {
        qTable = new State[QLearningConstant.DIMENSION][QLearningConstant.DIMENSION];
        for (int j = 0; j < QLearningConstant.DIMENSION; j++) {
            for (int i = 0; i < QLearningConstant.DIMENSION; i++) {
                qTable[j][i] = new State();
            }
        }
    }

    public State[][] getQTable() {
        return qTable;
    }

    public State getStateByCell(Cell cell) {
        return qTable[cell.coordinate().x()][cell.coordinate().y()];
    }
}
