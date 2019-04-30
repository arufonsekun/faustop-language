
class Nain {

	public static void main(String[] args) {
		String a = "voce destr\n? \n 1u+=iu \n me > =9;()u ovo?Pais da Europa\n";
		String b = "como voce\n\n 1+= 1?\n é gostosa\n";
		String c = "if\n(a<4)       {\nmostrai(\"MEU OVO\")\n}\n";
		Lexer lutor = new Lexer();
		// lutor.setCode(a);
		lutor.setCode(a);
		// lutor.setCode(c);
		Token cu = lutor.getNextToken();
		//
		while (cu != null) {
			System.out.println(cu.getName() + " " + cu.getType() + " - " + cu.getRow() + ":" + cu.getCol());
			cu = lutor.getNextToken();
		}


		// while (!lutor.getLexeme().equals("")) {};
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
		// System.out.println(lutor.getLexeme());
	}

}
