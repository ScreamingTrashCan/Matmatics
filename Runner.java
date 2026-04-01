


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
        

        //CLI cli = new CLI();
        MainMenuGUI menu = new MainMenuGUI();
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
