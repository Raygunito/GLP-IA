package gui.algorithmPanel;

import javax.swing.*;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartPanel;

import data.astar.ACell;
import data.elements.Tile;
import data.elements.Trail;
import data.elements.UselessTile;
import data.elements.Wall;
import gui.GUIConstant;
import gui.primitivePanel.GridPanel;
import gui.utilsPanel.InformationPanel;
import log.LoggerUtility;
import process.astar.AStarCore;

import java.awt.*;
import java.util.ArrayList;

/**
 * AStarPanel is a JPanel class used to render and control the AStar algorithm.
 * It implements the Runnable interface, allowing it to run as a thread.
 */
public class AStarPanel extends JPanel implements Runnable {
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 175;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 175;
    private volatile boolean paused = false;
    private AStarCore core;
    private GridPanel gridpanel;
    private InformationPanel ip;
    private volatile int speed;
    private static Logger logger = LoggerUtility.getLogger(AStarPanel.class, "text");

    /**
     * Creates an AStarPanel with a default size of 40x40.
     */
    public AStarPanel() {
        super();
        core = new AStarCore(40);
        init();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Creates an AStarPanel with a size of nxn, a speed of speed, and an
     * InformationPanel of ip.
     * 
     * @param n
     * @param speed
     * @param ip
     */
    public AStarPanel(int n, int speed, InformationPanel ip) {
        super();
        core = new AStarCore(n);
        this.ip = ip;
        this.speed = speed;
        init();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Creates an AStarPanel with a size of nxn, a speed of speed.
     * 
     * @param n
     * @param speed
     */
    public AStarPanel(int n, int speed) {
        super();
        core = new AStarCore(n);
        init();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Initializes the AStarPanel
     */
    public void init() {
        gridpanel = new GridPanel(core.getGrid());
        this.add(gridpanel);
    }

    /**
     * Updates the AStarPanel with the current state of the grid.
     */
    private void update() {
        ArrayList<ACell> parents = core.getClosedList().get(core.getClosedList().size() - 1).getGenealogy();
        for (int i = 0; i < core.getGrid().getSize(); i++) {
            for (int j = 0; j < core.getGrid().getSize(); j++) {
                ACell cell = core.getGrid().getCell(i, j);
                if (core.getClosedList().contains(core.getGrid().getCell(i, j))) {
                    if (parents.contains(core.getGrid().getCell(i, j))) {
                        cell.setElement(new Trail(cell.getCoordinate()));
                    } else {
                        cell.setElement(new UselessTile(cell.getCoordinate()));
                    }
                } else if (core.getGrid().getGrid()[i][j].isCanAccess()) {
                    cell.setElement(new Tile(cell.getCoordinate()));
                } else {
                    cell.setElement(new Wall(cell.getCoordinate()));
                }
            }
        }
        gridpanel.setGrid(core.getGrid());
        // TODO Finir l'update de la value en temps réel.
        if (ip != null) {
            ip.setInfoValue1(String.valueOf(core.getClosedListSize()));
            ip.setInfoValue2(String.valueOf(core.getCurrentPathSize()));
            ip.repaint();
        }
    }

    /**
     * Processes the current state of the grid.
     */
    public synchronized void process() {
        core.process();
        update();
    }

    @Override
    public void run() {
        while (!core.workFinished()) {
            if (!paused) {
                process();
                try {
                    Thread.sleep(600 - (speed * 60));
                } catch (InterruptedException e) {
                    return;
                }
                revalidate();
                repaint();
            }
        }
        test();
    }

    public int getNumberOfCellVisited() {
        return core.getClosedListSize();
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void test() {
        ChartPanel chartPanel = new ChartPanel(core.chartManager.getHeuristicEvolutionChart());
        chartPanel.setPreferredSize(new Dimension(300, 150));
        this.ip.add(chartPanel);
    }
}
