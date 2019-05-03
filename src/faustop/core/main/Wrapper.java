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

         Lexer lutor = new Lexer();

         //try-catch statement required to read files
         try {
             File file = new File(args[0]);
             Scanner reader = new Scanner(file);

             String sourceCode = "";
             String buffer = "";

             while(reader.hasNextLine()) {

                 buffer = reader.nextLine();
                 sourceCode = sourceCode.concat(buffer);
             }

             System.out.println(sourceCode);

             lutor.setCode(sourceCode);
             Token tokien = lutor.getNextToken();

             //lexer isn't identifing literals
             while (tokien != null) {
                 System.out.println("Token value: "+tokien.getName()+" type: "+tokien.getType());
                 tokien = lutor.getNextToken();
             }

         } catch (Exception e) {
             System.out.println("File opening error");
             e.printStackTrace();
         }
     }
}
