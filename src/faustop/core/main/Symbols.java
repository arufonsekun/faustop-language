// package faustop.core.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

class Symbol {
    
    public static final Map<String, String> symbols;
    static {
        Map<String, String> aMap = new HashMap<String, String>();
        aMap.put("int", "keyword");
        aMap.put("double", "keyword");
        aMap.put("char", "keyword");
        aMap.put("stringue", "keyword");
        symbols = Collections.unmodifiableMap(aMap);
    }
    
}