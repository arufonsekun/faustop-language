package faustop.core.vars;

public class String_ extends Variable {

	private String value;

    public String_(String name, String pValue) {
        super(name, "STRING");
		this.setValue(pValue);
    }

    public void setValue(String pValue) {
        this.value = pValue;
    }

	public String getValue() {
		return this.value;
	}

    public String greaterThan(String pObj) {
		String ans = Boolean.toString(this.lexicalOrder(pObj));
        return ans;
    }

    public String lessThan(String pObj) {
        if (this.equal(pObj).equals("true")) return "false";
        return Boolean.toString(!this.lexicalOrder(pObj));
    }

    public String equal(String pObj) {
		System.out.println("String class"+pObj);
		Boolean ans = this.getValue().equals(pObj);
		System.out.println("String class"+ans);
        return Boolean.toString(ans);
    }

    public String greaterThanOrEqualTo(String pObj) {
		Boolean ans1 = this.greaterThan(pObj).equals("true");
		Boolean ans2 = this.equal(pObj).equals("true");
        return Boolean.toString(ans1 || ans2);
    }

    public String lessThanOrEqualTo(String pObj) {
		Boolean ans1 = this.lessThan(pObj).equals("true");
		Boolean ans2 = this.equal(pObj).equals("true");
        return Boolean.toString(ans1 || ans2);
    }

	public String differs(String pValue) {
		Boolean ans = this.value.equals(pValue);
		return Boolean.toString(!ans);
	}

    /*
    * lexicalOrder() returns true if value class attribute comes before
    * in the lexicographic order, false otherwise.
    * */
    private boolean lexicalOrder(String pValue) {

        String value = this.getValue();
        int valueSize = value.length();
        int pValueSize = pValue.length();
        int size = valueSize < pValueSize ? valueSize : pValueSize;

        for (int i = 0; i < size; i++) {
            if (value.charAt(i) != pValue.charAt(i)) {
                if (value.charAt(i) > pValue.charAt(i)) return true;
                else return false;
            }
        }

        if (value.charAt(size-1) == pValue.charAt(size-1))
            if (valueSize == pValueSize) return false;
            else return valueSize > pValueSize;
        return false;

    }

}
