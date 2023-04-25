package data.qlearn;

import java.util.ArrayList;
import java.util.Random;

import data.abstracts.AbstractGrid;
import process.qlearn.GridBorderException;

public class QGrid extends AbstractGrid {
    public static final float MALUS_VALUE = -100f;
    public static final float ENDING_VALUE = 300f;
    public static final float WALL_VALUE = -999f;

    public QGrid(int n) {
        super(n);
        initCellValue();
        initBorderValue();
    }

    private void initBorderValue(){
        int size = super.getSize();
        for (int i = 0; i < size; i++) {
            ((QCell)super.getGrid()[i][0]).setqValue(WALL_VALUE,Direction.UP.getValue());
            ((QCell)super.getGrid()[i][size-1]).setqValue(WALL_VALUE, Direction.DOWN.getValue());
        }
        for (int i = 0; i < size ; i++) {
            ((QCell)super.getGrid()[0][i]).setqValue(WALL_VALUE,Direction.LEFT.getValue());
            ((QCell)super.getGrid()[size-1][i]).setqValue(WALL_VALUE, Direction.RIGHT.getValue());    
        }
    }
    private void initCellValue() {
        int size = super.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                super.getGrid()[i][j] = new QCell(i, j);
            }
        }
        QCell endingCell = new QCell(size - 1, size - 1);
        QCell startingCell = new QCell(0, 0);
        for (int i = 0; i < 4; i++) {
            endingCell.setqValue(ENDING_VALUE, i);
        }
        super.getGrid()[size - 1][size - 1] = endingCell;
        super.setEndingAbstractCell(endingCell);
        super.getGrid()[0][0] = startingCell;
        super.setStartingAbstractCell(startingCell);
    }

    public QCell getUp(QCell cell) throws GridBorderException {
        int x = cell.getCoordinate().coordinateX();
        int y = cell.getCoordinate().coordinateY() - 1;
        return getCellfromPosition(cell, x, y);

    }

    public QCell getDown(QCell cell) throws GridBorderException {
        int x = cell.getCoordinate().coordinateX();
        int y = cell.getCoordinate().coordinateY() + 1;
        return getCellfromPosition(cell, x, y);

    }

    public QCell getLeft(QCell cell) throws GridBorderException {
        int x = cell.getCoordinate().coordinateX() - 1;
        int y = cell.getCoordinate().coordinateY();
        return getCellfromPosition(cell, x, y);

    }

    public QCell getRight(QCell cell) throws GridBorderException {
        int x = cell.getCoordinate().coordinateX() + 1;
        int y = cell.getCoordinate().coordinateY();
        return getCellfromPosition(cell, x, y);

    }

    private QCell getCellfromPosition(QCell cell, int x, int y) throws GridBorderException {
        if (x < 0 || x >= super.getSize() || y < 0 || y >= super.getSize()) {
            throw new GridBorderException();
        }
        return (QCell) super.getCell(x, y);
    }

    public ArrayList<QCell> getNeighbors(QCell cell) {
        ArrayList<QCell> alCell = new ArrayList<QCell>();
        try {
            alCell.add(getUp(cell));
        } catch (GridBorderException e) {
            // e.printStackTrace();
        }
        try {
            alCell.add(getRight(cell));
        } catch (GridBorderException e) {
            // e.printStackTrace();
        }
        try {
            alCell.add(getDown(cell));
        } catch (GridBorderException e) {
            // e.printStackTrace();
        }
        try {
            alCell.add(getLeft(cell));
        } catch (GridBorderException e) {
            // e.printStackTrace();
        }
        return alCell;
    }

    public QCell getRandomNeighbors(QCell cell) {
        ArrayList<QCell> alCell = getNeighbors(cell);
        int index = new Random().nextInt(alCell.size());
        return alCell.get(index);
    }

    public QCell getCell(int x, int y) {
        return (QCell) super.getCell(x, y);
    }

    public QCell getCellFromDirection(QCell currentCell, Direction dir) {
        QCell tmp = null;
        switch (dir) {
            case UP:
                try {
                    tmp = getUp(currentCell);
                } catch (Exception e) {
                }
                break;
            case DOWN:
                try {
                    tmp = getDown(currentCell);
                } catch (Exception e) {
                }
                break;
            case LEFT:
                try {
                    tmp = getLeft(currentCell);
                } catch (Exception e) {
                }
                break;
            default:
                try {
                    tmp = getRight(currentCell);
                } catch (Exception e) {
                }
                break;
        }
        return tmp;
    }

    public float[][][] generateArrayQValue() {
        int size = super.getSize();
        float[][][] tab = new float[size][size][4];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < 4; k++)
                    tab[i][j][k] =((QCell)super.getCell(i, j)).getqValue()[k];
            }
        }
        return tab;
    }
    public void updateQValueFromQTable(QTable qTable){
        for (int i = 0; i < qTable.getSize(); i++) {
            for (int j = 0; j < qTable.getSize(); j++) {
                for (int k = 0; k < 4; k++) {
                    ((QCell)super.getCell(i, j)).setqValue(qTable.getQValue(i, j, k), k); 
                }
            }
        }
    }
    public void setEndingCell(QCell endingCell) {
        super.setEndingAbstractCell(endingCell);
    }

    public void setStartingCell(QCell startingCell) {
        super.setStartingAbstractCell(startingCell);
    }

    public int getSize() {
        return super.getSize();
    }

    public QCell getEndingCell() {
        return (QCell) super.getEndingAbstractCell();
    }

    public QCell getStartingCell() {
        return (QCell) super.getStartingAbstractCell();
    }
}