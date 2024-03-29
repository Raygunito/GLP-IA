package data.utils;

/**
 * The Coordinate class is a public record that takes two int parameters:
 * coordinateX and coordinateY.
 */
public class Coordinate {
    int coordinateX, coordinateY;
    public Coordinate(int x, int y) {
        coordinateX = x;
        coordinateY = y;
    }
    /**
     * This method calculates the distance between two Coordinate objects. It takes
     * one parameter: cellCoordinate which is a Coordinate object. It returns a
     * double value which is the distance between the two Coordinate objects.
     * 
     * @param cellCoordinate coordinate of the cell
     * @return double distance of 2 cells using Pythagoras theorem
     */
    public double calculateDistance(Coordinate cellCoordinate) {
        return Math.sqrt(Math.pow(cellCoordinate.coordinateX - coordinateX, 2)
                + Math.pow(cellCoordinate.coordinateY - coordinateY, 2));
    }
    public int coordinateX() {
        return coordinateX;
    }
    public int coordinateY() {
        return coordinateY;
    }
}
