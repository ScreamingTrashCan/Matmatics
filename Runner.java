import java.util.Scanner;

public class Runner {
    
    public static void main(String[] args) {
        BaseTranslateMenu();
    }

    // ------------------------
    // In-terminal Base Translator
    // ------------------------
    public static void BaseTranslateMenu()
    {
        BaseTranslator bT = new BaseTranslator();

        Scanner s = new Scanner(System.in);

        System.out.println("BASE TRANSLATOR INPUT COMMAND: \n>calculate \n>set \n>info \n>exit");

        String sLine = s.nextLine();

        while(!sLine.equals("exit"))
        {
            switch(sLine)
            {
                case "calculate":
                    bT.calculate();
                    System.out.println("|| OUTPUT: " + bT.getOutput() + " ||");
                    break;
                case "set":
                    System.out.println("   What would you like to change? \n   >input \n   >output \n   >inbase \n   >outbase");
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
                            System.out.println("Unknown command.");
                    }
                    break;
                case "info":
                    System.out.println(bT.toString());
                    break;
                default:
                    System.out.println("Unknown command.");
                    break;
            }

            System.out.println("INPUT COMMAND: \n>calculate \n>set\n>info \n>exit");

            sLine = s.nextLine();
        }

        s.close();
    }

}
