package data.minmax;

public class PlayerNode implements Node {
    private final int value;
    private int cost;
    private final AdversaryNode leftChild, middleChild, rightChild;

    public PlayerNode(int value, AdversaryNode leftChild, AdversaryNode middleChild, AdversaryNode rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.middleChild = middleChild;
        this.rightChild = rightChild;
    }

    @Override
    public Node getLeftChild() {
        // TODO Auto-generated method stub
        return leftChild;
    }

    @Override
    public Node getMiddleChild() {
        // TODO Auto-generated method stub
        return middleChild;
    }

    @Override
    public Node getRightChild() {
        // TODO Auto-generated method stub
        return rightChild;
    }

    @Override
    public int calculateHeuristic() {
        int leftCost, middleCost, rightCost;
        if(value==0){
            return 1;
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
        cost= Math.max(Math.max(leftCost, middleCost), rightCost);
        return cost;
    }
    public int getValue(){
        return value;
    }
}
