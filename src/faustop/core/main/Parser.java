import java.util.LinkedList;
import java.util.HashMap;
import java.util.regex.Pattern;

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

    public Parser() {

        // the first node of the parseTree will always be a instruction?
        private Tree parseTree = new Tree(Token("INSTRUCTION", "", -1, -1));

        // bota um atributo bool que verifica se uma instrução foi processada.
        // começa com true e o wrapper só para de mandar Tokens quando esse
        // atributo for falso.

        //regexes ahead (they should be somehow global)

        String kwBI = "keywordbuiltin";
        String kwFC = "keywordflowcontroller";
        String kwType = "keywordtype";

        String id = "identifier";

        String opAritm = "operatorarithmetic";
        String opLog = "operatorlogic";
        String opAssign = "operatorassignment";
        String opRel = "operatorrelational";

        String delParOpen = "delimiterparenthesesopen";
        String delParClose = "delimiterparenthesesclose";
        String delCurlyOpen = "delimitercurlybracketopen";
        String delCurlyClose = "delimitercurlybracketclose";
        String delQuot = "delimiterquotationmark";
        String delDoubQuot = "delimiterdoublequotationmark";
        String delSemicolon = "delimitersemicolon";

        String lit = "literal";

        // auxiliar regexes

        String funcCall = kwBI + delParOpen
                          + "(" + exp + ")?"
                          + delParClose;

        String exp = "(" + id + "|"
                     + lit + "|"
                     + funcCall + "|"
                     + opAritm + "|" + opLog + "|" + opRel
                     + delParOpen + "|" + delParClose + ")+";
        String dec = "^" + kwType + id + "$";
        String init = "^" + kwType + id + opAssign + exp + "$";
        String assign = "^" + id + opAssign + exp + "$";
        String flow = "^" + kwFC + delParOpen + exp + delParOpen
                            + delCurlyOpen
                            + "(" + exp + "|" + dec + "|" + init + "|" + assign ")*"
                            + delCurlyClose + "$"; // lacks recursion

        // actual regexes

        this.instructionRules.put(exp, "expression");
        this.instructionRules.put(dec, "declaration");
        this.instructionRules.put(init, "initialization");
        this.instructionRules.put(assign, "assignment");
        this.instructionRules.put(flow, "flowController");

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

    public boolean isValidInstruction() Token token : instructions{
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

    private boolean isKeyWord(Token pToken) {
        if (token.getType().equals(this.kwBI) ||
            token.getType().equals(this.kwFC) ||
            token.getType().equals(this.kwType)) {

            return true;
        } else {
            return false;
        }
    }

    public void buildParseTree() {
        /*
         * Comments.
         * */

        // LinkedList<Token> instructions = this.getInstruction();
        Node lastParent = this.parseTree.root;
        Token token;
        int currentToken = 0;
        int startOfExp = -1;
        boolean beginExp = false;

        for (; currentToken < this.instruction.size(); currentToken++) {
            token = this.instructions.get(currentToken);

            // current is keyword
            if (!beginExp && this.isKeyWord(token)) {
                this.parseTree.addNode(token, lastParent);

            // previous was keyword
            } else if (!beginExp &&
                       this.isKeyWord(this.instructions.get(currentToken-1))) {
                this.parseTree.addNode(token, lastParent);

            // current is '=' sing
            } else if (!beginExp && token.getType().equals(this.opAssign)) {
                this.parseTree.addNode(token, lastParent);

            // may form an expression
            } else {
                // current is a valid start of expression
                if (!beginExp && (token.getType().equals(this.id) ||
                    token.getType().equals(this.lit) ||
                    token.getType().equals(this.delParOpen) ||
                    token.getType().equals(this.delQuot) ||
                    token.getType().equals(this.delDoubQuot))) {
                        beginExp = !beginExp;
                        lastParent = new Node(Token("EXP", "", -1, -1), lastParent);
                        this.parseTree.addNode(token, lastParent);

                // current is a valid end of expression
                } else if (beginExp && (token.getType().equals(this.id) ||
                                        token.getType().equals(this.lit) ||
                                        token.getType().equals(this.delParClose) ||
                                        token.getType().equals(this.delQuot) ||
                                        token.getType().equals(this.delDoubQuot))) {
                        beginExp = !beginExp;


                //
                } else if () {

                }
            }
        }
        // tries to expand the token to a expression
        // if fails, create a leaf (terminal) node
}
        }
    }

}
