import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Stack;


class ExpressionParser {
    /*
     * Resposible for handling expressions (arithmetic and logic).
     * 
     * Author: Jean Carlo Hilger
     * E-mail: hilgerjeancarlo@gmail.com
     * */
    
    public static String eval(ArrayList<Node> pExpList) {
        /*
         * Receives a Node containing an expression and returns
         * a string with the result value of that string.
         * */
        
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
                if (first) {
                    expression.add(0, helper.pop());
                    first = false;
                }
                expression.add(0, child);
                expression.add(0, helper.pop());
                
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