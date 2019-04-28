//package faustop.core.main;

class Lexer {
    
    // the full input (.fau) code
    private String code;
    
    // last position reading the code
    private int codePosition;
    
    // public String getCode() {
    //     return this.code;
    // }
    
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
        
        String lexeme;
        for ( ; this.codePosition; this.codePosition++) {
            if (code.charAt(this.codePosition) == ' ') {
                return lexeme;
            }
            
            lexeme += this.code.charAt(this.codePosition);
        }
    }
}