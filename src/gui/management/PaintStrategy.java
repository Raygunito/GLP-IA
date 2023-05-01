package gui.management;

import java.awt.Graphics2D;
import java.awt.Graphics;
import data.elements.Element;

public interface PaintStrategy {
    public void draw(Graphics2D g,int x, int y, int size);
    void setColor(Graphics graphics, Element element);
}
