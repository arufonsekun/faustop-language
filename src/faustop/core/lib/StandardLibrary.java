package faustop.core.lib;

import java.util.ArrayList;
import java.util.Scanner;

import faustop.core.main.util.Memory;
import faustop.core.main.util.Node;

/*
 * StandardLibrary class defines the Faustop native methods.
 *
 * @author Junior Vitor Ramisch <junior.ramisch@gmail.com>
 * */

public class StandardLibrary {

	/*
	 * mostrailn()` shows the given content.
	 * */
    public static final void mostrai(String pValue) {

        System.out.print(pValue);

    }

	/*
	 * `mostrailn()` shows the given content and adds
	 * a newline character.
	 * */
    public static final void mostrailn(String pValue) {

        System.out.println(pValue);

    }

	/*
	 * `entrai()` method reads the content from the terminal.
	 * */
	public static final void entrai(ArrayList<Node> children) {

        Scanner input = new Scanner(System.in);
        String newValue = input.nextLine();

        Node exp = children.get(1);
        String varName = exp.children().get(1).key().getName();

        if (Memory.intMap.containsKey(varName)) {
            Memory.intMap.get(varName).setValue(newValue);

        } else if (Memory.doubleMap.containsKey(varName)) {
            Memory.doubleMap.get(varName).setValue(newValue);

        } else if (Memory.stringMap.containsKey(varName)) {
            Memory.stringMap.get(varName).setValue(newValue);

        } else {
            System.out.println("Variable `"+varName+"` not defined");

        }

    }
}
