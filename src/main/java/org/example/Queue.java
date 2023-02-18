package org.example;

import org.example.dataClass.Cell;

import java.util.ArrayList;
import java.util.Comparator;

public class Queue {
    private final ArrayList<Cell> queue;

    public Queue(){
        queue=new ArrayList<>();
    }
    public Cell handle(){
        queue.sort(Comparator.comparingDouble(Cell::getHeuristicCost));
        Cell cell=queue.get(0);
        queue.remove(0);
        return cell;
    }

    public ArrayList<Cell> getQueue() {
        return queue;
    }
}
