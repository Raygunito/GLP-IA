package gui;

import javax.swing.JPanel;

public class MinMaxGUI extends JPanel{
    private ControlPanel cp;
    private InformationPanel ip;

    public MinMaxGUI() {
        super();
        cp = new ControlPanel(GUIConstant.MINMAX);
        ip = new InformationPanel(GUIConstant.MINMAX);
    }
}
