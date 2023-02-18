package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class AStarGUI extends JPanel{
    private static final int WIDTH=GUIConstant.SCALING_FACTOR*400;
    private static final int HEIGHT=GUIConstant.SCALING_FACTOR*180;
    private ControlPanel cp;
    private InformationPanel ip;
    private JPanel upperPanel;
    private JPanel astarPanel;
    private JButton button;

    public AStarGUI() {
        super();
        button = new JButton("Mon bouton");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(GUIConstant.DIM_X,GUIConstant.DIM_Y));
        cp = new ControlPanel(GUIConstant.ASTAR);
        ip = new InformationPanel(GUIConstant.ASTAR);

        //TODO Remplacer le astarpanel avec sa version fonctionnelle
        astarPanel = new JPanel();
        astarPanel.setPreferredSize(new Dimension(1000,530));
        astarPanel.setMaximumSize(new Dimension(1000,530));
        initUpperPanel();
        
        button.addActionListener(new MaSuperAction());

        add(button);
        add(upperPanel);
        add(ip);

        // placementDebug();
    }

    class MaSuperAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            button.setText("J'ai changé !");
        }
        
        
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
