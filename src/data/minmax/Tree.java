package data.minmax;

import java.util.ArrayList;
import java.util.Comparator;

public class Tree {
    private final PlayerNode root;
    public Tree(PlayerNode root) {
        this.root=root;
    }
public Node findMove(){
    ArrayList<Node> children=new ArrayList<>();
    children.add(root.getLeftChild());
    children.add(root.getMiddleChild());
    children.add(root.getRightChild());
    children.sort(Comparator.comparingInt(Node::calculateHeuristic));
    return children.get(children.size()-1);
}
    
}
