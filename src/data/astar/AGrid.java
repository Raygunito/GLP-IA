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

    /**
     * Returns the cell above the specified cell if it is accessible. Throws an exception otherwise.
     * @param cell the cell whose "up" neighbor is being retrieved
     * @return the cell above the specified cell
     * @throws CellIsWallException
     */
    public ACell getUp(ACell cell) throws CellIsWallException {
        if (!((ACell) super.getGrid()[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() - 1])
                .isCanAccess()) {
            throw new CellIsWallException();
        }
        return (ACell) super.getGrid()[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() - 1];
    }
    /**
     * Returns the cell below the specified cell if it is accessible. Throws an exception otherwise.
     * @param cell the cell whose "down" neighbor is being retrieved
     * @return the cell below the specified cell
     * @throws CellIsWallException
     */
    public ACell getDown(ACell cell) throws CellIsWallException {
        if (!((ACell) super.getGrid()[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() + 1])
                .isCanAccess()) {
            throw new CellIsWallException();
        }
        return (ACell) super.getGrid()[cell.getCoordinate().coordinateX()][cell.getCoordinate().coordinateY() + 1];
    }
    /**
     * Returns the cell at the right of the specified cell if it is accessible. Throws an exception otherwise.
     * @param cell the cell whose "right" neighbor is being retrieved
     * @return the cell to the right the specified cell
     * @throws CellIsWallException
     */
    public ACell getRight(ACell cell) throws CellIsWallException {
        if (!((ACell) super.getGrid()[cell.getCoordinate().coordinateX() + 1][cell.getCoordinate().coordinateY()])
                .isCanAccess()) {
            throw new CellIsWallException();
        }
        return (ACell) super.getGrid()[cell.getCoordinate().coordinateX() + 1][cell.getCoordinate().coordinateY()];
    }
    /**
     * Returns the cell to the left of the specified cell if it is accessible. Throws an exception otherwise.
     * @param cell the cell whose "left" neighbor is being retrieved
     * @return the cell left of the specified cell
     * @throws CellIsWallException
     */
    public ACell getLeft(ACell cell) throws CellIsWallException {
        if (!((ACell) super.getGrid()[cell.getCoordinate().coordinateX() - 1][cell.getCoordinate().coordinateY()])
                .isCanAccess()) {
            throw new CellIsWallException();
        }
        return (ACell) super.getGrid()[cell.getCoordinate().coordinateX() - 1][cell.getCoordinate().coordinateY()];
    }

    /**
     *Calculates the heuristic cost of the given cell, which is the sum of the cell's
     * cost and the estimated distance to the ending cell multiplied by the heuristic
     * ratio. The result is stored in the cell's heuristic cost field.
     * @param cell the cell for which to calculate the heuristic cost
     */
    public void calculateHeuristicCost(ACell cell) {
        cell.setHeuristicCost(
                cell.getCost() + cell.getCoordinate().calculateDistance(super.getEndingAbstractCell().getCoordinate())
                        * HEURISTIC_RATIO);
    }

    /**
     * This function was mainly made to debug the algorithm before the GUI was made
     * @return the maze with each cell status in the form of a text
     */
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

    /**
     *Return a two-dimensional array of ACell object representing the grid for this search.  The array is a copy of the grid stored in the superclass.
     * @return a two-dimensional array of abstract cell object
     */
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
