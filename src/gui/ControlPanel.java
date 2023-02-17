package gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel{
    //TODO scalable factor to take account, prepare parameter for each algorithms
    private static final int width=GUIConstant.SCALING_FACTOR*50;
    private static final int height=GUIConstant.SCALING_FACTOR*175;
    private JPanel commonPanel,aStarParameter,qLearnParameter,minMaxParameter, option1Panel, option2Panel;
    private JButton back,start,stop,restart;
    private JLabel option1,option2;
    private JLabel parameterTag;
    private JTextField opt1Field,opt2Field;
    public ControlPanel(String algoName) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initCommon();
        setupCommon();
        add(commonPanel);

        switch (algoName) {
            case GUIConstant.ASTAR:
                initAStarParameter();
                add(aStarParameter);
                break;
        
            case GUIConstant.MINMAX:
                initMinMaxParameter();
                add(minMaxParameter);
                break;
        
            case GUIConstant.QLEARN:
                initQLearnParameter();
                add(qLearnParameter);
                break;
        
            default:
                break;
        }
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
        commonPanel.add(Box.createVerticalGlue());
    }
    
    private void initAStarParameter(){
        aStarParameter = new JPanel();
        aStarParameter.setLayout(new BoxLayout(aStarParameter, BoxLayout.Y_AXIS));
        parameterTag = new JLabel("Parameter :");
        
        option1 = new JLabel("Size :");
        option2 = new JLabel("Speed :");

        aStarParameter.add(parameterTag);
        aStarParameter.add(option1);
        aStarParameter.add(option2);
    }
    private void initQLearnParameter(){
        qLearnParameter = new JPanel();
        qLearnParameter.setLayout(new BoxLayout(qLearnParameter, BoxLayout.Y_AXIS));
        parameterTag = new JLabel("Parameter :");
        option1 = new JLabel("Iteration:");
        option2 = new JLabel("Learning rate:");
        opt1Field = new JTextField();
        opt2Field = new JTextField();

        qLearnParameter.add(parameterTag);
        qLearnParameter.add(option1);
        qLearnParameter.add(option2);
        qLearnParameter.add(opt1Field);
        qLearnParameter.add(opt2Field);
        
        
    }
    private void initMinMaxParameter(){
        minMaxParameter = new JPanel();
        minMaxParameter.setLayout(new BoxLayout(minMaxParameter, BoxLayout.Y_AXIS));
        parameterTag = new JLabel("Parameter :");
        option1 = new JLabel("Coins :");
        option2 = new JLabel("Depth :");

        minMaxParameter.add(parameterTag);
        minMaxParameter.add(option1);
        minMaxParameter.add(option2);
        
    }
}
