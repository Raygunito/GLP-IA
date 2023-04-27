package process.qlearn;

public class GridBorderException extends Exception {
    /**
     * Exception thrown when trying to access a cell outside the borders of the
     * grid.
     */
    public GridBorderException() {
        super("Border of the map");
    }
}
