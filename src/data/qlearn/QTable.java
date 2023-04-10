package data.qlearn;

import java.text.DecimalFormat;

public class QTable {
    private float[][][] qtab;
    private int size;

    public QTable(Grid grid) {
        qtab = grid.generateArrayQValue();
        size = grid.getSize();
    }

    public void updateAllValues(float[][][] table) {
        qtab = table;
    }

    public void setQValue(int x, int y, Direction dir, float value) {
        qtab[x][y][dir.getValue()] = value;
    }

    public float getQValue(int x, int y, int action) {
        return qtab[x][y][action];
    }
    public int getSize() {
        return size;
    }
    public String printTable() {
        String res = "\tUP\tDOWN\tLEFT\tRIGHT\n";
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < qtab.length; i++) {
            for (int j = 0; j < qtab.length; j++) {
                res += "[" + i + "," + j + "]\t";
                for (int k = 0; k < qtab[i][j].length; k++) {
                    res += df.format(qtab[i][j][k]) + "\t";
                }
                res += "\n";
            }
            res += "\n";
        }
        return res;
    }

}
