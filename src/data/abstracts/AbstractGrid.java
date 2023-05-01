package data.abstracts;

/**
 * The AbstractGrid class represents a grid in a game board.
 * It is an abstract class that provides common functionality for grids in
 * different game boards.
 * Each grid has a two-dimensional array of AbstractCells, a size, and starting
 * and ending cells.
 */
public abstract class AbstractGrid {
    /**
     * The two-dimensional array of AbstractCells that makes up the grid.
     */
    private AbstractCell[][] grid;

    /**
     * The size of the grid.
     */
    private int size;

    /**
     * The starting cell of the grid.
     */
    private AbstractCell startingAbstractCell;

    /**
     * The ending cell of the grid.
     */
    private AbstractCell endingAbstractCell;

    /**
     * Constructs a new AbstractGrid with the given size.
     * 
     * @param size the size of the grid
     */
    public AbstractGrid(int size) {
        this.grid = new AbstractCell[size][size];
        this.size = size;
    }

    /**
     * Returns the two-dimensional array of AbstractCells that makes up the grid.
     * 
     * @return the two-dimensional array of AbstractCells that makes up the grid
     */
    public AbstractCell[][] getGrid() {
        return grid;
    }

    /**
     * Sets the two-dimensional array of AbstractCells that makes up the grid.
     * 
     * @param grid the new two-dimensional array of AbstractCells that makes up the
     *             grid
     */
    public void setGrid(AbstractCell[][] grid) {
        this.grid = grid;
    }

    /**
     * Returns the size of the grid.
     * 
     * @return the size of the grid
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the starting cell of the grid.
     * 
     * @return the starting cell of the grid
     */
    public AbstractCell getStartingAbstractCell() {
        return startingAbstractCell;
    }

    /**
     * Sets the starting cell of the grid.
     * 
     * @param startingAbstractCell the new starting cell of the grid
     */
    public void setStartingAbstractCell(AbstractCell startingAbstractCell) {
        this.startingAbstractCell = startingAbstractCell;
    }

    /**
     * Returns the ending cell of the grid.
     * 
     * @return the ending cell of the grid
     */
    public AbstractCell getEndingAbstractCell() {
        return endingAbstractCell;
    }

    /**
     * Sets the ending cell of the grid.
     * 
     * @param endingAbstractCell the new ending cell of the grid
     */
    public void setEndingAbstractCell(AbstractCell endingAbstractCell) {
        this.endingAbstractCell = endingAbstractCell;
    }

    /**
     * Returns the AbstractCell at the specified coordinates in the grid.
     * 
     * @param x the x-coordinate of the AbstractCell
     * @param y the y-coordinate of the AbstractCell
     * @return the AbstractCell at the specified coordinates in the grid
     */
    public AbstractCell getCell(int x, int y) {
        return grid[x][y];
    }
}
