package data.minmax;

/**
 * The PlayerNode class extends the Node class to represent a node in a player's
 * decision tree in a game.
 * It represents a node where the player must choose the maximum cost from their
 * available options.
 */
public class PlayerNode extends Node {

    /**
     * Constructs a PlayerNode with the specified value and child nodes.
     * 
     * @param value       the value of the node
     * @param leftChild   the left child node
     * @param middleChild the middle child node
     * @param rightChild  the right child node
     */
    public PlayerNode(int value, Node leftChild, Node middleChild, Node rightChild) {
        super(-1, value, leftChild, middleChild, rightChild);
    }

    /**
     * Returns the maximum cost of the left, middle, and right child nodes.
     * 
     * @param leftCost   the cost of the left child node
     * @param middleCost the cost of the middle child node
     * @param rightCost  the cost of the right child node
     * @return the maximum cost of the child nodes
     */
    @Override
    public int returnCost(int leftCost, int middleCost, int rightCost) {
        return Math.max(Math.max(leftCost, middleCost), rightCost);
    }
}
