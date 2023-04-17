package process.astar;


import java.util.ArrayList;
import java.util.Comparator;

import data.astar.ACell;

public class Queue {
    private final ArrayList<ACell> queue;

    public Queue(){
        queue=new ArrayList<>();
    }
    public ACell handle(){
        queue.sort(Comparator.comparingDouble(ACell::getHeuristicCost));
        ACell cell=queue.get(0);
        queue.remove(0);
        return cell;
    }

    public ArrayList<ACell> getQueue() {
        return queue;
    }
}
