package process.visitor;
import java.awt.Graphics;
import java.awt.Graphics2D;

import data.elements.Hole;
import data.elements.Tile;
import data.elements.Trail;
import data.elements.UselessTile;
import data.elements.Wall;
import gui.management.PaintStrategy;

public class DrawingVisitor implements ElementVisitor<Void> {
    private PaintStrategy paintStrategy;
    private Graphics graphics;
    private int cellSize;
    public DrawingVisitor(Graphics g, PaintStrategy ps,int cellsize) {
        this.graphics=g;
        this.paintStrategy = ps;
        this.cellSize = cellsize;
    }

    @Override
    public Void visit(Wall wall) {
        // System.out.println("Je suis un Wall");
        paintStrategy.setColor(graphics, wall);
        paintStrategy.draw((Graphics2D) graphics, wall.getCoordinate().coordinateX()*cellSize, wall.getCoordinate().coordinateY()*cellSize, cellSize);
        
        return null;
    }

    @Override
    public Void visit(Tile tile) {
        // System.out.println("Je suis un Tile");
        paintStrategy.setColor(graphics, tile);
        paintStrategy.draw((Graphics2D) graphics, tile.getCoordinate().coordinateX()*cellSize, tile.getCoordinate().coordinateY()*cellSize, cellSize);
        
        return null;
    }
    
    @Override
    public Void visit(Hole hole) {
        // System.out.println("Je suis un Hole");
        paintStrategy.setColor(graphics, hole);
        paintStrategy.draw((Graphics2D) graphics, hole.getCoordinate().coordinateX()*cellSize, hole.getCoordinate().coordinateY()*cellSize, cellSize);
        
        return null;
    }

    @Override
    public Void visit(Trail trail) {
        // System.out.println("Je suis un Trail");
        paintStrategy.setColor(graphics, trail);
        paintStrategy.draw((Graphics2D) graphics, trail.getCoordinate().coordinateX()*cellSize, trail.getCoordinate().coordinateY()*cellSize, cellSize);
        return null;
    }

    @Override
    public Void visit(UselessTile uselessTile) {
        paintStrategy.setColor(graphics, uselessTile);
        paintStrategy.draw((Graphics2D) graphics, uselessTile.getCoordinate().coordinateX()*cellSize, uselessTile.getCoordinate().coordinateY()*cellSize, cellSize);
        return null;
    }
    
}
