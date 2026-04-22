import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

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
        

        CLI cli = new CLI();

        /*
        JFrame frame = new JFrame("Matmatics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setSize(new Dimension(1920, 1080));

        ImageIcon icon = new ImageIcon("Media/Matti/Matti_Idle.png");
        Image iconImage = icon.getImage();
        frame.setIconImage(iconImage);

        MainMenuGUI panel = new MainMenuGUI();
        frame.getContentPane().add(panel);

        frame.setVisible(true);
        */
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
