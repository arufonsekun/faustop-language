import java.util.HashMap;
import java.util.ArrayList;
import vars.*;

class Interpreter {

    private HashMap<String, Integer_> ramInt = new HashMap<String, Integer_>();
    private HashMap<String, Double_> ramDob = new HashMap<String, Double_>();
    private HashMap<String, Char_> ramCh = new HashMap<String, Char_>();
    private HashMap<String, String_> ramStr = new HashMap<String, String_>();
    private HashMap<String, Boolean_> ramBo = new HashMap<String, Boolean_>();

    public void run(Node pTreeRoot) {

        if (pTreeRoot == null) return;

        for (Node child : pTreeRoot.children()) {

            if (child.key().getType().equals("keywordtype")) {
                this.newVariable(child.parent());
            }

            //System.out.println(child.key().getType());

            this.run(child);
        }

    }

    private void newVariable(Node pParent) {
        String type="", name="", value="";
        boolean assign;

        for (Node child : pParent.children()) {
            if (Symbols.isKeyWord(child.key())) {
                type = child.key().getName();
            }
            else if (Symbols.isIdentifier(child.key())) {
                name = child.key().getName();
            }
            else if (Symbols.isExpression(child.key())) {
                //value = Expression parse handler
            }
        }

        if (this.isString(type)) {
            String_ a = new String(0)
        } else if (this.isChar(type)) {

        } else if (this.isInt(type)) {

        } else if (this.isDouble(type)) {

        } else {

        }

        // System.out.println(ram.get(a).getName());
        // ram.put(name, var);
    }

    private boolean isString(String pToken) {
        return pToken.getName().equals("oloko");
    }

    private boolean isChar(String pToken) {
        return pToken.getName().equals("olokinho");
    }

    private boolean isInt(String pToken) {
        return pToken.getName().equals("inte");
    }

    private boolean isDouble(String pToken) {
        return pToken.getName().equals("double");
    }

    private boolean isBool(String pToken) {
        return pToken.getName().equals("bool");
    }

}
