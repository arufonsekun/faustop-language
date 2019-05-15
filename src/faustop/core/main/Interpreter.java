package faustop.core.main;

import java.util.HashMap;
import java.util.ArrayList;
import faustop.core.vars.*;
import faustop.core.main.util.*;
import faustop.core.main.*;

/*

 * */

public class Interpreter {

	// TODO: REMOVE THIS GAMBI
	public ArrayList<Node> testa = new ArrayList<Node>();

    public void run(Node pTreeRoot) {

        if (pTreeRoot == null) return;

        for (Node child : pTreeRoot.children()) {

			//System.out.println(child.key().getName() + " " + child.key().getType());
            if (child.key().getType().equals("keywordtype")) {
				//olokinho, oloko, string, bool
                this.newVariable(child.parent());

			}
			
            //System.out.println(child.key().getType());

            this.run(child);
        }

    }

	//TODO: find a better name ot this method
	private void handleBuiltIn(Node pParent) {
		String value;
		for (Node child : pParent.children()) {
			if (Symbols.isExpression(child.key())){
				this.testa.add(child);
				value = ExpressionParser.eval(this.testa);
				System.out.println(value);
				System.out.println(child.key().getType()+" "+child.key().getName());
			}
		}
	}

    private void newVariable(Node pParent) {
        String type="", name="", value="";
        boolean assign;

        for (Node child : pParent.children()) {
            if (Symbols.isKeyWord(child.key())) {
                type = child.key().getName();

			} else if (Symbols.isIdentifier(child.key())) {
                name = child.key().getName();

			} else if (Symbols.isExpression(child.key())) {
				// this.testa.clear();
                // this.traverse(child.parent());
				// value = ExpressionParser.eval(this.testa);
				value = ExpressionParser.eval(child.children());
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
        // xSystem.out.println(Memory.intMap.get(name).getValue());
        //System.out.println(strMap.get(name).getType() + " "+strMap.get(name).getName());
		//testa.clear();
	}

    private boolean isString(String pTType) {
        return pTType.equals("bicho");
    }

    private boolean isInt(String pTType) {
        return pTType.equals("olokinho");
    }

    private boolean isDouble(String pTType) {
        return pTType.equals("oloko");
    }

    private boolean isBool(String pTType) {
        return pTType.equals("paiseuropa");
    }

	// TODO: THIS IS A GAMBI
	/*
	* Traverse through the nodes of the Tree.
	* */
	private void traverse(Node pRoot) {

        for (Node child : pRoot.children()) {
			System.out.println(pRoot.key().getName());
            this.traverse(child);
        }

		if (pRoot.parent() != null
			&& pRoot.parent().key().getType().equals("EXP")) {
			this.testa.add(pRoot);
			// System.out.println("AJKSBDSABHUSAD");

		}
    }

}
