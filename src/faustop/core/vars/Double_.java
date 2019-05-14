package faustop.core.vars;

public class Double_ extends Variable {

	private double value;

    public Double_(String pName, String pValue) {
        super(pName, "DOUBLE");
        this.setValue(pValue);
    }

    public double getValue() {
        return this.value;
    }

	public void setValue(String pValue) {
		this.value = Double.parseDouble(pValue);
	}

    public boolean greaterThan(Double_ pObj) {
        return (this.value > pObj.getValue());
    }

    public boolean lessThan(Double_ pObj) {
        return (this.value < pObj.getValue());
    }

    public boolean greaterThanOrEqualTo(Double_ pObj) {
        return (this.value >= pObj.getValue());
    }

    public boolean lessThanOrEqualTo(Double_ pObj) {
        return (this.value <= pObj.getValue());
    }

    public boolean equals(Double_ pObj) {
        return (this.value == pObj.getValue());
    }

    public boolean differs(Double_ pObj) {
        return (this.value != pObj.getValue());
    }

    /*
    * ?? Return a Double_ type or a void ??
     * */

    public void plus(Double_ pObj) {
        this.value += pObj.getValue();
    }

    public void plus(double pValue) {
        this.value += pValue;
    }

    public void minus(Double_ pObj) {
        this.value -= pObj.getValue();
    }

    public void minus(double pValue) {
        this.value -= pValue;
    }

    public void times(Double_ pObj) {
        this.value *= pObj.getValue();
    }

    public void times(double pValue) {
        this.value *= pValue;
    }

    public void division(Double_ pObj) {
        this.value /= pObj.getValue();
    }

    public void division(double pValue) {
        this.value /= pValue;
    }

    public void pow(Double_ pObj) {
        this.value = (double) Math.pow(this.value, pObj.getValue());
    }

    public void pow(double pValue) {
        this.value = (double) Math.pow(this.value, pValue);
    }

}
