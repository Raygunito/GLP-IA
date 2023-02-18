package gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class GUI extends JFrame{
    private Main_menu menu;
    private AStarGUI astar;
    public GUI() {
        super("The Cognitive Crew");
        Container c = getContentPane();
        // c.add(new Main_menu());
        // c.add(new ControlPanel(GUIConstant.ASTAR));
        c.add(new AStarGUI());
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        new GUI();
    }    
}
