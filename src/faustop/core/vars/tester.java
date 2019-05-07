class Main {
    public static void main(String[] args) {
        Boolean_ a = new Boolean_("a", "true");
        Integer_ b = new Integer_("b", "1");
        Integer_ c = new Integer_("c", "3");
        Double_ d = new Double_("d", "1.1");
        Double_ e = new Double_("e", "1.2");

        a.equals(b);

        // System.out.println(b.greaterThan(d));
        // System.out.println(d.getValue());
    }
}
