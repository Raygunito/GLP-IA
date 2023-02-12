package data.minmax;

public interface Node {
    Node getLeftChild();
    Node getMiddleChild();
    Node getRightChild();

    int calculateHeuristic();


    int getValue();
}
