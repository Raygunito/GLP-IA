package data.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class GUI extends JFrame{
    public GUI() {
        super("The Cognitive Crew");
        Container c = getContentPane();
        c.add(new main_menu());
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        new GUI();
    }
}
