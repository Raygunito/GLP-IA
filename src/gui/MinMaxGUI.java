package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import gui.algorithmPanel.MinMaxPanel;
import gui.utilsPanel.ControlPanel;
import gui.utilsPanel.ForcedPause;
import gui.utilsPanel.InformationPanel;
import log.LoggerUtility;

public class MinMaxGUI extends JPanel implements ForcedPause {
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 400;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private MinMaxPanel minMaxPanel;
    private Thread minMaxThread;
    private static Logger logger = LoggerUtility.getLogger(MinMaxGUI.class, "text");

    public MinMaxGUI() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X, GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.MINMAX);
        cp.setOpt1Value(10);
        cp.setOpt2Value(5);
        ip = new InformationPanel(GUIConstant.MINMAX);
        minMaxPanel = new MinMaxPanel(Integer.valueOf(cp.getOpt1Field().getText()),
                Integer.valueOf(cp.getOpt2Field().getText()), MinMaxGUI.this.ip);
        minMaxThread = new Thread(minMaxPanel);

        initUpperPanel();
        cp.addActionListenerStart(new ActionStart());
        cp.addActionListenerRestart(new ActionRestart());
        cp.addActionListenerStop(new ActionStop());
        cp.getStop().setEnabled(false);
        add(upperPanel);
        add(ip);

        logger.info("MinMaxGUI ready for run.");
        // placementDebug();
    }

    private void placementDebug() {
        cp.setBorder(BorderFactory.createLineBorder(Color.black));
        setBorder(BorderFactory.createLineBorder(Color.black));
        minMaxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        ip.setBorder(BorderFactory.createLineBorder(Color.black));
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private void initUpperPanel() {
        upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        upperPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        upperPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        upperPanel.add(cp);
        try {
            URL playerURL = this.getClass().getResource("/res/player.png");
            JLabel jLabelPlayer = new JLabel(new ImageIcon(new ImageIcon(ImageIO.read(playerURL)).getImage().getScaledInstance(GUIConstant.SCALING_FACTOR*70, HEIGHT/2, Image.SCALE_SMOOTH)));
            upperPanel.add(jLabelPlayer);
        } catch (IOException e) {
        }
        upperPanel.add(minMaxPanel);
        try {
            JLabel jLabelBot = new JLabel(new ImageIcon(new ImageIcon(ImageIO.read(this.getClass().getResource("/res/bot.png"))).getImage().getScaledInstance(GUIConstant.SCALING_FACTOR*70, HEIGHT/2, Image.SCALE_SMOOTH)));
            upperPanel.add(jLabelBot);
        } catch (IOException e) {
        }

    }

    public void resetButton() {
        cp.getStart().setEnabled(true);
        cp.getRestart().setEnabled(true);
        cp.getStop().setEnabled(true);
        cp.getStop().setText("Stop");
    }

    public void resetTextfield() {
        cp.getOpt1Field().setEditable(true);
        cp.getOpt2Field().setEditable(true);
    }

    class ActionStart implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!minMaxPanel.isPaused()) {
                minMaxThread.start();
                cp.getStart().setEnabled(false);
                cp.getStop().setEnabled(true);
                cp.getOpt1Field().setEditable(false);
                cp.getOpt2Field().setEditable(false);
            }
        }
    }

    class ActionRestart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            minMaxThread.interrupt();
            upperPanel.remove(2);
            minMaxPanel = new MinMaxPanel(verifyOpt1(),verifyOpt2(), MinMaxGUI.this.ip);
            minMaxThread = new Thread(minMaxPanel);
            upperPanel.add(minMaxPanel,2);
            upperPanel.revalidate();
            upperPanel.repaint();
            ip.resetValue();
            resetButton();
            resetTextfield();
        }

        public int verifyOpt1(){
            String inputCoins =  cp.getOpt1Field().getText();
            int coins = minMaxPanel.getCoin();
            if (inputCoins.matches("\\d+")) {
                coins = Integer.valueOf(inputCoins);
                if (coins > 30){
                    logger.warn("Maximum coins exceeded : " + coins);
                    coins = 30;
                }
                if (coins <5){
                    logger.warn("Minimum coins exceeded : " + coins);
                    coins = 5;
                }
                cp.setOpt1Value(coins);
            } else {
                logger.warn("User input coins invalid : "+ inputCoins);
                cp.setOpt1Value(minMaxPanel.getCoin());
            }
            return coins;
        }
        
        public int verifyOpt2(){
            String inputDepth =  cp.getOpt2Field().getText();
            int depth = minMaxPanel.getDepth();
            if (inputDepth.matches("\\d+")) {
                depth = Integer.valueOf(inputDepth);
                if (depth > 5){
                    logger.warn("Maximum depth exceeded : " + depth);
                    depth = 5;
                }
                if (depth < 1){
                    logger.warn("Minimum depth exceeded : " + depth);
                    depth = 1;
                }
                cp.setOpt2Value(depth);
            } else {
                logger.warn("User input depth invalid : "+ inputDepth);
                cp.setOpt2Value(minMaxPanel.getDepth());
            }
            return depth;
        }
    }

    class ActionStop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            minMaxPanel.togglePaused();
            stopAndResume();
        }
    }
    
    public ControlPanel getCp() {
        return cp;
    }

    public InformationPanel getIp() {
        return ip;
    }

    private void stopAndResume(){
        if (minMaxPanel.isPaused()) {
            cp.getStop().setText("Resume");
            cp.getOpt2Field().setEditable(true);
        } else {
            cp.getStop().setText("Stop");
            cp.getOpt2Field().setEditable(false);
        }
    }

    @Override
    public void togglePaused() {
        if (!minMaxPanel.isPaused() && minMaxThread.isAlive()){
            minMaxPanel.togglePaused();
            stopAndResume();
        }
    }
}
