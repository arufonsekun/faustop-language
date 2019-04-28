//package faustop.core.main;

class Main {
    
    public static void main(String[] args) {
        Lexer lutor = new Lexer();
        lutor.setCode("voce destrui meu ovo");
        
        System.out.println(lutor.getLexeme());
        System.out.println(lutor.getLexeme());
        System.out.println(lutor.getLexeme());
    }
    
}