package main.java.org.example;

public class Grid {
    private final Cell[][] grid;
    private static final int DIM = 3;
    private final Cell startingCell;
    private final Cell endingCell;

    public Grid() {
        grid = new Cell[DIM][DIM];
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (i == DIM - 1 && j == DIM - 1) grid[i][j] = new Cell(new Coordinate(i, j), 1);
                else if (i == DIM - 2 && j == DIM - 2) grid[i][j] = new Cell(new Coordinate(i, j), -1);
                else grid[i][j] = new Cell(new Coordinate(i, j), 0);
            }
        }
        startingCell = grid[0][0];
        endingCell = grid[DIM - 1][DIM - 1];
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
