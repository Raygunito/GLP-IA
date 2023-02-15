package main.java.org.example;

public class Cell {
    private final Coordinate coordinate;
    private final int reward;

    public Cell(Coordinate coordinate, int reward) {
        this.coordinate = coordinate;
        this.reward = reward;
    }
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getReward() {
        return reward;
    }
}
