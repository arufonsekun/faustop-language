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
        Map<String, String> aMap = new HashMap<>();
		// type definition
        aMap.put("olokinho", "keywordtype"); // int
        aMap.put("oloko", "keywordtype"); // double
        aMap.put("bicho", "keywordtype"); // string
        aMap.put("paiseuropa", "keywordtype"); // boolean

		// flow control
		aMap.put("eagora", "keywordflowcontroller"); // if
		// aMap.put("for", "keywordflowcontroller");
        aMap.put("churrasqueira", "keywordflowcontroller"); // while

        // built-in
        aMap.put("entrai", "keywordbuiltin"); // input
        aMap.put("mostrai", "keywordbuiltin"); // output

		// arithmetic operators
		aMap.put("+", "operatorarithmetic");
		aMap.put("*", "operatorarithmetic");
		aMap.put("/", "operatorarithmetic");
		aMap.put("-", "operatorarithmetic");
		aMap.put("^", "operatorarithmetic");
        aMap.put("%", "operatorarithmetic");

        // assignment operator
        aMap.put("=", "operatorassignment");

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

        if (pToken.getType().equals(Symbols.lit)
            || pToken.getType().equals(Symbols.delParOpen)
            || pToken.getType().equals(Symbols.delDoubQuot)
			|| pToken.getType().equals(Symbols.opAssign)) {
            return true;
        }

        return false;
    }

	/*
	* Utility function checks whether the given token represents
	* a 'expandible' token.
	* */
    public static final boolean isMiddleOfExpression(Token pToken) {

        if (Symbols.isOperator(pToken)
            || pToken.getType().equals(Symbols.delParOpen)
			|| pToken.getType().equals(Symbols.delParClose)
            || pToken.getType().equals(Symbols.lit)
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

        if (pToken.getType().equals(Symbols.delCurlyOpen)
		    || Symbols.isKeyWord(pToken)
			|| pToken.getType().equals(Symbols.id)) {
            return true;
        }

        return false;
    }

    public static final boolean isEndOfInstruction(Token pToken) {
        /*
        * Utility function checks if given token is a 'end'
        * of instruction.
        * */

        if (pToken.getType().equals(Symbols.delSemicolon)
            || pToken.getType().equals(Symbols.delCurlyClose)) {
            return true;
        }

        return false;
    }
	
	/*
	* Utility function checks if given token is 
	* an operator of any type.
	* */
	public static final boolean isOperator(Token pToken) {
		if (pToken.getType().equals(Symbols.opAritm)
			|| pToken.getType().equals(Symbols.opRel)
			// || pToken.getType().equals(Symbol.opAssign)
			|| pToken.getType().equals(Symbols.opLog)) {
			return true;
		}
		
		return false;
	}

	public static boolean isIdentifier(Token pToken) {
		return pToken.getType().equals(Symbols.id);
	}
	
	public static boolean isLiteral(Token pToken) {
		return pToken.getType().equals(Symbols.lit);
	}

	public static boolean isAssignment(Token pToken) {
		return pToken.getType().equals(Symbols.opAssign);
	}

	public static boolean isExpression(Token pToken) {
		return pToken.getType().equals("EXP");
	}

}
