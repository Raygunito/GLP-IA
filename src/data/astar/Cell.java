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
    
    /**
     * 
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     * @param parent The parent cell of the cell.
     * @param canAccess A boolean representing whether or not the cell can be accessed.
     */
    public Cell(int x, int y, Cell parent, boolean canAccess) {
        coordinate = new Coordinate(x, y);
        this.parent = parent;
        this.canAccess = canAccess;
        this.element = null;
    }
    /**
     * Returns the coordinate of the cell.
     * @return Coordinate of the cell
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }
    /**
     * Returns the heuristic cost of the cell.
     * @return double heuristic cost
     */
    public double getHeuristicCost() {
        return heuristicCost;
    }
    /**
     * Sets the heuristic cost of the cell.
     * @param heuristicCost new value of heuristicCost
     */
    public void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }
    /**
     * Returns a boolean indicating whether or not the cell can be accessed.
     * @return boolean true if this cell is accessible
     */
    public boolean isCanAccess() {
        return canAccess;
    }
    /**
     * Sets whether or not the cell can be accessed.
     * @param canAccess
     */
    public void setCanAccess(boolean canAccess) {
        this.canAccess = canAccess;
    }
    /**
     * Returns the parent cell of the cell.
     * @return Cell of the parent
     */
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
    /**
     * Returns an ArrayList of cells representing the genealogy of the cell.
     * @return Arraylist of all the cell that composed the path
     */
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
