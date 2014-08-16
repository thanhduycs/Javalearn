package datastruct.postfix;

import java.util.*;
import java.util.regex.*;

/**
 *
 * @author SONY VAIO
 */
public class Mathematic {

    protected int mode; // 0: Degrees 1:Radian 2:Gradian

    public Mathematic() {
        mode = 0;
    }

    public Mathematic(int mode) {
        this.mode = mode;
    }

    public boolean isMatched(String input) {
        input = ReBuild(input);

        if (input.matches(".*[\\+\\-\\*\\/\\%]{2}.*")) {
            return false;

        }
        if (input.matches(".*[\\+\\-\\*\\/\\%]\\).*")) {
            return false;

        }
        if (input.matches(".*[\\+\\-\\*\\/\\%]+\\Z")) {
            return false;

        }
        if (input.matches("\\A[\\*\\/\\%].*")) {
            return false;


        }
        Stack<String> stack = new Stack<String>();
        Pattern pattern = Pattern.compile("\\(\\)]|mod");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String operation = matcher.group();
            if (operation.matches("\\(")) {
                stack.push(operation);
            } else if (operation.matches("\\)")) {
                stack.pop();
            }
        }
        if (!stack.isEmpty()) {
            return false;

        }
        return true;
    }

    public double Evaluate(String input) {
        System.out.print("Evaluate: " + input + " = ");
        input = ReBuild(input);
        Stack<String> stack = new Stack<String>();
        Queue<String> out = new ArrayDeque<String>();                //  postfix
        Stack<Double> result = new Stack<Double>();                  //  result

        //reciproc ^ sqrt +-*/
        Pattern pattern = Pattern.compile("int|frac|powten|powe|ln|log|asinh|acosh|atanh|asin[^ h\\(0-9]?|acos[^ h\\(0-9]?|atan[^ h\\(0-9]?|[^ a\\+\\-\\*\\/\\%\\(]?sinh|[^ a\\+\\-\\*\\/\\%\\(]?cosh|[^ a\\+\\-\\*\\/\\%\\(]?tanh|sin[^ h\\(0-9]?|cos[^ h\\(0-9]?|tan[^ h\\(0-9]?|square|cube|!|fact|reciproc|\\^|sqrt|cubroot|yroot|\\-|[\\+\\*\\/\\%\\(\\)]|mod");
        Matcher matcher = pattern.matcher(input);

        int index = 0;
        int offset = 0;
        while (matcher.find()) {
            String operation = matcher.group();
            if (input.substring(0, matcher.start()).matches("(.*[eE\\*\\/\\(])|( *)") && operation.matches("\\+|\\-")) {
                continue;


            }
            index = matcher.start();
            String token = input.substring(offset, index);
            offset = matcher.end();
            if (!token.matches(" *")) {
                out.add(token);
            }

            if (operation.matches("\\+|\\-|\\*|\\/|\\%|mod")) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(operation)) {
                    out.add(stack.pop());
                }
                stack.push(operation);
            } else if (operation.matches("\\(")) {
                stack.push(operation);
            } else if (operation.matches("\\)")) {
                while (!stack.peek().matches("\\(")) {
                    out.add(stack.pop());
                }
                stack.pop();
            } else if (operation.matches("int|frac|powten|powe|ln|log|asinh|acosh|atanh|asin|acos|atan|sinh|cosh|tanh|sin|cos|tan|square|cube|!|fact|reciproc|\\^|sqrt|cubroot|yroot")) {
                stack.push(operation);
            }
        }
        if (!input.substring(offset).matches(" *")) {
            out.add(input.substring(offset));
        }
        while (!stack.isEmpty()) {
            if (!stack.peek().matches("\\(")) //  sovled: (5)
            {
                out.add(stack.peek());
            }
            stack.pop();
        }
        // Completed convert infix to postfix

        // Begin parse postfix to value
        while (!out.isEmpty()) {
            String token = out.poll();
            Double a, b;
            if (token.matches("\\+")) {
                b = result.pop();
                a = result.pop();
                result.push(a + b);
            } else if (token.matches("\\-")) {
                b = result.pop();
                a = result.pop();
                result.push(a - b);
            } else if (token.matches("\\*")) {
                b = result.pop();
                a = result.pop();
                result.push(a * b);
            } else if (token.matches("\\/")) {
                b = result.pop();
                a = result.pop();
                result.push(a / b);
            } else if (token.matches("mod|\\%")) {
                b = result.pop();
                a = result.pop();
                result.push(a % b);
            } else if (token.matches("yroot")) {
                b = result.pop();
                a = result.pop();
                result.push(Math.pow(a, 1.0 / b));
            } else if (token.matches("cubroot")) {
                a = result.pop();
                result.push(Math.pow(a, (1 / 3.0)));
            } else if (token.matches("sqrt")) {
                a = result.pop();
                result.push(Math.sqrt(a));
            } else if (token.matches("\\^")) {
                b = result.pop();
                a = result.pop();
                result.push(Math.pow(a, b));
            } else if (token.matches("reciproc")) {
                a = result.pop();
                result.push(1 / a);
            } else if (token.matches("!|fact")) {
                a = result.pop();
                result.push(ExtMathFunc.factorial(a));
            } else if (token.matches("cube")) {
                a = result.pop();
                result.push(Math.pow(a, 3));
            } else if (token.matches("square")) {
                a = result.pop();
                result.push(Math.pow(a, 2));
            } else if (token.matches("sin")) {
                a = result.pop();
                a = ToRadian(a);
                result.push(Math.sin(a));
            } else if (token.matches("cos")) {
                a = result.pop();
                a = ToRadian(a);
                result.push(Math.cos(a));
            } else if (token.matches("tan")) {
                a = result.pop();
                a = ToRadian(a);
                result.push(Math.tan(a));
            } else if (token.matches("sinh")) {
                a = result.pop();
                result.push(Math.sinh(a));
            } else if (token.matches("cosh")) {
                a = result.pop();
                result.push(Math.cosh(a));
            } else if (token.matches("tanh")) {
                a = result.pop();
                result.push(Math.tanh(a));
            } else if (token.matches("asin")) {
                a = result.pop();
                result.push(RadianTo(Math.asin(a)));
            } else if (token.matches("acos")) {
                a = result.pop();
                result.push(RadianTo(Math.acos(a)));
            } else if (token.matches("atan")) {
                a = result.pop();
                result.push(RadianTo(Math.atan(a)));
            } else if (token.matches("asinh")) {
                a = result.pop();
                result.push(Math.sinh(a));
            } else if (token.matches("acosh")) {
                a = result.pop();
                result.push(Math.cosh(a));
            } else if (token.matches("atanh")) {
                a = result.pop();
                result.push(RadianTo(Math.tanh(a)));
            } else if (token.matches("ln")) {
                a = result.pop();
                result.push(Math.log(a));
            } else if (token.matches("log")) {
                a = result.pop();
                result.push(Math.log10(a));
            } else if (token.matches("powe")) {
                a = result.pop();
                result.push(Math.pow(Math.E, a));
            } else if (token.matches("powten")) {
                a = result.pop();
                result.push(Math.pow(10.0, a));
            } else if (token.matches("frac")) {
                a = result.pop();
                result.push(ExtMathFunc.frac(a));
            } else if (token.matches("int")) {
                a = result.pop();
                result.push(Math.floor(a));
            } else {
                result.push(Double.valueOf(token));
            }
        }
        System.out.println(result.peek());
        return result.pop();
    }

    public int precedence(String operation) {
        if (operation.matches("\\+|\\-")) {
            return 1;
        } else if (operation.matches("\\*|\\/|mod")) {
            return 2;
        } else if (operation.matches("int|frac|powe|ln|log|asinh|acosh|atanh|asin|acos|atan|sinh|cohs|tanh|sin|cos|tan|square|cube|!|reciproc|\\^|sqrt|cubroot")) {
            return 3;
        }
        return 0;
    }

    public double ToRadian(double a) {
        switch (mode) {
            case 0:
                return (a * Math.PI) / 180.0; //Degrees -> Radian
            case 2:
                return (a * Math.PI) / 200.0; //Grad -> Radian
            default:
                return a;
        }
    }

    public double RadianTo(double a) {
        switch (mode) {
            case 0:                             // Degree
                return a * 180.0 / Math.PI;
            case 2:
                return a * 200.0 / Math.PI;
            default:
                return a;
        }
    }

    public String ReBuild(String input) {
        String output = input.replaceAll(" +", "");                             // Remove
        while (true) {
            if (!Pattern.compile("(\\-\\-)|(\\-\\+)|(\\+\\-)|(\\+\\+)").matcher(output).find()) {
                break;

            }
            output = output.replaceAll("(\\-\\-)|(\\+\\+)", "+");               // -- ++       =>        +
            output = output.replaceAll("(\\-\\+)|(\\+\\-)", "-");               // -+ +-       =>        -
        }

        output = output.replaceAll("\\A *\\+ *", "");                           // +1        =>        1
        output = output.replaceAll("\\A *\\- *", "0-");                         // -1        =>        0-1
        output = output.replaceAll("\\( *\\+ *", "(");                          // (+2+2)    =>       (2+2)
        output = output.replaceAll("\\( *\\- *", "(0-");                        // (-2+2)    =>       (0-2+2)

        Pattern pa = Pattern.compile("[0-9.]+\\(");
        Matcher match = pa.matcher(output);
        while (match.find()) {
            String temp = match.group().replaceAll(" *\\(", "");
            output = output.replaceFirst("[0-9.]+ *\\(", temp + "*(");
        }
        return output;
    }

    public String ExLast(String input) {
        System.out.print("Last: " + input + "  =>  ");
        input = ReBuild(input);
        int index = input.length() - 1;
        int NofB = 0;
        boolean found = false;
        final int prority = precedence(input.charAt(index) + "");
        while (index >= 0 && !found) {
            char chr = input.charAt(index);
            switch (chr) {
                case '(':
                    NofB--;
                    if (NofB < 0) {
                        index += 2;
                        found = true;
                    }
                    break;
                case ')':
                    NofB++;
                    break;
                default:
                    int prece = precedence(input.charAt(index) + "");
                    if (NofB == 0 && prece != 0 && (prece < prority || prority == 0)) {
                        index += 2;
                        found = true;
                        break;
                    }
            }
            index--;
        }
        if (index < 0) {
            index = 0;

        }
        System.out.println((prority == 0) ? input.substring(index) : input.substring(index, input.length() - 1));
        return (prority == 0) ? input.substring(index) : input.substring(index, input.length() - 1);
    }

    public double EvaluateLast(String input) {
        return Evaluate(ExLast(input));
    }

    public String FormatBracket(String input) {
        int NoB = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                NoB++;

            } else if (input.charAt(i) == ')') {
                NoB--;

            }
        }
        while (NoB > 0) {
            input += ')';
            NoB--;
        }
        while (NoB < 0) {
            input = '(' + input;
            NoB++;
        }
        return input;
    }

    public static void main(String args[]) {
        Mathematic mathmatics = new Mathematic();
        double result = mathmatics.Evaluate("sin(170)");
        System.out.println("Result: " + result);
        
        double expect_result = Math.sin((170 * Math.PI) / 180.0);
        System.out.println("Expect: " + expect_result);

//
//        System.out.println(mathmatics.EvaluateLast("sqrt( sqrt(sqrt(sqrt(sqrt(sqrt(sqrt(sqrt(sqrt(9)))))))) ) + 1"));
//
//        System.out.println(mathmatics.isMatched("/1+ (1-2-9)"));
//
//        String t = "8++--++--++--8";
//        System.out.println(mathmatics.ReBuild(t));
//        System.out.println(t);
//
//        System.out.println(mathmatics.FormatBracket("((1+3+4))"));
//
//        Pattern pa = Pattern.compile(".*[eE\\*\\/\\(]");
//        Matcher ma = pa.matcher("1+(");
//        while(ma.find())
//            System.out.println(ma.group() + "|+");

    }
}

