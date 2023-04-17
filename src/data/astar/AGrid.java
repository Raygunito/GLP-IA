package data.astar;

import data.abstracts.AbstractCell;
import data.abstracts.AbstractGrid;
import process.astar.CellIsWallException;

public class AGrid extends AbstractGrid {
    private static final int DIM = 10;
    private static final float HEURISTIC_RATIO = 3;

    public AGrid(int n) {
        super(n);
    }

    public AGrid() {
        this(DIM);

    }

    public ACell getUp(ACell cell) throws CellIsWallException {
        if (!((ACell) super.getGrid()[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() - 1])
                .isCanAccess()) {
            throw new CellIsWallException();
        }
        return (ACell) super.getGrid()[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() - 1];
    }

    public ACell getDown(ACell cell) throws CellIsWallException {
        if (!((ACell) super.getGrid()[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() + 1])
                .isCanAccess()) {
            throw new CellIsWallException();
        }
        return (ACell) super.getGrid()[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() + 1];
    }

    public ACell getRight(ACell cell) throws CellIsWallException {
        if (!((ACell) super.getGrid()[cell.getCoordinate().coordinateX() + 1][cell.getCoordinate().coordinateY()])
                .isCanAccess()) {
            throw new CellIsWallException();
        }
        return (ACell) super.getGrid()[cell.getCoordinate().coordinateX() + 1][cell.getCoordinate().coordinateY()];
    }

    public ACell getLeft(ACell cell) throws CellIsWallException {
        if (!((ACell) super.getGrid()[cell.getCoordinate().coordinateX() - 1][cell.getCoordinate().coordinateY()])
                .isCanAccess()) {
            throw new CellIsWallException();
        }
        return (ACell) super.getGrid()[cell.getCoordinate().coordinateX() - 1][cell.getCoordinate().coordinateY()];
    }

    public void calculateHeuristicCost(ACell cell) {
        cell.setHeuristicCost(
                cell.getCost() + cell.getCoordinate().calculateDistance(super.getEndingAbstractCell().getCoordinate())
                        * HEURISTIC_RATIO);
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < super.getSize(); i++) {
            for (int j = 0; j < super.getSize(); j++) {
                res += "|" + (((ACell) super.getGrid()[i][j]).isCanAccess() ? 0 : 1) + "|";
            }
            res += "\n";
        }
        return res;
    }

    public ACell[][] getGrid() {
        ACell[][] aCellGrid = new ACell[super.getSize()][super.getSize()];
        for (int i = 0; i < super.getSize(); i++) {
            for (int j = 0; j < super.getSize(); j++) {
                aCellGrid[i][j] = (ACell) super.getGrid()[i][j];
            }
        }
        return aCellGrid;
    }

    public ACell getCell(int i, int j) {
        return (ACell) super.getCell(i, j);
    }

    public void setCell(int i, int j, AbstractCell abstractCell){
        super.getGrid()[i][j] = abstractCell;
    }

    public int getDIM() {
        return DIM;
    }

    public ACell getEndingCell() {
        return (ACell) super.getEndingAbstractCell();
    }

    public void setEndingCell(ACell endingCell) {
        super.setEndingAbstractCell(endingCell);
    }

    public ACell getStartingCell() {
        return (ACell) super.getStartingAbstractCell();
    }

    public int getSize() {
        return super.getSize();
    }

    public void setStartingCell(ACell startingCell) {
        super.setStartingAbstractCell(startingCell);
    }
}
