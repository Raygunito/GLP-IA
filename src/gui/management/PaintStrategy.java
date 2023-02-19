package gui.management;

import java.awt.Graphics2D;

public interface PaintStrategy {
    public void draw(Graphics2D g,int x, int y, int size);
}
