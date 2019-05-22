package faustop.core.vars;

/*
 * Integer class represents a faustop integer Type.
 *
 * @author Junior Vitor Ramisch <junior.ramisch@gmail.com>
 * */

public class IntegerType extends Variable {

	private int value;

    public IntegerType(String pName, String pValue) {

        super(pName, "INTEGER");
		this.setValue(pValue);

    }

    public int getValue() {

        return this.value;

    }

	public void setValue(String pValue) {

		this.value = Integer.parseInt(pValue);

	}

    public String greaterThan(int pValue) {

		String ans = Boolean.toString(this.getValue() > pValue);
        return ans;

    }

    public String lessThan(int pValue) {

		String ans = Boolean.toString(this.getValue() < pValue);
        return ans;

    }

    public String greaterThanOrEqualTo(int pValue) {

		String ans = Boolean.toString(this.getValue() >= pValue);
        return ans;

    }

    public String lessThanOrEqualTo(int pValue) {

		String ans = Boolean.toString(this.getValue() <= pValue);
        return ans;

    }

    public String equal(int pValue) {

		String ans  = Boolean.toString(this.getValue() == pValue);
        return ans;

    }

    public String differs(int pValue) {

		String ans = Boolean.toString(this.getValue() != pValue);
        return ans;

    }

    public String plus(int pValue) {

		String ans = Integer.toString(this.getValue() + pValue);
        this.setValue(ans);
		return ans;

    }

    public String minus(int pValue) {

		String ans = Integer.toString(this.getValue() - pValue);
        this.setValue(ans);
		return ans;

    }

    public String times(int pValue) {

		String ans = Integer.toString(this.getValue() * pValue);
        this.setValue(ans);
		return ans;

    }

    public String division(int pValue) {

		String ans = Integer.toString(this.getValue() / pValue);
        this.setValue(ans);
		return ans;

    }

    public String pow(int pValue) {

		String ans = Long.toString((long)Math.pow(this.getValue(), pValue));
        this.setValue(ans);
		return ans;

    }

    public String mod(int pValue) {

		String ans = Integer.toString(this.getValue() % pValue);
        this.setValue(ans);
		return ans;

    }

}
