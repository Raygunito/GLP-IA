package process.minmax;

import data.minmax.AdversaryNode;
import data.minmax.Node;
import data.minmax.PlayerNode;

/**
 * The TreeFactory class provides methods to build a game tree for a two-player
 * game.
 */
public class TreeFactory {

    /**
     * Builds an AdversaryNode object representing a node in a game tree for a
     * two-player game where the current player is the adversary.
     * 
     * @param baseValue the base value of the node
     * @param depth     the depth of the node in the game tree
     * @return the AdversaryNode object representing the node
     */
    public static Node buildAdversaryNode(int baseValue, int depth) {
        if (depth >= 0 && baseValue >= 0) {
            Node leftChild = buildPlayerNode(baseValue - 1, depth - 1);
            Node middleChild = buildPlayerNode(baseValue - 2, depth - 1);
            Node rightChild = buildPlayerNode(baseValue - 3, depth - 1);
            return new AdversaryNode(baseValue, leftChild, middleChild, rightChild);
        }
        return Node.initNode();
    }

    /**
     * 
     * Builds a PlayerNode object representing a node in a game tree for a
     * two-player game where the current player is the player.
     * 
     * @param baseValue the base value of the node
     * @param depth     the depth of the node in the game tree
     * @return the PlayerNode object representing the node
     */
    public static Node buildPlayerNode(int baseValue, int depth) {
        if (depth >= 0 && baseValue >= 0) {
            Node leftChild = buildAdversaryNode(baseValue - 1, depth - 1);
            Node middleChild = buildAdversaryNode(baseValue - 2, depth - 1);
            Node rightChild = buildAdversaryNode(baseValue - 3, depth - 1);
            return new PlayerNode(baseValue, leftChild, middleChild, rightChild);
        }
        return Node.initNode();
    }
}