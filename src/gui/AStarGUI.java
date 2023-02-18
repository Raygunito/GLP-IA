package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class AStarGUI extends JPanel implements Runnable{
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
<<<<<<< HEAD

=======
>>>>>>> parent of 7986dd8 (working only on GUI)
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
        upperPanel.add(astarPanel);
        
    }

    @Override
    public void run() {
        System.out.println("AStarGUI run.");
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
