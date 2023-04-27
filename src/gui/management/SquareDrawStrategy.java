package gui.management;

import java.awt.Graphics;
import java.awt.Graphics2D;

import data.elements.Element;

public class SquareDrawStrategy implements PaintStrategy {
    /**
     * 
     * Draws a square using the provided Graphics2D object, x and y coordinates, and
     * size.
     * 
     * @param g    the Graphics2D object to use for drawing
     * @param x    the x coordinate of the upper-left corner of the square
     * @param y    the y coordinate of the upper-left corner of the square
     * @param size the size of the square
     */
    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.fillRect(x, y, size, size);
    }

    /**
     * 
     * Sets the color of the provided Graphics object to the color of the provided
     * Element.
     * 
     * @param graphics the Graphics object to set the color for
     * @param element  the Element whose color to use
     */
    @Override
    public void setColor(Graphics graphics, Element element) {
        graphics.setColor(element.getColor());
    }
}
