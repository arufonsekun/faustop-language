package faustop.core.main;

import java.util.HashMap;
import java.util.ArrayList;
import faustop.core.vars.*;
import faustop.core.main.util.*;
import faustop.core.main.*;

public class Interpreter {

	// TODO: REMOVE THIS GAMBI
	public ArrayList<Node> testa = new ArrayList<Node>();

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
			// System.out.println(child.key().getType());
			// System.out.println(child.parent().children());
            if (Symbols.isKeyWord(child.key())) {
                type = child.key().getName();

			} else if (Symbols.isIdentifier(child.key())) {
                name = child.key().getName();

			} else if (Symbols.isExpression(child.key())) {
                this.traverse(child.parent());
				value = ExpressionParser.eval(this.testa);
            }
        }

        // System.out.println("miauaua"+value);

        if (this.isString(type)) {
            // String_ a = new String_(name, "");
            // strMap.put(name, a);
        } else if (this.isInt(type)) {
            Integer_ c = new Integer_(name, value);
            Memory.intMap.put(name, c);
        } else if (this.isDouble(type)) {
            Double_ d = new Double_(name, value);
            Memory.doubleMap.put(name, d);
        } else {
            // Boolean_ e = new Boolean_(name, true);
            // booMap.put(name, e);
        }
        System.out.println(Memory.intMap.get(name).getValue());
        //System.out.println(strMap.get(name).getType() + " "+strMap.get(name).getName());
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

	// TODO: THIS IS A GAMBI
	private void traverse(Node pRoot) {
        /*
         * Traverse through the nodes of the Tree.
         * */


        for (Node child : pRoot.children()) {
            this.traverse(child);
        }

		if (pRoot.parent() != null
			&& pRoot.parent().key().getType().equals("EXP")) {
			this.testa.add(pRoot);
			// System.out.println("AJKSBDSABHUSAD");
			// System.out.println(pRoot.key().getType());

		}
    }

}
