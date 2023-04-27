package data.qlearn;


/**
 * This enum makes up for all the direction used by QLearn (can be extended to AStar)
 */
public enum Direction {
    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3);

    private final int value;

    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Return the opposite direction
     * @param dir the direction whose opposite direction is to be returned.
     * @return the opposite direction of the given direction.
     */
    public static Direction getOpposiDirection(Direction dir) {
        switch (dir) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            default:
                return LEFT;
        }
    }

    public static Direction getDirectionFromValue(int value) throws IllegalArgumentException{
        for (Direction dir : Direction.values()) {
            if (dir.getValue() == value) {
                return dir;
            }
        }
        throw new IllegalArgumentException("Invalid direction value: " + value);
    }
}
