package GraphCalculator;

import static java.lang.Math.pow;

public class Calculation {


    public double CalculateBi(double oper1, double oper2, String operator) {
        if (operator == "+") {
            return oper1 + oper2;
        }

        if (operator == "-") {
            return  oper1 - oper2;
        }

        if (operator == "*") {
            return oper1 * oper2;
        }

        if (operator == "/") {
            return oper1 / oper2;
        }

        if (operator == "^") {
            return pow(oper1,oper2);
        }

        // never reach
        throw new Error();
    }


    public double CalculateMono(double oper, String operator) {
        if (operator == "sin") {
            return Math.sin(oper);
        }

        if (operator == "cos") {
            return Math.cos(oper);
        }

        if (operator == "tan") {
            return Math.tan(oper);
        }

        // never reach
        throw new Error();
    }
}




