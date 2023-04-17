package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import gui.algorithmPanel.MinMaxPanel;
import gui.utilsPanel.ControlPanel;
import gui.utilsPanel.InformationPanel;
import log.LoggerUtility;

public class MinMaxGUI extends JPanel implements Runnable{
    private static final int WIDTH=GUIConstant.SCALING_FACTOR*400;
    private static final int HEIGHT=GUIConstant.SCALING_FACTOR*180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private MinMaxPanel minMaxPanel;
    private Thread minMaxThread;
    private static Logger logger = LoggerUtility.getLogger(MinMaxGUI.class, "text");

    public MinMaxGUI() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X,GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.MINMAX);
        cp.setOpt1Value(10);
        cp.setOpt2Value(5);
        ip = new InformationPanel(GUIConstant.MINMAX);
        //TODO Remplacer le minMaxPanel avec sa version fonctionnelle
        minMaxPanel = new MinMaxPanel(Integer.valueOf(cp.getOpt1Field().getText()),Integer.valueOf(cp.getOpt2Field().getText()),MinMaxGUI.this.ip);
        minMaxThread = new Thread(minMaxPanel);
        
        initUpperPanel();
        cp.addActionListenerStart(new ActionStart());
        cp.addActionListenerRestart(new ActionRestart());
        cp.addActionListenerStop(new ActionStop());
        add(upperPanel);
        add(ip);

        // placementDebug();
    }
    
    private void placementDebug() {
        cp.setBorder(BorderFactory.createLineBorder(Color.black));
        setBorder(BorderFactory.createLineBorder(Color.black));
        minMaxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        ip.setBorder(BorderFactory.createLineBorder(Color.black));
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private void initUpperPanel(){
        upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        upperPanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        upperPanel.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        upperPanel.add(cp);
        upperPanel.add(Box.createHorizontalStrut(GUIConstant.SCALING_FACTOR*80));
        upperPanel.add(minMaxPanel);
        
    }
    @Override
    public void run() {
        logger.info("MinMaxGUI ready for run.");
        repaint();
        revalidate();
    }

    public void resetButton(){
        cp.getStart().setEnabled(true);
        cp.getRestart().setEnabled(true);
        cp.getStop().setEnabled(true);
        cp.getStop().setText("Stop");
    }

    public void resetTextfield(){
        cp.getOpt1Field().setEditable(true);
        cp.getOpt2Field().setEditable(true);
    }


    class ActionStart implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            minMaxThread.start();
        }
    }

    class ActionRestart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            minMaxThread.interrupt();
            upperPanel.remove(2);
            minMaxPanel = new MinMaxPanel(Integer.valueOf(cp.getOpt1Field().getText()),
                    Integer.valueOf(cp.getOpt2Field().getText()),MinMaxGUI.this.ip);
            minMaxThread = new Thread(minMaxPanel);
            upperPanel.add(minMaxPanel);
            upperPanel.revalidate();
            upperPanel.repaint();
            ip.resetValue();
        }

    }

    class ActionStop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            minMaxPanel.togglePaused();
        }
    }

    public ControlPanel getCp() {
        return cp;
    }
    public InformationPanel getIp() {
        return ip;
    }
}
