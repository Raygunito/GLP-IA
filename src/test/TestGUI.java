package test;

import javax.swing.UIManager;

import gui.GUI;

public class TestGUI implements Runnable {
    static final double TARGET_FPS = 60.0;
    static final double FRAME_TIME = 1000000000.0 / TARGET_FPS;
    GUI tc2gui = new GUI();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        // GUI tc2gui = new GUI();
        new Thread(new TestGUI()).start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / FRAME_TIME;
            lastTime = now;
            if (delta >= 1) {
                tc2gui.revalidate();
                tc2gui.repaint();
                delta--;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
