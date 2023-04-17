package process.astar;


import java.util.ArrayList;
import java.util.Random;

import data.abstracts.AbstractGrid;
import data.astar.ACell;
import data.astar.AGrid;
import data.elements.Tile;
import data.elements.Wall;
import data.utils.Coordinate;

public class GridFactory {
    private final ArrayList<ArrayList<ACell>> listOfAreas;
    private final ArrayList<ACell> walls;

    public GridFactory() {
        listOfAreas = new ArrayList<>();
        walls = new ArrayList<>();
    }

    public AGrid BuildGrid() {
        AGrid grid = new AGrid();
        createCells(grid);
        createPath(grid);
        setStartAndEnding(grid);
        return grid;
    }
    public AbstractGrid BuildGrid(int n) {
        AGrid grid = new AGrid(n);
        createCells(grid);
        createPath(grid);
        setStartAndEnding(grid);
        return grid;
    }

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

    private void createPath(AGrid grid) {
        Random random = new Random();
        while (!walls.isEmpty()) {
            dig(grid, random.nextInt(walls.size()));
        }
    }

    private void dig(AGrid grid, int random) {
        ACell wall = walls.get(random);
        if (wall.getCoordinate().coordinateX() % 2 == 1) {
            digHorizontally(grid, wall);
        } else {
            digVertically(grid, wall);
        }
        walls.remove(wall);
    }

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

    private boolean areNotBothInArea(ACell cell1, ACell cell2, ArrayList<ACell> area) {
        return area.contains(cell1) && !area.contains(cell2);

    }   

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
