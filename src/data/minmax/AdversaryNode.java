package data.minmax;

public class AdversaryNode implements Node{
    private int value;
    private PlayerNode leftNode,middleNode,rightNode;
    public AdversaryNode(int value) {
        super();
    }
    
    @Override
    public Node getLeftChild() {
        return leftNode;
    }
    @Override
    public Node getMiddleChild() {
        return middleNode;
    }
    @Override
    public Node getRightChild() {
        return rightNode;
    }
    
}

