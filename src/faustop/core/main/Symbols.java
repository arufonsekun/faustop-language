// package faustop.core.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

class Symbols {

    public static final Map<String, String> symbols;

	static {
        Map<String, String> aMap = new HashMap<>();
		// type definition
        aMap.put("inte", "keyword");
        aMap.put("double", "keyword");
        aMap.put("xar", "keyword");
        aMap.put("stringue", "keyword");
        aMap.put("bool", "keyword");

		// flow control
		aMap.put("if", "keyword");
		aMap.put("for", "keyword");
        aMap.put("while", "keyword");

        // built-in
        aMap.put("input", "keyword");
        aMap.put("output", "keyword");

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
