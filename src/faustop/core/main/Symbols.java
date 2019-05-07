// package faustop.core.main;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

class Symbols {

	private static final String kwBI = "keywordbuiltin";
    private static final String kwFC = "keywordflowcontroller";
    private static final String kwType = "keywordtype";

    private static final String id = "identifier";

    private static final String opAritm = "operatorarithmetic";
    private static final String opLog = "operatorlogic";
    private static final String opAssign = "operatorassignment";
    private static final String opRel = "operatorrelational";

    private static final String delParOpen = "delimiterparenthesesopen";
    private static final String delParClose = "delimiterparenthesesclose";
    private static final String delCurlyOpen = "delimitercurlybracketopen";
    private static final String delCurlyClose = "delimitercurlybracketclose";
    private static final String delQuot = "delimiterquotationmark";
    private static final String delDoubQuot = "delimiterdoublequotationmark";
    private static final String delSemicolon = "delimitersemicolon";

    private static final String lit = "literal";

    public static final Map<String, String> symbols;

    // Grammar = {INSTRUCTION, TERMINAL, EXPRESSION}
    //            INSTRUCTION: TERMINAL + EXPRESSION (sla)
    //            TERMINAL: ID | LIT_NUMBER | OPERATOR | DELIMITER (any token?)
    //            EXPRESSION: ID/LIT_NUMBER OPERATOR ID/LIT_NUMBER |
    //                        EXPRESSION OPERATOR EXPRESSION

	static {
        Map<String, String> aMap = new HashMap<>();
		// type definition
        aMap.put("inte", "keywordtype");
        aMap.put("double", "keywordtype");
        aMap.put("olokinho", "keywordtype"); // char
        aMap.put("oloko", "keywordtype"); // string
        aMap.put("bool", "keywordtype");

		// flow control
		aMap.put("if", "keywordflowcontroller");
		// aMap.put("for", "keywordflowcontroller");
        aMap.put("while", "keywordflowcontroller");

        // built-in
        aMap.put("entrai", "keywordbuiltin");
        aMap.put("mostrai", "keywordbuiltin");

		// arithmetic operators
		aMap.put("+", "operatorarithmetic");
		aMap.put("*", "operatorarithmetic");
		aMap.put("/", "operatorarithmetic");
		aMap.put("-", "operatorarithmetic");
		aMap.put("^", "operatorarithmetic");
        aMap.put("%", "operatorarithmetic");

        // assignment operators
        aMap.put("=", "operatorassignment");
        aMap.put("+=", "operatorassignment");
		aMap.put("*=", "operatorassignment");
		aMap.put("/=", "operatorassignment");
		aMap.put("-=", "operatorassignment");
        aMap.put("^=", "operatorassignment");
        aMap.put("%=", "operatorassignment");

        // logic operators
        aMap.put("!", "operatorlogic");
        aMap.put("&&", "operatorlogic");
        aMap.put("||", "operatorlogic");

        // relational operators
		aMap.put(">", "operatorrelational");
		aMap.put("<", "operatorrelational");
		aMap.put("<=", "operatorrelational");
		aMap.put(">=", "operatorrelational");
		aMap.put("==", "operatorrelational");
        aMap.put("!=", "operatorrelational");

		// delimiters
		aMap.put("(", "delimiterparenthesesopen");
		aMap.put(")", "delimiterparenthesesclose");
		aMap.put("{", "delimitercurlybracketopen");
		aMap.put("}", "delimitercurlybracketclose");
		aMap.put(";", "delimitersemicolon");
		aMap.put("\'", "delimiterquotationmark");
		aMap.put("\"", "delimiterdoublequotationmark");

        symbols = Collections.unmodifiableMap(aMap);
    }

	public static final boolean isKeyWord(Token pToken) {
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

    public static final boolean isStartOfExpression(Token pToken) {
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

    public static final boolean isMiddleOfExpression(Token pToken) {
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

    public static final boolean isEndOfExpression(Token pToken) {
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

    public static final boolean isStartOfInstruction(Token pToken) {
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

    public static final boolean isEndOfInstruction(Token pToken) {
        /*
        * Utility function checks if given token is a 'end'
        * of instruction.
        * Actually, checks if the previous token is the last of
        * a instruction.
        * */

        if (pToken.getType().equals(this.delSemicolon)
            || pToken.getType().equals(this.delCurlyClose)) {
            // || pToken.getType().equals(this.delSemicolon)) {
            // System.out.println("\tisEndOfInstruction:::::: " + pToken.getType());
            return true;
        }

        return false;
    }

}
