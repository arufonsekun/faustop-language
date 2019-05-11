package faustop.core.vars;

public class Integer_ extends Variable {

	private int value;

    public Integer_(String pName, String pValue) {
        super(pName, "INTEGER");
		this.setValue(pValue);
    }

    public int getValue() {
        return this.value;
    }

	public void setValue(String pValue) {
		this.value = Integer.parseInt(pValue);
	}

    public boolean greaterThan(Integer_ pObj) {
        return (this.getValue() > pObj.getValue());
    }

    public boolean lessThan(Integer_ pObj) {
        return (this.getValue() < pObj.getValue());
    }

    public boolean greaterThanOrEqualTo(Integer_ pObj) {
        return (this.getValue() >= pObj.getValue());
    }

    public boolean lessThanOrEqualTo(Integer_ pObj) {
        return (this.getValue() <= pObj.getValue());
    }

    public boolean equals(Integer_ pObj) {
        return (this.getValue() == pObj.getValue());
    }

    public boolean differs(Integer_ pObj) {
        return (this.getValue() != pObj.getValue());
    }

    /*
     * ?? Return a Integer type or a void ??
     * */

    public void plus(int pValue) {
        this.setValue(Integer.toString(this.getValue() + pValue));
    }

    public void minus(Integer_ pObj) {
        this.setValue(Integer.toString(this.getValue() - pObj.getValue()));
    }

    public void minus(int pValue) {
        this.setValue(Integer.toString(this.getValue() - pValue));
    }

    public void times(Integer_ pObj) {
        this.setValue(Integer.toString(this.getValue() * pObj.getValue()));
    }

    public void times(int pValue) {
        this.setValue(Integer.toString(this.getValue() * pValue));
    }

    public void division(Integer_ pObj) {
        this.setValue(Integer.toString(this.getValue() / pObj.getValue()));
    }

    public void division(int pValue) {
        this.setValue(Integer.toString(this.getValue() / pValue));
    }

    //(int)->casting because pow() returns a Long
    public void pow(int pValue) {
        this.setValue(Integer.toString((int) Math.pow(this.getValue(), pValue)));
    }

    public void mod(Integer_ pObj) {
        this.setValue(Integer.toString(this.getValue() % pObj.getValue()));
    }

    public void mod(int pValue) {
        this.setValue(Integer.toString(this.getValue() % pValue));
    }

}
