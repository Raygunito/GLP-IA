package data.minmax;

public class Test {
    public static void main(String[] args){
        Node node=TreeFactory.buildPlayerNode(10,10);
        Tree tree=new Tree(node);
        System.out.println(tree.findMove());
    }
}
