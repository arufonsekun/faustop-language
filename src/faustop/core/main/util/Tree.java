package faustop.core.main.util;

import java.util.ArrayList;

public class Tree {
    /*
     * Simple representation of a n-ary tree.
     * Used to store the Parse tree from
     * Parser stage.
     *
     * Author: Jean Carlo Hilger
     * E-mail: hilgerjeancarlo@gmail.com.
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

    public void addNode(Node newNode) {
        /*
         * Inserts a new node at the tree.
         * */

        newNode.parent().addChildren(newNode);
    }

    // public void addNode(Token pKey, Token pParentKey) {
    //     /*
    //      * Inserts a new node at the tree.
    //      * */
    //
    //     // System.out.println(pKey);
    //
    //     Node parent = this.search(pParentKey); // fazerwere
    //     Node newNode = new Node(pKey, parent);
    //
    //     parent.addChildren(newNode);
    // }

    public void addNode(ArrayList<Node> pChildren, Node pParent) {
        /*
         * Inserts a new node at the tree.
         * */

        for (Node child : pChildren) {
            pParent.addChildren(child);
        }
    }

    public void traverse(Node pRoot) {
        /*
         * Traverse through the nodes of the Tree.
         * */

        if (pRoot == null) {
            System.out.println("DASDJHSGDUGDUYSDGYJHSDBKJSBDKSADBKsd");
            return;
        }

        for (Node child : pRoot.children()) {
           
            this.traverse(child);
        }

        System.out.println(pRoot.key().getType() + " |"+pRoot.key().getName()+"|" + " -> " + pRoot.parent().key().getType());
    }

}
