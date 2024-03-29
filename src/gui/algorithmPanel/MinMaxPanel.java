package gui.algorithmPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import gui.GUIConstant;
import gui.primitivePanel.CoinPanel;
import gui.utilsPanel.InformationPanel;
import log.LoggerUtility;
import process.minmax.MinMaxCore;

public class MinMaxPanel extends JPanel implements Runnable {
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 175;
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 175;
    private MinMaxCore minMaxCore;
    private JButton one, two, three;
    private JPanel playPanel;
    private CoinPanel coinPanel;
    private InformationPanel ip;
    private volatile boolean paused = false;
    private static Logger logger = LoggerUtility.getLogger(MinMaxPanel.class, "text");

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
        logger.info("Successfully created MinMaxPanel with coin : " + coin + " and difficulty : " + difficulty);
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
        playPanel.add(Box.createHorizontalStrut(GUIConstant.SCALING_FACTOR * 25));
        playPanel.add(two);
        playPanel.add(Box.createHorizontalStrut(GUIConstant.SCALING_FACTOR * 25));
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
        one.setEnabled(true);
        two.setEnabled(true);
        three.setEnabled(true);
        if (new Random().nextInt(2) == 0) {
            minMaxCore.setPlayerTurn(true);
        }
        while (!minMaxCore.isEnded()) {
            if (!paused){
                drawArrow(!minMaxCore.isPlayerTurn());
            }
            if (!paused && !minMaxCore.isPlayerTurn()) {
                toggleButton(false);
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {}
                int diff = minMaxCore.getCoin();
                minMaxCore.process();
                coinPanel.setCoinNumber(minMaxCore.getCoin());
                updateInfo(diff);
                toggleButton(true);
                logger.info("Le bot a pris " + String.valueOf(diff - minMaxCore.getCoin()) + " coins.");
            }
        }
        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }
        if (!minMaxCore.isPlayerTurn()) {
            logger.info("Ayo our AI is dumb.");
            JOptionPane.showMessageDialog(this, "You win !");
        } else {
            logger.info("AI ? That's my boi.");
            JOptionPane.showMessageDialog(this, "You lose !");
        }
        togglePaused();
    }

    private void updateInfo(int diff){
        int nodeAmount = Integer.valueOf(ip.getInfoValue2());
        ip.setInfoValue1(String.valueOf(diff - minMaxCore.getCoin()));
        ip.setInfoValue2(String.valueOf(nodeAmount + minMaxCore.getAmountOfNodeCreated()));
    }
    private void drawArrow(boolean botTurn) {
        Graphics2D g2d = (Graphics2D) getGraphics();
        int x1 =100;
        int x2 = 50;
        int y1 = 100;
        int y2 = 100;
        if (botTurn){
            x1 = 450;
            y1 = 100;
            x2 = 500;
            y2 = 100;
        }
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1, y1, x2, y2);
        int arrowSize = 10;
        int x3; 
        int y3;
        if (botTurn){
            x3 = (int) (x2 - arrowSize * Math.cos(-Math.PI / 4));
            y3 = (int) (y2 - arrowSize * Math.sin(-Math.PI / 4));
        }else{
            x3 = (int) (x2 + arrowSize * Math.cos(-Math.PI / 4));
            y3 = (int) (y2 + arrowSize * Math.sin(-Math.PI / 4));
        }
        g2d.drawLine(x2, y2, x3, y3);
        if (botTurn) {
            x3 = (int) (x2 - arrowSize * Math.cos(Math.PI / 4));
            y3 = (int) (y2 - arrowSize * Math.sin(Math.PI / 4));
        } else {
            x3 = (int) (x2 + arrowSize * Math.cos(Math.PI / 4));
            y3 = (int) (y2 + arrowSize * Math.sin(Math.PI / 4));
        }
        g2d.drawLine(x2, y2, x3, y3);
    }

    public void togglePaused() {
        one.setEnabled(paused);
        two.setEnabled(paused);
        three.setEnabled(paused);
        paused = !paused;
    }

    public void toggleButton(boolean bool) {
        one.setEnabled(bool);
        two.setEnabled(bool);
        three.setEnabled(bool);
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
            logger.info("Le joueur a pris " + fixedAmount + " coins.");
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public int getCoin() {
        return minMaxCore.getCoin();
    }

    public int getDepth() {
        return minMaxCore.getDifficulty();
    }
}
