package gui.algorithmPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import process.minmax.MinMaxCore;
import process.minmax.TreeFactory;

public class MinMaxPanel extends JPanel {
    private MinMaxCore minMaxCore;
    private JButton one, two, three;
    private JPanel playPanel, coinPanel;
    private int coinNumber;

    public MinMaxPanel(int coin, int difficulty) {
        super();
        minMaxCore = new MinMaxCore(coin, difficulty);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initPlayPanel();
        initCoinPanel(coin);
        add(coinPanel);
        add(playPanel);
        setPreferredSize(new Dimension(300, 300));
    }

    private void initCoinPanel(int coin) {
        coinNumber = coin;
        coinPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                int coinSize = Math.min(width / coinNumber, height);
                int x = (width - coinNumber * coinSize) / 2;
                int y = (height - coinSize) / 2;
                g2d.setColor(getBackground());
                g2d.fillRect(0, 0, width, height);
                g2d.setColor(Color.YELLOW); // Set the fill color to yellow
                for (int i = 0; i < coinNumber; i++) {
                    g2d.fillOval(x + i * coinSize, y, coinSize, coinSize); // Fill the oval with yellow
                }
            }
        };
        coinPanel.setPreferredSize(new Dimension(300, 50));
        coinPanel.setBackground(getBackground());
        coinPanel.setForeground(getForeground());

    }

    private void initPlayPanel() {
        playPanel = new JPanel();
        playPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");

        playPanel.add(one);
        playPanel.add(two);
        playPanel.add(three);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        JFrame f = new JFrame();
        f.add(new MinMaxPanel(10, 5));
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
