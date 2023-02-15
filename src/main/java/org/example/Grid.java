package main.java.org.example;

public class Grid {
    private final Cell[][] grid;
    private static final int DIM = 3;

    public Grid() {
        grid = new Cell[DIM][DIM];
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if(i==DIM-1 && j==DIM-1) grid[i][j]=new Cell(new Coordinate(i,j),1);
                else if(i==DIM-2 && j==DIM-2)grid[i][j]=new Cell(new Coordinate(i,j),-1);
                else grid[i][j] = new Cell(new Coordinate(i, j), 0);
            }
        }
    }
    public Cell[][] getGrid(){
        return grid;
    }
    public Cell move(Cell cell,int direction){
        return switch (direction) {
            case 0 -> grid[cell.getCoordinate().x()][cell.getCoordinate().y() + 1];
            case 1 -> grid[cell.getCoordinate().x() + 1][cell.getCoordinate().y()];
            case 2 -> grid[cell.getCoordinate().x()][cell.getCoordinate().y() - 1];
            case 3 -> grid[cell.getCoordinate().x() - 1][cell.getCoordinate().y()];
            default -> throw new IllegalArgumentException();
        };
    }
}
