package data.astar;

import java.util.ArrayList;

import data.abstracts.AbstractCell;
import data.elements.Element;
import data.elements.Tile;
import data.utils.Coordinate;

public class ACell extends AbstractCell{
    private ACell parent;
    private boolean canAccess;
    private double heuristicCost;
    private int cost;
    
    /**
     * 
     * @param x The x coordinate of the cell.
     * @param y The y coordinate of the cell.
     * @param parent The parent cell of the cell.
     * @param canAccess A boolean representing whether or not the cell can be accessed.
     */
    public ACell(int x, int y, ACell parent, boolean canAccess) {
        super(new Coordinate(x, y), new Tile(new Coordinate(x, y)));
        this.parent = parent;
        this.canAccess = canAccess;
    }
    /**
     * Returns the coordinate of the cell.
     * @return Coordinate of the cell
     */
    public Coordinate getCoordinate() {
        return super.getCoordinate();
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
    public ACell getParent() {
        return parent;
    }

    public void setParent(ACell parent) {
        this.parent = parent;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Element getElement() {
        return super.getElement();
    }

    public void setElement(Element element) {
        super.setElement(element);
    }

    public void calculateCost() {
        cost = parent.getCost() + 1;
    }
    /**
     * Returns an ArrayList of cells representing the genealogy of the cell.
     * @return Arraylist of all the cell that composed the path
     */
    public ArrayList<ACell> getGenealogy() {
        ArrayList<ACell> genealogy = new ArrayList<>();
        ACell parent = this;
        genealogy.add(this);
        while (parent.getParent() != null) {
            parent = parent.getParent();
            genealogy.add(parent);
        }
        return genealogy;
    }

    @Override
    public String toString() {
        return "["+super.getCoordinate().coordinateX()+","+super.getCoordinate().coordinateY()+"]";
    }
}
