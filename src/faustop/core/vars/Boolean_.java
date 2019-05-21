package faustop.core.vars;


public class Boolean_ extends Variable {

    /*
     * Boolean type represents a faustop logic
     * value.
     * @ author: Junior Vitor Ramisch
     * E-mail: junior.ramisch@gmail.com
     * */
    private boolean value;

    public Boolean_(String pName, String pValue) {
        super(pName, "BOOLEAN");
        this.setValue(pValue);
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(String pValue) {
        this.value = Boolean.parseBoolean(pValue);
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
