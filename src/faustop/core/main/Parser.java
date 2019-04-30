import java.util.LinkedList;


class Parser {

    private Token token;
    private LinkedList<Token> instruction = new LinkedList<>();

    public Token getToken() {
        return this.token;
    }

    public void setToken(Token pToken) {
        /*
         * Apart from being a setter of this.token,
         * also appends the token to the current instruction.
         * */

        this.token = pToken;
        this.instruction.add(pToken);
    }

    public LinkedList<Token> getInstruction() {
        return this.instruction;
    }

    public boolean endOfInstruction() {
        /*
         * Checks if a final delimiter was found.
         * */

        return this.instruction.getLast().getName().equals(";");
        // missing conditional and loop cases
    }

    public boolean isValidInstruction() {
        /*
         * Identity type of instruction.
         * Checks if instruction follows all the rules concerning to that
         * kind of instruction.
         * The Interpreter must, then, if instruction is valid, execute it;
         * else, it must throw an error. Nevertheless, the instruction
         * LinkedList must be clear afterwards.
         * */

        return false;
    }
}
