package faustop.core.main.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class Symbols {

	public static final String kwBI = "keywordbuiltin";
    public static final String kwFC = "keywordflowcontroller";
    public static final String kwType = "keywordtype";

    public static final String id = "identifier";

    public static final String opAritm = "operatorarithmetic";
    public static final String opLog = "operatorlogic";
    public static final String opAssign = "operatorassignment";
    public static final String opRel = "operatorrelational";

    public static final String delParOpen = "delimiterparenthesesopen";
    public static final String delParClose = "delimiterparenthesesclose";
    public static final String delCurlyOpen = "delimitercurlybracketopen";
    public static final String delCurlyClose = "delimitercurlybracketclose";
    public static final String delQuot = "delimiterquotationmark";
    public static final String delDoubQuot = "delimiterdoublequotationmark";
    public static final String delSemicolon = "delimitersemicolon";

    public static final String lit = "literal";

    public static final Map<String, String> symbols;

    // Grammar = {INSTRUCTION, TERMINAL, EXPRESSION}
    //            INSTRUCTION: TERMINAL + EXPRESSION (sla)
    //            TERMINAL: ID | LIT_NUMBER | OPERATOR | DELIMITER (any token?)
    //            EXPRESSION: ID/LIT_NUMBER OPERATOR ID/LIT_NUMBER |
    //                        EXPRESSION OPERATOR EXPRESSION

	static {
		//TODO: 2nd paramether of `put()` method should be the previous defined strings

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

        if (pToken.getType().equals(Symbols.kwBI)
            || pToken.getType().equals(Symbols.kwFC)
            || pToken.getType().equals(Symbols.kwType)) {
            return true;
        }

        return false;
    }

    public static final boolean isStartOfExpression(Token pToken) {
        /*
         * Utility function checks if the given token starts an expression.
         * */

        if (pToken.getType().equals(Symbols.id)
            || pToken.getType().equals(Symbols.lit)
            || pToken.getType().equals(Symbols.delParOpen)
            || pToken.getType().equals(Symbols.delQuot)
            || pToken.getType().equals(Symbols.delDoubQuot)) {
            return true;
        }

        return false;
    }

    public static final boolean isMiddleOfExpression(Token pToken) {
        /*
         * Utility function checks whether the given token represents
         * */

        if (pToken.getType().equals(Symbols.opAritm)
            || pToken.getType().equals(Symbols.opLog)
            || pToken.getType().equals(Symbols.opRel)
            || pToken.getType().equals(Symbols.delParOpen)
            || pToken.getType().equals(Symbols.delParClose)
            || pToken.getType().equals(Symbols.delQuot)
            || pToken.getType().equals(Symbols.delDoubQuot)) {
            return true;
        }

        return false;
    }

    public static final boolean isEndOfExpression(Token pToken) {
        /*
         * Utility function checks if pToken is ends an expression.
         */

        if (pToken.getType().equals(Symbols.id)
            || pToken.getType().equals(Symbols.lit)
            || pToken.getType().equals(Symbols.delParClose)
            || pToken.getType().equals(Symbols.delQuot)
            || pToken.getType().equals(Symbols.delDoubQuot)) {
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

        if (pToken.getType().equals(Symbols.delCurlyOpen)) {
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

        if (pToken.getType().equals(Symbols.delSemicolon)
            || pToken.getType().equals(Symbols.delCurlyClose)) {
            // || pToken.getType().equals(Symbols.delSemicolon)) {
            // System.out.println("\tisEndOfInstruction:::::: " + pToken.getType());
            return true;
        }

        return false;
    }

	public static boolean isIdentifier(Token pToken) {
		return pToken.getType().equals(Symbols.id);
	}

	public static boolean isAssignment(Token pToken) {
		return pToken.getType().equals(Symbols.opAssign);
	}

	public static boolean isExpression(Token pToken) {
		return pToken.getType().equals("EXP");
	}

}
