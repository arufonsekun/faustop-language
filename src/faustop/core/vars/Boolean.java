package vars;

public class Boolean extends Variable {

    /*
     * Boolean type represents a faustop logic
     * value.
     * @ author: Junior Vitor Ramisch
     * E-mail: junior.ramisch@gmail.com
     * */
    private boolean value;

    public Boolean (String pName, String pValue) {
        super(pName, "BOOLEAN", pValue);
    }

    public boolean getValue() {
        return super.getValue().equals("true") ? true : false;
    }

    public void setValue(String pValue) {
        this.setValue(pValue);
    }

    public boolean logicalAnd(Boolean pObj) {
        return (this.value && pObj.getValue());
    }

    public boolean logicalOr(Boolean pObj) {
        return (this.value || pObj.getValue());
    }

    public boolean logicalNot() {
        return !(this.value);
    }

    public boolean equals(Boolean pObj) {
        return (this.value == pObj.getValue());
    }

    public boolean differs(Boolean pObj) {
        return (this.value != pObj.getValue());
    }

}
