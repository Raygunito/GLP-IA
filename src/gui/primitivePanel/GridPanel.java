package gui.primitivePanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.astar.Grid;
import data.elements.Element;
import gui.management.PaintStrategy;
import gui.management.SquareDrawStrategy;
import process.astar.GridFactory;
import process.visitor.DrawingVisitor;
import process.visitor.ElementVisitor;
/*
 * //TODO WORK IN PROGRESS 
 */
public class GridPanel extends JPanel {
    private Grid grid;

    public GridPanel(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(400, 400));
        setMaximumSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        int cellSize = getWidth() / grid.getSize();
        
        DrawingVisitor drawingVisitor = new DrawingVisitor(g2d, new SquareDrawStrategy());
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
    public static void main(String[] args) {
        JFrame f = new JFrame();
        GridFactory gridFactory = new GridFactory();
        Grid grid = gridFactory.BuildGrid(30);
        f.add(new GridPanel(grid));
        f.pack();
        f.setVisible(true);
    }
}
