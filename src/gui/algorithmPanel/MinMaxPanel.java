package gui.algorithmPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.GUIConstant;
import gui.primitivePanel.CoinPanel;
import gui.utilsPanel.InformationPanel;
import process.minmax.MinMaxCore;

public class MinMaxPanel extends JPanel implements Runnable {
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 175;
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 175;
    private MinMaxCore minMaxCore;
    private JButton one, two, three;
    private JPanel playPanel;
    private CoinPanel coinPanel;
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
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void initCoinPanel(int coin) {
        coinPanel = new CoinPanel(coin);
        coinPanel.setPreferredSize(new Dimension(GUIConstant.SCALING_FACTOR * 100, GUIConstant.SCALING_FACTOR * 75));
        coinPanel.setBackground(getBackground());
        coinPanel.setForeground(getForeground());
    }

    private void initPlayPanel() {
        playPanel = new JPanel();
        playPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        playPanel.setPreferredSize(new Dimension(GUIConstant.SCALING_FACTOR * 100, GUIConstant.SCALING_FACTOR * 25));
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");

        Font fontSize = new Font("Segoe UI", Font.PLAIN, GUIConstant.SCALING_FACTOR * 6);
        one.setFont(fontSize);
        two.setFont(fontSize);
        three.setFont(fontSize);

        one.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(15, 42, 15, 42)));
        two.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(15, 42, 15, 42)));
        three.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(),
                BorderFactory.createEmptyBorder(15, 42, 15, 42)));
        playPanel.add(one);
        playPanel.add(Box.createHorizontalStrut(GUIConstant.SCALING_FACTOR*25));
        playPanel.add(two);
        playPanel.add(Box.createHorizontalStrut(GUIConstant.SCALING_FACTOR*25));
        playPanel.add(three);

        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
    }

    private void initActionListener() {
        one.addActionListener(new takeCoin(1));
        two.addActionListener(new takeCoin(2));
        three.addActionListener(new takeCoin(3));

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        one.setEnabled(true);
        two.setEnabled(true);
        three.setEnabled(true);
        while (!minMaxCore.isEnded()) {
            if (!paused && !minMaxCore.isPlayerTurn()) {
                int diff = minMaxCore.getCoin();
                minMaxCore.process();
                ip.setInfoValue1(String.valueOf(diff - minMaxCore.getCoin()));
                coinPanel.setCoinNumber(minMaxCore.getCoin());
                coinPanel.repaint();
                revalidate();
                repaint();
            }
        }
        if (!minMaxCore.isPlayerTurn()) {
            JOptionPane.showMessageDialog(this, "You win !");
        } else {
            JOptionPane.showMessageDialog(this, "You lose !");
        }
        togglePaused();
    }

    public void togglePaused() {
        one.setEnabled(paused);
        two.setEnabled(paused);
        three.setEnabled(paused);
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
            instance.minMaxCore.playerMove(currentCoin - fixedAmount);
            coinPanel.setCoinNumber(currentCoin - fixedAmount);
            System.out.println("j'ai pris " + fixedAmount + " coins.");
        }
    }
}
