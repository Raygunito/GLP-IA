package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.algorithmPanel.MinMaxPanel;
import gui.utilsPanel.ControlPanel;
import gui.utilsPanel.InformationPanel;

public class MinMaxGUI extends JPanel implements Runnable{
    private static final int WIDTH=GUIConstant.SCALING_FACTOR*400;
    private static final int HEIGHT=GUIConstant.SCALING_FACTOR*180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private MinMaxPanel minMaxPanel;
    public MinMaxGUI() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X,GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.MINMAX);
        cp.setOpt1Value(10);
        cp.setOpt2Value(5);
        ip = new InformationPanel(GUIConstant.MINMAX);
        //TODO Remplacer le minMaxPanel avec sa version fonctionnelle
        minMaxPanel = new MinMaxPanel(Integer.valueOf(cp.getOpt1Field().getText()),Integer.valueOf(cp.getOpt2Field().getText()));
        minMaxPanel.setPreferredSize(new Dimension(300,300));
        minMaxPanel.setMaximumSize(new Dimension(300,300));
        
        initUpperPanel();
        
        add(upperPanel);
        add(ip);

        // placementDebug();
    }
    
    private void initUpperPanel(){
        upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        upperPanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        upperPanel.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        upperPanel.add(cp);
        upperPanel.add(minMaxPanel);
        
    }
    @Override
    public void run() {
        System.out.println("MinMaxGUI run.");
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
