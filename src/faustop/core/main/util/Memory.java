package faustop.core.main.util;

import java.util.HashMap;
import java.util.Map;
import faustop.core.vars.*;

/*
 * Represents a "memory" for the language.
 * */

public class Memory {

	public static HashMap<String, IntegerType> intMap = new HashMap<String, IntegerType>();
	public static HashMap<String, DoubleType> doubleMap = new HashMap<String, DoubleType>();
	public static HashMap<String, StringType> stringMap = new HashMap<String, StringType>();
	public static HashMap<String, BooleanType> booleanMap = new HashMap<String, BooleanType>();

	/*
	 * Receives the name of a variable and returns
	 * a string with its value.
	 * */
	public static String getValueOf(String pVarName) {

		if (intMap.containsKey(pVarName)) {
			return ""+intMap.get(pVarName).getValue();

		} else if (doubleMap.containsKey(pVarName)) {
			return ""+doubleMap.get(pVarName).getValue();

		} else if (booleanMap.containsKey(pVarName)) {
			return ""+booleanMap.get(pVarName).getValue();

		} else {
			return ""+stringMap.get(pVarName).getValue();
		}

	}

}
