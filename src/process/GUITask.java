package process;
import gui.GUI;
public class GUITask implements Runnable {

    public int timer;

    private GUI gui;

    public GUITask(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        while (true) {
            // gui.update();
        }
    }

}


