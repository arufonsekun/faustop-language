package faustop.core.vars;


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
        return this.getValue();
    }

    public void setValue(String pValue) {
        this.setValue(pValue);
    }

    public String logicalAnd(Boolean pValue) {
        return Boolean.toString(this.value && pValue);
    }

    public String logicalOr(Boolean pValue) {
        return Boolean.toString(this.value || pValue);
    }

    public String logicalNot() {
        return Boolean.toString(!(this.value));
    }

    public String equal(Boolean pValue) {
        return Boolean.toString(this.value == pValue);
    }

    public String differs(Boolean pValue) {
        return Boolean.toString(this.value != pValue);
    }

}
