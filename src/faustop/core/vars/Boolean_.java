package vars;

public class Boolean_ extends Variable {

    /*
     * Boolean type represents a faustop logic
     * value.
     * @ author: Junior Vitor Ramisch
     * E-mail: junior.ramisch@gmail.com
     * */
    private boolean value;

    public Boolean_ (String pName) {
        super(pName, "BOOLEAN");
    }

    public boolean getValue() {
        return super.getValue().equals("true") ? true : false;
    }

    public void setValue(String pValue) {
        this.setValue(pValue);
    }

    public boolean logicalAnd(Boolean_ pObj) {
        return (this.value && pObj.getValue());
    }

    public boolean logicalOr(Boolean_ pObj) {
        return (this.value || pObj.getValue());
    }

    public boolean logicalNot() {
        return !(this.value);
    }

    public boolean equals(Boolean_ pObj) {
        return (this.value == pObj.getValue());
    }

    public boolean differs(Boolean_ pObj) {
        return (this.value != pObj.getValue());
    }

}
