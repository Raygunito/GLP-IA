package data.qlearn;

import java.util.ArrayList;
import java.util.Random;

import process.qlearn.GridBorderException;

public class Grid {
    private final Cell[][] cellGrid;
    private Cell endingCell, startingCell;
    private int size;

    public Grid(int n) {
        cellGrid = new Cell[n][n];
        size = n;
        initCellValue();
    }

    private void initCellValue(){
        for (int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid.length; j++) {
                cellGrid[i][j] = new Cell(i, j,null);
            }
        }
        endingCell = new Cell(size-1, size-1, null);
        endingCell.setqValue(200);
        cellGrid[size-1][size-1] = endingCell;
        startingCell = new Cell(0, 0, null);
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

    public ArrayList<Cell> getNeighbors(Cell cell){
        ArrayList<Cell> alCell = new ArrayList<Cell>();
        try {
            alCell.add(getDown(cell));
        } catch (GridBorderException e) {
            // e.printStackTrace();
        } 
        try {
            alCell.add(getUp(cell));
        } catch (GridBorderException e) {
            // e.printStackTrace();
        } 
        try {
            alCell.add(getLeft(cell));
        } catch (GridBorderException e) {
            // e.printStackTrace();
        } 
        try {
            alCell.add(getRight(cell));
        } catch (GridBorderException e) {
            // e.printStackTrace();
        } 
        return alCell;
    }

    public Cell getRandomNeighbors(Cell cell){
        ArrayList<Cell> alCell = getNeighbors(cell);
        int index = new Random().nextInt(alCell.size());
        return alCell.get(index);
    }

    public Cell getCell(int x, int y){
        return cellGrid[x][y];
    }
    public float[][] generateArrayQValue(){
        float[][] tab = new float[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tab[i][j] = cellGrid[i][j].getqValue();
            }
        }
        return tab;
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
