package process.visitor;

import data.elements.Hole;
import data.elements.Tile;
import data.elements.Trail;
import data.elements.UselessTile;
import data.elements.Wall;

public class AStarNodeVisitor implements ElementVisitor<Void>{

    @Override
    public Void visit(Wall wall) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visit(Tile tile) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visit(UselessTile uselessTile) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Void visit(Hole hole) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visit(Trail trail) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
