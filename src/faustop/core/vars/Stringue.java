class Stringue extends Variable {

    private String value;

    public Stringue(String name, String pValue) {
        super(name, "STRINGUE");
        this.setValue(pValue);
    }

    public void setValue(String pValue) {
        this.value = pValue;
    }

    public String getValue() {
        return this.value;
    }

    public boolean greaterThan(Stringue pObj) {
        return this.lexicalOrder(pObj.getValue());
    }

    public boolean lessThan(Stringue pObj) {
        if (this.equal(pObj)) return false;
        return !this.lexicalOrder(pObj.getValue());
    }

    public boolean equal(Stringue pObj) {
        return this.value.equals(pObj.getValue());
    }

    public boolean greaterThanOrEqualTo(Stringue pObj) {
        return (this.greaterThan(pObj) || this.equal(pObj));
    }

    public boolean lessThanOrEqualTo(Stringue pObj) {
        return (this.lessThan(pObj) || this.equal(pObj));
    }

    private boolean lexicalOrder(String pValue) {

        /*
         * lexicalOrder() returns true if value class attribute comes before
         * in the lexicographic order, false otherwise.
         * */

        int valueSize = this.value.length();
        int pValueSize = pValue.length();
        int size = valueSize < pValueSize ? valueSize : pValueSize;

        for (int i = 0; i < size; i++) {
            if (this.value.charAt(i) != pValue.charAt(i)) {
                if (this.value.charAt(i) > pValue.charAt(i)) return true;
                else return false;
            }
        }

        if (this.value.charAt(size-1) == pValue.charAt(size-1))
            if (valueSize == pValueSize) return false;
            else return valueSize > pValueSize;
        return false;

    }

}
