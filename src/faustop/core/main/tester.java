
class Nain {

	public static void main(String[] args) {

		/*String a = "voce destr\n? \n 1u+=iu \n me > =9;()u ovo?Pais da Europa\n";
		String b = "como voce\n\n 1+= 1?\n é gostosa\n";
		String c = "if\n(a<4)       {\nmostrai(\"MEU OVO\")\n}\n";
		// lutor.setCode(a);
		// lutor.setCode(c);
		//*/
		
		String command = "int @ = 4;\n";
		Lexer lutor = new Lexer();
		lutor.setCode(command);
		Token cu = lutor.getNextToken();
		while (cu != null) {
			System.out.println(cu.getName() + " " + cu.getType() + " - " + cu.getRow() + ":" + cu.getCol());
			cu = lutor.getNextToken();
		}

		/*Tree t = new Tree();
		Token to;

		for (int i =0; i < 50; i++) {
			to = new Token("BUNDA", ""+i, 1, 2);
			System.out.println(to);
			t.addNode(to);
		}

		t.traverse(t.root);*/

	}

}
