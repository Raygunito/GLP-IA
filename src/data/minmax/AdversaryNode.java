package data.minmax;

public class AdversaryNode extends Node {

    public AdversaryNode(int value, Node leftChild, Node middleChild, Node rightChild) {
        super(1,value,leftChild,middleChild,rightChild);
    }

    /**
     * Returns the minimum of three costs: the cost of the left child, the cost of the
     * middle child, and the cost of the right child. This function is typically used
     * in the context of a MinMax algorithm to determine the minimum cost among the
     * possible moves.
     * @param leftCost the cost of the left child
     * @param middleCost the cost of the middle child
     * @param rightCost the cost of the right child
     * @return the minimum of the three costs
     */
    @Override
    public int returnCost(int leftCost, int middleCost, int rightCost) {
        return Math.min(Math.min(leftCost,middleCost),rightCost);
    }
}

