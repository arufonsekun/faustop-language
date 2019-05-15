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

    public String greaterThan(Double_ pObj) {
		String ans = Boolean.toString(this.value > pObj.getValue());
        return ans;
    }

    public String lessThan(Double_ pObj) {
		String ans = Boolean.toString(this.value < pObj.getValue());
        return ans;
    }

    public String greaterThanOrEqualTo(Double_ pObj) {
		String ans = Boolean.toString(this.value >= pObj.getValue());
        return ans;
    }

    public String lessThanOrEqualTo(Double_ pObj) {
		String ans = Boolean.toString(this.value <= pObj.getValue());
        return ans;
    }

    public String equals(Double_ pObj) {
		String ans = Boolean.toString(this.value == pObj.getValue());
        return ans;
    }

    public String differs(Double_ pObj) {
		String ans = Boolean.toString(this.value != pObj.getValue());
        return ans;
    }

    /*
    * ?? Return a Double_ type or a void ??
     * */

    public String plus(Double_ pObj) {
		String ans = Double.toString(this.getValue() + pObj.getValue());
        this.setValue(ans);
		return ans;
    }

    public String plus(double pValue) {
		String ans = Double.toString(this.getValue() + pValue);
        this.setValue(ans);
        return ans;
    }

    public String minus(Double_ pObj) {
		String ans = Double.toString(this.getValue() - pObj.getValue());
        this.setValue(ans);
		return ans;
    }

    public String minus(double pValue) {
		String ans = Double.toString(this.getValue() - pValue);
        this.setValue(ans);
		return ans;
    }

    public String times(Double_ pObj) {
		String ans = Double.toString(this.getValue() * pObj.getValue());
        this.setValue(ans);
		return ans;
    }

    public String times(double pValue) {
		String ans = Double.toString(this.getValue() * pValue);
        this.setValue(ans);
		return ans;
    }

    public String division(Double_ pObj) {
		String ans = Double.toString(this.getValue() / pObj.getValue());
        this.setValue(ans);
		return ans;
    }

    public String division(double pValue) {
		String ans = Double.toString(this.getValue() / pValue);
        this.setValue(ans);
		return ans;
    }

    public String pow(Double_ pObj) {
		String ans = Double.toString((double) Math.pow(this.value, pObj.getValue()));
        this.setValue(ans);
		return ans;
    }

    public String pow(double pValue) {
		String ans = Double.toString((double) Math.pow(this.value, pValue));
        this.setValue(ans);
		return ans;
    }

}
