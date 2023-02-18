package main.java.org.example;

public class QTable {
    private final State[][] qTable;

    public QTable() {
        qTable = new State[3][3];
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
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
