package faustop.core.main;

import java.io.File;
import java.util.Scanner;

import faustop.core.main.util.*;
import faustop.core.main.*;

/*
 * Wrapper class packs lexer, parser and semantic analyzer
 * functionalities together.
 * Author: Junior Vitor Ramisch
 * E-mail: junior.ramisch@gmail.com
 * */
public class Wrapper {

     public static void main(String[] args) {

    	Token token;
        Lexer lexer = new Lexer();
        Parser parser = new Parser();
        Interpreter interpreter = new Interpreter();
        Tree parseTree; //parseTree is passed to interpreter

        // System.out.println(sourceCode);

		String sourceCode = Wrapper.readFile(args);
		if (sourceCode == null || sourceCode.equals("")) return;

        lexer.setCode(sourceCode); //set source code
        token = lexer.getNextToken(); //get the first token

        while (token != null) {
            parser.addToken(token); // adds the token into the token list
            //System.out.println("Name: "+token.getName()+" type: "+token.getType());
            token = lexer.getNextToken();
        }

        parser.buildParseTree(); //build the parse tree based on the token list
        parseTree = parser.getParseTree(); //get the parseTree
        interpreter.run(parseTree.root());

    }

    /*
    * Reads the .fau file from system and returns
    * a String representing it.
    * */
	private static String readFile(String[] args) {

        try {
          	File file = new File(args[0]);
            Scanner reader = new Scanner(file);

            String sourceCode = "";
            String buffer = "";

            while(reader.hasNextLine()) {
                buffer = reader.nextLine();
                sourceCode = sourceCode.concat(buffer+'\n');
            }

			return sourceCode;

		} catch (Exception e) {
			System.out.println("File opening error");
			e.printStackTrace();
			return null;
		}
	}
}
