class Main {
    public static void main(String[] args) {
        Char c = new Char("c", 'q');
        System.out.println(c.getName() +" = "+c.getValue());
        System.out.println("q == "+c.getValue()+" : "+c.equals('q'));
        System.out.println("q != "+c.getValue()+" : "+c.differs('q'));

        boolean x = "aaa" > "bbb";
        System.out.println(x);
    }
}
