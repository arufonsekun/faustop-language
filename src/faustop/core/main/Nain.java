package faustop	.core.main;
import java.util.ArrayList;

class Nain {

	public static ArrayList<Node> cu = new ArrayList<Node>();

	public static void main(String[] args) {

		// String a = "\"voce destr\n? \n 1u+=iu \n me > =9;()u ovo?Pais da Europa\"\n";
		//String a = "como voce\n\n 1+= 1?\n é gostosa\n";
		// String a = "if\n(aasdsw<=4)       {\nmostrai(\"a(@#sd\");\n}\n";
		// String a = "inte a = cu+bosta + 2 * 3 + (1 + 2)/5^9;\n"; // Parser ok
		// String a = "if(4 > 2) {\n\t1+1;\n}\n";
		// String a = "if(a > 4){\nif(b > 3){\na=1;\n}\n}";

		// String a = "inte a = 10 + 2 - 8 + 3;";
		// String a = "inte cucuu = 9 * 1 - 10 + 2 * 8 - 3;";
		// String a = "inte cucuu = 6 + 9 * 5 * 7 - 9 + 4 * 2 - 6;";
		String a = "inte cucuu = 6 + 9 * 5 / 15 - 9 + 4 * 2 / 8;";

		// se passar pra esse GG:
		// String a = "inte a = 0;\nwhile(a > 4) {\n\ta += 1;\n\tif (a != 2) {\n\t\tmostrai(\"SUCK\");\n\t}\n\n}\n"; // parser OK
		// String a = "inte a = 0;\nwhile(a > 4) {\n\tif (a != 2) {\n\t\tmostrai(\"SUCK\");\n\t}\n\ta += 1;\n\n}\n"; // not OK
		// System.out.println();
		// System.out.println(a);
		// System.out.println();
		// //
		// String a = "inte a = 1;";

		Lexer lutor = new Lexer();
		Parser superman = new Parser();

		lutor.setCode(a);

		Token cu = lutor.getNextToken();
		//
		while (cu != null) {
			superman.addToken(cu);
			cu = lutor.getNextToken();
		}

		superman.buildParseTree();
		Tree bDeBosta = superman.getParseTree();

		try {
			Nain.traverse(bDeBosta.root());
		} catch (Exception e) {
			System.out.println("File opening error");
			e.printStackTrace();
			// return null;
		}
		ExpressionParser.eval(Nain.cu);

		// String a = "if (cu > (3 + 1) * 6) {\noloko res = \"MAIOR\";\n}\n";
		// String b = "inte a = 3 + 2 * 3;\n";

	}

	public static void traverse(Node pRoot) {
        /*
         * Traverse through the nodes of the Tree.
         * */

        if (pRoot == null) {
            System.out.println("DASDJHSGDUGDUYSDGYJHSDBKJSBDKSADBKsd");
            return;
        }

        for (Node child : pRoot.children()) {
            Nain.traverse(child);
        }

		if (pRoot.parent() != null
			&& pRoot.parent().key().getType().equals("EXP")) {
			// System.out.println(pRoot.children());
			// ExpressionParser.eval(child);
			Nain.cu.add(pRoot);

		}

        // System.out.println(pRoot.key().getType() + " -> " + pRoot.parent().key().getType());
    }

}
