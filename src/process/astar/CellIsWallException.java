package process.astar;

/**
 * 
 * An exception to be thrown when a cell is detected as a wall.
 * 
 * This exception extends the built-in Java Exception class.
 */
public class CellIsWallException extends Exception {

    /**
     * Constructs a new CellIsWallException object with no message.
     */
    public CellIsWallException() {
        super();
    }
}
