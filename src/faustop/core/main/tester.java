package faustop.core.main;

class Main {
    
    public static void main(String[] args) {
        Token a = new Token("ant", "ID", 1, 3);
        
        System.out.println(a.getName() + a.getType() + a.getRow() + a.getCol());
    }
    
}