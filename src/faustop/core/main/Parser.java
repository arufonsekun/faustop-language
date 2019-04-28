class Parser {
    String private token; //token being assembled / token assembled
    String private instruction; //may be that AST instead of a string

    public String getLastToken() {
        return this.lastToken;
    }

    public void setLastToken(String pLastToken) {
        this.lastToken = pLastToken;
        // instruction += pLastToken
    }

    public boolean endOfInstruction() {
        // checks if this.instruction is complete (; or })
    }

    public boolean isValidInstruction() {
        // Identity type of instruction
        // Checks if instruction follows all the rules concerning to that
        // kind of instruction
    }


}
