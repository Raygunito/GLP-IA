package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import javax.swing.UIManager;

import org.junit.Test;

import gui.GUI;

public class Main_GUI{
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        new Thread(new GUI()).start();
    }

    @Test
    public void test(){
        assertEquals(2, 2);
    }
}
