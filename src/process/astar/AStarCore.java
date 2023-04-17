package process.astar;


import java.util.ArrayList;

import data.astar.Cell;
import data.astar.Grid;
import gui.algorithmPanel.AStarPanel;
import gui.instrument.ChartManager;
import log.LoggerUtility;
import org.apache.log4j.Logger;

public class AStarCore {
    private Queue openList;
    private ArrayList<Cell> closedList;
    private final Grid grid;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public ChartManager chartManager = new ChartManager();
    private static Logger logger = LoggerUtility.getLogger(AStarCore.class, "text");

    public AStarCore() {
        openList = new Queue();
        closedList = new ArrayList<>();
        grid = new GridFactory().BuildGrid();
        openList.getQueue().add(grid.getStartingCell());
    }

    public AStarCore(int n) {
        openList = new Queue();
        closedList = new ArrayList<>();
        grid = new GridFactory().BuildGrid(n);
        openList.getQueue().add(grid.getStartingCell());
    }

    /**
     * Fait une étape de l'algorithme A*.
     */
    public void process() {
        Cell cell = openList.handle();
        updateOpenList(cell);
        closedList.add(cell);
    }
    public boolean isEnded() {
        return closedList.contains(grid.getEndingCell());
    }
    public boolean queueIsEmpty(){
        return openList.getQueue().isEmpty();
    }
    public boolean workFinished(){
        return isEnded()||queueIsEmpty();
    }


    /**
     *Mets à jour les valeurs des cellules filles
     * @param cell
     */
    public void updateOpenList(Cell cell) {
        for (int i = 0; i < 4; i++) {
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
                logger.warn("Did not succeed in updating the open list");
            }
            logger.debug("list has been updated");
        }
    }

    /**
     * Met à jour le cout heuristic de la cellule
     * @param cell
     */
    public void updateCosts(Cell cell) {
        cell.calculateCost();
        grid.calculateHeuristicCost(cell);
        chartManager.registerHeightByStep((int)cell.getCost());
    }

    /**
     * Permet une fois le labyrinthe résolu de montrer le chemin le plus optimal
     * @param cell
     * @return res
     */
    public String showPath(Cell cell) {
        String res = "";
        for (int k = 0; k < grid.getSize(); k++) {
            for (int m = 0; m < grid.getSize(); m++) {
                res += "|" + (closedList.contains(grid.getGrid()[k][m]) ? (cell.getGenealogy().contains(grid.getGrid()[k][m]) ? ANSI_BLUE + "2" + ANSI_RESET : ANSI_RED + "2" + ANSI_RESET) : (grid.getGrid()[k][m].isCanAccess() ? ANSI_GREEN + "0" + ANSI_RESET : 1)) + "|";
            }
            res += "\n";
        }
        logger.info("Path has been successfully shown");
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
    
    public int getClosedListSize(){
        return closedList.size();
    }

    public int getCurrentPathSize(){
        return closedList.get(closedList.size() - 1).getGenealogy().size();
    }
}
