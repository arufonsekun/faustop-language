// package faustop.core.main;

import java.util.ArrayList;

class Node {
    /*
     * Simple class representing a node of a n-ary tree.
     *
     * Author: Junior Vitor Ramisch.
     * E-mail: junior.ramisch@gmail.com.
     * */

    public Token key;
    public ArrayList<Node> children;
    public Node parent;

    public Node (Token pKey, Node pParent) {
        this.key = pKey;
        this.parent = pParent;
        this.children = new ArrayList<Node>();
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

    public Node root;

    public Tree() {

    }

    public void addNode(Token pKey) {
        /*
         * Inserts a new node at the tree.
         * */

        System.out.println(pKey);

        Node p = this.getParent(); // fazerwere
        Node n = new Node(pKey, p);

        p.children.add(n);

    }

    private Node getParent() {
        return this.root;
    }

    public void traverse(Node root) {
        /*
         * Traverse through the nodes of the Tree.
         * */

        if (root == null) return;

        for (Node child : root.children) {
            this.traverse(child);
        }
        System.out.println(root.key.getName());
        // faz coisas in order
    }

}
// Reviewed by Fernando
