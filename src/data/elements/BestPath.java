package data.elements;

import java.awt.Color;

import data.utils.Coordinate;
import process.visitor.ElementVisitor;

public class BestPath implements Element {
    private Coordinate coordinate;

    public BestPath(Coordinate c) {
        this.coordinate = c;
    }

    @Override
    public <E> E accept(ElementVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Color getColor() {
        return Color.CYAN;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "BestPath";
    }
}
