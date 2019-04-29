class Char extends Variable {

    private char value;

    public Char(String pName, char pValue) {
        super(pName, "CHAR");
        this.setValue(pValue);
    }

    public void setValue(char pValue) {
        this.value = pValue;
    }

    public char getValue() {
        return this.value;
    }

    public boolean greaterThan(Char pObj) {
        return (this.value > pObj.getValue());
    }

    public boolean lessThan(Char pObj) {
        return (this.value < pObj.getValue());
    }

    public boolean greaterThanOrEqualTo(Char pObj) {
        return (this.value >= pObj.getValue());
    }

    public boolean lessThanOrEqualTo(Char pObj) {
        return (this.value <= pObj.getValue());
    }

    public boolean equals(Char pObj) {
        return (this.value == pObj.getValue());
    }

    public boolean differs(Char pObj) {
        return (this.value != pObj.getValue());
    }

}
