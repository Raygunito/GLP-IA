package data.elements;

import java.awt.Color;

import data.utils.Coordinate;
import process.visitor.ElementVisitor;

public class Hole implements Element{
    private Coordinate coordinate;
    public Hole(Coordinate c) {
        this.coordinate = c;
    }
    @Override
    public <E> E accept(ElementVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Color getColor() {
        return Color.decode("#910c03");
    }
    
    public Coordinate getCoordinate() {
        return coordinate;
    }
    @Override
    public String toString() {
        return "Hole";
    }

    
}
