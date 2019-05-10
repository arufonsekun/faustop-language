package main;
class Nain {

	public static void main(String[] args) {

		// String a = "voce destr\n? \n 1u+=iu \n me > =9;()u ovo?Pais da Europa\n";
		//String a = "como voce\n\n 1+= 1?\n é gostosa\n";
		// String a = "if\n(aasdsw<=4)       {\nmostrai(\"MEU OVO\")\n}\n";
		// String a = "inte a = cu+bosta + 2 * 3 + (1 + 2)/5^9;\n"; // Parser ok
		// String a = "if(4 > 2) {\n1+1;\n}\n";
		// String a = "if(a > 4){\nif(b > 3){\na=1;\n}\n}";
		String a = "stringue a = \"hello world\";\nmostrai(a);\n";
		// String a = "inte a = 1;";

		Lexer lutor = new Lexer();
		Parser superman = new Parser();

		lutor.setCode(a);

		Token cu = lutor.getNextToken();
		//
		while (cu != null) {
			superman.addToken(cu);
			System.out.println(cu.getName());
			cu = lutor.getNextToken();
		}

		// superman.buildParseTree();
		// Tree bDeBosta = superman.getParseTree();
		// bDeBosta.traverse(bDeBosta.root());
		// System.out.println(superman.instrs);

		// String a = "if (cu > (3 + 1) * 6) {\noloko res = \"MAIOR\";\n}\n";
		// String b = "inte a = 3 + 2 * 3;\n";

	}

}
