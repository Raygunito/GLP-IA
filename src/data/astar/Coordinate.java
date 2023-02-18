package data.astar;

public record Coordinate(int coordinateX, int coordinateY) {

    public double calculateDistance(Coordinate cellCoordinate) {
        return Math.sqrt(Math.pow(cellCoordinate.coordinateX - coordinateX, 2) + Math.pow(cellCoordinate.coordinateY - coordinateY, 2));
    }
}
