package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import gui.algorithmPanel.AStarPanel;
import gui.utilsPanel.ControlPanel;
import gui.utilsPanel.InformationPanel;
import log.LoggerUtility;

public class AStarGUI extends JPanel {
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 400;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private AStarPanel astarPanel;
    private Thread astarThread;
    private static Logger logger = LoggerUtility.getLogger(AStarGUI.class, "text");

    public AStarGUI() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X, GUIConstant.DIM_Y));
        setMaximumSize(new Dimension(GUIConstant.DIM_X, GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.ASTAR);
        cp.setOpt1Value(40);
        cp.setOpt2Value(3);
        ip = new InformationPanel(GUIConstant.ASTAR);
        astarPanel = new AStarPanel(Integer.valueOf(cp.getOpt1Field().getText()),
                Integer.valueOf(cp.getOpt2Field().getText()), this.ip);
        astarThread = new Thread(astarPanel);
        initUpperPanel();

        cp.addActionListenerStart(new ActionStart());
        cp.addActionListenerRestart(new ActionRestart());
        cp.addActionListenerStop(new ActionStop());

        cp.getStop().setEnabled(false);

        add(upperPanel);
        add(ip);
        // placementDebug();
        logger.info("AStarGUI ready for run.");
    }

    private void placementDebug() {
        cp.setBorder(BorderFactory.createLineBorder(Color.black));
        setBorder(BorderFactory.createLineBorder(Color.black));
        astarPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        ip.setBorder(BorderFactory.createLineBorder(Color.black));
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private void initUpperPanel() {
        upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        upperPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        upperPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        upperPanel.add(cp);
        upperPanel.add(Box.createHorizontalStrut(GUIConstant.SCALING_FACTOR * 50));
        upperPanel.add(astarPanel);
    }

    public ControlPanel getCp() {
        return cp;
    }

    public InformationPanel getIp() {
        return ip;
    }

    public void resetButton() {
        cp.getStart().setEnabled(true);
        cp.getRestart().setEnabled(true);
        cp.getStop().setEnabled(false);
        cp.getStop().setText("Stop");
    }

    public void resetTextfield() {
        cp.getOpt1Field().setEditable(true);
        cp.getOpt2Field().setEditable(true);
    }

    class ActionStart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!astarPanel.isPaused()) {
                astarThread.start();
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
            astarPanel.togglePaused();
            astarThread.interrupt();
            upperPanel.remove(2);
            astarPanel = new AStarPanel(Integer.valueOf(cp.getOpt1Field().getText()),
                    Integer.valueOf(cp.getOpt2Field().getText()), AStarGUI.this.ip);
            astarThread = new Thread(astarPanel);
            upperPanel.add(astarPanel);
            upperPanel.revalidate();
            upperPanel.repaint();
            if (ip.getComponentCount() == 3) {
                ip.remove(2);
            }
            ip.resetValue();
            resetButton();
            resetTextfield();
        }

    }

    class ActionStop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            astarPanel.togglePaused();
            String input = cp.getOpt2Field().getText();
            if (input.matches("\\d+")) {
                astarPanel.setSpeed(Integer.valueOf(cp.getOpt2Field().getText()));
            } else {
                cp.setOpt2Value(astarPanel.getSpeed());
                logger.warn("User input invalid for speed : " + input);
                JOptionPane.showMessageDialog(astarPanel,
                        "Warning ! Speed value is not a number, previous value inserted.", "Not numeric !",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            if (astarPanel.isPaused()) {
                cp.getStop().setText("Resume");
                cp.getOpt2Field().setEditable(true);
            } else {
                cp.getStop().setText("Stop");
                cp.getOpt2Field().setEditable(false);
            }
        }
    }
}
