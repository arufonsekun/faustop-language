//package faustop.core.vars;

class Variable {

    private String name;
    private String type;

    public Variable (String pName, String pType) {
        /* *
           * Constructor of class Variable:
           * name represents the variable name
           * type represents the variabel type
           * */
        setName(pName);
        setType(pType);

    }

    public String getName() {
        return this.name;
    }

    private void setName(String pName) {
        this.name = pName;
    }

    public String getType(){
        return this.type;
    }

    private void setType(String pType)  {
        this.type = pType;
    }

}
