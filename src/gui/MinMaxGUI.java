package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class MinMaxGUI extends JPanel{
    private static final int WIDTH=GUIConstant.SCALING_FACTOR*400;
    private static final int HEIGHT=GUIConstant.SCALING_FACTOR*180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private JPanel minMaxPanel;
    public MinMaxGUI() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X,GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.ASTAR);
        ip = new InformationPanel(GUIConstant.ASTAR);
        //TODO Remplacer le minMaxPanel avec sa version fonctionnelle
        minMaxPanel = new JPanel();
        minMaxPanel.setPreferredSize(new Dimension(1000,530));
        minMaxPanel.setMaximumSize(new Dimension(1000,530));
        
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

}
