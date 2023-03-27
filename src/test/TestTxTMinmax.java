package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import data.minmax.AdversaryNode;
import data.minmax.Node;
import data.minmax.PlayerNode;
import process.minmax.Tree;
import process.minmax.TreeFactory;

public class TestTxTMinmax {
    public static void main(String[] args) {
        Node node = TreeFactory.buildPlayerNode(10, 10);
        Tree tree = new Tree(node);
        System.out.println(tree.findMove());
    }

    @Test
    public void testFindMove() {
        PlayerNode p1 = new PlayerNode(1, null, null, null);
        PlayerNode p2 = new PlayerNode(2, null, null, null);
        PlayerNode p3 = new PlayerNode(3, null, null, null);
        PlayerNode p4 = new PlayerNode(4, null, null, null);
        PlayerNode p5 = new PlayerNode(5, null, null, null);
        PlayerNode p6 = new PlayerNode(6, null, null, null);
        PlayerNode p7 = new PlayerNode(7, null, null, null);
        PlayerNode p8 = new PlayerNode(8, null, null, null);
        PlayerNode p10 = new PlayerNode(10, null, null, null);

        AdversaryNode a1 = new AdversaryNode(11, p5, p6, p4);
        AdversaryNode a5 = new AdversaryNode(13, p1, p2, p3);
        AdversaryNode a10 = new AdversaryNode(15, p7, p10, p8);
        

        PlayerNode root = new PlayerNode(20, a10, a5, a1);
        // Best move should be 
        Tree tree = new Tree(root);
        assertEquals(13, tree.findMove());
    }

    @Test
    public void testFindMoveThreeChildren() {

        Node leftChild = new PlayerNode(3, null, null, null);
        Node middleChild = new PlayerNode(5, null, null, null);
        Node rightChild = new PlayerNode(4, null, null, null);

        Node root = new AdversaryNode(0, leftChild, middleChild, rightChild);
        Tree tree = new Tree(root);
        // the highest value child should be the middle child with value 5
        assertEquals(5, tree.findMove());
    }

    @Test
    public void testFindMoveSameValueChildren() {

        Node leftChild = new PlayerNode(2, null, null, null);
        Node middleChild = new PlayerNode(2, null, null, null);
        Node rightChild = new PlayerNode(2, null, null, null);

        Node root = new AdversaryNode(0, leftChild, middleChild, rightChild);
        Tree tree = new Tree(root);
        // any child should be valid, but the method should return only one value
        assertEquals(2, tree.findMove());
    }

    @Test
    public void testFindMoveMissingChild() {
        Node leftChild = new PlayerNode(1, null, null, null);
        Node rightChild = new PlayerNode(2, null, null, null);

        Node root = new AdversaryNode(0, leftChild, null, rightChild);
        Tree tree = new Tree(root);
        // the method should catch the NullPointerException and return -3 because it is a winning state for AdvNode
        assertEquals(-3, tree.findMove());
    }

    @Test
    public void testFindMoveOnlyOneChild() {
        Node onlychild = new AdversaryNode(2, null, null, null);
        Node child = new PlayerNode(5, onlychild, null, null);
        Tree tree = new Tree(child);
        // player node should select any node where he win i.e. middle child or right child and not the left child
        assertEquals(-3, tree.findMove());
    }

    @Test
    public void testFindMoveNoChildren() {
        Node root = new AdversaryNode(0, null, null, null);
        Tree tree = new Tree(root);
        // the method should catch the NullPointerException and return -3 because winning state
        assertEquals(-3, tree.findMove());
    }
}
