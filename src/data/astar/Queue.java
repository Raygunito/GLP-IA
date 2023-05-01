package data.astar;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A queue data structure implemented using an ArrayList.
 */
public class Queue {
    private final ArrayList<ACell> queue;

    /**
     * Constructs an empty queue.
     */
    public Queue() {
        queue = new ArrayList<>();
    }

    /**
     * Removes and returns the cell with the lowest heuristic cost from the queue.
     * @return the cell with the lowest heuristic cost
     */
    public ACell handle() {
        queue.sort(Comparator.comparingDouble(ACell::getHeuristicCost));
        ACell cell = queue.get(0);
        queue.remove(0);
        return cell;
    }

    /**
     * Returns the queue.
     * @return the queue
     */
    public ArrayList<ACell> getQueue() {
        return queue;
    }
}
