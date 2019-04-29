//package faustop.core.main;

class Lexer {

    // the full input (.fau) code
    private String code;

    // last position reading the code
    private int codePosition;

	//
	private final char[] delimiters = {'(', ')', '{', '}', '+', '*', '-', '/',
	                                   '=', ';', '?', '\n', '\'', '\"'};

    public void setCode(String pCode) {
        this.code = pCode;
    }

    public Token getNextToken() {
        /*
         * Reads the code until a Token is identified.
         * Once found, the Token is returned.
         * */

        return new Token("a", "b", 1, 2);
    }

	public String getLexeme() {
        /*
         * Reads the code and returns the smaller
         * meaningful portion of the string.
         * */

        String lexeme = "";
        for ( ; this.codePosition < this.code.length(); this.codePosition++) {
            if (this.contains(this.code.charAt(this.codePosition))) {
                this.consumeBlanks();

                return lexeme;
			}

			if (this.code.charAt(this.codePosition) == '?') {
				this.consumeComments();
				this.consumeBlanks();
			}

			if (this.codePosition >= this.code.length()) {
				return "";
			}

			lexeme += this.code.charAt(this.codePosition);

        }

        return lexeme;
    }

	/*public String getLexeme() {
        //
         * Reads the code and returns the smaller
         * meaningful portion of the string.
         //*

        String lexeme = "";
        for ( ; this.codePosition < this.code.length(); this.codePosition++) {
            if (this.code.charAt(this.codePosition) == ' ' ||
				this.code.charAt(this.codePosition) == '\n') {
                this.consumeBlanks();

                return lexeme;

			}

			if (this.code.charAt(this.codePosition) == '?') {
				this.consumeComments();
				this.consumeBlanks();

			}

			if (this.codePosition >= this.code.length()) {
				return "";
			}

			lexeme += this.code.charAt(this.codePosition);

        }

        return lexeme;
    }*/

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
		 * Ignores the comments ('?') in the input code
		 * when tokenizing.
		 * */

		 while (this.code.charAt(this.codePosition) != '\n' &&
		 		this.codePosition < this.code.length()) {
			 this.codePosition++;
			 // System.out.println("adafgwaffw ++" + this.codePosition);
		 }
	}

	private boolean contains(char x) {
		for (int i = 0; i < this.delimiters.length; i++) {
			if (this.delimiters[i] == x) return true;
		}

		return false;
	}

}
