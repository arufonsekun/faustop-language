//package faustop.core.vars;

class Boolean extends Variable {

    private boolean value;

    public Boolean (String pName, String pValue) {
        /* *
           * Boolean constructor, has name and value as parameters
           * super() calls Variable constructor
           * */
        super(pName, "BOOLEAN");
        setValue(pValue);
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(String pValue) {
        this.value = Boolean.parseBoolean(pValue);
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
