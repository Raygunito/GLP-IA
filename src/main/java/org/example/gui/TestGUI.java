package main.java.org.example.gui;

public class TestGUI {
    public static void main(String[] args) {
        GUI gui = new GUI();
        while (!gui.getCore().isEnded() && !gui.getCore().getOpenList().getQueue().isEmpty()) {
            gui.process();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
