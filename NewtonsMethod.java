
import java.util.ArrayList;

public class NewtonsMethod {

    private String original, derivative;
    private final Calculator calc;

    private ArrayList<Double> guesses;

    public NewtonsMethod() {
        calc = new Calculator();
    }

    // -----------------------
    // Public Calculate Method
    // -----------------------
    public Double Calculate(String original, String derivative, double initial, int iterations) {
        guesses = new ArrayList<>();
        guesses.add(initial);
        this.original = original;
        this.derivative = derivative;
        Runner.debugText("Using Newtons Method with\nOriginal: " + this.original + "\nDerivative: " + this.derivative);
        for (int i = 0; i < iterations; i++) {
            guesses.add(method());
        }
        try {
            if(guesses.get(guesses.size() -1) == null) {
                System.out.println("Numbers got too small/large!");
            Runner.statusError();
            return Double.NaN;
            }   
            else {
                return guesses.get(guesses.size() - 1);  
            }
        } catch (NullPointerException e) {
            System.out.println("Numbers got too small/large!");
            Runner.statusError();
            return Double.NaN;
        }
        catch (Exception e)
        {
            Runner.debugText("Ran into an unknown error!");
            Runner.statusFatalError();
            return Double.NaN;
        }
    }

    // ---------------------
    // Value of the Original
    // ---------------------
    public Double original(double xVal) {
        try {
            String o = replaceX(original, xVal);
            Runner.debugText("Original substituted is " + o);
            return calc.Calculate(o);
        } catch (NullPointerException e) {
            Runner.debugText("Error: Could not compute original.");
            Runner.statusError();
            return Double.NaN;
        }
    }

    // -----------------------
    // Value of the Derivative
    // -----------------------
    public Double derivative(double xVal) {
        try {
            String d = replaceX(derivative, xVal);
            Runner.debugText("Original substituted is " + d);
            return calc.Calculate(d);
        } catch (NullPointerException e) {
            Runner.debugText("Error: Could not compute original.");
            Runner.statusError();
            return Double.NaN;
        }
    }

    // -----------------------
    // Newtons Method Equation
    // -----------------------
    public Double method() {
        try {
            String eq = guesses.get(guesses.size() - 1) + "-(" + original(guesses.get(guesses.size() - 1)) + "/" + derivative(guesses.get(guesses.size() - 1)) + ")";
            Runner.debugText("The Method equation is " + eq);
            return calc.Calculate(eq);
        } catch (NullPointerException e) {
            Runner.debugText("Error: Could not compute original.");
            Runner.statusError();
            return Double.NaN;
        }
    }

    // ------------------------
    // Replace "x" with a value
    // ------------------------
    public String replaceX(String equation, double xVal) {
        String newEquation = "";
        Runner.debugText("Replacing \"x\" in " + equation + " with " + xVal);
        try {
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
        } catch (Exception e) {
            Runner.debugText("Error: Could not substitute x.");
            Runner.statusError();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Original: " + original + "\nDerivative: " + derivative + "\nLast guess: " + (guesses.isEmpty() ? "none" : guesses.get(guesses.size() - 1));
    }

}
