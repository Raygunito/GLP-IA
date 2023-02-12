package data.minmax;

public class AdversaryNode implements Node {
    private final int value;
    private int cost;
    private final PlayerNode leftChild, middleChild, rightChild;

    public AdversaryNode(int value, PlayerNode leftChild, PlayerNode middleChild, PlayerNode rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.middleChild = middleChild;
        this.rightChild = rightChild;
    }

    @Override
    public Node getLeftChild() {
        return leftChild;
    }

    @Override
    public Node getMiddleChild() {
        return middleChild;
    }

    @Override
    public Node getRightChild() {
        return rightChild;
    }

    public int calculateHeuristic() {
        int leftCost, middleCost, rightCost;
        if (value == 0) {
            cost = 1;
            return cost;
        }
        try {
            leftCost = leftChild.calculateHeuristic();
        } catch (NullPointerException e) {
            leftCost = 0;
        }
        try {
            middleCost = middleChild.calculateHeuristic();
        } catch (NullPointerException e) {
            middleCost = 0;
        }
        try {
            rightCost = rightChild.calculateHeuristic();
        } catch (NullPointerException e) {
            rightCost = 0;
        }
        cost = Math.min(Math.min(leftCost, middleCost), rightCost);
        return cost;
    }

    public int getValue() {
        return value;
    }
}

