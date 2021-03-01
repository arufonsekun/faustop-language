package faustop.core.main.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * Class Symbols keeps ways to recognize and classify tokens.
 * Define kind of a Grammar of the Language.
 *
 * @author
 * */

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

        Map<String, String> aMap = new HashMap<>();
		// type definition keywords
        aMap.put("olokinho", Symbols.KW_TYPE); // int
        aMap.put("oloko", Symbols.KW_TYPE); // double
        aMap.put("bicho", Symbols.KW_TYPE); // string
        aMap.put("paiseuropa", Symbols.KW_TYPE); // boolean

		// flow control statements
		aMap.put("eagora", Symbols.KW_FC); // if
        aMap.put("churrasqueira", Symbols.KW_FC); // while

        // built-in functions
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
	 * Checks of the given token is a keyword type.
	 * */
	public static final boolean isKeyWord(Token pToken) {

		String tType = pToken.getType();

		boolean isKwBI = tType.equals(Symbols.KW_BI);
		boolean isFC = tType.equals(Symbols.KW_FC);
		boolean isKwT = tType.equals(Symbols.KW_TYPE);

		if (isKwBI || isFC || isKwT) {
            return true;

		}

        return false;

    }

	/*
	 * Checks if the given token starts an expression.
	 * */
    public static final boolean isStartOfExpression(Token pToken) {

		String tType = pToken.getType();

		boolean isLit = tType.equals(Symbols.LIT);
		boolean isParOp = tType.equals(Symbols.DEL_PAR_OPEN);
		boolean isDoubQuo = tType.equals(Symbols.DEL_DOUB_QUOT);
		boolean isOpAssi = tType.equals(Symbols.OP_ASSIGN);

        if (isLit || isParOp || isDoubQuo || isOpAssi) {
            return true;

		}

        return false;

    }

	/*
	 * Checks whether the given token represents
	 * a 'expandible' token.
	 * */
    public static final boolean isMiddleOfExpression(Token pToken) {

		String tType = pToken.getType();

		boolean isOp = Symbols.isOperator(pToken);
		boolean isParOp = tType.equals(Symbols.DEL_PAR_OPEN);
		boolean isParClo = tType.equals(Symbols.DEL_PAR_CLOSE);
		boolean isLit = tType.equals(Symbols.LIT);
		boolean isDoubQuo = tType.equals(Symbols.DEL_DOUB_QUOT);

        if (isOp || isParOp || isParClo || isLit || isDoubQuo) {
            return true;

		}

        return false;

    }

	/*
	 * Checks if given token ends an expression.
	 * */
    public static final boolean isEndOfExpression(Token pToken) {

		String tType = pToken.getType();

		boolean isId = tType.equals(Symbols.ID);
		boolean isLit = tType.equals(Symbols.LIT);
		boolean isParClo = tType.equals(Symbols.DEL_PAR_CLOSE);
		boolean isDoubQuo = tType.equals(Symbols.DEL_DOUB_QUOT);

        if (isId || isLit || isParClo || isDoubQuo) {
            return true;

		}

        return false;

    }

	/*
	 * Checks if given token starts an instruction.
	 * */
    public static final boolean isStartOfInstruction(Token pToken) {

		String tType = pToken.getType();

		boolean isKW = Symbols.isKeyWord(pToken);
		boolean isCurOp = tType.equals(Symbols.DEL_CURLY_OPEN);
		boolean isId = tType.equals(Symbols.ID);

        if (isKW || isCurOp || isId) {
            return true;

		}

        return false;

    }

	/*
	 * Checks if given token ends an instruction.
	 * */
    public static final boolean isEndOfInstruction(Token pToken) {

		String tType = pToken.getType();

		boolean isDelSemi = tType.equals(Symbols.DEL_SEMICOLON);
		boolean isDelCurClo = tType.equals(Symbols.DEL_CURLY_CLOSE);

		if (isDelSemi || isDelCurClo) {
            return true;

		}

        return false;

    }

	/*
 	 * Checks if given token is an operator of any type.
	 * */
	public static final boolean isOperator(Token pToken) {

		String tType = pToken.getType();

		boolean isOpArit = tType.equals(Symbols.OP_ARITM);
		boolean isOpRel = tType.equals(Symbols.OP_REL);
		boolean isOpLog = tType.equals(Symbols.OP_LOG);

		if (isOpArit || isOpRel || isOpLog) {
			return true;

		}

		return false;

	}

	/*
	 * Checks if given token is an identifier.
	 * */
	public static boolean isIdentifier(Token pToken) {

		return pToken.getType().equals(Symbols.ID);

	}

	/*
	 * Checks if given token is a literal.
	 * */
	public static boolean isLiteral(Token pToken) {

		return pToken.getType().equals(Symbols.LIT);

	}

	/*
	 * Checks if given token is an assignment sign.
	 * */
	public static boolean isAssignment(Token pToken) {

		return pToken.getType().equals(Symbols.OP_ASSIGN);

	}

	/*
	 * Checks if given token is the root of a subtree
	 * that represents an expression
	 * */
	public static boolean isExpression(Token pToken) {

		return pToken.getType().equals("EXP");

	}

	/*
	 * Checks if given String is equals to `bicho`.
	 * */
	public static boolean isString(String pTType) {

        return pTType.equals("bicho");

    }

	/*
	 * Checks if given String is equals to `olokinho`.
	 * */
    public static boolean isInt(String pTType) {

        return pTType.equals("olokinho");

    }

	/*
	 * Checks if given String is equals to `oloko`.
	 * */
    public static boolean isDouble(String pTType) {

        return pTType.equals("oloko");

    }

	/*
	 * Checks if given String is equals to `paiseuropa`.
	 * */
    public static boolean isBool(String pTType) {

        return pTType.equals("paiseuropa");

    }
}
