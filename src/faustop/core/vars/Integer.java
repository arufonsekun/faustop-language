class Integer extends Variable {

    private int value;

    public Integer(String name, int pValue) {
        super(name, "INTEGER");
        this.setValue(pValue);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int pValue) {
        this.value = pValue;
    }

    public boolean greaterThan(Integer pObj) {
        return (this.value > pObj.getValue());
    }

    public boolean lessThan(Integer pObj) {
        return (this.value < pObj.getValue());
    }

    public boolean greaterThanOrEqualTo(Integer pObj) {
        return (this.value >= pObj.getValue());
    }

    public boolean lessThanOrEqualTo(Integer pObj) {
        return (this.value <= pObj.getValue());
    }

    public boolean equals(Integer pObj) {
        return (this.value == pObj.getValue());
    }

    public boolean differs(Integer pObj) {
        return (this.value != pObj.getValue());
    }

    /*
     * ?? Return a Integer type or a void ??
     * */

    public void plus(Integer pObj) {
        this.value += pObj.getValue();
    }

    public void plus(int pValue) {
        this.value += pValue;
    }

    public void minus(Integer pObj) {
        this.value -= pObj.getValue();
    }

    public void minus(int pValue) {
        this.value -= pValue;
    }

    public void times(Integer pObj) {
        this.value *= pObj.getValue();
    }

    public void times(int pValue) {
        this.value *= pValue;
    }

    public void division(Integer pObj) {
        this.value /= pObj.getValue();
    }

    public void division(int pValue) {
        this.value /= pValue;
    }

    public void pow(Integer pObj) {
        this.value = (int) Math.pow(this.value, pObj.getValue());
    }

    public void pow(int pValue) {
        this.value = (int) Math.pow(this.value, pValue);
    }

    public void mod(Integer pObj) {
        this.value %= pObj.getValue();
    }

    public void mod(int pValue) {
        this.value %= pValue;
    }

}
