package gui.primitivePanel;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import gui.management.CoinDrawStrategy;
import gui.management.PaintStrategy;

public class CoinPanel extends JPanel {
    private PaintStrategy paintStrategy;
    private int coinNumber;
    public CoinPanel(int coin) {
        coinNumber = coin;
        paintStrategy = new CoinDrawStrategy();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int coinSize = Math.min(width / coinNumber, height);
        int x = (width - coinNumber * coinSize) / 2;
        int y = (height - coinSize) / 2;
        paintStrategy.draw(g2d, x, y, coinSize, width, height, coinSize, getBackground());
    }
}
