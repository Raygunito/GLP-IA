package org.example;

import org.example.dataClass.Cell;
import org.example.dataClass.Grid;

import java.util.ArrayList;
import java.util.Random;

public class GridFactory {
    private final ArrayList<ArrayList<Cell>> listOfAreas;
    private final ArrayList<Cell> walls;

    public GridFactory() {
        listOfAreas = new ArrayList<>();
        walls = new ArrayList<>();
    }

    public Grid BuildGrid() {
        Grid grid = new Grid();
        createCells(grid);
        createPath(grid);
        setStartAndEnding(grid);
        return grid;
    }

    private void createCells(Grid grid) {
        for (int i = 0; i < grid.getDIM(); i++) {
            for (int j = 0; j < grid.getDIM(); j++) {
                if (i % 2 == 1 || j % 2 == 1) {
                    grid.getGrid()[i][j] = new Cell(i, j, null, false);
                    if (i % 2 == 0 || j % 2 == 0) {
                        walls.add(grid.getGrid()[i][j]);
                    }
                } else {
                    grid.getGrid()[i][j] = new Cell(i, j, null, true);
                    listOfAreas.add(new ArrayList<>());
                    listOfAreas.get(listOfAreas.size() - 1).add(grid.getGrid()[i][j]);
                }
            }
        }
    }

    private void createPath(Grid grid) {
        Random random = new Random();
        while (!walls.isEmpty()) {
            dig(grid, random.nextInt(walls.size()));
        }
    }

    private void dig(Grid grid, int random) {
        Cell wall = walls.get(random);
        if (wall.getCoordinate().coordinateX() % 2 == 1) {
            digHorizontally(grid, wall);
        } else {
            digVertically(grid, wall);
        }
        walls.remove(wall);
    }

    private void digVertically(Grid grid, Cell wall) {
        for (ArrayList<Cell> area : listOfAreas) {
            try {
                if (areNotBothInArea(grid.getUp(wall), grid.getDown(wall), area)) {
                    ArrayList<Cell> JoiningArea = findArea(listOfAreas, grid.getDown(wall));
                    area.addAll(JoiningArea);
                    listOfAreas.remove(JoiningArea);
                    wall.setCanAccess(true);
                    break;
                }
            } catch (CellIsWallException ignored) {
            } catch (IndexOutOfBoundsException ioobe) {
                wall.setCanAccess(true);
                walls.remove(wall);
            }
        }
    }

    private void digHorizontally(Grid grid, Cell wall) {
        for (ArrayList<Cell> area : listOfAreas) {
            try {
                if (areNotBothInArea(grid.getRight(wall), grid.getLeft(wall), area)) {
                    ArrayList<Cell> joiningArea = findArea(listOfAreas, grid.getLeft(wall));
                    area.addAll(joiningArea);
                    listOfAreas.remove(joiningArea);
                    wall.setCanAccess(true);
                    break;
                }
            } catch (CellIsWallException ignored) {
            } catch (IndexOutOfBoundsException ioobe) {
                wall.setCanAccess(true);
                walls.remove(wall);
            }
        }
    }

    private boolean areNotBothInArea(Cell cell1, Cell cell2, ArrayList<Cell> area) {
        return area.contains(cell1) && !area.contains(cell2);

    }

    private void setStartAndEnding(Grid grid) {
        grid.setStartingCell(grid.getCell(0, 0));
        grid.getStartingCell().setCanAccess(true);
        grid.getStartingCell().setCost(0);
        grid.getStartingCell().setHeuristicCost(0);
        grid.setEndingCell(grid.getCell(grid.getDIM() - 1, grid.getDIM() - 1));
        grid.getEndingCell().setCanAccess(true);
    }

    private ArrayList<Cell> findArea(ArrayList<ArrayList<Cell>> listOfAreas, Cell cell) {
        for (ArrayList<Cell> area : listOfAreas) {
            if (area.contains(cell)) {
                return area;
            }
        }
        return null;
    }
}
