// package faustop.core.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

class Symbols {

    public static final Map<String, String> symbols;

	static {
        Map<String, String> aMap = new HashMap<String, String>();
		// type definition
        aMap.put("inte", "keyword");
        aMap.put("double", "keyword");
        aMap.put("xar", "keyword");
        aMap.put("stringue", "keyword");

		// flow control
		aMap.put("if", "keyword");
		aMap.put("for", "keyword");

		// operators
		aMap.put("+", "operator");
		aMap.put("*", "operator");
		aMap.put("/", "operator");
		aMap.put("-", "operator");
		aMap.put("**", "operator");
		aMap.put("+=", "operator");
		aMap.put("*=", "operator");
		aMap.put("/=", "operator");
		aMap.put("-=", "operator");
		aMap.put("++", "operator");
		aMap.put("--", "operator");
		aMap.put(">", "operator");
		aMap.put("<", "operator");
		aMap.put("<=", "operator");
		aMap.put(">=", "operator");
		aMap.put("==", "operator");

		// delimiters
		aMap.put("(", "delimiter");
		aMap.put(")", "delimiter");
		aMap.put("{", "delimiter");
		aMap.put("}", "delimiter");
		aMap.put("=", "delimiter");

        symbols = Collections.unmodifiableMap(aMap);
    }

}
