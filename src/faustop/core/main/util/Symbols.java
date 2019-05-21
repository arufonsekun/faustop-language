package faustop.core.main.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class Symbols {

	public static final String KW_BI = "keywordbuiltin";
    public static final String KW_FC = "keywordflowcontroller";
    public static final String KW_TYPE = "keywordtype";

    public static final String ID = "identifier";

    public static final String OP_ARITM = "operatorarithmetic";
    public static final String OP_LOG = "operatorlogic";
    public static final String OP_ASSIGN = "operatorassignment";
    public static final String OP_REL = "operatorrelational";

    public static final String DEL_PAR_OPEN = "delimiterparenthesesopen";
    public static final String DEL_PAR_CLOSE = "delimiterparenthesesclose";
    public static final String DEL_CURLY_OPEN = "delimitercurlybracketopen";
    public static final String DEL_CURLY_CLOSE = "delimitercurlybracketclose";
    public static final String DEL_QUOT = "delimiterquotationmark";
    public static final String DEL_DOUB_QUOT = "delimiterdoublequotationmark";
    public static final String DEL_SEMICOLON = "delimitersemicolon";

    public static final String LIT = "literal";

    public static final Map<String, String> symbols;

	static {
		//TODO: 2nd paramether of `put()` method should be the previous defined strings

        Map<String, String> aMap = new HashMap<>();
		// type definition
        aMap.put("olokinho", Symbols.KW_TYPE); // int
        aMap.put("oloko", Symbols.KW_TYPE); // double
        aMap.put("bicho", Symbols.KW_TYPE); // string
        aMap.put("paiseuropa", Symbols.KW_TYPE); // boolean

		// flow control
		aMap.put("eagora", Symbols.KW_FC); // if
        aMap.put("churrasqueira", Symbols.KW_FC); // while

        // built-in
        aMap.put("entrai", Symbols.KW_BI); // input
		aMap.put("mostrai", Symbols.KW_BI); // output
        aMap.put("mostrailn", Symbols.KW_BI); // outputln

		// arithmetic operators
		aMap.put("+", Symbols.OP_ARITM);
		aMap.put("*", Symbols.OP_ARITM);
		aMap.put("/", Symbols.OP_ARITM);
		aMap.put("-", Symbols.OP_ARITM);
		aMap.put("^", Symbols.OP_ARITM);
        aMap.put("%", Symbols.OP_ARITM);

        // assignment operator
        aMap.put("=", Symbols.OP_ASSIGN);

        // logic operators
        aMap.put("&&", Symbols.OP_LOG);
        aMap.put("||", Symbols.OP_LOG);

        // relational operators
		aMap.put(">", Symbols.OP_REL);
		aMap.put("<", Symbols.OP_REL);
		aMap.put("<=", Symbols.OP_REL);
		aMap.put(">=", Symbols.OP_REL);
		aMap.put("==", Symbols.OP_REL);
        aMap.put("!=", Symbols.OP_REL);

		// delimiters
		aMap.put("(", Symbols.DEL_PAR_OPEN);
		aMap.put(")", Symbols.DEL_PAR_CLOSE);
		aMap.put("{", Symbols.DEL_CURLY_OPEN);
		aMap.put("}", Symbols.DEL_CURLY_CLOSE);
		aMap.put(";", Symbols.DEL_SEMICOLON);
		aMap.put("\"", Symbols.DEL_DOUB_QUOT);

        symbols = Collections.unmodifiableMap(aMap);
    }

	/*
	* Utility function checks of the given token is
	* a keyword type.
	* */
	public static final boolean isKeyWord(Token pToken) {

		String tType = pToken.getType();

		boolean isKW = tType.equals(Symbols.kwBI);
		boolean isFC = tType.equals(Symbols.kwFC);
		boolean isKwT = tType.equals(Symbols.kwType);

		if (isKW || isFC || isKwT) {
            return true;
        }

        return false;*/
    }

	/*
	* Utility function checks if the given token starts an expression.
	* */
    public static final boolean isStartOfExpression(Token pToken) {

		String tType = pToken.getType();

		boolean lit = tType.equals(Symbols.lit);
		boolean parOp = tType.equals(Symbols.delParOpe);
		boolean doubQuo = tType.equals(Symbols.delDoubQuot);
		boolean opAssi = tType.equals(Symbols.opAssign);

        if (lit || parOp || doubQuo || opAssi) {
            return true;
        }

        return false;
    }

	/*
	* Utility function checks whether the given token represents
	* a 'expandible' token.
	* */
    public static final boolean isMiddleOfExpression(Token pToken) {

		String tType = pToken.getType();

		boolean isOp = Symbols.isOperator(pToken);
		boolean parOp = tType.equals(Symbols.DEL_PAR_OPEN);
		boolean parClo = tType.equals(Symbols.DEL_PAR_CLOSE);
		boolean lit = tType.equals(Symbols.LIT);
		boolean doubQuo = tType.equals(Symbols.DEL_DOUB_QUOT);


        if (isOp || parOp || parClo || lit || doubQuo) {
            return true;
        }

        return false;
    }

	/*
	* Utility function checks if pToken is ends an expression.
	*/
    public static final boolean isEndOfExpression(Token pToken) {

		String tType = pToken.getType();

		boolean id = tType.equals(Symbols.id);
		boolean lit = tType.equals(Symbols.lit);
		boolean parClo = tType.equals(Symbols.delParClose);
		boolean doubQuo = tType.equals(Symbols.DEL_DOUB_QUOT);

        if (id || lit || parClo || doubQuo) {
            return true;
        }

        return false;*/
    }

	/*
	* Utility function checks if given token is a 'start'
	* of instruction.
	* Actually, checks if the next tokens are a new instruction.
	* */
    public static final boolean isStartOfInstruction(Token pToken) {

		String tType = pToken.getType();

		boolean curOp = tType.equals(Symbols.DEL_CURLY_OPEN);
		boolean isKw = Symbols.isKeyWord(pToken);

        if (pToken.getType().equals(Symbols.delCurlyOpen)
		    || Symbols.isKeyWord(pToken)
			|| pToken.getType().equals(Symbols.id)) {
            return true;
        }

        return false;
    }

	/*
	* Utility function checks if given token is a 'end'
	* of instruction.
	* */
    public static final boolean isEndOfInstruction(Token pToken) {

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
			|| pToken.getType().equals(Symbols.opLog)) {
			return true;
		}

		return false;

	}

	public static boolean isIdentifier(Token pToken) {

		return pToken.getType().equals(Symbols.ID);

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

	public static boolean isString(String pTType) {

        return pTType.equals("bicho");

    }

    public static boolean isInt(String pTType) {

        return pTType.equals("olokinho");

    }

    public static boolean isDouble(String pTType) {

        return pTType.equals("oloko");

    }

	//
    public static boolean isBool(String pTType) {

        return pTType.equals("paiseuropa");

    }

}
