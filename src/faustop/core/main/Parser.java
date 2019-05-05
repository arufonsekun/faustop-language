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

    private LinkedList<Token> instruction = new LinkedList<>();
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

    public LinkedList<Token> getInstruction() {
        return this.instruction;
    }

    public void addToken(Token pToken) {
        this.instruction.add(pToken);
    }

    public boolean endOfInstruction() {
        /*
         * Checks if a final delimiter was found.
         * */

        return this.instruction.getLast().getName().equals(";")
               || this.instruction.getLast().getName().equals("");
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

    public void buildParseTree() {
        /*
         * Comments.
         * */

        // LinkedList<Token> instructions = this.getInstruction();
        Node parent = this.parseTree.root();
        Token token;
        int currentToken = 0;
        int startOfExp = -1;

        for (; currentToken < this.instruction.size(); currentToken++) {
            token = this.instruction.get(currentToken);
            // System.out.println(token.getName() + " " + token.getType() + " - " + token.getRow() + ":" + token.getCol());

            // current is keyword
            if (this.isKeyWord(token)) {
                // System.out.println(token.getName() + " " + token.getType() + " - " + token.getRow() + ":" + token.getCol());
                this.parseTree.addNode(token, parent);

            // previous was keyword
            } else if (currentToken > 0 &&
                   this.isKeyWord(this.instruction.get(currentToken - 1))) {
                this.parseTree.addNode(token, parent);

            // current is '=' sing
            } else if (token.getType().equals(this.opAssign)) {
                this.parseTree.addNode(token, parent);

            // may form an expression
            } else {
                // current is a valid start of expression
                if (token.getType().equals(this.id) ||
                    token.getType().equals(this.lit) ||
                    token.getType().equals(this.delParOpen) ||
                    token.getType().equals(this.delQuot) ||
                    token.getType().equals(this.delDoubQuot)) {
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

    private int buildExpression(int pCurrentToken, Node pParent) {
        /*
         * Utility function builds a subtree for an expression.
         * Returns the index of the token where the expression
         * ends.
         * */

        Token token;
        int maxSize = this.instruction.size();
        ArrayList<Node> children = new ArrayList<Node>();
        Queue<String> parQueue = new LinkedList<>();

        for (; pCurrentToken < maxSize; pCurrentToken++) {
            token = this.instruction.get(pCurrentToken);

            if (token.getName().equals("(")) {
                parQueue.add("(");

            } else if (token.getName().equals(")")) {
                if (parQueue.isEmpty()) {
                    System.out.println("AUSDHDIAHDWUOJWDIAOWJAIFJO");
                    // pCurrentToken--;
                    break;

                } else {
                    parQueue.poll();
                }
            }

            if (token.getType().equals(this.id) ||
                token.getType().equals(this.lit) ||
                token.getType().equals(this.delParClose) ||
                token.getType().equals(this.delQuot) ||
                token.getType().equals(this.delDoubQuot)) {
                if (pCurrentToken < maxSize - 1 &&
                    (this.instruction.get(pCurrentToken+1).getType().equals(this.opAritm) ||
                     this.instruction.get(pCurrentToken+1).getType().equals(this.opLog) ||
                     this.instruction.get(pCurrentToken+1).getType().equals(this.opRel) ||
                     this.instruction.get(pCurrentToken+1).getType().equals(this.delParOpen) ||
                     this.instruction.get(pCurrentToken+1).getType().equals(this.delParClose) ||
                     this.instruction.get(pCurrentToken+1).getType().equals(this.delQuot) ||
                     this.instruction.get(pCurrentToken+1).getType().equals(this.delDoubQuot))) {
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
