package gui.algorithmPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.primitivePanel.CoinPanel;
import process.minmax.MinMaxCore;

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
        coinPanel = new CoinPanel(coin);
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
}