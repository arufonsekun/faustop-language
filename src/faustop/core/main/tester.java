
class Nain {

	public static void main(String[] args) {

		//String a = "voce destr\n? \n 1u+=iu \n me > =9;()u ovo?Pais da Europa\n";
		//String a = "como voce\n\n 1+= 1?\n é gostosa\n";
		String a = "if\n(aasdsw<= 4)       {\nmostrai(\"MEU OVO\")\n}\n";
		Lexer lutor = new Lexer();

		lutor.setCode(a);

		Token cu = lutor.getNextToken();
		//
		while (cu != null) {
			System.out.println(cu.getName() + " " + cu.getType() + " - " + cu.getRow() + ":" + cu.getCol());
			cu = lutor.getNextToken();
		}

		// String a = "if (cu > (3 + 1) * 6) {\noloko res = \"MAIOR\";\n}\n";
		// String b = "inte a = 3 + 2 * 3;\n";

	}

}
