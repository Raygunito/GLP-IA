package gui.management;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import data.elements.Element;

/**
 * This class is responsible for drawing coins on a given Graphics2D
 * using the specified parameters.
 */
public class CoinDrawStrategy implements PaintStrategy {
    /**
     * Draws a single coin on the given Graphics2D object with the specified
     * parameters.
     * @param g    the Graphics2D object to draw on
     * @param x    the x-coordinate of the top-left corner of the coin
     * @param y    the y-coordinate of the top-left corner of the coin
     * @param size the size of the coins
     */
    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, size, size);
    }
    /**
     * Shouldn't be used but if used, set the color to yellow (for the coin)
     * @param grapchis the Graphics2D object to draw on
     * @param element the element
     */
    @Override @Deprecated
    public void setColor(Graphics graphics, Element element) {
        graphics.setColor(Color.yellow);
    }

}
