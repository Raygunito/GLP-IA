package data.elements;

import java.awt.Color;

import data.utils.Coordinate;
import process.visitor.ElementVisitor;

public class Trail implements Element{
    private Coordinate coordinate;

    public Trail(Coordinate c) {
        coordinate = c;
    }

    @Override
    public <E> E accept(ElementVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return "Trail";
    }
    
}
