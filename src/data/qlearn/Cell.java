package data.qlearn;

import data.utils.Coordinate;
import data.elements.Element;
import data.elements.Tile;

public class Cell {
    private final Coordinate coordinate;
    private Cell parent;
    private Element element;
    private float qValue;

    /**
     * 
     * @param x         The x coordinate of the cell.
     * @param y         The y coordinate of the cell.
     * @param parent    The parent cell of the cell.
     * @param canAccess A boolean representing whether or not the cell can be
     *                  accessed.
     */
    public Cell(int x, int y, Cell parent) {
        coordinate = new Coordinate(x, y);
        this.parent = parent;
        this.element = new Tile(coordinate);
        this.qValue = 0;
    }

    /**
     * Returns the coordinate of the cell.
     * 
     * @return Coordinate of the cell
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Returns the parent cell of the cell.
     * 
     * @return Cell of the parent
     */
    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public float getqValue() {
        return qValue;
    }

    public void setqValue(float qValue) {
        this.qValue = qValue;
    }
}
