import java.util.HashMap;
import faustop.core.vars.Variable;

class Interpreter {

    private HashMap<String, Variable> ram = new HashMap<String, Variable>();
    // private Tree parseTree;

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
        String type, name, value;
        boolean assign;
        Variable var;

        for (Node child : pParent.children()) {
            if (this.isString(child.key())) {
                var = new String_();

            } else if (this.isChar(child.key())) {
                var = new Char_();

            } else if (this.isInt(child.key())) {
                var = new Integer_();

            } else if (this.isDouble(child.key())) {
                var = new Double_();

            } else if (this.isBool(child.key())) {
                var = new Boolean_();

            } else if (this.is)
        }

        //inte a;
        System.out.println((pParent.children()).get(0).key().getName());
        System.out.println((pParent.children()).get(1).key().getName());
        System.out.println((pParent.children()).get(2).key().getName());

        // if (children.get(2).key().getType().equals("delimitersemicolon")) {
        //     this.ram[]
        // }
    }

    private boolean isString(Token pToken) {
        return pToken.getName().equals("oloko");
    }

    private boolean isChar(Token pToken) {
        return pToken.getName().equals("olokinho");
    }

    private boolean isInt(Token pToken) {
        return pToken.getName().equals("inte");
    }

    private boolean isDouble(Token pToken) {
        return pToken.getName().equals("double");
    }

    private boolean isBool(Token pToken) {
        return pToken.getName().equals("bool");
    }

}
