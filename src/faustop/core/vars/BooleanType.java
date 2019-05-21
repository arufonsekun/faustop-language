package faustop.core.vars;

/*
 * Boolean type represents a faustop logic value.
 * @author: Junior Vitor Ramisch <junior.ramisch@gmail.com>
 * */

public class BooleanType extends Variable {

    private boolean value;

    public BooleanType(String pName, String pValue) {

        super(pName, "BOOLEAN");
        this.setValue(pValue);

    }

    public boolean getValue() {

        return this.value;

    }

    public void setValue(String pValue) {

        this.value = Boolean.parseBoolean(pValue);

    }

    public String logicalAnd(Boolean pValue) {

        return Boolean.toString(this.value && pValue);

    }

    public String logicalOr(Boolean pValue) {

        return Boolean.toString(this.value || pValue);

    }

    public String equal(Boolean pValue) {

        return Boolean.toString(this.value == pValue);

    }

    public String differs(Boolean pValue) {

        return Boolean.toString(this.value != pValue);

    }

}
