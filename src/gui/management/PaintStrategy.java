package gui.management;

import java.awt.Graphics2D;
import java.awt.Graphics;
import data.astar.ACell;
import data.elements.Element;

import java.awt.Color;

public interface PaintStrategy {
    public void draw(Graphics2D g,int x, int y, int size);
    public void draw(Graphics2D g2d, int x, int y, int size, int width, int height, int n, Color background);
    void setColor(Graphics graphics, Element element);
}
