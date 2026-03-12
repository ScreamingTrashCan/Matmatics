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

        try
        {
            debug = args[0].equals("debug");
        }
        catch (Exception e) {
            debug = false;
        }


        //NewtonsMethod nw = new NewtonsMethod();
        //System.out.println(nw.Calculate("x^5-2x^4+3x^3-8x^2+10x-4", "5x^4-8x^3+9x^2-16x+10", 2, 70));
        
    }


    // -------
    // Statues
    // -------
    public static Status getStatus()
    {
        return status;
    }
    public static void statusNormal()
    {
        status = Status.Normal;
        debugText("Status set to normal.");
    }
    public static void statusThinking()
    {
        status = Status.Thinking;
        debugText("Status set to thinking.");
    }
    public static void statusError()
    {
        status = Status.Error;
        debugText("Status set to error.");
    }
    public static void statusFatalError()
    {
        status = Status.FatalError;
        debugText("status set to fatal error.");
    }

    // --------------
    // Debug Dialogue
    // --------------
    public static void debugText(String txt)
    {
        if(debug)
        {
            System.out.println(txt);
        }
    }

}