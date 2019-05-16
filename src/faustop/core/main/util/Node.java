package faustop.core.main.util;

import java.util.ArrayList;

/*
 * Simple class representing a node of a n-ary tree.
 *
 * Author: Junior Vitor Ramisch.
 * E-mail: junior.ramisch@gmail.com.
 * */
public class Node {

    private Token key;
    private ArrayList<Node> children;
    private Node parent;

    public Node (Token pKey, Node pParent) {
        this.key = pKey;
        this.parent = pParent;
        this.children = new ArrayList<Node>();
    }

    public Token key() {
        return this.key;
    }

    public Node parent() {
        return this.parent;
    }

    public ArrayList<Node> children() {
        return this.children;
    }

    /*
    * Adds a new child to children list.
    * */
    public void addChildren(Node pNewNode) {
        this.children.add(pNewNode);

    }

}
