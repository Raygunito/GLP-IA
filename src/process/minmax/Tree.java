package process.minmax;

import java.util.ArrayList;
import java.util.Comparator;

import data.minmax.Node;

/**
 * This class represents a game tree.
 */
public class Tree {
    /**
     * The root node of the game tree.
     */
    private final Node root;

    /**
     * Constructs a new `Tree` object with the given root node.
     * 
     * @param root the root node of the game tree
     */
    public Tree(Node root) {
        this.root = root;
    }

    /**
     * Finds the optimal move by determining the child node with the highest
     * heuristic value.
     * 
     * @return the value of the optimal move, or -3 if an error occurs
     */
    public int findMove() {
        ArrayList<Node> children = new ArrayList<>();
        children.add(root.getLeftChild());
        children.add(root.getMiddleChild());
        children.add(root.getRightChild());
        try {
            children.sort(Comparator.comparingInt(Node::calculateHeuristic));
        } catch (NullPointerException npe) {
            return -3;
        }
        return children.get(children.size() - 1).getValue();
    }

    public int countAmountOfNode(){
        return root.count();
    }
}
