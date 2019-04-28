class Char extends Variable {

    private char value;

    public Char(String pName, char pValue) {
        /* *
           * Char constructor, has a name and a value as parameters
           * super() calls Variable constructor
           * */
        super(pName, "CHAR");
        setValue(pValue);
    }

    public void setValue(char pValue) {
        this.value = pValue;
    }

    public char getValue() {
        return this.value;
    }

    public boolean greaterThan(Char pValue) {
        return (this.value > pValue.getValue());
    }

    public boolean lessThan(Char pValue) {
        return (this.value < pValue.getValue());
    }

    public boolean greaterThanOrEqualTo(Char pValue) {
        return (this.value >= pValue.getValue());
    }

    public boolean lessThanOrEqualTo(Char pValue) {
        return (this.value <= pValue.getValue());
    }

    public boolean equals(Char pValue) {
        return (this.value == pValue.getValue());
    }

    public boolean differs(Char pValue) {
        return (this.value != pValue.getValue());
    }

}
