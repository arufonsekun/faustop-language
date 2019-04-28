//package faustop.core.main;

class Main {

    public static void main(String[] args) {
        Lexer lutor = new Lexer();
        lutor.setCode("int a = 4");

        System.out.println(lutor.getLexeme());
        System.out.println(lutor.getLexeme());
        System.out.println(lutor.getLexeme());
    }

}
