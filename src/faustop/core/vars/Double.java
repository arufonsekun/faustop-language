class Double extends Variable {
    private double value;

    public Double(String pName, double pValue) {
        super(pName, "DOUBLE");
        this.setValue(pValue);
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double pValue) {
        this.value = pValue;
    }

    public boolean greaterThan(Double pObj) {
        return (this.value > pObj.getValue());
    }

    public boolean lessThan(Double pObj) {
        return (this.value < pObj.getValue());
    }

    public boolean greaterThanOrEqualTo(Double pObj) {
        return (this.value >= pObj.getValue());
    }

    public boolean lessThanOrEqualTo(Double pObj) {
        return (this.value <= pObj.getValue());
    }

    public boolean equals(Double pObj) {
        return (this.value == pObj.getValue());
    }

    public boolean differs(Double pObj) {
        return (this.value != pObj.getValue());
    }

    /*
    * ?? Return a Double type or a void ??
     * */

    public void plus(Double pObj) {
        this.value += pObj.getValue();
    }

    public void plus(double pValue) {
        this.value += pValue;
    }

    public void minus(Double pObj) {
        this.value -= pObj.getValue();
    }

    public void minus(double pValue) {
        this.value -= pValue;
    }

    public void times(Double pObj) {
        this.value *= pObj.getValue();
    }

    public void times(double pValue) {
        this.value *= pValue;
    }

    public void division(Double pObj) {
        this.value /= pObj.getValue();
    }

    public void division(double pValue) {
        this.value /= pValue;
    }

    public void pow(Double pObj) {
        this.value = (double) Math.pow(this.value, pObj.getValue());
    }

    public void pow(double pValue) {
        this.value = (double) Math.pow(this.value, pValue);
    }

    public void mod(Double pObj) {
        this.value %= pObj.getValue();
    }

    public void mod(double pValue) {
        this.value %= pValue;
    }
}
