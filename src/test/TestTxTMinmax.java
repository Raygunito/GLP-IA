package test;

import data.minmax.Node;
import data.minmax.Tree;
import process.minmax.TreeFactory;

public class TestTxTMinmax {
    public static void main(String[] args){
        Node node=TreeFactory.buildPlayerNode(10,10);
        Tree tree=new Tree(node);
        System.out.println(tree.findMove());
    }
}
