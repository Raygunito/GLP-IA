package data.elements;

import java.awt.Color;

import process.visitor.ElementVisitor;

public interface Element {
    <E> E accept(ElementVisitor<E> visitor);
    Color getColor();
}
