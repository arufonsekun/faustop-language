@FunctionalInterface
interface MethodParser <T1, T2>{
    public void apply(T1 arg1, T2 arg2);
}

class OperatorParser {
    public static Map<String, MethodParser> INTEGER_METHODS = new HashMap<String, MethodParser>();
    MethodParser<Integer_, Integer> plus = (pObj, pValue) -> pObj.plus(pValue);
    MethodParser<Integer_, Integer> minus = (pObj, pValue) -> pObj.minus(pValue);
    MethodParser<Integer_, Integer> times = (pObj, pValue) -> pObj.times(pValue);
    MethodParser<Integer_, Integer> division = (pObj, pValue) -> pObj.division(pValue);
    INTEGER_METHODS.put("+", plus);
    INTEGER_METHODS.put("-", minus);
    INTEGER_METHODS.put("*", times);
    INTEGER_METHODS.put("/", division);
}
