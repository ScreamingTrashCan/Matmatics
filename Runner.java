import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;

public class Runner {
    
    public static void main(String[] args) {

        //Debug

        Scanner s = new Scanner(System.in);
        MainMenu(s);
    }

    // ------------------------
    // Main Menu
    // ------------------------
    public static void MainMenu(Scanner s)
    {
        String sLine;

        do
        {
            System.out.println("CHOOSE A COMMAND: \n>calculator \n>basetranslator \n>exit \n");

            sLine = s.nextLine();

            System.out.println();

            switch(sLine)
            {
                case "calculator":
                    CalculateMenu(s);
                case "basetranslator":
                    do
                    {
                        System.out.println("CHOOSE A TYPE: \n>gui \n>terminal \n>back \n");

                        sLine = s.nextLine();

                        System.out.println();

                        switch(sLine)
                        {
                            case "gui":
                                setup();
                                MainMenu(s);
                                break;
                            case "terminal":
                                BaseTranslatorTerminal(s);
                                MainMenu(s);
                                break;
                            case "back":
                                System.out.println("Returning to main menu...\n");
                                MainMenu(s);
                                break;
                            default:
                                System.out.println("Unknown command.\n");
                        }
                    } while(!sLine.equals("back"));
                case "exit":
                    System.out.println("Exiting program...\n");
                    break;
                default:
                    System.out.println("Unknown command.\n");
                    break;
            }
        } while(!sLine.equals("exit"));
        
        s.close();

        System.exit(0);
    }

    // ------------------------
    // In-terminal Calculator
    // ------------------------
    public static void CalculateMenu(Scanner s)
    {
        Calculator calc = new Calculator();

        System.out.println("CALCULATOR INPUT COMMAND: \n>calculate \n>exit");

        String sLine = s.nextLine();

        while(!sLine.equals("exit"))
        {
            switch(sLine)
            {
                case "calculate":
                    System.out.println("    Note - Write it in Base 10, no spaces, and only +,-,*,/,^");
                    System.out.println("When nesting parenthesis, use the formatting ([{}])");
                    System.out.println("    Input your equation:");
                    sLine = s.nextLine();
                    System.out.println("|| OUTPUT: " + calc.Calculate(sLine) + " ||");
                    break;
                case "basetranslator":
                    BaseTranslatorTerminal(s);
                default:
                    System.out.println("Unknown command.");
                    break;
            }

            System.out.println("CALCULATOR INPUT COMMAND: \n>calculate \n>exit");

            sLine = s.nextLine();
        }

        MainMenu(s);
    }

    // ------------------------
    // Instantiates GUI
    // ------------------------
    public static void setup()
    {
        JFrame frame = new JFrame("Matmatics Base Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setSize(new Dimension(1920, 1080));
        
        BaseTranslatorGUI panel = new BaseTranslatorGUI();
        frame.add(panel);

        frame.setVisible(true);
        frame.toFront();
        frame.requestFocus();
    }

    // ------------------------
    // In-terminal Base Translator
    // ------------------------
    public static void BaseTranslatorTerminal(Scanner s)
    {
        BaseTranslator bT = new BaseTranslator();

        System.out.println("BASE TRANSLATOR INPUT COMMAND: \n>calculate \n>set \n>info \n>exit \n");

        String sLine = s.nextLine();

        System.out.println();

        while(!sLine.equals("exit"))
        {
            switch(sLine)
            {
                case "calculate":
                    bT.calculate();
                    System.out.println("|| OUTPUT: " + bT.getOutput() + " ||");
                    break;
                case "set":
                    System.out.println("   What would you like to change? \n   >input \n   >output \n   >inbase \n   >outbase   \n");
                    sLine = s.nextLine();
                    System.out.println("      Choose value:");
                    switch(sLine)
                    {
                        case "input":
                            bT.setInput(s.nextLine());
                            break;
                        case "output":
                            bT.setOutput(s.nextLine());
                            break;
                        case "inbase":
                            try {
                                bT.setInBase(Integer.parseInt(s.nextLine()));
                            }
                            catch (Exception e){
                                System.out.println("Error: Could not complete operation. " + e);
                            }
                            break;
                        case "outbase":
                            try {
                                bT.setOutBase(Integer.parseInt(s.nextLine()));
                            }
                            catch (Exception e){
                                System.out.println("Error: Could not complete operation. " + e);
                            }
                            break;
                        default:
                            System.out.println("Unknown command.\n");
                    }
                    break;
                case "info":
                    System.out.println(bT.toString());
                    break;
                default:
                    System.out.println("Unknown command.\n");
                    break;
            }

            System.out.println("INPUT COMMAND: \n>calculate \n>set\n>info \n>exit");

            sLine = s.nextLine();

            System.out.println();
        }
        
        MainMenu(s);
    }
}