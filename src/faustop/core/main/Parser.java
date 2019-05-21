package faustop.core.main;

import faustop.core.main.util.Node;
import faustop.core.main.util.Tree;
import faustop.core.main.util.Token;
import faustop.core.main.util.Symbols;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

/*
 * Represents a Parser.
 * A parser is the responsible for executing the
 * sintatic analisys stage.
 * This class just build a parse tree, no error 
 * checking is really made here. 
 *
 * @author Jean Carlo Hilger <hilgerjeancarlo@gmail.com>
 * */
 
public class Parser {

    private LinkedList<Token> tokenList;
    private Tree parseTree;

    public Parser() {
        
        this.parseTree = new Tree(new Token("INSTRUCTION", "", -1, -1));
        this.tokenList = new LinkedList<>();
        
    }

    public Tree getParseTree() {
        
        return this.parseTree;
        
    }

    public LinkedList<Token> getTokenList() {
        
        return this.tokenList;
        
    }

    /*
     * Appends a single token to the `tokenList`
     * */
    public void addToken(Token pToken) {
        
        this.tokenList.add(pToken);
        
    }

    /*
     * Builds the parse tree (by calling utility methods).
     * */
    public void buildParseTree() {
        
        this.buildInstruction(this.parseTree.root(), 0);
        
    }

    /*
     * Utility method builds a subtree for an instruction.
     * */
    private void buildInstruction(Node pParent, int pCurrToken) {
        
        Token token;
        Node parent = pParent;
        int maxSize = this.tokenList.size();

        for (int currToken=pCurrToken; currToken < maxSize; currToken++) {
            token = this.tokenList.get(currToken);

            // current token starts a new instruction
            if (Symbols.isStartOfInstruction(token)) {

                // avoid outOfRange
                boolean smallerThanMax = currToken < maxSize - 1;
                // next is an equal (`=`) symbol
                boolean nextIsOpAssign = smallerThanMax && 
                            this.tokenList.get(currToken + 1).getType().equals(Symbols.opAssign);
                // parent is an INSTR node that starts with an keyword type.
                boolean isKwType = parent.key().getName().equals(Symbols.kwType);

                // the instruction is an assigment (with variable declaration)
                if (nextIsOpAssign && isKwType) {
                    this.parseTree.addNode(token, parent);

                } else {
                    parent = this.createInstNode(token, parent);
                }

            // current token ends the current instruction
            } else if (Symbols.isEndOfInstruction(token)) {
                parent = this.endInstNode(token, parent);
            }

            // current token starts a new expression
            if (Symbols.isStartOfExpression(token)) {
                Node expParent = this.createExpNode(token, parent);
                currToken = this.buildExpression(currToken+1, expParent) - 1;
            }

        }
    
    }

    /*
     * Builds an expression node.
     * */
    private int buildExpression(int pCurrToken, Node pParent) {
        Token token;
        int maxSize = this.tokenList.size();

        int currToken=pCurrToken;

        while (currToken < maxSize) {
            token = this.tokenList.get(currToken);

            // checks whether the expression can be expanded or not.
            if (Symbols.isEndOfExpression(token)) {
                
                // next token may expand the current expression
                if (currToken < maxSize - 1 // avoid outOfRange
                    && Symbols.isMiddleOfExpression(this.tokenList.get(currToken + 1))) {
                    this.parseTree.addNode(token, pParent); 

                // the expression is really ended
                } else {
                    this.parseTree.addNode(token, pParent);
                    return currToken + 1;
                }

            } else {
                this.parseTree.addNode(token, pParent);
            }
            
            currToken++;
        }

        return currToken + 1;
    }

    /*
     * Utility method creates and returns a new
     * instruction node (INSTR).
     * */
    private Node createInstNode(Token pToken, Node pParent) {
        
        Node insParent = new Node(new Token("INSTR", pToken.getType(), -1, -1), pParent);

        if (pToken.getType().equals(Symbols.delCurlyOpen)) {
            this.parseTree.addNode(pToken, pParent);
            this.parseTree.addNode(insParent);

        } else {
            this.parseTree.addNode(insParent);
            this.parseTree.addNode(pToken, insParent);
        }

        return insParent;
        
    }

    /*
     * Utility method returns the parent node
     * according to the current end of instruction.
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
     * Utility method creates and returns a new
     * expression node (EXP).
     * */
    private Node createExpNode(Token pToken, Node pParent) {
        
        Node expParent = new Node(new Token("EXP", "", -1, -1), pParent);

        if (pToken.getType().equals(Symbols.opAssign)) {
            this.parseTree.addNode(pToken, pParent);
            this.parseTree.addNode(expParent);

        } else {
            this.parseTree.addNode(expParent);
            this.parseTree.addNode(pToken, expParent);
        }

        return expParent;
        
    }

}
