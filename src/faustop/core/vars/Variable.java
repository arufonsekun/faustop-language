package faustop.core.vars;

/*
* Superclass of all types. Atributes name and type are commom
* to every object type like Boolean, Integer, etc.
*
* Author: Junior Vitor Ramisch
* E-mail: junior.ramisch@gmail.com
* */

public class Variable {

    private String name;
    private String type;

    public Variable (String name, String pType) {
        this.setType(pType);
        this.setName(name);
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

}
