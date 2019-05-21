package faustop.core.main;

import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Queue;

import faustop.core.main.util.*;

/*
 * Represents a Parser.
 * A parser is the responsible for executing the
 * sintatic analisys stage.
 *
 * Author: Jean Carlo Hilger.
 * E-mail: hilgerjeancarlo@gmail.com.
 *
 * Author: Junior Vitor Ramisch.
 * E-mail: junior.ramisch@gmail.com.
 *
 * Author: Paulo GS Comasetto.
 * E-mail: paulogscomasetto@gmail.com.
 * */
public class Parser {
    
    private int a = 0;
    private int b = 0;

    private LinkedList<Token> tokenList = new LinkedList<>();
    private Tree parseTree;

    public Parser() {
        this.parseTree = new Tree(new Token("INSTRUCTION", "", -1, -1));
    }

    public Tree getParseTree() {
        return this.parseTree;
    }

    public LinkedList<Token> getTokenList() {
        return this.tokenList;
    }

    public void addToken(Token pToken) {
        this.tokenList.add(pToken);
    }

    /*
     * Builds the parse tree for the parser.
     * */
    public void buildParseTree() {
        this.buildInstruction(this.parseTree.root(), 0);
    }

    /*
     * Utility function builds a subtree for an instruction.
     * */
    private void buildInstruction(Node pParent, int pCurrToken) {

        Token token;
        Node parent = pParent;
        int maxSize = this.tokenList.size();

        for (int currToken=pCurrToken; currToken < maxSize; currToken++) {
            token = this.tokenList.get(currToken);

            // current token starts a new instruction
            if (Symbols.isStartOfInstruction(token)) {

                // System.out.println("aaaaaa   " + parent.key().getName());
    /////////////// BUG TODO : GAMBIARRARARARARARARRA START
                if (currToken < maxSize - 1 // avoid outOfRange
                    && this.tokenList.get(currToken + 1).getType().equals(Symbols.opAssign)
                    && parent.key().getName().equals(Symbols.kwType)) {
                    // System.out.println(token.getName());
                    // só adiciona
                    this.parseTree.addNode(token, parent);
                    // currToken++;
    /////////////// BUG TODO : GAMBARRARARRARARARRARARRARRARARI END
                } else {
                    parent = this.createInstNode(token, parent);

                }

            // current token ends the current instruction
            } else if (Symbols.isEndOfInstruction(token)) {
                parent = this.endInstNode(token, parent);
                // System.out.println(parent);
                // currToken++;

            }

            // current token starts a new expression
            if (Symbols.isStartOfExpression(token)) {
                Node expParent = this.createExpNode(token, parent);
                currToken = this.buildExpression(currToken+1, expParent) - 1;
            }

            // this.parseTree.addNode(token, parent);
        }
    }

    /*
     * Builds an expression node.
     * */
    private int buildExpression(int pCurrToken, Node pParent) {
        Token token;
        int maxSize = this.tokenList.size();

        int currToken=pCurrToken;

        for(; currToken < maxSize; currToken++) {
            token = this.tokenList.get(currToken);

            // vê se é fim mesmo ou se dá pra botar mais merda
            if (Symbols.isEndOfExpression(token)) {
                // next token may expand the current expression
                if (currToken < maxSize - 1 // avoid outOfRange
                    && Symbols.isMiddleOfExpression(this.tokenList.get(currToken + 1))) {
                    this.parseTree.addNode(token, pParent); // BUG: isso pode dar merda
                                                            // por causa dos pontero

                // the expression is really ended
                } else {
                    this.parseTree.addNode(token, pParent); // BUG: isso pode dar merda
                                                            // por causa dos pontero
                    return currToken + 1;
                }

            } else {
                this.parseTree.addNode(token, pParent); // BUG: isso pode dar merda
                                                        // por causa dos pontero
            }
        }

        return currToken + 1;
    }

    /*
     * Utility function creates and returns a new
     * instruction node (INST).
     * */
    private Node createInstNode(Token pToken, Node pParent) {
        Node insParent = new Node(new Token("INSTR", pToken.getType(), -1, -1), pParent);
        this.a++;

        if (pToken.getType().equals(Symbols.delCurlyOpen)) {
            this.parseTree.addNode(pToken, pParent);
            this.parseTree.addNode(insParent);

        } else {
            this.parseTree.addNode(insParent);
            this.parseTree.addNode(pToken, insParent);
        }

        return insParent;
    }

    /* TODO: comment
     * Utility function returns the new parent
     * for whereblavblallal.
     * */
    private Node endInstNode(Token pToken, Node pParent) {
        if (pToken.getType().equals(Symbols.delCurlyClose)) {
            this.parseTree.addNode(pToken, pParent.parent());
            return pParent.parent().parent();

        } else {
            this.parseTree.addNode(pToken, pParent);
            return pParent.parent();
        }
    }

    /*
     * Utility function creates and returns a new
     * expression node (EXP).
     * */
    private Node createExpNode(Token pToken, Node pParent) {
        Node expParent = new Node(new Token("EXP", "", -1, -1), pParent);
        this.b++;

        if (pToken.getType().equals(Symbols.opAssign)) {
            this.parseTree.addNode(pToken, pParent);
            this.parseTree.addNode(expParent); // expParent.key(), expParent.parent()

        } else {
            this.parseTree.addNode(expParent);
            this.parseTree.addNode(pToken, expParent);
        }

        return expParent;
    }

}
