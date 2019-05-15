package faustop.core.main.util;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import faustop.core.vars.*;
import faustop.core.main.util.*;

// TODO: search for 'test' in comments and remake the code below the comment

/*
 * Resposible for handling expressions (arithmetic and logic).
 *
 * Author: Jean Carlo Hilger
 * E-mail: hilgerjeancarlo@gmail.com
 * */
public class ExpressionParser {

    public static String eval(ArrayList<Node> pExpList) {
        /*
         * Receives a Node containing an expression and returns
         * a string with the result value of that string.
         * */

		OperatorParser.init();
		// //
        // Integer_ a = new Integer_("a", "2.18");
        // Integer_ b = new Integer_("b", "9");
		// OperatorParser.INTEGER.get("-").apply(a, 1.18);
        // OperatorParser.INTEGER.get("*").apply(b, 9);
		// System.out.println("\tAAAAAAAAA: " + a.getValue());
        // System.out.println("\tBBBBBBBBB : " + b.getValue());

        ArrayList<Node> postfix = buildPostFix(pExpList);

		// System.out.println();
        System.out.println();
        String expressionVal = postfixToAnswer(postfix);
		System.out.println(expressionVal);
        return expressionVal;
    }

    //
    // Utility Functions
    //

    private static ArrayList<Node> buildPostFix(ArrayList<Node> pExpList) {
        /*
         * Converts the given (infix) expression to
         * a postfix expression format. Returns it.
         * */

        ArrayList<Node> expression = new ArrayList<Node>();
        Stack<Node> helper = new Stack<Node>();

        for (Node child : pExpList) {
            // Symbols.isIdentifier(child.key()) || Symbols.isLiteral(child.key())
            if (child.key().getType().equals("identifier")
                || child.key().getType().equals("literal")) {
                expression.add(child);
                // System.out.println(child.key().getType() + " - " + child.key().getName());

            // Symbols.isOperator(child.key())
            } else if (child.key().getType().equals("operatorarithmetic")
                       || child.key().getType().equals("operatorlogic")
                       || child.key().getType().equals("operatorrelational")
                       || child.key().getType().equals("operatorassignment")) {

                while(!helper.empty()
                      && (ExpressionParser.getPriority(helper.peek().key().getName()))
                          >= ExpressionParser.getPriority(child.key().getName())) {
                    expression.add(helper.pop());
                }

                helper.push(child);
            }
        }

        while (!helper.empty()) {
            expression.add(helper.pop());
        }

        return expression;
    }

    private static String postfixToAnswer(ArrayList<Node> pPostFixExp) {
        /*
         * Receives the postfix expression and returns
         * the right order which it should be evaluated.
         * */

        Stack<Node> helper = new Stack<Node>();
        ArrayList<Node> expression = new ArrayList<Node>();

        // test
        Node aux=null;
		int aux1;

        for (Node child : pPostFixExp) {
            // Symbols.isIdentifier(child.key()) || Symbols.isLiteral(child.key())
            if (child.key().getType().equals("literal")) {
                helper.push(child);

			} else if (child.key().getType().equals("identifier")) {
				helper.push(new Node(new Token("literal",
							         Memory.getValueOf(child.key().getName()), -1, -1),
							child.parent()));
							// System.out.println("MERDAM<ERDAM<ERDA::::");

            // Symbols.isOperator(child.key())
            } else if (child.key().getType().equals("operatorarithmetic")
                       || child.key().getType().equals("operatorlogic")
                       || child.key().getType().equals("operatorrelational")
                       || child.key().getType().equals("operatorassignment")) {

				// if (child.key().getType().equals())

				// first, tries to convert to int
				try {
					// aux = helper.poll();
					aux = helper.pop();

					int a = Integer.parseInt(aux.key().getName());
					Integer_ b = new Integer_("b", Integer.parseInt(helper.peek().key().getName()));

					OperatorParser.INTEGER.get(child.key().getName()).apply(b, a);

					aux = new Node(new Token("literal", "" + b.getValue(), -1, -1), child.parent());

				} catch (Exception e1) {

					helper.push(aux);

					// System.out.println(231232);
					// if fails, tries to double
					try {
						// System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa");
						// double a = Double.parseDouble(helper.pop().key().getName());
						aux = helper.pop();

						double a = Double.parseDouble(aux.key().getName());
						Double_ b = new Double_("b", Double.parseDouble(helper.peek().key().getName()));

						OperatorParser.DOUBLE.get(child.key().getName()).apply(b, a);

						aux = new Node(new Token("literal", "" + b.getValue(), -1, -1), child.parent());

					// at last, convert to string
					} catch (Exception e2) {
						helper.push(aux);

						System.out.println("TUDO ERRADO");
					}
				}

				helper.pop();

				helper.push(aux);
            }
        }

        // System.out.println("Ans: " + helper.pop().key().getName());
        return helper.pop().key().getName();
    }
    
    /*
     * Utility function to convert a `Node` list to
     * a `String` list (containing the values only).
     * */
    private static String nodeToString(ArrayList<Node> pExpression) {
        ArrayList<String> expression = new ArrayList<String>();
        
        for (Node node : pExpression) {
            // if `node` is a literal, simply add its value
            if (node.key().getType().equals("literal")) {
                expression.add(node.key().getName());

            // if `node` is a identifier, get its value in `Memory`
            } else if (node.key().getType().equals("identifier")) {
               expression.add(Memory.getValueOf(node.key().getName());
            }
        }
        
        return expression;
    }

    private static int getPriority(String pOperator) {
        /*
         * Utility function that returns the precedence
         * of the given operator.
         * */

        if (Pattern.matches("(^)", pOperator)) {
            return 6;

        } else if (Pattern.matches("(%)", pOperator)) {
            return 5;

        } else if (Pattern.matches("(\\*|/)", pOperator)) {
            return 4;

        } else if (Pattern.matches("(\\+|-)", pOperator)) {
            return 3;

        } else if (Pattern.matches("(>|<|>=|<=|==|!=)", pOperator)) {
            return 2;

        } else if (Pattern.matches("(!|\\||\\|&&)", pOperator)) {
            return 1;
        }

        return -1;
    }


}
