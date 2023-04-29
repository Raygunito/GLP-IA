package process.visitor;

import data.elements.BestPath;
import data.elements.Hole;
import data.elements.Tile;
import data.elements.Trail;
import data.elements.UselessTile;
import data.elements.Wall;

public class AStarNodeVisitor implements ElementVisitor<Void>{

    @Override
    public Void visit(Wall wall) {
        return null;
    }

    @Override
    public Void visit(Tile tile) {
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

    @Override
    public Void visit(Hole hole) {
        return null;
    }

    @Override
    public Void visit(Trail trail) {
        return null;
    }
    
}
