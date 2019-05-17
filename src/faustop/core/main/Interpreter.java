package faustop.core.main;

import java.util.HashMap;
import java.util.ArrayList;
import faustop.core.vars.*;
import faustop.core.main.util.*;
import faustop.core.main.*;
import faustop.core.lib.*;

/*
 * Interpreter class travels through the parse tree
 * visiting each instruction node and executing it.
 * */
public class Interpreter {

    public void run(Node pTreeRoot) {
        if (pTreeRoot == null) return;
///////////// BUG TODO : THIS CODE IS SHITTY
        for (Node child : pTreeRoot.children()) {
            // System.out.println(child.key().getName());

            if (child.key().getName().equals("keywordtype")) {
                this.newVariable(child);

			} else if (child.key().getName().equals("keywordbuiltin")) {
				this.handleBuiltIn(child);

            } else if (child.key().getName().equals("keywordflowcontroller"))  {
                if (child.children().get(0).key().getName().equals("eagora")) {
                    Node body = If.evalIf(child);
                    this.run(body);

                } else if (child.children().get(0).key().getName().equals("churrasqueira")) {
                    // System.out.println("\tmerdaaaaaaaa");
                    Node exp = child.children().get(1);
                    String value = ExpressionParser.eval(exp.children());

                    while (value.equals("true")) {
                        this.run(child.children().get(3));
                        exp = child.children().get(1);
                        value = ExpressionParser.eval(exp.children());
                    }
                }
///////////// BUG TODO : end of "THIS CODE IS SHITTY"
            } else if (child.key().getName().equals("identifier")
                       && child.key().getType().equals("INSTR")) {
                this.changeVariable(child);
            }

            //this.run(child);
        }

    }

	private void handleBuiltIn(Node pParent) {

		String value;
		String command = pParent.children().get(0).key().getName();
		Node exp = pParent.children().get(1);

		if (command.equals("mostrai")) {
			value = ExpressionParser.eval(exp.children());
			StandardLibrary.mostrai(value);

		} else if (command.equals("mostrailn")) {
            value = ExpressionParser.eval(exp.children());
			StandardLibrary.mostrailn(value);
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
				value = ExpressionParser.eval(child.children());

            }
        }

        if (Symbols.isString(type)) {
            String_ a = new String_(name, value);
            Memory.stringMap.put(name, a);

	    } else if (Symbols.isInt(type)) {
            Integer_ c = new Integer_(name, value);
            Memory.intMap.put(name, c);

		} else if (Symbols.isDouble(type)) {
            Double_ d = new Double_(name, value);
            Memory.doubleMap.put(name, d);

		} else {
            // Boolean_ e = new Boolean_(name, true);
            // booMap.put(name, e);
        }
	}

    private void changeVariable(Node pParent) {
        // System.out.println("CUCUCUC");
        Token tok = pParent.children().get(0).key();
        String varName = tok.getName();
        String newValue = ExpressionParser.eval(pParent.children().get(2).children());

        if (Memory.stringMap.containsKey(varName)) {
            Memory.stringMap.get(varName).setValue(newValue);

        } else if (Memory.doubleMap.containsKey(varName)) {
            Memory.doubleMap.get(varName).setValue(newValue);

        } else if (Memory.intMap.containsKey(varName)) {
            Memory.intMap.get(varName).setValue(newValue);
        }
    }

}
