package process.astar;

import java.util.ArrayList;

import data.astar.ACell;
import data.astar.AGrid;
import data.astar.Queue;
import gui.instrument.ChartManager;

/**
 * The AStarCore class implements the core functionality of the A* algorithm.
 * It uses a queue as the open list, and an ArrayList to store the closed list.
 */
public class AStarCore {
    private Queue openList;
    private ArrayList<ACell> closedList;
    private AGrid grid;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public ChartManager chartManager = new ChartManager();

    /**
     * The default constructor for the AStarCore class.
     * It initializes the open list queue and closed list ArrayList, and creates a
     * new grid with the GridFactory.
     * It adds the starting cell to the open list queue.
     */
    public AStarCore() {
        openList = new Queue();
        closedList = new ArrayList<>();
        grid = new GridFactory().BuildGrid();
        openList.getQueue().add(grid.getStartingCell());
    }

    /**
     * The constructor for the AStarCore class with a specified grid size.
     * It initializes the open list queue and closed list ArrayList, and creates a
     * new grid with the GridFactory with the given size.
     * It adds the starting cell to the open list queue.
     * 
     * @param n the size of the grid
     */
    public AStarCore(int n) {
        openList = new Queue();
        closedList = new ArrayList<>();
        grid = (AGrid) new GridFactory().BuildGrid(n);
        openList.getQueue().add(grid.getStartingCell());
    }

    /**
     * Performs one step of the A* algorithm by handling a cell from the open list
     * queue,
     * updating the open list with its daughters, and adding it to the closed list.
     */
    public void process() {
        ACell cell = openList.handle();
        updateOpenList(cell);
        closedList.add(cell);
    }

    /**
     * Checks if the algorithm has ended by verifying if the closed list contains
     * the ending cell.
     * 
     * @return true if the ending cell is in the closed list, false otherwise
     */
    public boolean isEnded() {
        return closedList.contains(grid.getEndingCell());
    }

    /**
     * Checks if the open list queue is empty.
     * 
     * @return true if the open list queue is empty, false otherwise
     */
    public boolean queueIsEmpty() {
        return openList.getQueue().isEmpty();
    }

    /**
     * Checks if the algorithm has finished, i.e. if it has ended or if the open
     * list queue is empty.
     * 
     * @return true if the algorithm has ended or the open list queue is empty,
     *         false otherwise
     */
    public boolean workFinished() {
        return isEnded() || queueIsEmpty();
    }

    /**
     * 
     * Updates the open list with the daughter cells of the given cell, if they are
     * not in the closed or open lists.
     * 
     * @param cell the cell to update from
     */
    public void updateOpenList(ACell cell) {
        for (int i = 0; i < 4; i++) {
            ACell cellDaughter;
            try {
                switch (i) {
                    case 0:
                        cellDaughter = grid.getUp(cell);
                        break;
                    case 1:
                        cellDaughter = grid.getDown(cell);
                        break;
                    case 2:
                        cellDaughter = grid.getRight(cell);
                        break;
                    default:
                        cellDaughter = grid.getLeft(cell);
                        break;
                }
                if (!closedList.contains(cellDaughter) && !openList.getQueue().contains(cellDaughter)) {
                    cellDaughter.setParent(cell);
                    updateCosts(cellDaughter);
                    openList.getQueue().add(cellDaughter);
                }
            } catch (CellIsWallException | IndexOutOfBoundsException ignored) {
            }
        }
    }

    /**
     * Updates the costs (cost and heuristic cost) of the given cell.
     *
     * @param cell the cell to update the costs of
     */
    public void updateCosts(ACell cell) {
        cell.calculateCost();
        grid.calculateHeuristicCost(cell);
        chartManager.registerHeuristicByStep((int) cell.getCost());
    }

    /**
     * Displays the path found by the algorithm.
     *
     * @param cell the ending cell of the path
     * @return a string representing the grid with the path highlighted
     */
    public String showPath(ACell cell) {
        String res = "";
        for (int k = 0; k < grid.getSize(); k++) {
            for (int m = 0; m < grid.getSize(); m++) {
                res += "|" + (closedList.contains(grid.getGrid()[k][m])
                        ? (cell.getGenealogy().contains(grid.getGrid()[k][m]) ? ANSI_BLUE + "2" + ANSI_RESET
                                : ANSI_RED + "2" + ANSI_RESET)
                        : (grid.getGrid()[k][m].isCanAccess() ? ANSI_GREEN + "0" + ANSI_RESET : 1)) + "|";
            }
            res += "\n";
        }
        return res;
    }

    public Queue getOpenList() {
        return openList;
    }

    public AGrid getGrid() {
        return grid;
    }

    public ArrayList<ACell> getClosedList() {
        return closedList;
    }

    public int getClosedListSize() {
        return closedList.size();
    }

    public int getCurrentPathSize() {
        return closedList.get(closedList.size() - 1).getGenealogy().size();
    }
}
