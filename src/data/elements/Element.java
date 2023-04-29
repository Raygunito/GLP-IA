package data.elements;

import java.awt.Color;
import process.visitor.ElementVisitor;

/**
 * The Element interface represents an element in a game board.
 * It defines the behavior of game board elements, including their color and the
 * ability to accept visitors.
 */
public interface Element {
    /**
     * Accepts a visitor and returns the result of the visitor's visit.
     * 
     * @param visitor the visitor to accept
     * @param <E>     the type of the result of the visitor's visit
     * @return the result of the visitor's visit
     */
    <E> E accept(ElementVisitor<E> visitor);

    /**
     * Returns the color of the element.
     * 
     * @return the color of the element
     */
    Color getColor();
}
