//package faustop.core.main;
package faustop.core.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import faustop.core.main.util.*;

/*
 * Represents a Lexer.
 * A lexer is the responsible for executing the
 * lexical analisys stage.
 *
 * Author: Jean Carlo Hilger.
 * E-mail: hilgerjeancarlo@gmail.com.
 *
 * Author: Junior Vitor Ramisch.
 * E-mail: junior.ramisch@gmail.com.
 * */
public class Lexer {

    // the full input (.fau) code
    private String code;
    // last position reading the code
    private int codePosition;
  	// last position in the code where a '\n' was found
  	private int lastRow;
  	// TODO: FIX THIS GAMBIARRA
  	private boolean openQuote = false;
  	private String lastLexeme = "";

    public void setCode(String pCode) {
        this.code = pCode;
    }

    /*
    * Reads the code until a Token is identified.
    * Once found, the Token is returned.
    * */
    public Token getNextToken() {

		String lexeme = this.getLexeme();

        // System.out.println("|" + lexeme + "|");

		String type = Symbols.symbols.get(lexeme);

		if (type == null && lexeme != null && !lexeme.isEmpty()) {
			if (Pattern.matches("([A-z]|_)(\\w*)", lexeme)
                && !this.lastLexeme.equals("\"")) {
				type = "identifier";

			} else if (Pattern.matches("[0-9]+", lexeme)
                       || this.lastLexeme.equals("\"")
					   || Pattern.matches("^(?:0|[1-9][0-9]*)\\.[0-9]+$", lexeme)) {
				// TODO: separate this or in two ifs, and put literalnumber for one and literalstr to anothre
				type = "literal";

			} else {
				System.out.println("cannot find symbol");
				return null;

			}

		// ERROR: invalid caracter or word;
		} else if (type == null) {
			return null;
		}

		this.lastLexeme = new String(lexeme);

		// no more available tokens
		if (lexeme.isEmpty()) {
			return null;

		} else {
			return new Token(type, lexeme, 1, 2);
		}
    }

    /*
    * Reads the code and returns the smaller
    * meaningful portion of the string.
    * If there is no more lexemes to be build,
    * returns an empty String.
    * */
	private String getLexeme() {

        // TODO: REFACTOR THIS METHOD
        String lexeme = "";
        char current, previous;

        while (this.codePosition < this.code.length()) {
            current = this.code.charAt(this.codePosition);
            previous = this.codePosition > 0 ? this.code.charAt(this.codePosition-1) : 0;

            if (current == '\"') {

                if (!this.openQuote && this.isDelimiter(previous)) {
                    this.openQuote = !this.openQuote;
					this.codePosition++;

		            return "\"";

                } else if (this.openQuote && !lexeme.equals("")) {
                    this.openQuote = !this.openQuote;
					// System.out.println("LIXO |" + lexeme + "|");

		            return lexeme;
                }

				this.consumeBlanks();//BUG: here is the inseto
				this.codePosition++;

				return "\"";

            } else if (!this.openQuote
                       && (this.codePosition > 0
                           && this.isMathDelimiter(previous))
                       && (this.code.charAt(this.codePosition) == '=')
                       && !lexeme.isEmpty()) {
                this.codePosition++;
				this.consumeBlanks();
				return lexeme + current;

			// delimiters check
			} else if (!this.openQuote
                       && (this.isDelimiter(current)
				       || (this.codePosition > 0
				            && this.isDelimiter(previous)))) {

                this.consumeBlanks();
				if (!lexeme.isEmpty()) return lexeme;

			// string literal check
			} else if (!this.openQuote && current == '?') {
				this.consumeComments();
				this.consumeBlanks();
			}

			if (this.codePosition >= this.code.length()) return lexeme;
			lexeme += current;
            this.codePosition++;
        }
        return lexeme;
    }

    /*
    * Ignores the blank spaces (e.g. ' ', '\t', '\n') in the input code
    * when tokenizing.
    * */
	private void consumeBlanks() {

		while (this.codePosition < this.code.length() &&
			   (this.code.charAt(this.codePosition) == ' '
			    || this.code.charAt(this.codePosition) == '\n'
                || this.code.charAt(this.codePosition) == '\t')) {
			this.codePosition++;
		}
	}

    /*
    * Utility function ignores everything until a '\n' is found.
    * Called when a '?' (comment) is found.
    * */
	private void consumeComments() {

		while (this.code.charAt(this.codePosition) != '\n' &&
		       this.codePosition < this.code.length()) {
		 	this.codePosition++;
		}
	}

    /*
    * Utility function checks whether or not
    * the given char is a delimiter.
    * */
	private boolean isDelimiter(char x) {

		char[] delimiters = {'(', ')', '{', '}', '+', '*', '-', '/',
		                     '=', ';', '?', '\n', '\'', ' ', '<',
						 	 '>', '^', '!'};

		for (int i = 0; i < delimiters.length; i++) {
			if (delimiters[i] == x) {
				return true;
			}
		}

		return false;
	}

    /*
    * Utility function checks whether or not
    * the given char is a MATH delimiter.
    * */
	private boolean isMathDelimiter(char x) {

		char[] delimiters = {'+', '*', '-', '/', '>', '<', '^', '!'};

		for (int i = 0; i < delimiters.length; i++) {
			if (delimiters[i] == x) {
				return true;
			}
		}

		return false;
	}

}
