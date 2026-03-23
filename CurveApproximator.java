public class CurveApproximator {

    private String equation;
    private double[] interval;
    private double recs;


    // ---------------
    // Find Total Area
    // ---------------
    public double[] Calculate(int n)
    {
        recs = n;
        double LEP = 0;
        double REP = 0;
        for(int i = 0; i < n; i++)
        {
            LEP += Math.abs(findArea(i, "left"));
        }
        for(int i = n; i > 0; i--)
        {
            REP += Math.abs(findArea(i, "right"));
        }
        return new double[] {LEP, REP, (LEP+REP)/2};
    }


    // ------------------------
    // Find Area of 1 Iteration
    // ------------------------
    public double findArea(int offset, String lr)
    {
        Calculator c = new Calculator();
        return c.Calculate("((" + leftandright(offset)[1] + "-" + leftandright(offset)[0] + ")" + "/" + recs + ")" + "*" + findHeight(offset, lr));
    }
    
    // ------------------
    // Get Specific l & r
    // ------------------
    public double[] leftandright(int offset)
    {
        double l = offset * ((interval[1] - interval[0]) / recs);
        double r = l + ((interval[1] - interval[0]) / recs);
        return new double[] {l, r};
    }


    // --------
    // Get f(x)
    // --------
    public double findHeight(int offset, String lr)
    {
        Calculator c = new Calculator();
        double x = lr.equals("left") ? c.Calculate(offset + "*((" + leftandright(offset)[1] + "-" + leftandright(offset)[0] + ")/" + recs + ")" ) : c.Calculate((offset+1) + "*((" + leftandright(offset)[1] + "-" + leftandright(offset)[0] + ")/" + recs + ")" );
        String eq = replaceX(equation, x);
        return c.Calculate(eq);
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
    public String equation()
    {
        return equation;
    }
    public double[] interval()
    {
        return interval;
    }
    public void setEquation(String eq)
    {
        equation = eq;
    }
    public void setInterval(double[] in)
    {
        interval = in;
    }

}