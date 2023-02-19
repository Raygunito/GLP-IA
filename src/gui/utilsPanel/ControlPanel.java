package gui.utilsPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.GUIConstant;

public class ControlPanel extends JPanel{
    //TODO scalable factor to take account, prepare parameter for each algorithms
    private static final int WIDTH=GUIConstant.SCALING_FACTOR*50;
    private static final int HEIGHT=GUIConstant.SCALING_FACTOR*175;
    private JPanel commonPanel,aStarParameter,qLearnParameter,minMaxParameter, option1Panel, option2Panel;
    private JButton back,start,stop,restart;
    private JLabel option1,option2;
    private JLabel parameterTag;
    private JTextField opt1Field,opt2Field;

    public ControlPanel(String algoName) throws IllegalArgumentException{
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
                throw new IllegalArgumentException("Wrong name used to construct ControlPanel, use constants from GUIConstant.");
        }
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setMaximumSize(new Dimension(WIDTH,HEIGHT));
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
        commonPanel.setAlignmentX(CENTER_ALIGNMENT);
        back = new JButton("Back");
        start = new JButton("Start");
        stop = new JButton("Stop");
        restart = new JButton("Restart");
        
        commonPanel.setMaximumSize(new Dimension(WIDTH,HEIGHT/2));
        commonPanel.add(Box.createVerticalStrut(5));
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
        aStarParameter.setMaximumSize(new Dimension(WIDTH,HEIGHT/2));
        parameterTag = new JLabel("Parameter :");
        parameterTag.setAlignmentX(CENTER_ALIGNMENT);
        option1 = new JLabel("Size :");
        option2 = new JLabel("Speed :");
        
        InitOptionPanel();
        aStarParameter.add(Box.createVerticalGlue());
        aStarParameter.add(parameterTag);
        aStarParameter.add(Box.createVerticalGlue());
        aStarParameter.add(option1Panel);
        aStarParameter.add(option2Panel);
        aStarParameter.add(Box.createVerticalGlue());
    }
    
    private void initQLearnParameter(){
        qLearnParameter = new JPanel();
        qLearnParameter.setLayout(new BoxLayout(qLearnParameter, BoxLayout.Y_AXIS));
        qLearnParameter.setMaximumSize(new Dimension(WIDTH,HEIGHT/2));
        parameterTag = new JLabel("Parameter :");
        parameterTag.setAlignmentX(CENTER_ALIGNMENT);
        option1 = new JLabel("Iteration:");
        option2 = new JLabel("Learning rate:");
        
        InitOptionPanel();
        qLearnParameter.add(Box.createVerticalGlue());
        qLearnParameter.add(parameterTag);
        qLearnParameter.add(Box.createVerticalGlue());
        qLearnParameter.add(option1Panel);
        qLearnParameter.add(option2Panel);   
        qLearnParameter.add(Box.createVerticalGlue());
    }
    
    private void initMinMaxParameter(){
        minMaxParameter = new JPanel();
        minMaxParameter.setLayout(new BoxLayout(minMaxParameter, BoxLayout.Y_AXIS));
        minMaxParameter.setMaximumSize(new Dimension(WIDTH,HEIGHT/2));
        parameterTag = new JLabel("Parameter :");
        parameterTag.setAlignmentX(CENTER_ALIGNMENT);
        option1 = new JLabel("Coins :");
        option2 = new JLabel("Depth :");   
        
        InitOptionPanel();
        minMaxParameter.add(Box.createVerticalGlue());
        minMaxParameter.add(parameterTag);
        minMaxParameter.add(Box.createVerticalGlue());
        minMaxParameter.add(option1Panel);
        minMaxParameter.add(option2Panel);   
        minMaxParameter.add(Box.createVerticalGlue());
    }
    
    private void InitOptionPanel(){
        option1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        option2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        opt1Field = new JTextField(GUIConstant.SCALING_FACTOR);
        opt2Field = new JTextField(GUIConstant.SCALING_FACTOR);
        option1Panel.add(option1);
        option1Panel.add(opt1Field);
        option2Panel.add(option2);
        option2Panel.add(opt2Field);
    }

    private void placementDebug(){
        commonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        option1Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        opt1Field.setText(String.valueOf(aStarParameter.getWidth()));
        opt2Field.setText(String.valueOf(aStarParameter.getHeight()));
        option2Panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aStarParameter.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aStarParameter.setPreferredSize(new Dimension(20,40));
        option1Panel.setAlignmentX(CENTER_ALIGNMENT);
        option2Panel.setAlignmentX(CENTER_ALIGNMENT);
        
        parameterTag.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        parameterTag.setPreferredSize(new Dimension(30,20));
        option1Panel.setPreferredSize(new Dimension(20,10));
        option2Panel.setPreferredSize(new Dimension(20,10));
    }


    //Action Listener
    public void addActionListenerBack(ActionListener al){
        back.addActionListener(al);
    }
    public void addActionListenerStart(ActionListener al){
        start.addActionListener(al);
    }
    public void addActionListenerStop(ActionListener al){
        stop.addActionListener(al);
    }
    public void addActionListenerRestart(ActionListener al){
        restart.addActionListener(al);
    }

    public JTextField getOpt1Field() {
        return opt1Field;
    }
    public JTextField getOpt2Field() {
        return opt2Field;
    }
    public void setOpt1Value(int i) {
        this.opt1Field.setText(String.valueOf(i));
    }
    public void setOpt2Value(int i) {
        this.opt2Field.setText(String.valueOf(i));
    }
}
