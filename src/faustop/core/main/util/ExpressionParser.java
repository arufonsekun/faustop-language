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

/*
 * Resposible for handling expressions (arithmetic and logic).
 *
 * Author: Jean Carlo Hilger
 * E-mail: hilgerjeancarlo@gmail.com
 * */
public class ExpressionParser {

    /*
    * Receives a Node containing an expression and returns
    * a string with the result value of that string.
    * */
    public static String eval(ArrayList<Node> pExpList) {

		OperatorParser.init();

        ArrayList<Node> postfix = buildPostFix(pExpList);
        // for(Node a : postfix) {
        //     System.out.println(a.key().getName());
        // }
		//
		// System.out.println();
        // System.out.println();
        String expressionVal = postfixToAnswer(postfix);
        //System.out.println(expressionVal);
		// System.out.println(expressionVal);
        return expressionVal;
    }

    //
    // Utility Functions
    //

    /*
    * Converts the given (infix) expression to
    * a postfix expression format. Returns it.
    * */
    private static ArrayList<Node> buildPostFix(ArrayList<Node> pExpList) {

        ArrayList<Node> expression = new ArrayList<Node>();
		Stack<Node> helper = new Stack<Node>(); // stores the operators before
                                                // put them in the expression
		Stack<Integer> priority = new Stack<Integer>(); // stores the operators before
        int parMultiplier0 = 0;
        int parMultiplier1 = 0;

        for (Node child : pExpList) {

            if (child.key().getType().equals(Symbols.delParOpen)) {
                parMultiplier0++;

            } else if (child.key().getType().equals(Symbols.delParClose)) {
                parMultiplier0--;
            }

            if (Symbols.isIdentifier(child.key())
                || Symbols.isLiteral(child.key())) {
                expression.add(child);

            } else if (Symbols.isOperator(child.key())) {
				int currPriority = ExpressionParser.getPriority(child.key().getName()) + 7 * parMultiplier0;
                while(!helper.empty()
                      && (!priority.empty()
					  	  && (priority.peek() >= currPriority))) {
                    expression.add(helper.pop());
                    priority.pop();
                }

                helper.push(child);
				priority.push(currPriority);
            }
        }

        while (!helper.empty()) {
            expression.add(helper.pop());
        }

        return expression;
    }

    /*
    * Receives the postfix expression and returns
    * the right order which it should be evaluated.
    * */
    private static String postfixToAnswer(ArrayList<Node> pPostFixExp) {

        Stack<Node> helper = new Stack<Node>();
        ArrayList<Node> expression = new ArrayList<Node>();

        // test
        Node aux = null;

        for (Node child : pPostFixExp) {
            if (child.key().getType().equals("literal")) {

                helper.push(child);
				// System.out.println(123);

			} else if (child.key().getType().equals("identifier")) {
				helper.push(new Node(new Token("literal",
							         Memory.getValueOf(child.key().getName()), -1, -1),
							child.parent()));
							// System.out.println(321);

            } else if (Symbols.isOperator(child.key())) {
                String val;
				// first, tries to convert to int
				try {
					aux = helper.pop();

					int a = Integer.parseInt(aux.key().getName());
					IntegerType b = new IntegerType("b", ""+Integer.parseInt(helper.peek().key().getName()));

				    val = OperatorParser.INTEGER.get(child.key().getName()).apply(b, a);

                    aux = new Node(new Token("literal", "" + val, -1, -1), child.parent());

				} catch (Exception e1) {

					helper.push(aux);
					// System.out.println("CATCHE 1");
					// System.out.println(e1);
					// if fails, tries to double
					try {
						aux = helper.pop();

						double a = Double.parseDouble(aux.key().getName());
						DoubleType b = new DoubleType("b", ""+Double.parseDouble(helper.peek().key().getName()));

                        val = OperatorParser.DOUBLE.get(child.key().getName()).apply(b, a);

						aux = new Node(new Token("literal", "" + val, -1, -1), child.parent());

					} catch (Exception e2) {

                    	helper.push(aux);
						// System.out.println("CATCHE 2");
						// System.out.println(e2);

                        // if still failing, convert to boolean
                        try {
    						aux = helper.pop();

    						boolean a = Boolean.parseBoolean(aux.key().getName());

    						BooleanType b = new BooleanType("b", ""+Boolean.parseBoolean(helper.peek().key().getName()));

							System.out.println("DGHAUSAGADFUSAGDASFDUFYDTBSD: "+child.key().getName());
                            val = OperatorParser.BOOLEAN.get(child.key().getName()).apply(b, a);

    						aux = new Node(new Token("literal", "" + val, -1, -1), child.parent());

    					// at last, converts to string
                        } catch (Exception e3) {
                            helper.push(aux);
							// System.out.println("CATCHE 3");
							// e3.printStackTrace();

							try {
								aux = helper.pop();
								// System.out.println("aux: " + aux.key().getName());

								String a = aux.key().getName();
								StringType b = new StringType("b", helper.peek().key().getName());

								val = OperatorParser.STRING.get(child.key().getName()).apply(b, a);

								aux = new Node(new Token("literal", "" + val, -1, -1), child.parent());

							} catch (Exception e4) {
								// System.out.println("CATCHE 4");
								// System.out.println(e4);
								System.out.println("TUDO ERRADO");

							}

                        }
					}
				}

				helper.pop();

				helper.push(aux);
            }
        }

        // System.out.println("Ans: " + helper.pop().key().getName());
        return helper.pop().key().getName();
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
