package gui.algorithmPanel;

import javax.swing.*;

import data.astar.Cell;
import process.astar.AStarCore;

import java.awt.*;
import java.util.ArrayList;

public class AStarPanel extends JPanel implements Runnable {
    private AStarCore core;
    private JLabel[][] grid;

    public AStarPanel(){
        super();
        core=new AStarCore(40);
        init();
        setPreferredSize(new Dimension(300,300));
        setMaximumSize(new Dimension(300,300));
    }
    public AStarPanel(int n){
        super();
        core=new AStarCore(n);
        init();
        setPreferredSize(new Dimension(300,300));
        setMaximumSize(new Dimension(300,300));
    }

    public void init(){
        this.setLayout(new GridLayout(core.getGrid().getSize(), core.getGrid().getSize()));
        grid = new JLabel[core.getGrid().getSize()][core.getGrid().getSize()];
        for (int i = 0; i < core.getGrid().getSize(); i++) {
            for (int j = 0; j < core.getGrid().getSize(); j++) {
                grid[i][j] = new JLabel("■");
                if (core.getGrid().getCell(i, j).isCanAccess()) {
                    grid[i][j].setForeground(Color.GREEN);


                } else {
                    grid[i][j].setForeground(Color.BLACK);
                }
                this.add(grid[i][j]);
            }
        }
    }

    private void update() {
        ArrayList<Cell> parents = core.getClosedList().get(core.getClosedList().size() - 1).getGenealogy();
        for (int i = 0; i < core.getGrid().getSize(); i++) {
            for (int j = 0; j < core.getGrid().getSize(); j++) {
                grid[i][j].setForeground(core.getClosedList().contains(core.getGrid().getCell(i, j)) ? (parents.contains(core.getGrid().getCell(i, j)) ? Color.blue : Color.RED) : (core.getGrid().getGrid()[i][j].isCanAccess() ? Color.GREEN : Color.BLACK));
            }
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
            process();
            
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
    }

    public int getNumberOfCellVisited(){
        return core.getClosedListSize();
    }
}
