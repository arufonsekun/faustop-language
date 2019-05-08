package vars;

public class Stringue extends Variable {

    public Stringue(String name, String pValue) {
        super(name, "STRING", pValue);
    }

    public void setValue(String pValue) {
        super.setValue(pValue);
    }

    public boolean greaterThan(Stringue pObj) {
        return this.lexicalOrder(pObj.getValue());
    }

    public boolean lessThan(Stringue pObj) {
        if (this.equal(pObj)) return false;
        return !this.lexicalOrder(pObj.getValue());
    }

    public boolean equal(Stringue pObj) {
        return this.getValue().equals(pObj.getValue());
    }

    public boolean greaterThanOrEqualTo(Stringue pObj) {
        return (this.greaterThan(pObj) || this.equal(pObj));
    }

    public boolean lessThanOrEqualTo(Stringue pObj) {
        return (this.lessThan(pObj) || this.equal(pObj));
    }

    /*
    * lexicalOrder() returns true if value class attribute comes before
    * in the lexicographic order, false otherwise.
    * */
    private boolean lexicalOrder(String pValue) {

        String value = this.getValue();
        int valueSize = value.length();
        int pValueSize = pValue.length();
        int size = valueSize < pValueSize ? valueSize : pValueSize;

        for (int i = 0; i < size; i++) {
            if (value.charAt(i) != pValue.charAt(i)) {
                if (value.charAt(i) > pValue.charAt(i)) return true;
                else return false;
            }
        }

        if (value.charAt(size-1) == pValue.charAt(size-1))
            if (valueSize == pValueSize) return false;
            else return valueSize > pValueSize;
        return false;

    }

}
