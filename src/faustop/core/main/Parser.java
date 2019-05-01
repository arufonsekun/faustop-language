import java.util.LinkedList;
import java.util.HashMap;
import java.util.regex.Pattern;


class Parser {

    private Token token;
    private LinkedList<Token> instruction = new LinkedList<>();
    private HashMap<String, String> rules = new HashMap<>();

    public Parser() {
        //regexes ahead (they should be somehow global)

        String kwBI = "keywordbuiltin";
        String kwFC = "keywordflowcontroller";
        String kwType = "keywordtype";

        String id = "identifier";

        String opAritm = "operatorarithmetic";
        String opLog = "operatorlogic";
        String opAssignBasic = "operatorassignmentbasic";
        String opAssignAritm = "operatorassignmentarithmetic";
        String opRel = "operatorrelational";

        String delParenth = "delimiterparentheses";
        String delCurly = "delimitercurlybracket";
        String delQuot = "delimiterquotationmark";
        String delDoubQuot = "delimiterdoublequotationmark";
        String delSemicolon = "delimitersemicolon";

        String lit = "literal";

        // end of regexes

        String exp = "(" + id + "|"
                     + lit + "|"
                     + opAritm + "|" + opLog + "|" + opRel
                     + delParenth + ")+";

        this.rules.put(exp,
                       "expression");
        this.rules.put("^" + kw + "" + "$",
                       "declaration");
        this.rules.put("^=" + exp + "$",
                       "initialization");
        this.rules.put("^" + exp + "$",
                       "assignment");
        this.rules.put("", "");
    }

    public Token getToken() {
        return this.token;
    }

    public void setToken(Token pToken) {
        /*
         * Apart from being a setter of this.token,
         * also appends the token to the current instruction.
         * */

        this.token = pToken;
        this.instruction.add(pToken);
    }

    public LinkedList<Token> getInstruction() {
        return this.instruction;
    }

    public boolean endOfInstruction() {
        /*
         * Checks if a final delimiter was found.
         * */

        return this.instruction.getLast().getName().equals(";");
        // missing conditional and loop cases
    }

    public boolean isValidInstruction() {
        /*
         * Identity type of instruction.
         * Checks if instruction follows all the rules concerning to that
         * kind of instruction.
         * The Interpreter must, then, if instruction is valid, execute it;
         * else, it must throw an error. Nevertheless, the instruction
         * LinkedList must be clear afterwards.
         * */

        return false;
    }
}
