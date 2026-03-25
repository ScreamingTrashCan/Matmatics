
import java.util.ArrayList;

public class CurveApproximator {

    private String equation;
    private double[] interval;
    private ArrayList<Double[]> points;
    private double n;
    private ArrayList<Double> endPoints;

    // ---------
    // Calculate
    // ---------
    public double[] Calculate() {
        points = findPoints();
        endPoints = new ArrayList<>();
        ArrayList<Double[]> leps = getLEPs();
        double lepAreas = 0;
        for (Double[] lep : leps) {
            lepAreas += lep[2];
        }
        ArrayList<Double[]> reps = getREPs();
        double repAreas = 0;
        for (Double[] rep : reps) {
            repAreas += rep[2];
        }
        return new double[]{lepAreas, repAreas, (lepAreas + repAreas) / 2};
    }

    // ----
    // LEPs
    // ----
    public ArrayList<Double[]> getLEPs() {
        Runner.debugText("Getting LEPs...");
        ArrayList<Double[]> leps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double b = (interval[1] - interval[0]) / n;
            double h = findHeight(i, "left");
            double area = b * h;
            leps.add(new Double[]{b, h, area});
            Runner.debugText("Rectange #" + (i+1) + " Area: " + leps.get(i)[2]);
        }
        return leps;
    }

    // ----
    // REPs
    // ----
    public ArrayList<Double[]> getREPs() {
        Runner.debugText("Getting REPs...");
        ArrayList<Double[]> reps = new ArrayList<>();
        for (int i = (int) n - 1; i >= 0; i--) {
            double b = (interval[1] - interval[0]) / n;
            double h = findHeight(i, "right");
            double area = b * h;
            reps.add(new Double[]{b, h, area});
            Runner.debugText("Rectange #" + (i+1) + " Area: " + reps.get(i)[2]);
        }
        return reps;
    }

    // --------------
    // Get All Points
    // --------------
    public ArrayList<Double[]> findPoints() {
        Runner.debugText("Testing points...");
        ArrayList<Double[]> p = new ArrayList<>();
        double intervalSize = (interval[1] - interval[0]) / n;
        for (int i = 0; i < n; i++) {
            double l = interval[0] + (i * intervalSize);
            double r = interval[0] + ((i + 1) * intervalSize);
            p.add(new Double[]{l, r});
            Runner.debugText("Found a point at (" + l + ", " + r + ")");
        }
        return p;
    }

    // --------
    // Get f(x)
    // --------
    public double findHeight(int offset, String lr) {
        Calculator c = new Calculator();
        double x = lr.equals("left") ? points.get(offset)[0] : points.get(offset)[1];
        String eq = replaceX(equation, x);
        return c.Calculate(eq) / 2;
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

    // ---------------
    // Getter & Setter
    // ---------------
    public String equation() {
        return equation;
    }

    public double[] interval() {
        return interval;
    }

    public void setEquation(String eq) {
        equation = eq;
    }

    public void setInterval(double[] in) {
        interval = in;
    }

    public void setN(double n) {
        this.n = n;
    }
}
