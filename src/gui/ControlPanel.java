package gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel{
    //TODO scalable factor to take account, prepare parameter for each algorithms
    private static final int width=GUIConstant.SCALING_FACTOR*50;
    private static final int height=GUIConstant.SCALING_FACTOR*175;
    private JPanel commonPanel,aStarParameter,qLearnParameter,minMaxParameter;
    private JButton back,start,stop,restart;
    public ControlPanel(String algoName) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initCommon();
        setupCommon();
        add(commonPanel);


        setPreferredSize(new Dimension(width,height));
    }
    private void setupCommon() {
        back.setAlignmentX(CENTER_ALIGNMENT);
        start.setAlignmentX(CENTER_ALIGNMENT);
        stop.setAlignmentX(CENTER_ALIGNMENT);
        restart.setAlignmentX(CENTER_ALIGNMENT);
    }
    private void initCommon() {
        commonPanel=new JPanel();
        commonPanel.setLayout(new BoxLayout(commonPanel, BoxLayout.Y_AXIS));

        back = new JButton("Back");
        start = new JButton("Start");
        stop = new JButton("Stop");
        restart = new JButton("Restart");

        commonPanel.add(back);
        commonPanel.add(Box.createVerticalGlue());
        commonPanel.add(start);
        commonPanel.add(stop);
        commonPanel.add(restart);
    }
    
}
