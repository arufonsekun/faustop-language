package faustop.core.vars;

/*
* Superclass of all types. Atributes name and type are commom
* to every object type like Boolean, Integer, etc.
* Author: Junior Vitor Ramisch
* E-mail: junior.ramisch@gmail.com
* */
public class Variable {

    private String name;
    private String type;
    private String value;

    public Variable (String name, String pType, String pValue) {
        this.setType(pType);
        this.setName(name);
        this.setValue(pValue);
    }

    public String getSName() {
        return this.name;
    }

    public void setName(String pName) {
        this.name = pName;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String pType)  {
        this.type = pType;
    }

    public void setValue(String pValue) {
        this.value = pValue;
    }

    public String getStrValue() {
        return this.value;
    }

    public boolean greaterThan(Variable pObj){return false;}
    public boolean lessThan(Variable pObj){return false;}
    public boolean greaterThanOrEqualTo(Variable pObj){return false;}
    public boolean lessThanOrEqualTo(Variable pObj){return false;}
    public boolean equals(Variable pObj){return false;}
    public boolean differs(Variable pObj){return false;}

    //Integer and Double methods
    public void plus(Variable pObj){return;}
    public void minus(Variable pObj){return;}
    public void times(Variable pObj){return;}
    public void division(Variable pObj){return;}
    public void pow(Variable pObj){return;}
    public void mod(Variable pObj){return;}

    //Logical methods
    public boolean logicalAnd(Variable pObj){return false;}
    public boolean logicalOr(Variable pObj){return false;}
    public boolean logicalNot(){return false;}

    public int getIValue(){return 0;}
    public boolean getBValue(){return false;}
    public double getDValue(){return 0.0;}
    public String getSValue(){return null;}

    boolean lexicalOrder(String pValue){return false;}
}
