package process.minmax;

import java.util.ArrayList;
import java.util.Comparator;

import data.minmax.Node;

public class Tree {
    private final Node root;
    public Tree(Node root) {
        this.root=root;
    }
public int findMove(){
    ArrayList<Node> children=new ArrayList<>();
    children.add(root.getLeftChild());
    children.add(root.getMiddleChild());
    children.add(root.getRightChild());
    try {
        children.sort(Comparator.comparingInt(Node::calculateHeuristic));
    }catch(NullPointerException npe){
        return -3;
    }
    return children.get(children.size()-1).getValue();
}
    
}
