package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.utilsPanel.ControlPanel;
import gui.utilsPanel.InformationPanel;

public class QLearnGUI extends JPanel implements Runnable{
    private static final int WIDTH=GUIConstant.SCALING_FACTOR*400;
    private static final int HEIGHT=GUIConstant.SCALING_FACTOR*180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private JPanel qlearnPanel;

    public QLearnGUI() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X,GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.QLEARN);
        ip = new InformationPanel(GUIConstant.QLEARN);
        //TODO Remplacer le qlearnPanel avec sa version fonctionnelle
        qlearnPanel = new JPanel();
        qlearnPanel.setPreferredSize(new Dimension(1000,530));
        qlearnPanel.setMaximumSize(new Dimension(1000,530));

        initUpperPanel();
        
        add(upperPanel);
        add(ip);
    }
    
    private void initUpperPanel(){
        upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        upperPanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        upperPanel.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        upperPanel.add(cp);
        upperPanel.add(qlearnPanel);
        
    }

    @Override
    public void run() {
        System.out.println("QLearnGUI run.");
        repaint();
        revalidate();
    }

    public ControlPanel getCp() {
        return cp;
    }
    public InformationPanel getIp() {
        return ip;
    }
}
