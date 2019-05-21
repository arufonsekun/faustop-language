package faustop.core.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import faustop.core.main.util.Token;
import faustop.core.main.util.Symbols;

/*
 * Represents a Lexer.
 * A lexer is the responsible for executing the
 * lexical analisys stage.
 *
 * @author Jean Carlo Hilger <hilgerjeancarlo@gmail.com>.
 *
 * @author: Junior Vitor Ramisch <junior.ramisch@gmail.com>.
 * */
 
public class Lexer {

    // the full input (.fau) code
    private String code;
    // position reading the code
    private int codePosition;

  	private boolean openQuote = false;

    public void setCode(String pCode) {
        this.code = pCode;
    }

    /*
     * Reads the code until a Token is identified.
     * Once found, the Token is returned.
     * */
    public Token getNextToken() {

		String lexeme = this.getLexeme();

		String type = Symbols.symbols.get(lexeme);

		if (!lexeme.equals("\"") && this.openQuote) {
			type = "literal";
		}

        // lexeme not found in symbols map
		if (type == null && lexeme != null && !lexeme.isEmpty()) {
			if (Pattern.matches("([A-z]|_)(\\w*)", lexeme)) {
				type = "identifier";

			} else if (Pattern.matches("^([0-9]*)$", lexeme)
					   || Pattern.matches("^(?:0|[1-9][0-9]*)\\.[0-9]+$", lexeme)) {
				type = "literal";

			} else {
				System.out.println("cannot find symbol");
				return null;

			}

		// ERROR: invalid caracter or word;
		} else if (type == null) {
			return null;
		}

		// no more available tokens
		if (lexeme.isEmpty()) {
			return null;

		} else {
			return new Token(type, lexeme);
		}
    }

    /*
     * Reads the code and returns the smaller
     * meaningful portion of the string.
     * If there is no more lexemes to be build,
     * returns an empty String.
     * */
	private String getLexeme() {

        String lexeme = "";
        char current, previous;

        while (this.codePosition < this.code.length()) {
            current = this.code.charAt(this.codePosition);
            previous = this.codePosition > 0 ? this.code.charAt(this.codePosition-1) : 0;

            // previous is a `MathDelimiter`
            boolean prevIsMathDel = this.codePosition > 0
                                        && this.isMathDelimiter(previous);
            // previous is a `Delimiter`
            boolean prevIsDel = this.codePosition > 0
                                    && this.isDelimiter(previous);

            if (current == '\"') {
                return this.checkStringLiteral(lexeme);

            // for operands with two chars (e.g. >=, <=)
            } else if (!this.openQuote
                   && prevIsMathDel && current == '=' && !lexeme.isEmpty()) {
                this.codePosition++;
                this.consumeBlanks();
                
                return lexeme + current;

            } else if (!this.openQuote
                       && (this.isDelimiter(current) || prevIsDel)) {
                this.consumeBlanks();
                
                if (!lexeme.isEmpty()) return lexeme;
            }

            // comment check
            if (!this.openQuote
                && (current == '?' || previous == '?')) {
				this.consumeComments();
				this.consumeBlanks();
			}

			if (this.codePosition >= this.code.length()) return lexeme;
			lexeme += this.code.charAt(this.codePosition);;
            this.codePosition++;
        }
        
        return lexeme;
    
    }

    /*
     * Utility method ignores the blank spaces (e.g. ' ', '\t', '\n') in the input code
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
     * Utility method ignores everything until a `\n` is found.
     * Called when a `?` (comment) is found.
     * */
	private void consumeComments() {
        
		while (this.codePosition < this.code.length() 
               && this.code.charAt(this.codePosition) != '\n') {
		 	this.codePosition++;
		}
        
	}

    /*
     * Utility method checks whether or not
     * the given char is a delimiter.
     * */
	private boolean isDelimiter(char x) {

		char[] delimiters = {'(', ')', '{', '}', '+', '*', '-', '/',
		                     '=', ';', '?', '\n', '\"', ' ', '<',
						 	 '>', '^', '!'};

		for (int i = 0; i < delimiters.length; i++) {
			if (delimiters[i] == x) {
				return true;
			}
		}

		return false;
        
	}

    /*
     * Utility method checks whether or not
     * the given char is a MATH delimiter.
     * */
	private boolean isMathDelimiter(char x) {

		char[] delimiters = {'>', '<', '!', '='};

		for (int i = 0; i < delimiters.length; i++) {
			if (delimiters[i] == x) {
				return true;
			}
		}

		return false;
        
	}
    
    /*
     * Utility method decides how to proceed when a
     * String literal is starting or ending.
     * */
    private String checkStringLiteral(String pLexeme) {
        
        if (!this.openQuote && !pLexeme.equals("")) return pLexeme;

        if (this.openQuote && !pLexeme.equals("")) {
       
            return pLexeme;

        } else if (!this.openQuote) {
            this.openQuote = !this.openQuote;
            this.codePosition++;

            return "\"";
        }

        this.openQuote = !this.openQuote;
        this.codePosition++;

        return "\"";
        
    }

}
