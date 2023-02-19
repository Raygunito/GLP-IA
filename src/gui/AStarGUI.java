package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import gui.algorithmPanel.AStarPanel;

public class AStarGUI extends JPanel implements Runnable{
    private static final int WIDTH=GUIConstant.SCALING_FACTOR*400;
    private static final int HEIGHT=GUIConstant.SCALING_FACTOR*180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private AStarPanel astarPanel;
    public AStarGUI() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X,GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.ASTAR);
        ip = new InformationPanel(GUIConstant.ASTAR);
        //TODO Remplacer le astarpanel avec sa version fonctionnelle
        astarPanel = new AStarPanel();
        astarPanel.setPreferredSize(new Dimension(300,300));
        astarPanel.setMaximumSize(new Dimension(300,300));
        initUpperPanel();
        // add(astarPanel);
        add(upperPanel);
        add(ip);
        // placementDebug();
    }
    
    private void placementDebug(){
        cp.setBorder(BorderFactory.createLineBorder(Color.black));
        setBorder(BorderFactory.createLineBorder(Color.black));
        astarPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        ip.setBorder(BorderFactory.createLineBorder(Color.black));
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
        while (!astarPanel.getCore().isEnded() && !astarPanel.getCore().getOpenList().getQueue().isEmpty()) {
            astarPanel.process();
            String tmpCell = String.valueOf(astarPanel.getCore().getClosedList().size()-1);
            ip.setInfoValue1(tmpCell);
            String tmpCurrent = String.valueOf(astarPanel.getCore().getClosedList().get(astarPanel.getCore().getClosedList().size() - 1).getGenealogy().size());
            ip.setInfoValue2(tmpCurrent);
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
        revalidate();
    }

    public ControlPanel getCp() {
        return cp;
    }
    public InformationPanel getIp() {
        return ip;
    }
}
