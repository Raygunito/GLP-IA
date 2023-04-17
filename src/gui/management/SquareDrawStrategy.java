package gui.management;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import data.elements.Element;

public class SquareDrawStrategy implements PaintStrategy {

    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.fillRect(x, y, size, size);
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int size, int width, int height, int n, Color background) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setColor(Graphics graphics, Element element) {
        graphics.setColor(element.getColor());  
    }   
}
