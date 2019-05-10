package faustop.core.main.util;

import java.util.ArrayList;

public class Node {
    /*
     * Simple class representing a node of a n-ary tree.
     *
     * Author: Junior Vitor Ramisch.
     * E-mail: junior.ramisch@gmail.com.
     * */

    private Token key;
    private ArrayList<Node> children;
    private Node parent;

    public Node (Token pKey, Node pParent) {
        this.key = pKey;
        this.parent = pParent;
        this.children = new ArrayList<Node>();
    }

    // getters
    public Token key() {
        return this.key;
    }

    public Node parent() {
        return this.parent;
    }

    public ArrayList<Node> children() {
        return this.children;
    }

    public void addChildren(Node pNewNode) {
        /*
         * Adds a new child to children list.
         * */

        this.children.add(pNewNode);
    }

}
