package test;

import data.astar.Grid;
import data.elements.Element;
import process.astar.GridFactory;
import process.visitor.DrawingVisitor;

public class TestVisitor {
    public static void main(String[] args) {
        GridFactory gridFactory = new GridFactory();
        Grid grid = gridFactory.BuildGrid(3);
        DrawingVisitor drawingVisitor = new DrawingVisitor(null, null,10);
        for (int i = 0; i < grid.getSize(); i++) {
            for (int j = 0; j < grid.getSize(); j++) {
                Element e = grid.getCell(i, j).getElement();
                e.accept(drawingVisitor);
            }
        }
    }
}
