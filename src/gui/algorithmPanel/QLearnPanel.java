package gui.algorithmPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import data.qlearn.QCell;
import data.elements.*;
import gui.GUIConstant;
import gui.management.SquareDrawStrategy;
import gui.primitivePanel.GridPanel;
import gui.utilsPanel.InformationPanel;
import log.LoggerUtility;
import process.qlearn.QLearnCore;
import process.visitor.DrawingVisitor;

public class QLearnPanel extends JPanel implements Runnable{
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 175;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 175;
    private volatile boolean paused = false;
    private QLearnCore core;
    private GridPanel gridpanel;
    private InformationPanel ip;
    private JTextArea textArea;
    private static Logger logger = LoggerUtility.getLogger(AStarPanel.class, "text");

    public QLearnPanel(int nbIteration, float learningRate, InformationPanel ip){
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        core = new QLearnCore(15, nbIteration, learningRate, 0.5f, 0.8f);
        this.ip = ip;
        
        setPreferredSize(new Dimension(WIDTH*7/4, HEIGHT));
        setMaximumSize(new Dimension(WIDTH*7/4, HEIGHT));
        init();
        initTextArea();
    }
    
    private void initTextArea(){
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane js = new JScrollPane(textArea);
        js.setPreferredSize(new Dimension(WIDTH*2/3, HEIGHT-10));
        this.add(js);
    }

    public void init() {
        gridpanel = new GridPanel(core.getGrid());
        this.add(gridpanel);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void process(){
        core.doOneIteration();
        update();
    }
    
    private void update() {
        textArea.setText(core.getqTable().printTable());
        ArrayList<QCell> path = core.getPath();
        if (ip !=null) {
            ip.setInfoValue1(String.valueOf(core.getNbTot()-core.getNbIte()));
        }
    }

    @Override
    public void run() {
        System.out.println("ON MA LACHE DANS LA NATURE");
        while (core.getNbIte()>0){
            if (!paused){
                process();
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    return;
                }
                revalidate();
                repaint();
            }
        }
        int cellSize = gridpanel.getWidth() / core.getGrid().getSize();
        DrawingVisitor drawingVisitor = new DrawingVisitor(gridpanel.getGraphics(),new SquareDrawStrategy(), cellSize);
        for (QCell qCell : core.bestPath()) {
            Element element = qCell.getElement();
            qCell.setElement(new Trail(qCell.getCoordinate()));
            element.accept(drawingVisitor);

        }
    }
    /**
     * Toggles the paused state of the AStarPanel.
     */
    public void togglePaused() {
        paused = !paused;
    }

    /**
     * Checks the paused state of the AStarPanel.
     * 
     * @return boolean true if paused false otherwise
     */
    public boolean isPaused() {
        return paused;
    }
    
}
