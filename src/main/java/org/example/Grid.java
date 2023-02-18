package main.java.org.example;

public class Grid {
    private final Cell[][] grid;

    private final Cell startingCell;
    private final Cell endingCell;

    public Grid() {
        grid = new Cell[QLearningConstant.DIMENSION][QLearningConstant.DIMENSION];
        for (int i = 0; i < QLearningConstant.DIMENSION; i++) {
            for (int j = 0; j < QLearningConstant.DIMENSION; j++) {
                if (i == QLearningConstant.DIMENSION - 1 && j == QLearningConstant.DIMENSION - 1) grid[i][j] = new Cell(new Coordinate(i, j), 1);
                else if (i == QLearningConstant.DIMENSION - 2 && j == QLearningConstant.DIMENSION - 2) grid[i][j] = new Cell(new Coordinate(i, j), -1);
                else grid[i][j] = new Cell(new Coordinate(i, j), 0);
            }
        }
        startingCell = grid[0][0];
        endingCell = grid[QLearningConstant.DIMENSION - 1][QLearningConstant.DIMENSION - 1];
    }

    public Cell move(Cell cell, int direction) {
        return switch (direction) {
            case 0 -> grid[cell.coordinate().x()][cell.coordinate().y() + 1];
            case 1 -> grid[cell.coordinate().x() + 1][cell.coordinate().y()];
            case 2 -> grid[cell.coordinate().x() - 1][cell.coordinate().y()];
            case 3 -> grid[cell.coordinate().x()][cell.coordinate().y() - 1];
            default -> throw new IllegalArgumentException();
        };
    }

    public Cell getStartingCell() {
        return startingCell;
    }

    public Cell getEndingCell() {
        return endingCell;
    }
}
