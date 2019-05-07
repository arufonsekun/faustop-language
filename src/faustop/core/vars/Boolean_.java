//package faustop.core.vars;

class Boolean_ extends Variable {

    private boolean value;

    public Boolean_ (String pName, String pValue) {
        /* *
           * Boolean_ constructor, has name and value as parameters
           * super() calls Variable constructor
           * */
        super(pName, "BOOLEAN_");
        setValue(pValue);
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(String pValue) {
        if (pValue.equals("true")) {
            this.value = true;
            return;
        } else if (pValue.equals("false")){
            this.value = false;
            return;
        }
        System.out.println("ValueError");
        return;
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
