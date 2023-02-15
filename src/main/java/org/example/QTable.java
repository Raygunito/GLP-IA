package main.java.org.example;

public class QTable {
    private State[][] qTable;
    public  QTable() {
        qTable = new State[3][3];
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                qTable[j][i] = new State();
            }
        }
    }
    public State[][] getQTable(){
        return qTable;
    }
    public void setQTable(int x,int y,int z,int value){
        qTable[x][y].getQValue()[z]=value;
    }


}
