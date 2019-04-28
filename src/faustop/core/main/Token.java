package faustop.core.main;

class Token {

    private String type;
    private String name;
    private int row;
    private int col;

    public Token(String t, String n, int r, int c) {
        this.setType(t);
        this.setName(n);
        this.setRow(r);
        this.setCol(c);
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
