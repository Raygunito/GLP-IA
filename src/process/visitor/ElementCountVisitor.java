package process.visitor;

import data.elements.BestPath;
import data.elements.Element;
import data.elements.Hole;
import data.elements.Tile;
import data.elements.Trail;
import data.elements.UselessTile;
import data.elements.Wall;
import gui.instrument.ChartManager;

public class ElementCountVisitor implements ElementVisitor<Void>{

    private ChartManager chartManager;

    public ElementCountVisitor(ChartManager chartManager) {
        this.chartManager = chartManager;
    }

    public void visit(Element element){
        if (element instanceof Hole){
            visit((Hole) element);
        }
        if (element instanceof Tile) {
            visit((Tile) element);
            
        }
    }

    @Override
    public Void visit(Wall wall) {
        return null;
    }
    
    @Override
    public Void visit(Tile tile) {
        chartManager.countType("Tile");
        return null;
    }
    
    @Override
    public Void visit(Hole hole) {
        chartManager.countType("Hole");
        return null;
    }

    @Override
    public Void visit(Trail trail) {
        return null;
    }

    @Override
    public Void visit(UselessTile uselessTile) {
        return null;
    }

    @Override
    public Void visit(BestPath bestPath) {
        return null;
    }


}
