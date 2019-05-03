//package faustop.core.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Lexer {
    /*
     * Represents a Lexer.
     * A lexer is the responsible for executing the
     * lexical analisys stage.
     *
     * Author: Jean Carlo Hilger.
     * E-mail: hilgerjeancarlo@gmail.com.
     * */

    // the full input (.fau) code
    private String code;
    // last position reading the code
    private int codePosition;
	// last position in the code where a '\n' was found
	private int lastRow;

    public void setCode(String pCode) {
        this.code = pCode;
    }

    public Token getNextToken() {
        /*
         * Reads the code until a Token is identified.
         * Once found, the Token is returned.
         * */

		String lexeme = this.getLexeme();

		String type = Symbols.symbols.get(lexeme);

		// Pattern reg = Pattern.compile("([A-Za-z])\\w+");
		// ^[a-zA-Z_$][a-zA-Z_$0-9]*$
		// "^[_a-z]\\w*$"
		// Matcher mat = reg.matcher(lexeme);

        if (Pattern.matches("(_|A-z)(\\w+)", "2")) {
            System.out.println("ASADDSDASSDSD");
        }

		if (type == null && lexeme != null && !lexeme.isEmpty()) {
			type = "identificador";

		// ERROR: invalid caracter or word;
		} else if (type == null) {
			System.out.println("cannot find symbol");
			return null;
		}

		// no more available tokens
		if (lexeme.isEmpty()) {
			return null;

		} else {
			return new Token(type, lexeme, 1, 2);
		}
    }

	private String getLexeme() {
        /*
         * Reads the code and returns the smaller
         * meaningful portion of the string.
		 * If there is no more lexemes to be build,
		 * returns an empty String.
         * */

        String lexeme = "";

        for ( ; this.codePosition < this.code.length(); this.codePosition++) {
			// System.out.print(this.codePosition);

			// gambiarra? nao
			if ((this.codePosition > 0 &&
				 this.isMathDelimiter(this.code.charAt(this.codePosition-1))) &&
				(this.code.charAt(this.codePosition) == '=') &&
				!lexeme.isEmpty()) {

				this.consumeBlanks();
				this.codePosition++;
				return lexeme + this.code.charAt(this.codePosition-1);
			}

			// delimiters check
			if ((this.isDelimiter(this.code.charAt(this.codePosition)) ||
				(this.codePosition > 0 &&
				 this.isDelimiter(this.code.charAt(this.codePosition-1))))) {

				if (this.code.charAt(this.codePosition) == '\n') {
					this.lastRow = this.codePosition;
				}

				this.consumeBlanks();
				if (!lexeme.isEmpty()) return lexeme;
			}

			// comments check
			if (this.code.charAt(this.codePosition) == '?') {
				this.consumeComments();
				this.consumeBlanks();
			}

			if (this.codePosition >= this.code.length()) return lexeme;
			lexeme += this.code.charAt(this.codePosition);
        }

        return lexeme;
    }

	private void consumeBlanks() {
		/*
		 * Ignores the spaces (' ') in the input code
		 * when tokenizing.
		 * */

		while (this.codePosition < this.code.length() &&
			   (this.code.charAt(this.codePosition) == ' ' ||
			    this.code.charAt(this.codePosition) == '\n')) {
			this.codePosition++;
		}
	}

	private void consumeComments() {
		/*
		 * Ignores everything until a '\n' is found.
		 * Called when a '?' (comment) is found.
		 * */

		while (this.code.charAt(this.codePosition) != '\n' &&
		       this.codePosition < this.code.length()) {
		 	this.codePosition++;
		}
	}

	private boolean isDelimiter(char x) {
		/*
		 * Utility function checks whether or not
		 * the given char is a delimiter.
		 * */

		char[] delimiters = {'(', ')', '{', '}', '+', '*', '-', '/',
		                     '=', ';', '?', '\n', '\'', '\"', ' '};

		for (int i = 0; i < delimiters.length; i++) {
			if (delimiters[i] == x) {
				return true;
			}
		}

		return false;
	}

	private boolean isMathDelimiter(char x) {
		/*
		* Utility function checks whether or not
		* the given char is a MATH delimiter.
		* */

		char[] delimiters = {'+', '*', '-', '/', '>', '<'};

		for (int i = 0; i < delimiters.length; i++) {
			if (delimiters[i] == x) {
				return true;
			}
		}

		return false;
	}

}
