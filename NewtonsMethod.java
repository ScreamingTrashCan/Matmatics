
import java.util.ArrayList;

public class NewtonsMethod {

    private String original, derivative;
    private Calculator calc;

    private ArrayList<Double> guesses;

    public NewtonsMethod(String original, String derivative) {
        this.original = original;
        this.derivative = derivative;
        guesses = new ArrayList<>();
        calc = new Calculator();
    }

    public double Calculate(double initial, int iterations) {
        guesses.add(initial);
        for (int i = 0; i < iterations; i++) {
            guesses.add(method());
        }
        return guesses.get(guesses.size() - 1);
    }

    public double original(double xVal) {
        String o = replaceX(original, xVal);
        return calc.Calculate(o);
    }

    public double derivative(double xVal) {
        String d = replaceX(derivative, xVal);
        return calc.Calculate(d);
    }

    public double method() {
        String eq = guesses.get(guesses.size() - 1) + "-(" + original(guesses.get(guesses.size() - 1)) + "/" + derivative(guesses.get(guesses.size() - 1)) + ")";
        return calc.Calculate(eq);
    }

    public String replaceX(String equation, double xVal) {
        String newEquation = "";
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) != 'x') {
                newEquation += equation.charAt(i);
            } else {
                try {
                    if (Character.isDigit(equation.charAt(i - 1))) {
                        newEquation += "*" + xVal;
                    } else {
                        newEquation += xVal;
                    }
                } catch (Exception e) {
                    newEquation += xVal;
                }
            }
        }
        return newEquation;
    }

    @Override
    public String toString() {
        return "Original: " + original + "\nDerivative: " + derivative + "\nLast guess: " + (guesses.isEmpty() ? "none" : guesses.get(guesses.size() - 1));
    }

}
