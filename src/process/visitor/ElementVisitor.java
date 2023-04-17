package process.visitor;

import data.elements.BestPath;
import data.elements.Hole;
import data.elements.Tile;
import data.elements.Trail;
import data.elements.UselessTile;
import data.elements.Wall;

public interface ElementVisitor<E> {
    E visit(Wall wall);

    E visit(Tile tile);

    E visit(Hole hole);

    E visit(Trail trail);

    E visit(UselessTile uselessTile);
    
    E visit(BestPath bestPath);
}
    