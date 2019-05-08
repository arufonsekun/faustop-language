//package faustop.core.main;

class Token {
    /*
     * Represents a Token.
     * A token is the smallest lexical unit of
     * the grammar.
     *
     * Author: Jean Carlo Hilger.
     * E-mail: hilgerjeancarlo@gmail.com.
     * */

    private String type;
    private String name;
    private int row;
    private int col;

    public Token(String pType, String pName, int pRow, int pCol) {
        this.setType(pType);
        this.setName(pName);
        this.setRow(pRow);
        this.setCol(pCol);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String pType) {
        this.type = pType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int pRow) {
        this.row = pRow;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int pCol) {
        this.col = pCol;
    }

}
