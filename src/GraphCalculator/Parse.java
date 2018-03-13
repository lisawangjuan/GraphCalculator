package GraphCalculator;

import java.util.Stack;

public class Parse {
    private static String biOperators = new String("+-*/^");
    private static String monoOperators = new String("sincostan");

    // calculate the equation value of x = n
    public double calculate(String equation, int n) {

        // convert string equation to string array
        String[] tokens = equation.split("\\s+");

        // Stack for numbers: values
        Stack<Double> values = new Stack<Double>();

        // Stack for Operators: ops
        Stack<String> ops = new Stack<String>();

        for (int i = 0; i < tokens.length; i++) {
//            // Current token is a whitespace, skip it
//            if (tokens[i] == " ")
//                continue;

            // Current token is a number, push it to stack for numbers
            if (tokens[i].matches("-?\\d+(\\.\\d+)?")) {
                values.push(Double.parseDouble(tokens[i]));
            }

            // current token is variable x, replace x with given value n
            if (tokens[i].equals("x")) {
                values.push((double) n);
            }

            // Current token is an opening brace, push it to "ops"
            if (tokens[i].equals("(")) {
                ops.push(tokens[i]);

            }

            // Closing brace encountered, solve entire brace
            if (tokens[i].equals(")")) {
                while (!ops.peek().equals("(")) {
                    if (biOperators.contains(ops.peek())){
                        values.push(CalculateBi(ops.pop(), values.pop(), values.pop()));
                    }
                    else {
                        values.push(CalculateMono(ops.pop(), values.pop()));
                    }
                }
                ops.pop();
            }

            // Current token is an operator.
            if (biOperators.contains(tokens[i]) || monoOperators.contains(tokens[i])) {
                // While top of "ops" has same or greater precedence to current
                // token, which is an operator. Calculate the values and with top of "ops"
                while (!ops.empty() && hasPrecedence(tokens[i],ops.peek()))
                    if (biOperators.contains(ops.peek())){
                        values.push(CalculateBi(ops.pop(), values.pop(), values.pop()));
                    }
                    else {
                        values.push(CalculateMono(ops.pop(), values.pop()));
                    }
                // Push current token to "ops".
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            if (biOperators.contains(ops.peek())){
                values.push(CalculateBi(ops.pop(), values.pop(), values.pop()));
            }
            else {
                values.push(CalculateMono(ops.pop(), values.pop()));
            }

        // Top of "values" contains result, return it
        double ans = values.pop();
        if (!values.isEmpty()) {
            throw new RuntimeException();
        }
        else {
            return ans;
        }
    }


    // Returns true if "op2" has higher or same precedence as "op1",
    // otherwise returns false.
    public static boolean hasPrecedence(String op1, String op2) {
        if (op2.equals("sin") || op2.equals("cos") || op2.equals("tan")) {
            return true;
        }
        if (op2.equals("^") && (op1.equals("+") || op1.equals("-") || op1.equals("*") || op1.equals("/"))) {
            return true;
        }
        if ((op2.equals("*") || op2.equals("/")) && (op1.equals( "+") || op1.equals("-"))) {
            return true;
        }
        else {
            return false;
        }
    }


    // calculate the result of equation with binary operand
    public static double CalculateBi(String op, double b, double a) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0.0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
            case "^":
                return Math.pow(a,b);
        }
        return 0.0;
    }



    // calculate the result of equation with mono operand
    public static double CalculateMono(String op, double a) {
        switch (op) {
            case "sin":
                return Math.sin(a);
            case "cos":
                return Math.cos(a);
            case "tan":
                return Math.tan(a);
        }
        return 0.0;
    }

}
