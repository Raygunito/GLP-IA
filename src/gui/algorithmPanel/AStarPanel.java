package gui.algorithmPanel;

import javax.swing.*;

import data.astar.Cell;
import process.astar.AStarCore;

import java.awt.*;
import java.util.ArrayList;

public class AStarPanel extends JPanel {
    private AStarCore core;
    private JLabel[][] grid;
    public AStarPanel(){
        super();
        init();
    }
    public void init(){
        core=new AStarCore();
        this.setLayout(new GridLayout(core.getGrid().getDIM(), core.getGrid().getDIM()));
        grid = new JLabel[core.getGrid().getDIM()][core.getGrid().getDIM()];
        for (int i = 0; i < core.getGrid().getDIM(); i++) {
            for (int j = 0; j < core.getGrid().getDIM(); j++) {
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
        for (int i = 0; i < core.getGrid().getDIM(); i++) {
            for (int j = 0; j < core.getGrid().getDIM(); j++) {
                grid[i][j].setForeground(core.getClosedList().contains(core.getGrid().getCell(i, j)) ? (parents.contains(core.getGrid().getCell(i, j)) ? Color.blue : Color.RED) : (core.getGrid().getGrid()[i][j].isCanAccess() ? Color.GREEN : Color.BLACK));
            }
        }
    }
    public void process() {
        core.process();
        update();
    }

    public AStarCore getCore() {
        return core;
    }
}
