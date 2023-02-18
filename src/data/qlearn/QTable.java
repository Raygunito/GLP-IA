package data.qlearn;

public class QTable {
    private float[][] qtab;
    public QTable(float[][] tab) {
        qtab=tab;
    }
    
    
    public float getQtab(int x, int y) {
        return qtab[x][y];
    }
}
