package data.elements;

import java.awt.Color;

import data.utils.Coordinate;
import process.visitor.ElementVisitor;

public class Tile implements Element{
    private Coordinate coordinate;
    public Tile(Coordinate c) {
        this.coordinate = c;
    }
    @Override
    public <E> E accept(ElementVisitor<E> visitor) {
        // TODO Auto-generated method stub
        return visitor.visit(this);
    }
    @Override
    public Color getColor() {
        return Color.GRAY;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
    
}
