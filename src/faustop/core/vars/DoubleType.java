package faustop.core.vars;

/*
 * Double class represents a faustop double type. The
 * supported operations are: +, -, *, / and ^.
 *
 * @author Paulo G. S. Comasetto <paulogscomasetto@gmail.com>
 * */

public class DoubleType extends Variable {

	private double value;

    public DoubleType(String pName, String pValue) {

        super(pName, "DOUBLE");
        this.setValue(pValue);

    }

    public double getValue() {

        return this.value;

    }

	public void setValue(String pValue) {

		this.value = Double.parseDouble(pValue);

	}

    public String greaterThan(double pValue) {

		String ans = Boolean.toString(this.getValue() > pValue);
        return ans;

    }

    public String lessThan(double pValue) {

		String ans = Boolean.toString(this.getValue() < pValue);
        return ans;

    }

    public String greaterThanOrEqualTo(double pValue) {

		String ans = Boolean.toString(this.getValue() >= pValue);
        return ans;

    }

    public String lessThanOrEqualTo(double pValue) {

		String ans = Boolean.toString(this.getValue() <= pValue);
        return ans;

    }

    public String equal(double pValue) {

		String ans = Boolean.toString(this.getValue() == pValue);
        return ans;

    }

    public String differs(double pValue) {

		String ans = Boolean.toString(this.getValue() != pValue);
        return ans;

    }

    public String plus(double pValue) {

		String ans = Double.toString(this.getValue() + pValue);
        this.setValue(ans);
        return ans;

    }

    public String minus(double pValue) {

		String ans = Double.toString(this.getValue() - pValue);
        this.setValue(ans);
		return ans;

    }

    public String times(double pValue) {

		String ans = Double.toString(this.getValue() * pValue);
        this.setValue(ans);
		return ans;

    }

    public String division(double pValue) {

		String ans = Double.toString(this.getValue() / pValue);
        this.setValue(ans);
		return ans;

    }

    public String pow(double pValue) {

		String ans = Double.toString((double) Math.pow(this.value, pValue));
        this.setValue(ans);
		return ans;

    }

}
