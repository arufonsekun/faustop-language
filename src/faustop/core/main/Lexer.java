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
	// TODO: FIX THIS GAMBIARRA
	private boolean openQuote = false;
	private String lastLexeme = "";

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

		// System.out.println(this.lastLexeme.equals("\""));
		// System.out.println(" " + lexeme);
		if (type == null && lexeme != null && !lexeme.isEmpty()) {
			if (Pattern.matches("([A-z]|_)(\\w*)", lexeme)
                && !this.lastLexeme.equals("\"")) {
				type = "identifier";

			} else if (Pattern.matches("[0-9]+", lexeme)
                       || this.lastLexeme.equals("\"")) {
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

	private String getLexeme() {
        /*
         * Reads the code and returns the smaller
         * meaningful portion of the string.
		 * If there is no more lexemes to be build,
		 * returns an empty String.
         * */

        // TODO: everything in the middle of " should be a single literal token
        String lexeme = "";
        char current, previous;

        for ( ; this.codePosition < this.code.length(); this.codePosition++) {
            current = this.code.charAt(this.codePosition);
            previous = this.codePosition > 0 ? this.code.charAt(this.codePosition-1) : 0;

            if (current == '\"') {

                if (!this.openQuote && this.isDelimiter(previous)) {
                    this.openQuote = !this.openQuote;
                    return lexeme;
                }

                if (this.openQuote && !lexeme.equals("")) {

                    this.openQuote = !this.openQuote;
                    return lexeme;
                }

				this.codePosition++;
				return "\"";

			// comments check
			}

			// gambiarra? nao
			else if (!this.openQuote && (this.codePosition > 0 &&
				 this.isMathDelimiter(previous)) &&
				(this.code.charAt(this.codePosition) == '=') &&
				!lexeme.isEmpty()) {

				this.consumeBlanks();
				this.codePosition++;
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
		 * Utility function ignores everything until a '\n' is found.
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
		                     '=', ';', '?', '\n', '\'', ' ', '<',
						 	 '>', '^'};

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

		char[] delimiters = {'+', '*', '-', '/', '>', '<', '^'};

		for (int i = 0; i < delimiters.length; i++) {
			if (delimiters[i] == x) {
				return true;
			}
		}

		return false;
	}

}
