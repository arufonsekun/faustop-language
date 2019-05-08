package faustop.core.test;
import faustop.core.vars.*;
class Main {
    public static void main(String[] args) {
        Inte a = new Inte("a", "1");
        a.plus(1);
        System.out.println(a.getValue()+1);
    }
}
