package faustop.core.main.util;

/*
 * Class If has only one method whose purpose is
 * return a Node if his body must be executed (value == "true")
 * otherwise return null.
 *
 * @author Junior Vitor Ramisch <junior.ramisch@gmail.com>
 * */
public class If {
    
    public static Node evalIf(Node pInsIf) {

        Node exp = pInsIf.children().get(1);
        String value = ExpressionParser.eval(exp.children());

        if (value.equals("true")) {
            return pInsIf.children().get(3);

        } else {
            return null;
        }
    
    }

}
