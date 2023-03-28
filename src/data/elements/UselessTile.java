package data.elements;

import java.awt.Color;

import data.astar.Coordinate;
import process.visitor.ElementVisitor;

public class UselessTile implements Element{
    private Coordinate coordinate;
    public UselessTile(Coordinate c) {
        this.coordinate = c;
    }
    @Override
    public <E> E accept(ElementVisitor<E> visitor) {
        // TODO Auto-generated method stub
        return visitor.visit(this);
    }
    @Override
    public Color getColor() {
        return Color.RED;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
    
}
