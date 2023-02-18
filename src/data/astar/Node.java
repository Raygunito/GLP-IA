package data.astar;

public class Node {
    private int[] position;
    private int value;
    private float heuristic;
    public Node(int[] pos, int value, float heuristic) {
        this.position=pos;
        this.value=value;
        this.heuristic=heuristic;
    }




    public float getHeuristic() {
        return heuristic;
    }
    public int[] getPosition() {
        return position;
    }
    public int getValue() {
        return value;
    }
}
