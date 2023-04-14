package data.qlearn;

import java.util.ArrayList;
import java.util.Random;

import process.qlearn.GridBorderException;

public class Grid {
    public static final float MALUS_VALUE = -100f;
    public static final float ENDING_VALUE = 300f;
    public static final float WALL_VALUE = -999f;

    private final Cell[][] cellGrid;
    private Cell endingCell, startingCell;
    private int size;

    public Grid(int n) {
        cellGrid = new Cell[n][n];
        size = n;
        initCellValue();
        initBorderValue();
    }
    private void initBorderValue(){
        for (int i = 0; i < cellGrid.length; i++) {
            cellGrid[i][0].setqValue(WALL_VALUE,Direction.UP.getValue());
            cellGrid[i][size-1].setqValue(WALL_VALUE, Direction.DOWN.getValue());
        }
        for (int i = 0; i < cellGrid.length; i++) {
            cellGrid[0][i].setqValue(WALL_VALUE,Direction.LEFT.getValue());
            cellGrid[size-1][i].setqValue(WALL_VALUE, Direction.RIGHT.getValue());    
        }
    }
    private void initCellValue() {
        for (int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid.length; j++) {
                cellGrid[i][j] = new Cell(i, j);
            }
        }
        endingCell = new Cell(size - 1, size - 1);
        startingCell = new Cell(0, 0);
        for (int i = 0; i < 4; i++) {
            endingCell.setqValue(ENDING_VALUE, i);
        }
        cellGrid[size - 1][size - 1] = endingCell;
        cellGrid[0][0] = startingCell;

    }

    public Cell getUp(Cell cell) throws GridBorderException {
        int x = cell.getCoordinate().coordinateX();
        int y = cell.getCoordinate().coordinateY() - 1;
        return getCellfromPosition(cell, x, y);

    }

    public Cell getDown(Cell cell) throws GridBorderException {
        int x = cell.getCoordinate().coordinateX();
        int y = cell.getCoordinate().coordinateY() + 1;
        return getCellfromPosition(cell, x, y);

    }

    public Cell getLeft(Cell cell) throws GridBorderException {
        int x = cell.getCoordinate().coordinateX() - 1;
        int y = cell.getCoordinate().coordinateY();
        return getCellfromPosition(cell, x, y);

    }

    public Cell getRight(Cell cell) throws GridBorderException {
        int x = cell.getCoordinate().coordinateX() + 1;
        int y = cell.getCoordinate().coordinateY();
        return getCellfromPosition(cell, x, y);

    }

    private Cell getCellfromPosition(Cell cell, int x, int y) throws GridBorderException {
        if (x < 0 || x >= this.size || y < 0 || y >= this.size) {
            throw new GridBorderException();
        }
        return cellGrid[x][y];
    }

    public ArrayList<Cell> getNeighbors(Cell cell) {
        ArrayList<Cell> alCell = new ArrayList<Cell>();
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

    public Cell getRandomNeighbors(Cell cell) {
        ArrayList<Cell> alCell = getNeighbors(cell);
        int index = new Random().nextInt(alCell.size());
        return alCell.get(index);
    }

    public Cell getCell(int x, int y) {
        return cellGrid[x][y];
    }

    public Cell getCellFromDirection(Cell currentCell, Direction dir) {
        Cell tmp = null;
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
        float[][][] tab = new float[size][size][4];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < 4; k++)
                    tab[i][j][k] = cellGrid[i][j].getqValue()[k];
            }
        }
        return tab;
    }
    public void updateQValueFromQTable(QTable qTable){
        for (int i = 0; i < qTable.getSize(); i++) {
            for (int j = 0; j < qTable.getSize(); j++) {
                for (int k = 0; k < 4; k++) {
                    cellGrid[i][j].setqValue(qTable.getQValue(i, j, k), k); 
                }
            }
        }
    }
    public void setEndingCell(Cell endingCell) {
        this.endingCell = endingCell;
    }

    public void setStartingCell(Cell startingCell) {
        this.startingCell = startingCell;
    }

    public int getSize() {
        return size;
    }

    public Cell getEndingCell() {
        return endingCell;
    }

    public Cell getStartingCell() {
        return startingCell;
    }
}
