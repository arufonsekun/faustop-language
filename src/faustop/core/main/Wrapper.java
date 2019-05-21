package faustop.core.main;

import java.io.File;
import java.util.Scanner;

import faustop.core.main.util.Token;
import faustop.core.main.util.Tree;

/*
 * Wrapper class packs lexer, parser and interpreter
 * functionalities together.
 *
 * @author Junior Vitor Ramisch <junior.ramisch@gmail.com>
 * */

public class Wrapper {

    /*
     * Main method calls everything else.
     * */
    public static void main(String[] args) {

        Token token;
        Lexer lexer = new Lexer();
        Parser parser = new Parser();
        Interpreter interpreter = new Interpreter();
        Tree parseTree;

		String sourceCode = Wrapper.readFile(args);
		if (sourceCode == null || sourceCode.equals("")) return;

        lexer.setCode(sourceCode); // set source code
        token = lexer.getNextToken(); // get the first token

        while (token != null) {
            parser.addToken(token); // adds the token into the token list
            token = lexer.getNextToken();
        }

        // builds the parse tree based on the token list
        parser.buildParseTree();

        parseTree = parser.getParseTree();
        interpreter.run(parseTree.root());

    }

    /*
     * Reads the .fau file from system and returns
     * a String representing its contents.
     * */
	private static String readFile(String[] args) {

        try {

          	File file = new File(args[0]);
            Scanner reader = new Scanner(file);

            String sourceCode = "";
            String buffer = "";

            while(reader.hasNextLine()) {
                buffer = reader.nextLine();
                sourceCode = sourceCode.concat(buffer + '\n');
            }

			return sourceCode;

		} catch (Exception e) {

			System.out.println("File opening error");
			e.printStackTrace();
			return null;

        }

    }
}
