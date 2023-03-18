package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import gui.algorithmPanel.AStarPanel;
import gui.utilsPanel.ControlPanel;
import gui.utilsPanel.InformationPanel;

public class AStarGUI extends JPanel implements Runnable {
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 400;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private AStarPanel astarPanel;
    private Thread astarThread;

    public AStarGUI() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X, GUIConstant.DIM_Y));
        setMaximumSize(new Dimension(GUIConstant.DIM_X, GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.ASTAR);
        cp.setOpt1Value(40);
        cp.setOpt2Value(3);
        ip = new InformationPanel(GUIConstant.ASTAR);
        // TODO Remplacer le astarpanel avec sa version fonctionnelle
        astarPanel = new AStarPanel(Integer.valueOf(cp.getOpt1Field().getText()),
                Integer.valueOf(cp.getOpt2Field().getText()), this.ip);
        astarThread = new Thread(astarPanel);
        initUpperPanel();

        cp.addActionListenerStart(new ActionStart());
        cp.addActionListenerRestart(new ActionRestart());
        cp.addActionListenerStop(new ActionStop());

        // add(astarPanel);
        add(upperPanel);
        add(ip);
        // placementDebug();
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
        upperPanel.add(Box.createHorizontalStrut(GUIConstant.SCALING_FACTOR*50));
        upperPanel.add(astarPanel);
    }

    @Override
    public void run() {
        System.out.println("AStarGUI run.");
        revalidate();
        repaint();
    }

    public ControlPanel getCp() {
        return cp;
    }

    public InformationPanel getIp() {
        return ip;
    }

    class ActionStart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            astarThread.start();
        }

    }

    class ActionRestart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            astarPanel.togglePaused();
            astarThread.interrupt();
            upperPanel.remove(2);
            astarPanel = new AStarPanel(Integer.valueOf(cp.getOpt1Field().getText()),
                    Integer.valueOf(cp.getOpt2Field().getText()),AStarGUI.this.ip);
            astarThread = new Thread(astarPanel);
            upperPanel.add(astarPanel);
            upperPanel.revalidate();
            upperPanel.repaint();
            ip.resetValue();
        }

    }

    class ActionStop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            astarPanel.togglePaused();
        }
    }
}
