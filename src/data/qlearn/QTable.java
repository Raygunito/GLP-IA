package data.qlearn;

import java.text.DecimalFormat;

public class QTable {
    private float[][][] qtab;
    private int size;

    /**
     * Constructor for creating a QTable instance from a QGrid instance
     * 
     * @param grid the QGrid instance from which to create the QTable
     */
    public QTable(QGrid grid) {
        qtab = grid.generateArrayQValue();
        size = grid.getSize();
    }

    /**
     * Updates all the values of the QTable with the given values
     * 
     * @param table a 3-dimensional array of float values representing the updated
     *              QTable
     */
    public void updateAllValues(float[][][] table) {
        qtab = table;
    }

    /**
     * Sets the Q-value of a cell in the QTable
     * 
     * @param x     the x-coordinate of the cell
     * @param y     the y-coordinate of the cell
     * @param dir   the direction for which the Q-value is to be set
     * @param value the Q-value to set for the given cell and direction
     */
    public void setQValue(int x, int y, Direction dir, float value) {
        qtab[x][y][dir.getValue()] = value;
    }

    /**
     * Gets the Q-value of a cell in the QTable for a given action
     * 
     * @param x      the x-coordinate of the cell
     * @param y      the y-coordinate of the cell
     * @param action the action for which to retrieve the Q-value
     * @return the Q-value for the given cell and action
     */
    public float getQValue(int x, int y, int action) {
        return qtab[x][y][action];
    }

    /**
     * Gets the Q-value of a cell in the QTable for a given direction
     * 
     * @param x   the x-coordinate of the cell
     * @param y   the y-coordinate of the cell
     * @param dir the direction for which to retrieve the Q-value
     * @return the Q-value for the given cell and direction
     */
    public float getQValue(int x, int y, Direction dir) {
        return qtab[x][y][dir.getValue()];
    }

    /**
     * Gets the size of the QTable (which is a square grid)
     * 
     * @return the size of the QTable
     */
    public int getSize() {
        return size;
    }

    /**
     * This just prints the q-table in a text format
     * 
     * @return the whole 3-dimensional table in a string format
     */
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
