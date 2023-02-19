package gui.management;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CoinDrawStrategy implements PaintStrategy {
    @Override
    public void draw(Graphics2D g2d, int x, int y, int size, int width, int height, int n, Color background) {

        g2d.setColor(background);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.YELLOW);
        for (int i = 0; i < n; i++) {
            g2d.fillOval(x + i * size, y, size, size);
        }
    }

    @Override
    public void draw(Graphics2D g, int x, int y, int size, int width, int height, int n) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        // TODO Auto-generated method stub

    }

}
