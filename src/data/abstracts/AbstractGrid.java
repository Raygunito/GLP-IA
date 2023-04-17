package data.abstracts;

public abstract class AbstractGrid {
    private AbstractCell[][] grid;
    private int size;
    private AbstractCell startingAbstractCell;
    private AbstractCell endingAbstractCell;

    public AbstractGrid(int size) {
        this.grid = new AbstractCell[size][size];
        this.size = size;
    }

    public AbstractCell[][] getGrid() {
        return grid;
    }

    public void setGrid(AbstractCell[][] grid) {
        this.grid = grid;
    }

    public int getSize() {
        return size;
    }

    public AbstractCell getStartingAbstractCell() {
        return startingAbstractCell;
    }

    public void setStartingAbstractCell(AbstractCell startingAbstractCell) {
        this.startingAbstractCell = startingAbstractCell;
    }

    public AbstractCell getEndingAbstractCell() {
        return endingAbstractCell;
    }

    public void setEndingAbstractCell(AbstractCell endingAbstractCell) {
        this.endingAbstractCell = endingAbstractCell;
    }

    public AbstractCell getCell(int x, int y){
        return grid[x][y];
    }
}
