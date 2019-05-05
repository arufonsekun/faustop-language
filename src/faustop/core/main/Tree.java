// package faustop.core.main;

import java.util.ArrayList;

class Node {
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

class Tree {
    /*
     * Simple representation of a n-ary tree.
     * Used to store the Parse tree from
     * Parser stage.
     *
     * Author: Junior Vitor Ramisch.
     * E-mail: junior.ramisch@gmail.com.
     * */

    private Node root;

    public Tree(Token pKey) {
        this.root = new Node(pKey, null);
    }

    public Node root() {
        return this.root;
    }

    public void addNode(Token pKey, Node pParent) {
        /*
         * Inserts a new node at the tree.
         * */

        Node newNode = new Node(pKey, pParent);

        pParent.addChildren(newNode);
    }

    public void addNode(Token pKey, Token pParentKey) {
        /*
         * Inserts a new node at the tree.
         * */

        System.out.println(pKey);

        Node parent = this.search(pParentKey); // fazerwere
        Node newNode = new Node(pKey, parent);

        parent.addChildren(newNode);
    }

    public void addNode(ArrayList<Node> pChildren, Node pParent) {
        /*
         * Inserts a new node at the tree.
         * */

        for (Node child : pChildren) {
            pParent.addChildren(child);
        }
    }

    private Node search(Token pKey) {
        /*
         * Utility function to search for a given value.
         * If found returns a Node obj else returns null.
         * */

        if (this.root == null) return this.root;

        if (this.root.children() == null) return this.root;

        for (Node child : this.root.children()) {
            return this.search(child.key());
        }

        return null;
    }

    public void traverse(Node root) {
        /*
         * Traverse through the nodes of the Tree.
         * */

        if (root == null) return;

        for (Node child : root.children()) {
            this.traverse(child);
            System.out.println(root.key.getName());
        }
        System.out.println(root.key().getType() + " -> " + root.parent().key().getType());
        // System.out.println(root.key().getType());
        // faz coisas in order
    }

}
