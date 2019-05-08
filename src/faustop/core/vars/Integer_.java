class Integer_ extends Variable {

    private int value;

    public Integer_(String name, String pValue) {
        super(name, "INTEGER_");
        this.setValue(pValue);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(String s) {
        this.value = Integer.parseInt(s);
    }

    public boolean greaterThan(Integer_ pObj) {
        return (this.value > pObj.getValue());
    }

    public boolean lessThan(Integer_ pObj) {
        return (this.value < pObj.getValue());
    }

    public boolean greaterThanOrEqualTo(Integer_ pObj) {
        return (this.value >= pObj.getValue());
    }

    public boolean lessThanOrEqualTo(Integer_ pObj) {
        return (this.value <= pObj.getValue());
    }

    public boolean equals(Integer_ pObj) {
        return (this.value == pObj.getValue());
    }

    public boolean differs(Integer_ pObj) {
        return (this.value != pObj.getValue());
    }

    /*
     * ?? Return a Integer type or a void ??
     * */

    public void plus(Integer_ pObj) {
        this.value += pObj.getValue();
    }

    public void plus(int pValue) {
        this.value += pValue;
    }

    public void minus(Integer_ pObj) {
        this.value -= pObj.getValue();
    }

    public void minus(int pValue) {
        this.value -= pValue;
    }

    public void times(Integer_ pObj) {
        this.value *= pObj.getValue();
    }

    public void times(int pValue) {
        this.value *= pValue;
    }

    public void division(Integer_ pObj) {
        this.value /= pObj.getValue();
    }

    public void division(int pValue) {
        this.value /= pValue;
    }

    //(int)->casting because pow() returns a Long
    public void pow(Integer_ pObj) {
        this.value = (int) Math.pow(this.value, pObj.getValue());
    }

    public void pow(int pValue) {
        this.value = (int) Math.pow(this.value, pValue);
    }

    public void mod(Integer_ pObj) {
        this.value %= pObj.getValue();
    }

    public void mod(int pValue) {
        this.value %= pValue;
    }

}
// Reviewed by Fernando
