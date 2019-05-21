package faustop.core.main.util;

import java.util.ArrayList;

/*
 * Simple representation of a n-ary tree.
 * Used to store the Parse tree from
 * Parser stage.
 *
 * @author Jean Carlo Hilger <hilgerjeancarlo@gmail.com>
 * */

public class Tree {

    private Node root;

    public Tree(Token pKey) {

        this.root = new Node(pKey, null);

    }

    public Node root() {

        return this.root;

    }

    /*
     * Inserts a new node at the tree.
     * */
    public void addNode(Token pKey, Node pParent) {

        Node newNode = new Node(pKey, pParent);

        pParent.addChildren(newNode);

    }

    /*
     * Inserts a new node at the tree.
     * */
    public void addNode(Node newNode) {

        newNode.parent().addChildren(newNode);

    }

    /*
     * Traverse through the nodes of the Tree.
     * This code may be used as an example of how
     * traverse the tree.
     * */
    public void traverse(Node pRoot) {

        if (pRoot == null) {
            return;
        }

        for (Node child : pRoot.children()) {
            this.traverse(child);
        }

        // Do stuff
    }

}
