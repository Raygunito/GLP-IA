package gui.primitivePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.management.PaintStrategy;
import gui.management.SquareDrawStrategy;
/*
 * //TODO WORK IN PROGRESS 
 */
public class GridPanel extends JPanel {
    private int gridSize;
    private PaintStrategy paintStrategy;

    public GridPanel(int gridSize, PaintStrategy paintStrategy) {
        this.gridSize = gridSize;
        this.paintStrategy = paintStrategy;
        setPreferredSize(new Dimension(300, 300));
        setMaximumSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        int cellSize = getWidth() / gridSize;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int x = i * cellSize;
                int y = j * cellSize;
                paintStrategy.draw(g2d, x, y, cellSize);
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.add(new GridPanel(40,new SquareDrawStrategy()));
        f.pack();
        f.setVisible(true);
    }
}
