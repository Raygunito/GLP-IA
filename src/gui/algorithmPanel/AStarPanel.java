package gui.algorithmPanel;

import javax.swing.*;

import org.jfree.chart.ChartPanel;

import data.astar.Cell;
import data.astar.Grid;
import data.elements.Tile;
import data.elements.Trail;
import data.elements.UselessTile;
import data.elements.Wall;
import gui.GUIConstant;
import gui.primitivePanel.GridPanel;
import gui.utilsPanel.InformationPanel;
import process.astar.AStarCore;

import java.awt.*;
import java.util.ArrayList;

public class AStarPanel extends JPanel implements Runnable {
    private static final int WIDTH = GUIConstant.SCALING_FACTOR * 175;
    private static final int HEIGHT = GUIConstant.SCALING_FACTOR * 175;
    private volatile boolean paused = false;
    private AStarCore core;
    // private JLabel[][] grid;
    private GridPanel gridpanel;
    private InformationPanel ip;
    private int speed;

    public AStarPanel() {
        super();
        core = new AStarCore(40);
        init();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }

    public AStarPanel(int n, int speed, InformationPanel ip) {
        super();
        core = new AStarCore(n);
        this.ip = ip;
        this.speed = speed;
        init();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }

    public AStarPanel(int n, int speed) {
        super();
        core = new AStarCore(n);
        init();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }

    public void init() {
        // this.setLayout(new GridLayout(core.getGrid().getSize(),
        // core.getGrid().getSize()));
        // grid = new JLabel[core.getGrid().getSize()][core.getGrid().getSize()];
        // for (int i = 0; i < core.getGrid().getSize(); i++) {
        // for (int j = 0; j < core.getGrid().getSize(); j++) {
        // grid[i][j] = new JLabel("■");
        // if (core.getGrid().getCell(i, j).isCanAccess()) {
        // grid[i][j].setForeground(Color.GREEN);

        // } else {
        // grid[i][j].setForeground(Color.BLACK);
        // }
        // this.add(grid[i][j]);
        // }
        // }
        gridpanel = new GridPanel(core.getGrid());
        this.add(gridpanel);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private void update() {
        ArrayList<Cell> parents = core.getClosedList().get(core.getClosedList().size() - 1).getGenealogy();
        for (int i = 0; i < core.getGrid().getSize(); i++) {
            for (int j = 0; j < core.getGrid().getSize(); j++) {
                Cell cell = core.getGrid().getCell(i, j);
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

    public synchronized void process() {
        core.process();
        update();
    }

    public AStarCore getCore() {
        return core;
    }

    @Override
    public void run() {
        while (!core.workFinished()) {
            if (!paused) {
                process();
                try {
                    Thread.sleep(800 - (speed * 10));
                } catch (InterruptedException e) {
                    return;
                }
                revalidate();
                repaint();
            }
        }
        // test();
    }

    public int getNumberOfCellVisited() {
        return core.getClosedListSize();
    }

    public void togglePaused() {
        paused = !paused;
    }

    public boolean isPaused() {
        return paused;
    }

    public void test() {
        ChartPanel chartPanel = new ChartPanel(core.chartManager.getHeightEvolutionChart());
        chartPanel.setPreferredSize(new Dimension(300,150));
        this.ip.add(chartPanel);
    }
}
