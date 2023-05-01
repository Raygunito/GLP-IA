package gui.algorithmPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartPanel;

import data.qlearn.QCell;
import data.elements.*;
import gui.GUIConstant;
import gui.instrument.ChartManager;
import gui.management.SquareDrawStrategy;
import gui.primitivePanel.GridPanel;
import gui.utilsPanel.InformationPanel;
import log.LoggerUtility;
import process.qlearn.QLearnCore;
import process.visitor.DrawingVisitor;
import process.visitor.ElementCountVisitor;

public class QLearnPanel extends JPanel implements Runnable {
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 175;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 175;
    private volatile boolean paused = false;
    private QLearnCore core;
    private GridPanel gridpanel;
    private InformationPanel ip;
    private JTextArea textArea;
    private ChartManager chartManager;
    private ElementCountVisitor elementCountVisitor;
    private static Logger logger = LoggerUtility.getLogger(QLearnPanel.class, "text");
    
    public QLearnPanel(int nbIteration, float learningRate, InformationPanel ip) {
        super();
        setLayout(new FlowLayout(FlowLayout.LEFT));
        core = new QLearnCore(15, nbIteration, learningRate, 0.5f, 0.8f);
        this.ip = ip;
        chartManager = new ChartManager();
        elementCountVisitor = new ElementCountVisitor(chartManager);
        
        setPreferredSize(new Dimension(WIDTH * 7 / 4, HEIGHT));
        setMaximumSize(new Dimension(WIDTH * 7 / 4, HEIGHT));
        init();
        initTextArea();
    }

    private void initTextArea() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane js = new JScrollPane(textArea);
        js.setPreferredSize(new Dimension(WIDTH * 2 / 3, HEIGHT - 10));
        this.add(js);
    }

    public void init() {
        gridpanel = new GridPanel(core.getGrid());
        this.add(gridpanel);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void process() {
        core.doOneIteration();
        update();
    }

    private void update() {
        textArea.setText(core.getqTable().printTable());
        for (QCell qCell : core.getPath()) {
            elementCountVisitor.visit(qCell.getElement());
            int cellSize = gridpanel.getWidth() / core.getGrid().getSize();
            Graphics2D g2d = (Graphics2D) gridpanel.getGraphics();
            Color color = g2d.getColor();
            g2d.setColor(Color.cyan);
            g2d.drawRect(qCell.getCoordinate().coordinateX() * cellSize, qCell.getCoordinate().coordinateY() * cellSize,
            cellSize, cellSize);
            g2d.setColor(color);
        }
        if (ip != null) {
            ip.setInfoValue1(String.valueOf(core.getNbTot() - core.getNbIte()));
            ip.setInfoValue2(String.valueOf(Integer.valueOf(ip.getInfoValue2())+core.getPath().size()));
            ip.repaint();
        }
    }
    
    @Override
    public void run() {
        int cellSize = gridpanel.getWidth() / core.getGrid().getSize();
        DrawingVisitor drawingVisitor = new DrawingVisitor(gridpanel.getGraphics(), new SquareDrawStrategy(), cellSize);
        while (core.getNbIte() > 0) {
            if (!paused) {
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
        for (QCell qCell : core.bestPath()) {
            Element element = qCell.getElement();
            qCell.setElement(new Trail(qCell.getCoordinate()));
            element.accept(drawingVisitor);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }

        ChartPanel chartPanel = new ChartPanel(chartManager.getTypeCountPie());
        chartPanel.setPreferredSize(new Dimension(300, 150));
        this.ip.add(chartPanel);


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

    public int getIteration() {
        return core.getNbTot();
    }

    public float getLearningRate() {
        return core.getLearningRate();
    }
}
