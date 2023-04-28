package gui.primitivePanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import gui.management.CoinDrawStrategy;
import gui.management.PaintStrategy;

/**
 * The CoinPanel class represents a panel that displays a specified number of
 * coins using a graphical interface.
 */
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

        g2d.setFont(new Font("ARIAL", Font.BOLD, 36));
        g2d.drawString("Remaining coins :", width/5, height/6);

        // int coinSize = (int) ((width - (coinNumber - 1) * 5) / (double) coinNumber); // calculate coin size
        // if (coinSize > 48) {
        //     coinSize = 48;
        // }
        //For responsive design replace coinImaginary with better math
        int coinImaginary = coinNumber < 10 ? coinNumber : 10;
        int coinSize = 48;
        int coinWidth = coinImaginary * coinSize;
        int totalSpacing = width - coinWidth;
        int spacing = totalSpacing / (coinImaginary +1);
        int extraSpacing = totalSpacing % (coinImaginary +1);

        int x = (extraSpacing / 2) + ((width - coinWidth - totalSpacing) / 2);
        int y = (height - coinSize) / 2;

        int internalLine = 0;
        int j = 0;
        int k = 0;
        
        for (int i = 0; i < coinNumber; i++) {
            if (internalLine == 10){
                internalLine = 0;
                j++;
                k=0;
            }
            paintStrategy.draw(g2d, x + k * (coinSize + spacing), y + j * (coinSize + spacing +2), coinSize);
            internalLine++;
            k++;
        }
    }

    public void setCoinNumber(int coinNumber) {
        this.coinNumber = coinNumber;
    }
}
