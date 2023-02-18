package test;

import javax.swing.UIManager;

import gui.GUI;


public class TestGUI {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        GUI tc2gui = new GUI();
    }   
}
