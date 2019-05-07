import java.io.File;
import java.util.Scanner;

class Wrapper {
    /*
     * For now Wrapper will be used just for testing Lexer,
     * Parser in the same class. In the future Wrapper class
     * will packs lexer, parser and semantic analyzer
     * functionalities together. Wrapper class is reading
     * each .fau file line and dividing each line in tokens.
     * Author: Junior Vitor Ramisch
     * E-mail: junior.ramisch@gmail.com
     * */
     public static void main(String[] args) {

         Token token;
         Lexer lexer = new Lexer();
         Parser parser = new Parser();
         Interpreter interpreter = new Interpreter();
         Tree parseTree;//parseTree is passed to interpreter

         //try-catch statement required to read files
         try {
             File file = new File(args[0]);
             Scanner reader = new Scanner(file);

             String sourceCode = "";
             String buffer = "";

             while(reader.hasNextLine()) {

                 buffer = reader.nextLine();
                 sourceCode = sourceCode.concat(buffer+'\n');
             }

             // System.out.println(sourceCode);

             lexer.setCode(sourceCode);//set source code
             token = lexer.getNextToken(); //get the first token

             while (token != null) {
                 parser.addToken(token);// adds the token into the token list
                 // System.out.println("Token value: "+token.getName()+" type: "+token.getType());
                 token = lexer.getNextToken();
             }

             // System.out.println(sourceCode);

             parser.buildParseTree();//build the parse tree based on the token list
             parseTree = parser.getParseTree();//get the parseTree
             interpreter.run(parseTree.root());

         } catch (Exception e) {
             System.out.println("File opening error");
             e.printStackTrace();
         }
     }
}
