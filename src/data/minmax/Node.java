package data.minmax;

/**
 * An abstract class that represents a node in a game tree for the MinMax
 * algorithm.
 */
public abstract class Node {
    private final int heuristic, value;
    private final Node leftChild, middleChild, rightChild;

    /**
     * Constructs a new node with the specified heuristic, value, and child nodes.
     * 
     * @param heuristic   the heuristic value of the node.
     * @param value       the value of the node.
     * @param leftChild   the left child node.
     * @param middleChild the middle child node.
     * @param rightChild  the right child node.
     */
    public Node(int heuristic, int value, Node leftChild, Node middleChild, Node rightChild) {
        this.heuristic = heuristic;
        this.value = value;
        this.leftChild = leftChild;
        this.middleChild = middleChild;
        this.rightChild = rightChild;
    }

    public static Node initNode() {
        return null;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getMiddleChild() {
        return middleChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Returns the value of the node.
     * 
     * @return the value of the node
     */
    public int getValue() {
        return value;
    }

    /**
     * Calculates the heuristic value for this node. We want to know if the value is
     * a multiple of 4 in the case of it being a leaf cause if it's the case we know
     * that the program will win.
     * 
     * @return the heuristic value for this node
     */
    public int calculateHeuristic() {
        if (value == 0) {
            return heuristic;
        }
        if (isLeaf()) {
            if (value % 4 == 0) {
                return heuristic;
            } else {
                return 0;
            }
        }
        return returnCost(getChildCost(leftChild), getChildCost(middleChild), getChildCost(rightChild));
    }

    /**
     * Returns the cost of a given child node
     * 
     * @param node the child node whose cost is to be calculated
     * @return an integer representing the estimated cost from the iven node to the
     *         goal node.
     */
    public int getChildCost(Node node) {
        int cost;
        try {
            cost = node.calculateHeuristic();
        } catch (NullPointerException e) {
            cost = 0;
        }
        return cost;
    }

    /**
     * Checks if the node is a leaf node (i.e. has no children).
     * 
     * @return true if the node is a leaf node, false otherwise.
     */
    public boolean isLeaf() {
        return leftChild == null && middleChild == null && rightChild == null;
    }

    /**
     * 
     * Returns the cost of this node based on the cost of its child nodes.
     * 
     * @param leftCost   the cost of the left child node
     * @param middleCost the cost of the middle child node
     * @param rightCost  the cost of the right child node
     * @return an integer representing the estimated cost from this node to the goal
     *         node, based on the costs of its child nodes.
     */
    public abstract int returnCost(int leftCost, int middleCost, int rightCost);

    public int count() {
        int count = 1;
        if (leftChild != null) {
            count += leftChild.count();
        }
        if (middleChild != null) {
            count += middleChild.count();
        }
        if (rightChild != null) {
            count += rightChild.count();
        }
        return count;
    }
}
