package data.elements;

import java.awt.Color;

import data.utils.Coordinate;
import process.visitor.ElementVisitor;

public class Wall implements Element{
    private Coordinate coordinate;
    public Wall(Coordinate c) {
        this.coordinate = c;
    }
    @Override
    public <E> E accept(ElementVisitor<E> visitor) {
        return visitor.visit(this);
    }
    @Override
    public Color getColor() {
        return Color.BLACK;
    }
    public Coordinate getCoordinate() {
        return coordinate;
    }
}
