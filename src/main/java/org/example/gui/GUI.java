package org.example.gui;

import org.example.AStarCore;
import org.example.dataClass.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private AStarCore core;
    private JLabel[][] grid;

    public GUI() {
        super();
        init();

    }

    private void init() {
        core = new AStarCore();
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(core.getGrid().getDIM(), core.getGrid().getDIM()));
        grid = new JLabel[core.getGrid().getDIM()][core.getGrid().getDIM()];
        for (int i = 0; i < core.getGrid().getDIM(); i++) {
            for (int j = 0; j < core.getGrid().getDIM(); j++) {
                grid[i][j] = new JLabel("■");
                if (core.getGrid().getCell(i, j).isCanAccess()) {
                    grid[i][j].setForeground(Color.GREEN);


                } else {
                    grid[i][j].setForeground(Color.BLACK);
                }
                contentPane.add(grid[i][j]);
            }
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

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