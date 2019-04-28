class Stringue extends Variable{

    private String value;

    public Stringue(String name, String pValue) {
        super(name, "STRINGUE");
        setValue(pValue);
    }

    public void setValue(String pValue) {
        this.value = pValue;
    }

    public String getValue(){
        return this.value;
    }

    public boolean greaterThan(Stringue pValue) {
        return lexicalOrder(pValue.getValue());
    }

    public boolean lessThan(Stringue pValue) {

        /*
         *
         * */

        if (this.equal(pValue)) return false;
        return !lexicalOrder(pValue.getValue());
    }

    public boolean equal(Stringue pValue){
        return this.value.equals(pValue.getValue());
    }

    public boolean greaterThanOrEqualTo(Stringue pValue) {
        return (greaterThan(pValue) || this.equal(pValue));
    }

    public boolean lessThanOrEqualTo(String pValue) {
        return (lessThan(pValue) || this.equal(pValue));
    }

    private boolean lexicalOrder(String pValue) {

        /*
         * lexicalOrder() returns true if value attribute comes before
         * in the lexicographic order, false otherwise
         * */

        int valueSize = this.value.length();
        int pValueSize = pValue.length();
        int size = valueSize < pValueSize ? valueSize : pValueSize;

        for (int i = 0; i < size; i++){
            if (this.value.charAt(i) != pValue.charAt(i)){
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
