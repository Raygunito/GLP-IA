package data.astar;

import java.util.ArrayList;

import data.elements.Element;

public class Cell {
    private final Coordinate coordinate;
    private Cell parent;
    private boolean canAccess;
    private double heuristicCost;
    private int cost;
    private Element element;

    public Cell(int x, int y, Cell parent, boolean canAccess) {
        coordinate = new Coordinate(x, y);
        this.parent = parent;
        this.canAccess = canAccess;
        this.element = null;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public double getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public boolean isCanAccess() {
        return canAccess;
    }

    public void setCanAccess(boolean canAccess) {
        this.canAccess = canAccess;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public void calculateCost() {
        cost = parent.getCost() + 1;
    }

    public ArrayList<Cell> getGenealogy() {
        ArrayList<Cell> genealogy = new ArrayList<>();
        Cell parent = this;
        genealogy.add(this);
        while (parent.getParent() != null) {
            parent = parent.getParent();
            genealogy.add(parent);
        }
        return genealogy;
    }
}
