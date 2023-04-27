package process.astar;

import java.util.ArrayList;
import java.util.Random;

import data.abstracts.AbstractGrid;
import data.astar.ACell;
import data.astar.AGrid;
import data.elements.Tile;
import data.elements.Wall;

/**
 * The GridFactory class is responsible for creating a grid for the A*
 * algorithm. It creates the cells for the grid,
 * randomly creates paths through the grid, sets the start and end points of the
 * grid, and returns the completed grid.
 * <p>
 * This class contains methods for creating cells, creating paths, setting start
 * and ending points, and finding areas
 * within the grid.
 */
public class GridFactory {
    /**
     * List of areas of cells within the grid that are accessible to each other.
     */
    private final ArrayList<ArrayList<ACell>> listOfAreas;
    /**
     * List of walls within the grid.
     */
    private final ArrayList<ACell> walls;

    /**
     * Constructs a new GridFactory object with empty lists for listOfAreas and
     * walls.
     */
    public GridFactory() {
        listOfAreas = new ArrayList<>();
        walls = new ArrayList<>();
    }

    /**
     * Creates a new AGrid object with a size of 10 and returns it after setting its
     * cells, paths, start and ending points.
     *
     * @return A new AGrid object with a size of 10.
     */
    public AGrid BuildGrid() {
        AGrid grid = new AGrid();
        createCells(grid);
        createPath(grid);
        setStartAndEnding(grid);
        return grid;
    }

    /**
     * Creates a new AGrid object with the specified size and returns it after
     * setting its cells, paths, start and ending points.
     *
     * @param n The size of the grid to be created.
     * @return A new AGrid object with the specified size.
     */
    public AbstractGrid BuildGrid(int n) {
        AGrid grid = new AGrid(n);
        createCells(grid);
        createPath(grid);
        setStartAndEnding(grid);
        return grid;
    }

    /**
     * Creates the cells for the grid and adds them to the provided AGrid object.
     *
     * @param grid The AGrid object to which the cells will be added.
     */
    private void createCells(AGrid grid) {
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                if (i % 2 == 1 || j % 2 == 1) {
                    ACell aCell = new ACell(i, j, null, false);
                    aCell.setElement(new Wall(aCell.getCoordinate()));
                    grid.setCell(i, j, aCell);
                    if (i % 2 == 0 || j % 2 == 0) {
                        walls.add(aCell);
                    }
                } else {
                    ACell aCell = new ACell(i, j, null, true);
                    aCell.setElement(new Tile(aCell.getCoordinate()));
                    grid.setCell(i, j, aCell);
                    listOfAreas.add(new ArrayList<>());
                    listOfAreas.get(listOfAreas.size() - 1).add(aCell);
                }
            }
        }
    }

    /**
     * Randomly creates paths through the grid by removing walls between areas.
     *
     * @param grid The AGrid object through which the paths will be created.
     */
    private void createPath(AGrid grid) {
        Random random = new Random();
        while (!walls.isEmpty()) {
            dig(grid, random.nextInt(walls.size()));
        }
    }

    /**
     * Removes a wall from the walls list and joins two areas if they are not
     * already joined.
     *
     * @param grid   The AGrid object through which the paths will be created.
     * @param random An integer representing the index of the wall to be removed.
     */
    private void dig(AGrid grid, int random) {
        ACell wall = walls.get(random);
        if (wall.getCoordinate().coordinateX() % 2 == 1) {
            digHorizontally(grid, wall);
        } else {
            digVertically(grid, wall);
        }
        walls.remove(wall);
    }

    /**
     * Joins two areas by removing a wall vertically.
     *
     * @param grid The AGrid object through which the paths will be created.
     * @param wall The wall to be removed.
     */
    private void digVertically(AGrid grid, ACell wall) {
        for (ArrayList<ACell> area : listOfAreas) {
            try {
                if (areNotBothInArea(grid.getUp(wall), grid.getDown(wall), area)) {
                    ArrayList<ACell> JoiningArea = findArea(listOfAreas, grid.getDown(wall));
                    area.addAll(JoiningArea);
                    listOfAreas.remove(JoiningArea);
                    wall.setCanAccess(true);
                    wall.setElement(new Tile(wall.getCoordinate()));
                    break;
                }
            } catch (CellIsWallException ignored) {
            } catch (IndexOutOfBoundsException ioobe) {
                wall.setCanAccess(true);
                wall.setElement(new Tile(wall.getCoordinate()));
                walls.remove(wall);
            }
        }
    }

    /**
     * Joins two areas by removing a wall horizontally.
     *
     * @param grid The AGrid object through which the paths will be created.
     * @param wall The wall to be removed.
     */
    private void digHorizontally(AGrid grid, ACell wall) {
        for (ArrayList<ACell> area : listOfAreas) {
            try {
                if (areNotBothInArea(grid.getRight(wall), grid.getLeft(wall), area)) {
                    ArrayList<ACell> joiningArea = findArea(listOfAreas, grid.getLeft(wall));
                    area.addAll(joiningArea);
                    listOfAreas.remove(joiningArea);
                    wall.setCanAccess(true);
                    wall.setElement(new Tile(wall.getCoordinate()));
                    break;
                }
            } catch (CellIsWallException ignored) {
            } catch (IndexOutOfBoundsException ioobe) {
                wall.setCanAccess(true);
                wall.setElement(new Tile(wall.getCoordinate()));
                walls.remove(wall);
            }
        }
    }

    /**
     * Determines if cell1 and cell2 are in the same area.
     *
     * @param cell1 The first cell to be compared.
     * @param cell2 The second cell to be compared.
     * @param area  The area in which cell1 and cell2 will be checked for.
     */
    private boolean areNotBothInArea(ACell cell1, ACell cell2, ArrayList<ACell> area) {
        return area.contains(cell1) && !area.contains(cell2);

    }

    /**
     * 
     * Sets the starting and ending cells of a given grid.
     * <p>
     * The starting cell is set to the cell at (0,0) position, and its "canAccess",
     * "cost" and "heuristicCost" attributes are set to true, 0 and 0 respectively.
     * <p>
     * The ending cell is set to the cell at (size-1,size-1) position, and its
     * "canAccess" attribute is set to true.
     * 
     * @param grid The grid object whose starting and ending cells are to be set.
     */
    private void setStartAndEnding(AGrid grid) {
        grid.setStartingCell(grid.getCell(0, 0));
        grid.getStartingCell().setCanAccess(true);
        grid.getStartingCell().setCost(0);
        grid.getStartingCell().setHeuristicCost(0);
        grid.setEndingCell(grid.getCell(grid.getSize() - 1, grid.getSize() - 1));
        grid.getEndingCell().setCanAccess(true);
    }

    private ArrayList<ACell> findArea(ArrayList<ArrayList<ACell>> listOfAreas, ACell cell) {
        for (ArrayList<ACell> area : listOfAreas) {
            if (area.contains(cell)) {
                return area;
            }
        }
        return null;
    }
}
