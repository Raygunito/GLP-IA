package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class AStarGUI extends JPanel{
    private static final int WIDTH=GUIConstant.SCALING_FACTOR*400;
    private static final int HEIGHT=GUIConstant.SCALING_FACTOR*180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private JPanel astarPanel;
    public AStarGUI() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X,GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.ASTAR);
        ip = new InformationPanel(GUIConstant.ASTAR);
        //TODO Remplacer le astarpanel avec sa version fonctionnelle
        astarPanel = new JPanel();
        astarPanel.setPreferredSize(new Dimension(1000,530));
        astarPanel.setMaximumSize(new Dimension(1000,530));
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
        upperPanel.add(astarPanel);
        
    }

    private void placementDebug(){
        cp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        astarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ip.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
