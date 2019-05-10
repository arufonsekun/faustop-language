package faustop.core.main;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

class Symbols {

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

}
