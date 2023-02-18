package data.minmax;

public class PlayerNode extends Node {

    public PlayerNode(int value, Node leftChild, Node middleChild, Node rightChild) {
        super(-1,value, leftChild, middleChild, rightChild);
    }
    @Override
    public int returnCost(int leftCost, int middleCost, int rightCost) {
        return Math.max(Math.max(leftCost, middleCost), rightCost);
    }
}
