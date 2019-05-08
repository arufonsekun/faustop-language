<<<<<<< HEAD
package vars;

public class Variable {
=======
class Variable {
>>>>>>> 8b1165647ead12ecf8af6a87e1a6995260fed1d4

    /*
     * Superclass of all types. Atributes name and type are commom
     * to every object type like Boolean, Integer, etc.
     * Author: Junior Vitor Ramisch
     * E-mail: junior.ramisch@gmail.com
     * */

    private String name;
    private String type;

    public Variable (String pName, String pType) {
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

    private void throwError() {
        System.out.println("Unsupported method");
    }

    //Boolean type methods
    public void logicalAnd(Variable pObj) {
        this.throwError();
    }

    public void logicalOr(Variable pObj) {
        this.throwError();
    }

<<<<<<< HEAD
    /*public void logicalNot() {
        this.throwError();
    }*/
=======
    public void logicalNot() {
        this.throwError();
    }
>>>>>>> 8b1165647ead12ecf8af6a87e1a6995260fed1d4

    public void equals(Variable pObj) {
        this.throwError();
    }

    public void differs(Variable pObj) {
        this.throwError();
    }

    //Integer, Double and String commom methods methods
    public void greaterThan(Variable pObj) {
        this.throwError();
    }

    public void lessThan(Variable pObj) {
        this.throwError();
    }

    public void greaterThanOrEqualTo(Variable pObj) {
        this.throwError();
    }

    public void lessThanOrEqualTo(Variable pObj) {
        this.throwError();
    }

    //Integer and Double commom methods
    public void plus(Variable pObj) {
        this.throwError();
    }

    public void minus(Variable pObj) {
        this.throwError();
    }

    public void times(Variable pObj) {
        this.throwError();
    }

    public void division(Variable pObj) {
        this.throwError();
    }

    public void pow(Variable pObj) {
        this.throwError();
    }

    public void mod(Variable pObj) {
        this.throwError();
    }

}
