package data.minmax;

public abstract class Node {
    private final int heuristic, value;
    private final Node leftChild, middleChild, rightChild;

    public Node(int heuristic, int value, Node leftChild, Node middleChild, Node rightChild) {
        this.heuristic = heuristic;
        this.value = value;
        this.leftChild = leftChild;
        this.middleChild = middleChild;
        this.rightChild = rightChild;
    }
    public static Node initNode(){
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

    public int getValue() {
        return value;
    }

    public int calculateHeuristic() {
        if (value == 0) {
            return heuristic;
        }
        return returnCost(getChildCost(leftChild), getChildCost(middleChild), getChildCost(rightChild));
    }

    public int getChildCost(Node node) {
        int cost;
        try {
            cost = node.calculateHeuristic();
        } catch (NullPointerException e) {
            cost = 0;
        }
        return cost;
    }

    public abstract int returnCost(int leftCost, int middleCost, int rightCost);
}
