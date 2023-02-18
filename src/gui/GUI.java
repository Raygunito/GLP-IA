package gui;

import process.GUITask;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class GUI extends JFrame{
    private Main_menu menu;
    private AStarGUI astar;
    private Container c;

    public GUI() {
        super("The Cognitive Crew");
        c = getContentPane();
        // c.add(new Main_menu());
        // c.add(new ControlPanel(GUIConstant.ASTAR));
        c.add(new AStarGUI());
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocationRelativeTo(null);
    }

    public void update() {
        c.revalidate();
        c.repaint();
    }



    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        Thread guiTask = new Thread(new GUITask(new GUI()));
        guiTask.start();
    }
}
