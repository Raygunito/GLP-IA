package process.minmax;

import data.minmax.AdversaryNode;
import data.minmax.Node;
import data.minmax.PlayerNode;

public class TreeFactory{
    public static Node buildAdversaryNode(int baseValue, int depth){
        if(depth>=0 && baseValue>=0){
            Node leftChild=buildPlayerNode(baseValue-1,depth-1);
            Node middleChild=buildPlayerNode(baseValue-2,depth-1);
            Node rightChild=buildPlayerNode(baseValue-3,depth-1);
            return new AdversaryNode(baseValue,leftChild,middleChild,rightChild);
        }
        return Node.initNode();
    }
    public static Node buildPlayerNode(int baseValue, int depth){
        if(depth>=0 && baseValue>=0){
            Node leftChild=buildAdversaryNode(baseValue-1,depth-1);
            Node middleChild=buildAdversaryNode(baseValue-2,depth-1);
            Node rightChild=buildAdversaryNode(baseValue-3,depth-1);
            return new PlayerNode(baseValue,leftChild,middleChild,rightChild);
        }
        return Node.initNode();
    }
}