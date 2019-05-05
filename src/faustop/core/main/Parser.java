import java.util.LinkedList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Queue;


class Parser {
    /*
     * Represents a Parser.
     * A parser is the responsible for executing the
     * sintatic analisys stage.
     *
     * Author: Paulo GS Comasetto.
     * E-mail: paulogscomasetto@gmail.com.
     * */

    private LinkedList<Token> tokenList = new LinkedList<>();
    private HashMap<String, String> instructionRules = new HashMap<>();
    private Tree parseTree;

    private String kwBI = "keywordbuiltin";
    private String kwFC = "keywordflowcontroller";
    private String kwType = "keywordtype";

    private String id = "identifier";

    private String opAritm = "operatorarithmetic";
    private String opLog = "operatorlogic";
    private String opAssign = "operatorassignment";
    private String opRel = "operatorrelational";

    private String delParOpen = "delimiterparenthesesopen";
    private String delParClose = "delimiterparenthesesclose";
    private String delCurlyOpen = "delimitercurlybracketopen";
    private String delCurlyClose = "delimitercurlybracketclose";
    private String delQuot = "delimiterquotationmark";
    private String delDoubQuot = "delimiterdoublequotationmark";
    private String delSemicolon = "delimitersemicolon";

    private String lit = "literal";

    public Parser() {

        // the first node of the parseTree will always be a instruction?
        this.parseTree = new Tree(new Token("INSTRUCTION", "", -1, -1));

        // bota um atributo bool que verifica se uma instrução foi processada.
        // começa com true e o wrapper só para de mandar Tokens quando esse
        // atributo for falso.

        //regexes ahead (they should be somehow global)



        // auxiliar regexes
        // String exp = "(" + id + "|"
        //              + lit + "|"
        //              + funcCall + "|"
        //              + opAritm + "|" + opLog + "|" + opRel
        //              + delParOpen + "|" + delParClose + ")+";
        //
        // String funcCall = kwBI + delParOpen
        //                    + "(" + exp + ")?"
        //                    + delParClose;
        //
        //
        // String dec = "^" + kwType + id + "$";
        // String init = "^" + kwType + id + opAssign + exp + "$";
        // String assign = "^" + id + opAssign + exp + "$";
        // String flow = "^" + kwFC + delParOpen + exp + delParOpen
        //                     + delCurlyOpen
        //                     + "(" + exp + "|" + dec + "|" + init + "|" + assign + ")*"
        //                     + delCurlyClose + "$"; // lacks recursion
        //
        // // actual regexes
        //
        // this.instructionRules.put(exp, "expression");
        // this.instructionRules.put(dec, "declaration");
        // this.instructionRules.put(init, "initialization");
        // this.instructionRules.put(assign, "assignment");
        // this.instructionRules.put(flow, "flowController");

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

    public boolean endOfInstruction() {
        /*
         * Checks if a final delimiter was found.
         * */

        return this.tokenList.getLast().getName().equals(";")
               || this.tokenList.getLast().getName().equals("");
        // missing conditional and loop cases
    }

    public boolean isValidInstruction() {
        /*
         * Identity type of instruction.
         * Checks if instruction follows all the instructionRules concerning to that
         * kind of instruction.
         * The Interpreter must, then, if instruction is valid, execute it;
         * else, it must throw an error. Nevertheless, the instruction
         * LinkedList must be clear afterwards.
         * */

        return false;
    }

    public void buildInstruction(Node pParent) {
        /*
         * Comments.
         * */

        Node parent = pParent;
        Token token;

        for (int currentToken = 0; currentToken < this.tokenList.size(); currentToken++) {
            token = this.tokenList.get(currentToken);
            // System.out.println(token.getName() + " " + token.getType() + " - " + token.getRow() + ":" + token.getCol());

            // current is keyword
            if (this.isKeyWord(token)) {
                // System.out.println(token.getName() + " " + token.getType() + " - " + token.getRow() + ":" + token.getCol());
                this.parseTree.addNode(token, parent);

            // previous was keyword
            } else if (currentToken > 0 &&
                   this.isKeyWord(this.tokenList.get(currentToken - 1))) {
                this.parseTree.addNode(token, parent);

            // may form an expression
            } else {
                // current is a valid start of expression
                if (this.isStartOfExpression(token)) {
                    Node expParent = new Node(new Token("EXP", "", -1, -1), parent);
                    this.parseTree.addNode(expParent.key(), parent);
                    currentToken = this.buildExpression(currentToken, expParent)-1;

                // no way of being an expression
                } else {
                    this.parseTree.addNode(token, parent);
                    
                }
            }
        }
    }

    //
    // Utility functions.
    //
    private boolean isKeyWord(Token pToken) {
        /*
         * Utility function checks of the given token is
         * a keyword type.
         * */

        if (pToken.getType().equals(this.kwBI) ||
            pToken.getType().equals(this.kwFC) ||
            pToken.getType().equals(this.kwType)) {
            return true;

        } else {
            return false;
        }
    }

    private boolean isStartOfExpression(Token pToken) {
        /*
         * Utility function checks if the given token starts an expression.
         * */
        if (pToken.getType().equals(this.id) ||
            pToken.getType().equals(this.lit) ||
            pToken.getType().equals(this.delParOpen) ||
            pToken.getType().equals(this.delQuot) ||
            pToken.getType().equals(this.delDoubQuot)) {
            return true;

        } else {
            return false;
        }
    }

    private boolean isEndOfExpression(Token pToken) {
        /*
         * Utility function checks if pToken is ends an expression.
         */

        if (pToken.getType().equals(this.id) ||
            pToken.getType().equals(this.lit) ||
            pToken.getType().equals(this.delParClose) ||
            pToken.getType().equals(this.delQuot) ||
            pToken.getType().equals(this.delDoubQuot)) {
                return true;
        } else {
            return false;
        }
    }

    private boolean isMiddleOfExpression(Token pToken) {
        /*
         * Utility function checks whether the given token represents
         *
         * */

        if (pToken.getType().equals(this.opAritm) ||
            pToken.getType().equals(this.opLog) ||
            pToken.getType().equals(this.opRel) ||
            pToken.getType().equals(this.delParOpen) ||
            pToken.getType().equals(this.delParClose) ||
            pToken.getType().equals(this.delQuot) ||
            pToken.getType().equals(this.delDoubQuot)) {
            return true;
        } else {
            return false;
        }
    }

    private int buildExpression(int pCurrentToken, Node pParent) {
        /*
         * Utility function builds a subtree for an expression.
         * Returns the index of the token where the expression
         * ends.
         * */

        Token token;
        int maxSize = this.tokenList.size();
        ArrayList<Node> children = new ArrayList<Node>();
        Queue<String> parQueue = new LinkedList<>();

        for (; pCurrentToken < maxSize; pCurrentToken++) {
            token = this.tokenList.get(pCurrentToken);

            if (token.getName().equals("(")) {
                parQueue.add("(");

            } else if (token.getName().equals(")")) {
                if (parQueue.isEmpty()) {
                    //System.out.println("AUSDHDIAHDWUOJWDIAOWJAIFJO");
                    // pCurrentToken--;
                    break;

                } else {
                    parQueue.poll();
                }
            }

            if (this.isEndOfExpression(token)) {
                if (pCurrentToken < maxSize - 1 &&
                    (this.isMiddleOfExpression(this.tokenList.get(pCurrentToken+1)))) {
                    children.add(new Node(token, pParent));

                } else {
                    children.add(new Node(token, pParent));
                    this.parseTree.addNode(children, pParent.parent());
                    return pCurrentToken + 1;
                }

            } else {
                children.add(new Node(token, pParent));
            }
        }

        this.parseTree.addNode(children, pParent.parent());
        return pCurrentToken;
    }

}
