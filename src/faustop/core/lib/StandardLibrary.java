package faustop.core.lib;
import faustop.core.vars.*;

public class StandardLibrary {
    public static final void mostrai(String pValue ) {
        System.out.println(pValue);
    }

    public static final void mostrai(Integer_ pVar) {
        System.out.println(pVar.getValue());
    }

    public static final void mostrai(String_ pVar) {
        System.out.println(pVar.getValue());
    }

    public static final void mostrai(Double_ pVar) {
        System.out.println(pVar.getValue());
    }

    public static final void mostrai(Boolean_ pVar) {
        System.out.println(pVar.getValue());
    }
}
