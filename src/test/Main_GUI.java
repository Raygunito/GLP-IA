package test;

import javax.swing.UIManager;

import gui.GUI;

public class Main_GUI{
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        new Thread(new GUI()).start();
    }
}
