
public class Runner {

    public static boolean debug = false;

    public enum Status {
        Normal,
        Thinking,
        Error,
        FatalError
    };
    private static Status status = Status.Normal;

    public static void main(String[] args) {

        try {
            debug = args[0].equals("debug");
        } catch (Exception e) {
            debug = false;
        }

        CurveApproximator ca = new CurveApproximator();
        ca.setEquation("6-x^2");
        ca.setInterval(new double[]{0, 2});
        for (int i = 0; i <= 2; i++) {
            System.out.println(ca.Calculate(2)[i]);
        }

        CLI cli = new CLI();
    }

    // -------
    // Statuses
    // -------
    public static Status getStatus() {
        return status;
    }

    public static void statusNormal() {
        status = Status.Normal;
        debugText("Status set to normal.");
    }

    public static void statusThinking() {
        status = Status.Thinking;
        debugText("Status set to thinking.");
    }

    public static void statusError() {
        status = Status.Error;
        debugText("Status set to error.");
    }

    public static void statusFatalError() {
        status = Status.FatalError;
        debugText("Status set to fatal error.");
    }

    // --------------
    // Debug Dialogue
    // --------------
    public static void debugText(String txt) {
        if (debug) {
            System.out.println(txt);
        }
    }

}
