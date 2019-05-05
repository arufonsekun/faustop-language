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
    
    public void buildParseTree() {
        /*
         * Builds the parse tree for the parser.
         * */
         
        this.buildInstruction(this.parseTree.root(), 0);
    }

    //
    // Utility functions.
    //
    
    private int buildInstruction(Node pParent, int pCurrToken) {
        /*
         * Utility function builds a subtree for an instruction.
         * */

        // Node parent = pParent;
        Token token;

        for (int currentToken = pCurrToken; currentToken < this.tokenList.size(); currentToken++) {
            token = this.tokenList.get(currentToken);

            // current is keyword
            if (this.isKeyWord(token)) {
                this.parseTree.addNode(token, pParent);

            // previous was keyword
            } else if (currentToken > 0
                       && this.isKeyWord(this.tokenList.get(currentToken - 1))) {
                this.parseTree.addNode(token, pParent);

            // may form new expression or instruction
            } else {
                // current is a valid start of expression
                if (this.isStartOfExpression(token)) {
                    Node expParent = new Node(new Token("EXP", "", -1, -1), pParent);
                    this.parseTree.addNode(expParent.key(), pParent);
                    
                    currentToken = this.buildExpression(currentToken, expParent) - 1;

                // current may be a start of instruction
                } else if (this.isStartOfInstruction(token)) {
                    this.parseTree.addNode(token, pParent);

                    Node insParent = new Node(new Token("INSTR", "", -1, -1), pParent);
                    this.parseTree.addNode(insParent);

                    currentToken = this.buildInstruction(insParent, currentToken + 1);
               
                // current token may ends an instruction
                } else if (this.isEndOfInstruction(token)) {
                    this.parseTree.addNode(token, pParent.parent());

                    return currentToken + 1;
                    
                // can not be neither an expression nor an instruction
                } else {
                    this.parseTree.addNode(token, pParent);
                }    
            }
        }
        
        return -1;
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
                if (pCurrentToken < maxSize - 1
                    && (this.isMiddleOfExpression(this.tokenList.get(pCurrentToken+1)))) {
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

    private boolean isKeyWord(Token pToken) {
        /*
         * Utility function checks of the given token is
         * a keyword type.
         * */

        if (pToken.getType().equals(this.kwBI)
            || pToken.getType().equals(this.kwFC)
            || pToken.getType().equals(this.kwType)) {
            return true;
        }
        
        return false;
    }

    private boolean isStartOfExpression(Token pToken) {
        /*
         * Utility function checks if the given token starts an expression.
         * */
         
        if (pToken.getType().equals(this.id)
            || pToken.getType().equals(this.lit)
            || pToken.getType().equals(this.delParOpen)
            || pToken.getType().equals(this.delQuot)
            || pToken.getType().equals(this.delDoubQuot)) {
            return true;
        }
        
        return false;
    }
    
    private boolean isMiddleOfExpression(Token pToken) {
        /*
         * Utility function checks whether the given token represents
         * */

        if (pToken.getType().equals(this.opAritm)
            || pToken.getType().equals(this.opLog)
            || pToken.getType().equals(this.opRel)
            || pToken.getType().equals(this.delParOpen)
            || pToken.getType().equals(this.delParClose)
            || pToken.getType().equals(this.delQuot)
            || pToken.getType().equals(this.delDoubQuot)) {
            return true;
        }
        
        return false;
    }

    private boolean isEndOfExpression(Token pToken) {
        /*
         * Utility function checks if pToken is ends an expression.
         */

        if (pToken.getType().equals(this.id)
            || pToken.getType().equals(this.lit)
            || pToken.getType().equals(this.delParClose)
            || pToken.getType().equals(this.delQuot)
            || pToken.getType().equals(this.delDoubQuot)) {
            return true;
        }
        
        return false;
    }

    private boolean isStartOfInstruction(Token pToken) {
        /*
        * Utility function checks if given token is a 'start'
        * of instruction.
        * Actually, checks if the next tokens are a new instruction.
        * */
        
        if (pToken.getType().equals(this.delCurlyOpen)) {
            return true;
        }
        
        return false;
    }
    
    private boolean isEndOfInstruction(Token pToken) {
        /*
        * Utility function checks if given token is a 'end'
        * of instruction.
        * Actually, checks if the previous token is the last of
        * a instruction.
        * */
        
        if (pToken.getType().equals(this.delCurlyOpen)
            || pToken.getType().equals(this.delCurlyClose)){
            // || pToken.getType().equals(this.delSemicolon)) {
            // System.out.println("\tisEndOfInstruction:::::: " + pToken.getType());
            return true;
        }
        
        return false;
    }

}
