package vars;

public class Char_ extends Variable {

    private char value;

    public Char_(String pName, char pValue) {
        super(pName, "CHAR");
        this.setValue(pValue);
    }

    public void setValue(char pValue) {
        this.value = pValue;
    }

    public char getValue() {
        return this.value;
    }

    public boolean greaterThan(Char_ pObj) {
        return (this.value > pObj.getValue());
    }

    public boolean lessThan(Char_ pObj) {
        return (this.value < pObj.getValue());
    }

    public boolean greaterThanOrEqualTo(Char_ pObj) {
        return (this.value >= pObj.getValue());
    }

    public boolean lessThanOrEqualTo(Char_ pObj) {
        return (this.value <= pObj.getValue());
    }

    public boolean equals(Char_ pObj) {
        return (this.value == pObj.getValue());
    }

    public boolean differs(Char_ pObj) {
        return (this.value != pObj.getValue());
    }

}
