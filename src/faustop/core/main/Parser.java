import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Queue;


class Parser {
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

    // TODO: Improve this method. To large and with gambiarra
    private int buildInstruction(Node pParent, int pCurrToken) {
        /*
         * Utility function builds a subtree for an instruction.
         * */

        Token token;

        for (int currentToken = pCurrToken; currentToken < this.tokenList.size(); currentToken++) {
            if (currentToken < 0) break;
            token = this.tokenList.get(currentToken);

            // current is keyword
            if (Symbols.isKeyWord(token)
                || (currentToken > 0
                	&& this.tokenList.get(currentToken - 1).getType().equals(this.delCurlyOpen))) {
                Node insParent = new Node(new Token("INSTR", "", -1, -1), pParent);

                this.parseTree.addNode(insParent);

                if (Symbols.isStartOfExpression(token)
                    && !this.tokenList.get(currentToken + 1).getType().equals(this.opAssign)) {
                    Node expParent = new Node(new Token("EXP", "", -1, -1), insParent);

                    this.parseTree.addNode(expParent.key(), insParent);
                    currentToken = this.buildExpression(currentToken, expParent) - 1;
                    currentToken = this.buildInstruction(insParent, currentToken + 1);

                } else {
                    this.parseTree.addNode(token, insParent);
                    currentToken = this.buildInstruction(insParent, currentToken + 1);
                }

            // previous was keyword: just append at the current parent
            } else if (currentToken > 0
                       && Symbols.isKeyWord(this.tokenList.get(currentToken - 1))) {
               this.parseTree.addNode(token, pParent);

            // may form new expression or instruction
            } else {
                // current is a valid start of expression
                if (Symbols.isStartOfExpression(token)
                    && !this.tokenList.get(currentToken + 1).getType().equals(this.opAssign)) {
                    Node expParent = new Node(new Token("EXP", "", -1, -1), pParent);

                    this.parseTree.addNode(expParent.key(), pParent);
                    currentToken = this.buildExpression(currentToken, expParent) - 1;

                // current may be a start of instruction
                } else if (Symbols.isStartOfInstruction(token)) {
                    this.parseTree.addNode(token, pParent);

                    Node insParent = new Node(new Token("INSTR", "", -1, -1), pParent);

                    this.parseTree.addNode(insParent);
                    currentToken = this.buildInstruction(insParent, currentToken + 1);

                // current token may ends an instruction
                } else if (Symbols.isEndOfInstruction(token)) {
                    if (token.getType().equals(this.delCurlyClose)) {
                        this.parseTree.addNode(token, pParent.parent());

                    } else {
                        this.parseTree.addNode(token, pParent);
                    }

                    return currentToken;

                // can not be neither an expression nor an instruction
                } else {
                    this.parseTree.addNode(token, pParent);
                }
            }
        }

        return -10;
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

            // parenting check
            if (token.getName().equals("(")) {
                parQueue.add("(");

            } else if (token.getName().equals(")")) {
                if (parQueue.isEmpty()) {
                    break;

                } else {
                    parQueue.poll();
                }
            }

            // possibly an end of expression token
            if (this.isEndOfExpression(token)) {
                // next token may expand the current expression
                if (pCurrentToken < maxSize - 1
                    && this.isMiddleOfExpression(this.tokenList.get(pCurrentToken + 1))
                    || (pCurrentToken > 0
                        && !this.tokenList.get(pCurrentToken - 1).getType().equals(this.delDoubQuot))
                    && !this.isEndOfInstruction(this.tokenList.get(pCurrentToken + 1))) {
                    children.add(new Node(token, pParent));

                // the expression is actually over
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
