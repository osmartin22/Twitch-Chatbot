package ozmar.commands;


public class Calculator {

    private String operation;
    private int pos = -1;
    private int currChar = 0;

    public Calculator() {

    }

    public void setOperation(String operation) {
        this.operation = operation;
        this.pos = -1;
        this.currChar = 0;
    }

    public double parse() {
        nextChar();
        double x = parseExpression();
        if (pos < operation.length()) {
            throw new RuntimeException("Unexpected: " + (char) currChar);
        }
        return x;
    }

    private void nextChar() {
        currChar = (++pos < operation.length()) ? operation.charAt(pos) : -1;
    }

    private boolean eatChar(int charToEat) {
        while (currChar == ' ') {
            nextChar();
        }
        if (currChar == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    private double parseExpression() {
        double x = parseTerm();
        while (true) {
            // Addition
            if (eatChar('+')) {
                x += parseTerm();

            } // Subtraction
            else if (eatChar('-')) {
                x -= parseTerm();

            } else {
                return x;
            }
        }
    }

    private double parseTerm() {
        double x = parseFactor();
        while (true) {
            // Multiplication
            if (eatChar('*')) {
                x *= parseFactor();

            } // Division
            else if (eatChar('/')) {
                x /= parseFactor();

            } else {
                return x;
            }
        }
    }

    private double parseFactor() {
        // Unary plus, treat +# as #
        if (eatChar('+')) {
            return parseFactor();

        } // Unary minus, treat -# as -#
        if (eatChar('-')) {
            return -parseFactor();
        }

        double x;
        int startPos = this.pos;

        // Parentheses
        if (eatChar('(')) {
            x = parseExpression();
            eatChar(')');

            // Get next number
        } else if ((currChar >= '0' && currChar <= '9') || currChar == '.') {
            while ((currChar >= '0' && currChar <= '9') || currChar == '.') {
                nextChar();
            }
            x = Double.parseDouble(operation.substring(startPos, this.pos));

            // Get next function
        } else if (currChar >= 'a' && currChar <= 'z') {
            while (currChar >= 'a' && currChar <= 'z') {
                nextChar();
            }

            String func = operation.substring(startPos, this.pos);
            x = parseFactor();
            switch (func) {
                case "sqrt":
                    x = Math.sqrt(x);
                    break;
                case "sin":
                    x = Math.sin(Math.toRadians(x));
                    break;
                case "cos":
                    x = Math.cos(Math.toRadians(x));
                    break;
                case "tan":
                    x = Math.tan(Math.toRadians(x));
                    break;
                default:
                    throw new RuntimeException("Unknown function: " + func);
            }
        } else {
            throw new RuntimeException("Unexpected: " + (char) currChar);
        }

        // Exponent
        if (eatChar('^')) {
            x = Math.pow(x, parseFactor());
        }

        return x;
    }
}
