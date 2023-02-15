package data.minmax;

import java.util.ArrayList;
import java.util.Comparator;

public class Tree {
    private final PlayerNode root;
    public Tree(PlayerNode root) {
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
        return 3;
    }
    return children.get(children.size()-1).getValue();
}
    
}
