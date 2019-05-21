package faustop.core.main.util;

import java.util.HashMap;
import java.util.Map;
import faustop.core.vars.*;

/*
 * Represents a "memory" for the language.
 * */
public class Memory {

	public static HashMap<String, Integer_> intMap = new HashMap<String, Integer_>();
	public static HashMap<String, Double_> doubleMap = new HashMap<String, Double_>();
	public static HashMap<String, String_> stringMap = new HashMap<String, String_>();
	public static HashMap<String, Boolean_> booleanMap = new HashMap<String, Boolean_>();

	/*
	 * Receives the name of a variable and returs
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
