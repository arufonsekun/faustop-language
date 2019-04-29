
class Nain {

	public static void main(String[] args) {
		String a = "voce destr\n? \n uiu \n meu ovo?Pais da Europa\n";
		Lexer lutor = new Lexer();
		lutor.setCode(a);

		// while (!lutor.getLexeme().equals("")) {};
		System.out.println(lutor.getLexeme());
		System.out.println(lutor.getLexeme());
		System.out.println(lutor.getLexeme());
		System.out.println(lutor.getLexeme());
		System.out.println(lutor.getLexeme());
		System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
	}

}
