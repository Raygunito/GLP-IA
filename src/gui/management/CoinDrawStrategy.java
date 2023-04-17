package gui.management;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import data.elements.Element;

public class CoinDrawStrategy implements PaintStrategy {
    @Override
    public void draw(Graphics2D g2d, int x, int y, int size, int width, int height, int n, Color background) {

        g2d.setColor(background);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.YELLOW);
        int spacing = 5;
        int coinsPerRow = 10;

        for (int i = 0; i < n; i++) {
            int row = i / coinsPerRow;
            int col = i % coinsPerRow;
            int coinX = x + col * (size + spacing);
            int coinY = y + row * (size + spacing);
            g2d.fillOval(coinX, coinY, size, size);
        }
    }

    

    @Override
    public void setColor(Graphics graphics, Element element) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void draw(Graphics2D g, int x, int y, int size) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, size, size);
    }

}
