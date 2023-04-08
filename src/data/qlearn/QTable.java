package data.qlearn;

import java.text.DecimalFormat;

public class QTable {
    private float[][] qtab;

    public QTable(Grid grid) {
        qtab = grid.generateArrayQValue();
    }

    public void updateAllValue(float[][] table) {
        qtab = table;
    }

    public float getQtab(int x, int y) {
        return qtab[x][y];
    }

    public String printTable() {
        String res = "";
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 0; i < qtab.length; i++) {
            for (int j = 0; j < qtab.length; j++) {
                res += "[" + i + "," + j + "] : " + df.format(qtab[i][j]);
            }
            res+= "\n";
        }
        return res;
    }
}
