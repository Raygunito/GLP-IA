package test;

import javax.swing.*;

import data.minmax.Tree;
import process.minmax.TreeFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMinMax extends JFrame {
    private int coinNumber;
    private JLabel[] coins;
    private JPanel coinPanel;

    public GUIMinMax() {
        super();
        init();
    }

    private void init() {
        coinNumber = 10;
        Container containerPane = getContentPane();
        containerPane.setLayout(new GridLayout(2, 1));
        coinPanel = new JPanel();
        coinPanel.setLayout(new FlowLayout());
        JPanel playPanel = new JPanel();
        playPanel.setLayout(new GridLayout(1, 3));
        coins = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            JLabel coin = new JLabel();
            coin.setText("●");
            coin.setForeground(Color.yellow);
            coins[i] = coin;
            coinPanel.add(coin);
        }
        JButton minusOne = new JButton("1");
        JButton minusTwo = new JButton("2");
        JButton minusThree = new JButton("3");
        minusOne.addActionListener(new takeCoins(this, 1));
        minusTwo.addActionListener(new takeCoins(this, 2));
        minusThree.addActionListener(new takeCoins(this, 3));
        playPanel.add(minusOne);
        playPanel.add(minusTwo);
        playPanel.add(minusThree);
        containerPane.add(coinPanel);
        containerPane.add(playPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        run();
    }

    public int getCoinNumber() {
        return coinNumber;
    }

    public void setCoinNumber(int coinNumber) {
        this.coinNumber = coinNumber;
    }

    public void update() {
        for (int i = coinNumber; i < coins.length; i++) {
            coins[i].setForeground(Color.black);
        }
        coinPanel.repaint();
        coinPanel.revalidate();
    }

    public void run() {
        Tree tree = new Tree(TreeFactory.buildPlayerNode(coinNumber, 10));
        coinNumber = Math.max(tree.findMove(), 0);
        update();
    }


    private class takeCoins implements ActionListener {
        private GUIMinMax gui;
        private int coinTaken;

        public takeCoins(GUIMinMax gui, int coinTaken) {
            this.gui = gui;
            this.coinTaken = coinTaken;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            gui.setCoinNumber(Math.max(gui.getCoinNumber() - coinTaken, 0));
            gui.update();
            gui.run();
        }
    }
}





