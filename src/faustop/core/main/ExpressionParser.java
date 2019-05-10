package faustop.core.main;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import faustop.core.vars.*;

// TODO: search for 'test' in comments and remake the code below the comment

class ExpressionParser {
    /*
     * Resposible for handling expressions (arithmetic and logic).
     *
     * Author: Jean Carlo Hilger
     * E-mail: hilgerjeancarlo@gmail.com
     * */

 	static {
        // arithmetic operator
        // Consumer<Integer_> sp = Integer_()::plus;

        // sp = Integer_::minus;
        // aMap.put("-", sp);
        // sp = Integer_times;
        // aMap.put("*", sp); // char
        // sp = Integer_division;
        // aMap.put("/", sp); // string

        //INTEGER_METHODS = Collections.unmodifiableMap(aMap);
    }


    public static String eval(ArrayList<Node> pExpList) {
        /*
         * Receives a Node containing an expression and returns
         * a string with the result value of that string.
         * */

        Integer_ a = new Integer_("a", "1");
        Integer_ b = new Integer_("b", "2");
        OperatorParser.INTEGER_METHODS.get("+").apply(a, b);
        System.out.println("Miaaauiaiu"+a.getValue());

        ArrayList<Node> postfix = buildPostFix(pExpList);
        for (Node n : postfix) {
            System.out.println(n.key().getName());
        }
        System.out.println();
        System.out.println();
        ArrayList<Node> expression = postfixToAnswer(postfix);

        for (Node n : expression) {
            System.out.println(n.key().getName());
        }

        return "";
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

    private static ArrayList<Node> postfixToAnswer(ArrayList<Node> pPostFixExp) {
        /*
         * Receives the postfix expression and returns
         * the right order which it should be evaluated.
         * */

        Stack<Node> helper = new Stack<Node>();
        ArrayList<Node> expression = new ArrayList<Node>();
        boolean first = true;

        // test
        Node aux;
        int aux1 = 0;

        for (Node child : pPostFixExp) {
            // Symbols.isIdentifier(child.key()) || Symbols.isLiteral(child.key())
            if (child.key().getType().equals("identifier")
                || child.key().getType().equals("literal")) {
                helper.push(child);

            // Symbols.isOperator(child.key())
            } else if (child.key().getType().equals("operatorarithmetic")
                       || child.key().getType().equals("operatorlogic")
                       || child.key().getType().equals("operatorrelational")
                       || child.key().getType().equals("operatorassignment")) {
                // if (first) {
                //     expression.add(0, helper.pop());
                //     first = false;
                // }

                // expression.add(0, helper.pop());
                // expression.add(0, child);
                // expression.add(0, helper.pop());

                // test

                if (child.key().getName().equals("+")) {
                    aux1 = 0;
                    aux1 += Integer.parseInt(helper.pop().key().getName());
                    aux1 += Integer.parseInt(helper.pop().key().getName());
                    System.out.println("PLUS " + aux1);

                } else if (child.key().getName().equals("-")) {
                    // aux1 = 0;
                    aux1 = Integer.parseInt(helper.pop().key().getName());
                    aux1 = Integer.parseInt(helper.pop().key().getName()) - aux1;
                    System.out.println("MINUS " + aux1);

                } else if (child.key().getName().equals("*")) {
                    aux1 = 1;
                    aux1 *= Integer.parseInt(helper.pop().key().getName());
                    aux1 *= Integer.parseInt(helper.pop().key().getName());
                    System.out.println("TIMES " + aux1);

                } else {
                    aux1 = Integer.parseInt(helper.pop().key().getName());
                    aux1 = Integer.parseInt(helper.pop().key().getName()) / aux1;
                    System.out.println("TIMES " + aux1);

                }

                helper.push(new Node(new Token("operatorarithmetic", ""+aux1, 1, 2), child.parent()));
            }
        }

        System.out.println("Ans: " + helper.pop().key().getName());
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
