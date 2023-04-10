package data.qlearn;

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
