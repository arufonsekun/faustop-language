package faustop.core.main.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

import faustop.core.vars.*;
/*
 * Resposible for handling expressions (arithmetic and logic).
 *
 * @author Jean Carlo Hilger <hilgerjeancarlo@gmail.com>
 * */

public class ExpressionParser {

    /*
     * Receives a Node containing an expression and returns
     * a string with the result value of that string.
     * */
    public static String eval(ArrayList<Node> pExpList) {

		OperatorParser.init();

        ArrayList<Node> postfix = buildPostFix(pExpList);

        String expressionVal = postfixToAnswer(postfix);

        return expressionVal;
    }

    /*
     * Converts the given (infix) expression to
     * a postfix expression format. Returns it.
     * */
    private static ArrayList<Node> buildPostFix(ArrayList<Node> pExpList) {

        ArrayList<Node> expression = new ArrayList<Node>();

		// stores the operators before put them in the expression.
		Stack<Node> helper = new Stack<Node>();

		// stores the priority of enqueued operators.
		Stack<Integer> priority = new Stack<Integer>();

        int parMultiplier = 0;

        for (Node child : pExpList) {

            if (child.key().getType().equals(Symbols.DEL_PAR_OPEN)) {
                parMultiplier++;

            } else if (child.key().getType().equals(Symbols.DEL_PAR_CLOSE)) {
                parMultiplier--;
            }

            if (Symbols.isIdentifier(child.key())
                || Symbols.isLiteral(child.key())) {
                expression.add(child);

            } else if (Symbols.isOperator(child.key())) {
				int currPriority = ExpressionParser.getPriority(child.key().getName()) + 7 * parMultiplier;

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

		// stores the operands before evaluating them
		Stack<Node> helper = new Stack<Node>();

		ArrayList<Node> expression = new ArrayList<Node>();

        Node aux = null;

        for (Node child : pPostFixExp) {

			String childType = child.key().getType();
			String childName = child.key().getName();

            if (childType.equals(Symbols.LIT)) {
                helper.push(child);

			} else if (childType.equals(Symbols.ID)) {
				helper.push(new Node(new Token(Symbols.LIT, Memory.getValueOf(childName)),
							child.parent()));

            } else if (Symbols.isOperator(child.key())) {
                String val;

				// first, tries to convert to int
				try {
					aux = helper.pop();

					int a = Integer.parseInt(aux.key().getName());
					int bValue = Integer.parseInt(helper.peek().key().getName());
					IntegerType b = new IntegerType("b", "" + bValue);

				    val = OperatorParser.INTEGER.get(childName).apply(b, a);

                    aux = new Node(new Token("literal", "" + val), child.parent());

				} catch (Exception e1) {

					helper.push(aux);

					// if fails, tries to convert to double
					try {
						aux = helper.pop();

						double a = Double.parseDouble(aux.key().getName());
						double bValue = Double.parseDouble(helper.peek().key().getName());
						DoubleType b = new DoubleType("b", "" + bValue);

                        val = OperatorParser.DOUBLE.get(childName).apply(b, a);

						aux = new Node(new Token("literal", "" + val), child.parent());

					} catch (Exception e2) {

                    	helper.push(aux);

                        // if still failing, convert to boolean
                        try {
    						aux = helper.pop();

    						boolean a = Boolean.parseBoolean(aux.key().getName());
							boolean bValue = Boolean.parseBoolean(helper.peek().key().getName());
    						BooleanType b = new BooleanType("b", "" + bValue);

                            val = OperatorParser.BOOLEAN.get(childName).apply(b, a);

    						aux = new Node(new Token("literal", "" + val), child.parent());

                        } catch (Exception e3) {

                            helper.push(aux);

							// at last, converts to string
							try {
								aux = helper.pop();

								String a = aux.key().getName();
								StringType b = new StringType("b", helper.peek().key().getName());

								val = OperatorParser.STRING.get(childName).apply(b, a);

								aux = new Node(new Token("literal", "" + val), child.parent());

							// if any error occurs, something is wrong
							} catch (Exception e4) {
								System.out.println("TUDO ERRADO");
								e4.printStackTrace();
							}
                        }
					}
				}

				helper.pop();

				helper.push(aux);

			}

		}

        return helper.pop().key().getName();

	}

	/*
 	 * Utility function that returns the precedence
	 * of the given operator.
	 * */
    private static int getPriority(String pOperator) {

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

        return 0;

	}

}
