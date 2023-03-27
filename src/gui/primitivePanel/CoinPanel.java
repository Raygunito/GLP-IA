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
        // super.paintComponent(g);
        // Graphics2D g2d = (Graphics2D) g;
        // int spacing = 5;
        // int width = getWidth();
        // int height = getHeight();
        // int coinSize = Math.min(width / (coinNumber+spacing), height);
        // int x = (width - coinNumber * coinSize) / 2;
        // int y = (height - coinSize) / 2;
        // for (int i = 0; i < coinNumber; i++) {
        //     paintStrategy.draw(g2d, x+i*(coinSize+spacing), y, coinSize);
        // }
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int coinSize = (int) ((width - (coinNumber - 1) * 5) / (double) coinNumber); // calculate coin size

        int coinWidth = coinNumber * coinSize;
        int totalSpacing = width - coinWidth;
        int spacing = totalSpacing / (coinNumber - 1);
        int extraSpacing = totalSpacing % (coinNumber - 1);

        int x = (extraSpacing / 2) + ((width - coinWidth - totalSpacing) / 2);

        int y = (height - coinSize) / 2;

        for (int i = 0; i < coinNumber; i++) {
            paintStrategy.draw(g2d, x + i * (coinSize + spacing), y, coinSize);
        }
    }
    public void setCoinNumber(int coinNumber) {
        this.coinNumber = coinNumber;
    }
}
