package faustop.core.main;

import java.util.ArrayList;
import java.util.HashMap;

import faustop.core.lib.StandardLibrary;
import faustop.core.main.util.ExpressionParser;
import faustop.core.main.util.If;
import faustop.core.main.util.Memory;
import faustop.core.main.util.Node;
import faustop.core.main.util.Symbols;
import faustop.core.main.util.Token;
import faustop.core.vars.*;


/*
 * Interpreter class travels through the parse tree
 * visiting each instruction node and executing it.
 *
 * @author Junior Vitor Ramisch <junior.ramisch@gmail.com>
 * */

public class Interpreter {

    /*
     * Method responsible for executing the parse tree.
     * That is, walk through the tree converting
     * interpreting it.
     * */
    public void run(Node pTreeRoot) {

        if (pTreeRoot == null) return;

        for (Node child : pTreeRoot.children()) {

            String instrName = child.key().getName();

            // variable definition
            if (instrName.equals(Symbols.KW_TYPE)) {
                this.newVariable(child);

            // built-in function
			} else if (instrName.equals(Symbols.KW_BI)) {
                this.handleBuiltIn(child);

            // flow controller (if or while)
            } else if (instrName.equals(Symbols.KW_FC))  {

                String instKw = child.children().get(0).key().getName();

                // if
                if (instKw.equals("eagora")) {
                    Node body = If.evalIf(child);
                    this.run(body);

                // while
                } else if (instKw.equals("churrasqueira")) {
                    Node exp = child.children().get(1);
                    String value = ExpressionParser.eval(exp.children());

                    while (value.equals("true")) {
                        this.run(child.children().get(3));
                        exp = child.children().get(1);
                        value = ExpressionParser.eval(exp.children());
                    }
                }

            // variable assignment (without declare it)
            } else if (instrName.equals("identifier")
                       && child.key().getType().equals("INSTR")) {
                this.changeVariable(child);
            }

        }

    }

    /*
     * Handles the built-in function calls.
     * */
	private void handleBuiltIn(Node pParent) {

		String command = pParent.children().get(0).key().getName();

        if(command.equals("mostrai") || command.equals("mostrailn")) {
    		Node exp = pParent.children().get(1);
			String value = ExpressionParser.eval(exp.children());

    		if (command.equals("mostrai")) {
    			StandardLibrary.mostrai(value);

    		} else if (command.equals("mostrailn")) {
    			StandardLibrary.mostrailn(value);

            }

        } else {
            StandardLibrary.entrai(pParent.children());

        }

	}

    /*
     * Creates a new variable in the proper
     * place in `Memory`.
     * Used for variable declarations (with or without
     * assignment).
     * */
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
            StringType s= new StringType(name, value);
            Memory.stringMap.put(name, s);

	    } else if (Symbols.isInt(type)) {
            IntegerType i = new IntegerType(name, value);
            Memory.intMap.put(name, i);

		} else if (Symbols.isDouble(type)) {
            DoubleType d = new DoubleType(name, value);
            Memory.doubleMap.put(name, d);

		} else {
            BooleanType b = new BooleanType(name, value);
            Memory.booleanMap.put(name, b);
        }

    }

    /*
     * Change the value of a variable that already exists
     * in `Memory`.
     * Used for variable assignments (not declaration).
     * */
    private void changeVariable(Node pParent) {

        Token tok = pParent.children().get(0).key();
        String varName = tok.getName();
        String newValue = ExpressionParser.eval(pParent.children().get(2).children());

        if (Memory.stringMap.containsKey(varName)) {
            Memory.stringMap.get(varName).setValue(newValue);

        } else if (Memory.doubleMap.containsKey(varName)) {
            Memory.doubleMap.get(varName).setValue(newValue);

        } else if (Memory.intMap.containsKey(varName)) {
            Memory.intMap.get(varName).setValue(newValue);

        } else {
            System.out.println("Variable `"+varName+"` not defined");
        }

    }

}
