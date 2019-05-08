package faustop.core.vars;

public class Inte extends Variable {

    public Inte(String pName, String pValue) {
        super(pName, "INTEGER", pValue);
    }

    public int getValue() {
        return Integer.parseInt(this.getStrValue());
    }

    public boolean greaterThan(Inte pObj) {
        return (this.getValue() > pObj.getValue());
    }

    public boolean lessThan(Inte pObj) {
        return (this.getValue() < pObj.getValue());
    }

    public boolean greaterThanOrEqualTo(Inte pObj) {
        return (this.getValue() >= pObj.getValue());
    }

    public boolean lessThanOrEqualTo(Inte pObj) {
        return (this.getValue() <= pObj.getValue());
    }

    public boolean equals(Inte pObj) {
        return (this.getValue() == pObj.getValue());
    }

    public boolean differs(Inte pObj) {
        return (this.getValue() != pObj.getValue());
    }

    /*
     * ?? Return a Integer type or a void ??
     * */

    public void plus(Inte pObj) {
        this.setValue(Integer.toString(this.getValue() + pObj.getValue()));
    }

    public void plus(int pValue) {
        this.setValue(Integer.toString(this.getValue() + pValue));
    }

    public void minus(Inte pObj) {
        this.setValue(Integer.toString(this.getValue() - pObj.getValue()));
    }

    public void minus(int pValue) {
        this.setValue(Integer.toString(this.getValue() - pValue));
    }

    public void times(Inte pObj) {
        this.setValue(Integer.toString(this.getValue() * pObj.getValue()));
    }

    public void times(int pValue) {
        this.setValue(Integer.toString(this.getValue() * pValue));
    }

    public void division(Inte pObj) {
        this.setValue(Integer.toString(this.getValue() / pObj.getValue()));
    }

    public void division(int pValue) {
        this.setValue(Integer.toString(this.getValue() / pValue));
    }

    //(int)->casting because pow() returns a Long
    public void pow(int pValue) {
        this.setValue(Integer.toString((int) Math.pow(this.getValue(), pValue)));
    }

    public void mod(Inte pObj) {
        this.setValue(Integer.toString(this.getValue() % pObj.getValue()));
    }

    public void mod(int pValue) {
        this.setValue(Integer.toString(this.getValue() % pValue));
    }

}
