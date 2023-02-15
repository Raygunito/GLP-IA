package data.minmax;

public class AdversaryNode extends Node {

    public AdversaryNode(int value, Node leftChild, Node middleChild, Node rightChild) {
        super(1,value,leftChild,middleChild,rightChild);
    }

    @Override
    public int returnCost(int leftCost, int middleCost, int rightCost) {
        return Math.min(Math.min(leftCost,middleCost),rightCost);
    }
}

