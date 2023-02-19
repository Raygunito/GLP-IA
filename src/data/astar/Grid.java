package data.astar;

import process.astar.CellIsWallException;

public class Grid {
    private final Cell[][] grid;
    private static final int DIM = 30;
    private static final float HEURISTIC_RATIO = 3;
    private Cell endingCell;
    private Cell startingCell;

    public Grid() {
        grid = new Cell[DIM][DIM];

    }


    public Cell getUp(Cell cell) throws CellIsWallException {
        if (!grid[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() - 1].isCanAccess()) {
            throw new CellIsWallException();
        }
        return grid[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() - 1];
    }

    public Cell getDown(Cell cell) throws CellIsWallException {
        if (!grid[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() + 1].isCanAccess()) {
            throw new CellIsWallException();
        }
        return grid[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() + 1];
    }

    public Cell getRight(Cell cell) throws CellIsWallException {
        if (!grid[cell.getCoordinate().coordinateX() + 1][cell.getCoordinate().coordinateY()].isCanAccess()) {
            throw new CellIsWallException();
        }
        return grid[cell.getCoordinate().coordinateX() + 1][cell.getCoordinate().coordinateY()];
    }


    public Cell getLeft(Cell cell) throws CellIsWallException {
        if (!grid[cell.getCoordinate().coordinateX() - 1][cell.getCoordinate().coordinateY()].isCanAccess()) {
            throw new CellIsWallException();
        }
        return grid[cell.getCoordinate().coordinateX() - 1][cell.getCoordinate().coordinateY()];
    }

    public void calculateHeuristicCost(Cell cell) {
        cell.setHeuristicCost(cell.getCost() + cell.getCoordinate().calculateDistance(endingCell.getCoordinate()) * HEURISTIC_RATIO);
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                res += "|" + (grid[i][j].isCanAccess() ? 0 : 1) + "|";
            }
            res += "\n";
        }
        return res;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCell(int i, int j) {
        return grid[i][j];
    }

    public int getDIM() {
        return DIM;
    }

    public Cell getEndingCell() {
        return endingCell;
    }

    public void setEndingCell(Cell endingCell) {
        this.endingCell = endingCell;
    }

    public Cell getStartingCell() {
        return startingCell;
    }

    public void setStartingCell(Cell startingCell) {
        this.startingCell = startingCell;
    }
}
