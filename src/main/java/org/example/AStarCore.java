package org.example;

import org.example.dataClass.Cell;
import org.example.dataClass.Grid;

import java.util.ArrayList;

public class AStarCore {
    private Queue openList;
    private ArrayList<Cell> closedList;
    private final Grid grid;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";


    public AStarCore() {
        openList = new Queue();
        closedList = new ArrayList<>();
        grid = new GridFactory().BuildGrid();
        openList.getQueue().add(grid.getStartingCell());
    }

    public void process() {
        Cell cell = openList.handle();
        updateOpenList(cell);
        closedList.add(cell);
    }

    public boolean isEnded() {
        return closedList.contains(grid.getEndingCell());
    }

    public void updateOpenList(Cell cell) {
        for (int i = 0; i < 8; i++) {
            Cell cellDaughter;
            try {
                cellDaughter = switch (i) {
                    case 0 -> grid.getUp(cell);
                    case 1 -> grid.getDown(cell);
                    case 2 -> grid.getRight(cell);
                    default -> grid.getLeft(cell);
                };
                if (!closedList.contains(cellDaughter) && !openList.getQueue().contains(cellDaughter)) {
                    cellDaughter.setParent(cell);
                    updateCosts(cellDaughter);
                    openList.getQueue().add(cellDaughter);
                }
            } catch (CellIsWallException | IndexOutOfBoundsException ignored) {
            }
        }
    }

    public void updateCosts(Cell cell) {
        cell.calculateCost();
        grid.calculateHeuristicCost(cell);
    }

    public String showPath(Cell cell) {
        String res = "";
        for (int k = 0; k < grid.getDIM(); k++) {
            for (int m = 0; m < grid.getDIM(); m++) {
                res += "|" + (closedList.contains(grid.getGrid()[k][m]) ? (cell.getGenealogy().contains(grid.getGrid()[k][m]) ? ANSI_BLUE + "2" + ANSI_RESET : ANSI_RED + "2" + ANSI_RESET) : (grid.getGrid()[k][m].isCanAccess() ? ANSI_GREEN + "0" + ANSI_RESET : 1)) + "|";
            }
            res += "\n";
        }
        return res;
    }

    public Queue getOpenList() {
        return openList;
    }

    public Grid getGrid() {
        return grid;
    }

    public ArrayList<Cell> getClosedList() {
        return closedList;
    }
}
