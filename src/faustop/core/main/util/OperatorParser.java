package faustop.core.main.util;

import java.util.HashMap;
import java.util.Map;
import faustop.core.vars.*;

/*
 * MethodParser interface noted with @FunctionalInterface
 * annotation is used to create kind of "lambda functions"
 * in the OperatorParser class. This lambda functions calls
 * the specified method of package `vars`.
 * */

@FunctionalInterface
interface MethodParser <T1, T2>{

    public String apply(T1 arg1, T2 arg2);

}

/*
 * OperatorParser class purpose is relate an operator to its operands, using a
 * hashmap of type String and MethodParser.
 * */

public class OperatorParser {

	public static Map<String, MethodParser> INTEGER = new HashMap<String, MethodParser>();
    public static Map<String, MethodParser> DOUBLE = new HashMap<String, MethodParser>();
    public static Map<String, MethodParser> STRING = new HashMap<String, MethodParser>();
	public static Map<String, MethodParser> BOOLEAN = new HashMap<String, MethodParser>();

	/*
	 * Builds the hash maps
	 * */

	public static void init() {

		OperatorParser.buildInteger();
        OperatorParser.buildDouble();
        OperatorParser.buildString();
		OperatorParser.buildBoolean();

	}

	/*
	 * Builds the hash map related to Integer Type operators
	 * */

	public static void buildInteger() {

		MethodParser<IntegerType, Integer> plus = (pObj, pValue) -> pObj.plus(pValue);
	    MethodParser<IntegerType, Integer> minus = (pObj, pValue) -> pObj.minus(pValue);
	    MethodParser<IntegerType, Integer> times = (pObj, pValue) -> pObj.times(pValue);
	    MethodParser<IntegerType, Integer> division = (pObj, pValue) -> pObj.division(pValue);

        MethodParser<IntegerType, Integer> grTh = (pObj, pValue) -> pObj.greaterThan(pValue);
        MethodParser<IntegerType, Integer> grThOrEq = (pObj, pValue) -> pObj.greaterThanOrEqualTo(pValue);
        MethodParser<IntegerType, Integer> lessTh = (pObj, pValue) -> pObj.lessThan(pValue);
        MethodParser<IntegerType, Integer> lessThOrEq = (pObj, pValue) -> pObj.lessThanOrEqualTo(pValue);
        MethodParser<IntegerType, Integer> eq = (pObj, pValue) -> pObj.equal(pValue);
        MethodParser<IntegerType, Integer> dif = (pObj, pValue) -> pObj.differs(pValue);
        MethodParser<IntegerType, Integer> mod = (pObj, pValue) -> pObj.mod(pValue);
        MethodParser<IntegerType, Integer> pow = (pObj, pValue) -> pObj.pow(pValue);

		OperatorParser.INTEGER.put("+", plus);
	    OperatorParser.INTEGER.put("-", minus);
	    OperatorParser.INTEGER.put("*", times);
	    OperatorParser.INTEGER.put("/", division);
        OperatorParser.INTEGER.put("%", mod);
        OperatorParser.INTEGER.put("^", pow);

        OperatorParser.INTEGER.put(">", grTh);
        OperatorParser.INTEGER.put(">=", grThOrEq);
        OperatorParser.INTEGER.put("<", lessTh);
        OperatorParser.INTEGER.put("<=", lessThOrEq);
        OperatorParser.INTEGER.put("==", eq);
        OperatorParser.INTEGER.put("!=", dif);

	}

	/*
	 * Builds the hash map related to Double Type operators
	 * */

     public static void buildDouble() {

 		MethodParser<DoubleType, Double> plus = (pObj, pValue) -> pObj.plus(pValue);
 	    MethodParser<DoubleType, Double> minus = (pObj, pValue) -> pObj.minus(pValue);
 	    MethodParser<DoubleType, Double> times = (pObj, pValue) -> pObj.times(pValue);
 	    MethodParser<DoubleType, Double> division = (pObj, pValue) -> pObj.division(pValue);

        MethodParser<DoubleType, Double> grTh = (pObj, pValue) -> pObj.greaterThan(pValue);
        MethodParser<DoubleType, Double> grThOrEq = (pObj, pValue) -> pObj.greaterThanOrEqualTo(pValue);
        MethodParser<DoubleType, Double> lessTh = (pObj, pValue) -> pObj.lessThan(pValue);
        MethodParser<DoubleType, Double> lessThOrEq = (pObj, pValue) -> pObj.lessThanOrEqualTo(pValue);
        MethodParser<DoubleType, Double> eq = (pObj, pValue) -> pObj.equal(pValue);
        MethodParser<DoubleType, Double> dif = (pObj, pValue) -> pObj.differs(pValue);

 		OperatorParser.DOUBLE.put("+", plus);
 	    OperatorParser.DOUBLE.put("-", minus);
 	    OperatorParser.DOUBLE.put("*", times);
 	    OperatorParser.DOUBLE.put("/", division);

        OperatorParser.DOUBLE.put(">", grTh);
        OperatorParser.DOUBLE.put(">=", grThOrEq);
        OperatorParser.DOUBLE.put("<", lessTh);
        OperatorParser.DOUBLE.put("<=", lessThOrEq);
        OperatorParser.DOUBLE.put("==", eq);
        OperatorParser.DOUBLE.put("!=", dif);

 	}

    /*
	 *  Builds the hash map related to String Type operators
	 * */

    public static void buildString() {

        MethodParser<StringType, String> grTh = (pObj, pValue) -> pObj.greaterThan(pValue);
        MethodParser<StringType, String> grThOrEq = (pObj, pValue) -> pObj.greaterThanOrEqualTo(pValue);
        MethodParser<StringType, String> lessTh = (pObj, pValue) -> pObj.lessThan(pValue);
        MethodParser<StringType, String> lessThOrEq = (pObj, pValue) -> pObj.lessThanOrEqualTo(pValue);
        MethodParser<StringType, String> eq = (pObj, pValue) -> pObj.equal(pValue);
        MethodParser<StringType, String> dif = (pObj, pValue) -> pObj.differs(pValue);

        OperatorParser.STRING.put(">", grTh);
        OperatorParser.STRING.put(">=", grThOrEq);
        OperatorParser.STRING.put("<", lessTh);
        OperatorParser.STRING.put("<=", lessThOrEq);
        OperatorParser.STRING.put("==", eq);
        OperatorParser.STRING.put("!=", dif);

 	}

    /*
	 * Builds the hash map related to Boolean Type operators
	 * */

    public static void buildBoolean() {

        MethodParser<BooleanType, Boolean> lgcOr = (pObj, pValue) -> pObj.logicalOr(pValue);
        MethodParser<BooleanType, Boolean> lgcAnd = (pObj, pValue) -> pObj.logicalAnd(pValue);
        MethodParser<BooleanType, Boolean> eq = (pObj, pValue) -> pObj.equal(pValue);
        MethodParser<BooleanType, Boolean> dif = (pObj, pValue) -> pObj.differs(pValue);

        OperatorParser.BOOLEAN.put("||", lgcOr);
        OperatorParser.BOOLEAN.put("&&", lgcAnd);
        OperatorParser.BOOLEAN.put("==", eq);
        OperatorParser.BOOLEAN.put("!=", dif);

 	}

}
