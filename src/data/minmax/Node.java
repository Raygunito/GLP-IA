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
    Node getLeftChild() {
        return leftChild;
    }

    Node getMiddleChild() {
        return middleChild;
    }

    Node getRightChild() {
        return rightChild;
    }

    int getValue() {
        return value;
    }

    int calculateHeuristic() {
        if (value == 0) {
            return heuristic;
        }
        return returnCost(getChildCost(leftChild), getChildCost(middleChild), getChildCost(rightChild));
    }

    int getChildCost(Node node) {
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
