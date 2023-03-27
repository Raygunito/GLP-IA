package gui.algorithmPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import gui.primitivePanel.CoinPanel;
import gui.utilsPanel.InformationPanel;
import process.minmax.MinMaxCore;

public class MinMaxPanel extends JPanel implements Runnable {
    private MinMaxCore minMaxCore;
    private JButton one, two, three;
    private JPanel playPanel;
    private CoinPanel coinPanel;
    // private int coinNumber;
    private InformationPanel ip;
    private boolean paused = false;

    public MinMaxPanel(int coin, int difficulty, InformationPanel ip) {
        super();
        this.ip = ip;
        minMaxCore = new MinMaxCore(coin, difficulty);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initPlayPanel();
        initCoinPanel(coin);
        initActionListener();
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
        one.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(15, 42, 15, 42)));
        two.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(15, 42, 15, 42)));
        three.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(15, 42, 15, 42)));
        playPanel.add(one);
        playPanel.add(two);
        playPanel.add(three);
    }

    private void initActionListener() {
        one.addActionListener(new takeCoin(1));
        two.addActionListener(new takeCoin(2));
        three.addActionListener(new takeCoin(3));

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (!minMaxCore.isEnded()) {
            if (!paused) {
                minMaxCore.process();
                togglePaused();
            }
            revalidate();
            repaint();
        }
    }

    public void togglePaused() {
        paused = !paused;
    }

    class takeCoin implements ActionListener {
        private int fixedAmount;

        public takeCoin(int n) {
            this.fixedAmount = n;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MinMaxPanel instance = MinMaxPanel.this;
            int currentCoin = instance.minMaxCore.getCoin();
            instance.minMaxCore.setCoin(currentCoin - fixedAmount);
            instance.minMaxCore.process();
            instance.initCoinPanel(instance.minMaxCore.getCoin());
            instance.coinPanel.repaint();
        }
    }
}
