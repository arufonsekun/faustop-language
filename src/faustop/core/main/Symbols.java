// package faustop.core.main;

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
        aMap.put("inte", "keyword");
        aMap.put("double", "keyword");
        aMap.put("olokinho", "keyword"); // char
        aMap.put("oloko", "keyword"); // string
        aMap.put("bool", "keyword");

		// flow control
		aMap.put("if", "keyword");
		aMap.put("for", "keyword");
        aMap.put("while", "keyword");

        // built-in
        aMap.put("entrai", "keyword");
        aMap.put("mostrai", "keyword");

		// arithmetic operators
		aMap.put("+", "operator");
		aMap.put("*", "operator");
		aMap.put("/", "operator");
		aMap.put("-", "operator");
		aMap.put("^", "operator");
        aMap.put("%", "operator");

        // assignment operators
        aMap.put("=", "operator");
        aMap.put("+=", "operator");
		aMap.put("*=", "operator");
		aMap.put("/=", "operator");
		aMap.put("-=", "operator");
        aMap.put("^=", "operator");
        aMap.put("%=", "operator");

        // logic operators
        aMap.put("!", "operator");
        aMap.put("&&", "operator");
        aMap.put("||", "operator");

        // relational operators
		aMap.put(">", "operator");
		aMap.put("<", "operator");
		aMap.put("<=", "operator");
		aMap.put(">=", "operator");
		aMap.put("==", "operator");
        aMap.put("!=", "operator");

		// delimiters
		aMap.put("(", "delimiter");
		aMap.put(")", "delimiter");
		aMap.put("{", "delimiter");
		aMap.put("}", "delimiter");
		aMap.put(";", "delimiter");
		aMap.put("\'", "delimiter");
		aMap.put("\"", "delimiter");

        symbols = Collections.unmodifiableMap(aMap);
    }

}
