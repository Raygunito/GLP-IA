package gui.primitivePanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import data.astar.Grid;
import data.elements.Element;
import gui.GUIConstant;
import gui.management.SquareDrawStrategy;
import process.visitor.DrawingVisitor;
/*
 * //TODO WORK IN PROGRESS 
 */
public class GridPanel extends JPanel {
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 170;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 170;
    private Grid grid;

    public GridPanel(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        int cellSize = getWidth() / grid.getSize();
        
        DrawingVisitor drawingVisitor = new DrawingVisitor(g2d, new SquareDrawStrategy(),cellSize);
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                Element e = grid.getCell(i, j).getElement();
                e.accept(drawingVisitor);
            }
        }
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}