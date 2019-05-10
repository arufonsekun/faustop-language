package main;
import java.util.HashMap;
import java.util.ArrayList;
import vars.*;

class Interpreter {

    public HashMap<String, Integer_> intMap = new HashMap<String, Integer_>();
    public HashMap<String, Double_> douMap = new HashMap<String, Double_>();
    public HashMap<String, String_> strMap = new HashMap<String, String_>();
    public HashMap<String, Boolean_> booMap = new HashMap<String, Boolean_>();

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

        System.out.println(name + " " + type);

        if (this.isString(type)) {
            String_ a = new String_(name, "birl");
            strMap.put(name, a);
        } else if (this.isInt(type)) {
            Integer_ c = new Integer_(name, 12);
            intMap.put(name, c);
        } else if (this.isDouble(type)) {
            Double_ d = new Double_(name, 1.56);
            douMap.put(name, d);
        } else {
            Boolean_ e = new Boolean_(name, true);
            booMap.put(name, e);
        }
        // System.out.println(intMap.get(name).getType() + " "+intMap.get(name).getName());
        // System.out.println(strMap.get(name).getType() + " "+strMap.get(name).getName());
    }

    private boolean isString(String pTType) {
        return pTType.equals("oloko");
    }

    private boolean isChar(String pTType) {
        return pTType.equals("olokinho");
    }

    private boolean isInt(String pTType) {
        return pTType.equals("inte");
    }

    private boolean isDouble(String pTType) {
        return pTType.equals("double");
    }

    private boolean isBool(String pTType) {
        return pTType.equals("bool");
    }

}
