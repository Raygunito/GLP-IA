package data.qlearn;

import data.utils.Coordinate;

import data.elements.Element;
import data.elements.Tile;

public class Cell {
    private final Coordinate coordinate;
    private Element element;
    private float[] qValue;

    /**
     * 
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     */
    public Cell(int x, int y) {
        coordinate = new Coordinate(x, y);
        this.element = new Tile(coordinate);
        this.qValue = new float[4];
    }

    /**
     * Returns the coordinate of the cell.
     * 
     * @return Coordinate of the cell
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public float[] getqValue() {
        return qValue;
    }

    public float getqValueFromDirection(Direction dir) {
        return qValue[dir.getValue()];
    }

    public void setqValue(float qValue, int direction) {
        this.qValue[direction] = qValue;
    }

    public int bestDirection() {
        int direction = 0;
        float maxVal = Float.NEGATIVE_INFINITY;
        for (int i = 0; i < qValue.length; i++) {
            if (qValue[i] > maxVal) {
                maxVal = qValue[i];
                direction = i;
            }
        }
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return (this.coordinate.coordinateX() == cell.getCoordinate().coordinateX())
                && (this.coordinate.coordinateY() == cell.getCoordinate().coordinateY());
    }

    @Override
    public String toString() {
        return "[" + coordinate.coordinateX() + "," + coordinate.coordinateY() + "]";
    }

}
