package data.qlearn;

import data.utils.Coordinate;
import data.abstracts.AbstractCell;
import data.elements.Element;
import data.elements.Tile;

public class QCell extends AbstractCell{
    private float[] qValue;

    /**
     * 
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     */
    public QCell(int x, int y) {
        super(new Coordinate(x, y), new Tile(new Coordinate(x, y)));
        this.qValue = new float[4];
    }

    /**
     * Returns the coordinate of the cell.
     * 
     * @return Coordinate of the cell
     */
    public Coordinate getCoordinate() {
        return super.getCoordinate();
    }

    public Element getElement() {
        return super.getElement();
    }

    public void setElement(Element element) {
        super.setElement(element);
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
        QCell cell = (QCell) o;
        return (super.getCoordinate().coordinateX() == cell.getCoordinate().coordinateX())
                && (super.getCoordinate().coordinateY() == cell.getCoordinate().coordinateY());
    }

    @Override
    public String toString() {
        return "[" + super.getCoordinate().coordinateX() + "," + super.getCoordinate().coordinateY() + "]";
    }

}
