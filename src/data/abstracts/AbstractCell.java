package data.abstracts;

import data.elements.Element;
import data.utils.Coordinate;

/**
 * The AbstractCell class represents a cell in a game board.
 * It is an abstract class that provides common functionality for cells in
 * different game boards.
 * Each cell has a coordinate and an element.
 */
public abstract class AbstractCell {
    /**
     * The coordinate of the cell.
     */
    private final Coordinate coordinate;

    /**
     * The element contained in the cell.
     */
    private Element element;

    /**
     * Constructs a new AbstractCell with the given coordinate and element.
     * 
     * @param coordinate the coordinate of the cell
     * @param element    the element contained in the cell
     */
    public AbstractCell(Coordinate coordinate, Element element) {
        this.coordinate = coordinate;
        this.element = element;
    }

    /**
     * Returns the coordinate of the cell.
     * 
     * @return the coordinate of the cell
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Returns the element contained in the cell.
     * 
     * @return the element contained in the cell
     */
    public Element getElement() {
        return element;
    }

    /**
     * Sets the element contained in the cell.
     * 
     * @param element the new element to be contained in the cell
     */
    public void setElement(Element element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "[" + getCoordinate().coordinateX() + "," + getCoordinate().coordinateY() + "] with element : " + getElement().toString();
    }

}
