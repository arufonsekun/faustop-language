import java.util.HashMap;
// import vars.Variable;

class Interpreter {

    // private HashMap<String, Variable> ram = new HashMap<String, Variable>();
    // private Tree parseTree;

    public void run(Node pTreeRoot) {

        if (pTreeRoot == null) return;

        for (Node child : pTreeRoot.children()) {
            this.run(child);
        }
        System.out.println(pTreeRoot.key().getName());

    }

}
