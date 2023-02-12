package data.minmax;

public class TreeFactory{
    public static PlayerNode buildTree(int baseValue,int depth){
        AdversaryNode leftChild= buildAdversaryNode(baseValue-1,depth-1);
        AdversaryNode middleChild= buildAdversaryNode(baseValue-2,depth-1);
        AdversaryNode rightChild= buildAdversaryNode(baseValue-3,depth-1);
        return new PlayerNode(baseValue,leftChild,middleChild,rightChild);
    }
    public static AdversaryNode buildAdversaryNode(int baseValue, int depth){
        if(depth>=0 && baseValue>=0){
            PlayerNode leftChild=buildPlayerNode(baseValue-1,depth-1);
            PlayerNode middleChild=buildPlayerNode(baseValue-2,depth-1);
            PlayerNode rightChild=buildPlayerNode(baseValue-3,depth-1);
            return new AdversaryNode(baseValue,leftChild,middleChild,rightChild);
        }
        return null;
    }
    public static PlayerNode buildPlayerNode(int baseValue, int depth){
        if(depth>=0 && baseValue>=0){
            AdversaryNode leftChild=buildAdversaryNode(baseValue-1,depth-1);
            AdversaryNode middleChild=buildAdversaryNode(baseValue-2,depth-1);
            AdversaryNode rightChild=buildAdversaryNode(baseValue-3,depth-1);
            return new PlayerNode(baseValue,leftChild,middleChild,rightChild);
        }
        return null;
    }
}