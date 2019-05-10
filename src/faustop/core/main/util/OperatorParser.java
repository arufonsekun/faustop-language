package faustop.core.main.util;

import java.util.HashMap;
import java.util.Map;

import faustop.core.vars.*;

//
@FunctionalInterface
interface MethodParser <T1, T2>{

    public void apply(T1 arg1, T2 arg2);

}

//
public class OperatorParser {

	public static Map<String, MethodParser> INTEGER = new HashMap<String, MethodParser>();
	public static Map<String, MethodParser> DOUBLE = new HashMap<String, MethodParser>();

	/*
	 * Builds the hash maps
	 * */
	public static void init() {
		OperatorParser.buildInteger();
		OperatorParser.buildDouble();
	}

	/*
	 * Builds the hash maps for the Integer_ type
	 * */
	public static void buildInteger() {
		MethodParser<Integer_, Integer> plus = (pObj, pValue) -> pObj.plus(pValue);
	    MethodParser<Integer_, Integer> minus = (pObj, pValue) -> pObj.minus(pValue);
	    MethodParser<Integer_, Integer> times = (pObj, pValue) -> pObj.times(pValue);
	    MethodParser<Integer_, Integer> division = (pObj, pValue) -> pObj.division(pValue);

		OperatorParser.INTEGER.put("+", plus);
	    OperatorParser.INTEGER.put("-", minus);
	    OperatorParser.INTEGER.put("*", times);
	    OperatorParser.INTEGER.put("/", division);
	}

	/*
	 * Builds the hash maps for the Double_ type
	 * */
	public static void buildDouble() {
		MethodParser<Double_, Double> plus = (pObj, pValue) -> pObj.plus(pValue);
	    MethodParser<Double_, Double> minus = (pObj, pValue) -> pObj.minus(pValue);
	    MethodParser<Double_, Double> times = (pObj, pValue) -> pObj.times(pValue);
	    MethodParser<Double_, Double> division = (pObj, pValue) -> pObj.division(pValue);

		OperatorParser.DOUBLE.put("+", plus);
	    OperatorParser.DOUBLE.put("-", minus);
	    OperatorParser.DOUBLE.put("*", times);
	    OperatorParser.DOUBLE.put("/", division);
	}

}
