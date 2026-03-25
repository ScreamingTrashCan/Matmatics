
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CLI {

    public HashMap<String, String> textColors = new HashMap<>();

    public Scanner scanner = new Scanner(System.in);

    public int input;
    public int mainInput;

    public CLI() {
        textColors.put("default", "\u001B[0m");
        textColors.put("red", "\u001B[31m");
        textColors.put("green", "\u001B[32m");
        textColors.put("blue", "\u001B[34m");
        textColors.put("purple", "\u001B[35m");

        System.out.println();

        while (mainInput != 3) // Not the Exit function
        {
            // ------------------
            // Start of interface
            // ------------------
            println("Welcome to Matmatics! Choose an option:", "purple");
            println("(1) Calculator", "default");
            println("(2) Base Translator", "default");
            println("(3) Curve Approximator", "default");
            println("(4) Exit\n", "default");
            mainInput = scanChoice(new ArrayList<>(Arrays.asList(1, 2, 3, 4))); // Takes in possible integer values
            switch (mainInput) {
                case 1:
                    // ----------
                    // Calculator
                    // ----------
                    input = 0;

                    println("Welcome to the calculator interface! Choose an option:", "blue");
                    println("(1) Calculate an Equation", "default");
                    println("(2) Return to Main Menu\n", "default");

                    while (input != 2) { // Not the Exit function
                        println("Choose an option:\n", "default");
                        input = scanChoice(new ArrayList<>(Arrays.asList(1, 2)));

                        switch (input) {
                            case 1: // Calculate
                                Calculator c = new Calculator();
                                println("Please input your equation:\n", "blue");
                                String in = scanner.nextLine();
                                try {
                                    Double answer = c.Calculate(in);
                                    if (answer != null && answer != Double.NaN) {
                                        println("\nEquation is: " + c.Calculate(in) + "\n", "default");
                                    } else {
                                        println("\nSorry, we couldn't compute the expression.\n", "red");
                                    }
                                } catch (Exception e) {
                                    println("\nSorry, an unknown error occured.\n", "red");
                                }

                                break;
                            case 2: // Exit
                                break;
                        }
                    }
                    break;
                case 2:
                    // ---------------
                    // Base Translator
                    // ---------------
                    BaseTranslator bT = new BaseTranslator();
                    input = 999;

                    while (input != 4) {
                        println("Welcome to the base translator interface! Choose an option:", "blue");
                        println("(1) Calculate your inputs", "default");
                        println("(2) Change input number and base", "default");
                        println("(3) Change output base", "default");
                        println("(4) Return to Main Menu\n", "default");
                        println("\nChoose an option:\n", "default");
                        input = scanChoice(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));

                        switch (input) {
                            case 1: // Calculate
                                String output = "";
                                try {
                                    output = bT.Calculate();
                                } catch (Exception e) {
                                    output = "Ran into an unknown error!\n";
                                }
                                switch (output) {
                                    case "Error! Please use option (2) to enter a proper value before calculating.":
                                    case "Error! Not a valid character.":
                                    case "Error! Cannot be a negative number.":
                                    case "Error! Used a character with a higher value than the input base.":
                                    case "Error! Something went wrong :(":
                                    case "Error! Input Base must be in the range of 2 - 36.":
                                    case "Ran into an unknown error!":
                                        println(output + "\n", "red");
                                        break;
                                    default:
                                        println("Output: " + output + "\n", "default");
                                        break;
                                }
                                break;
                            case 2: // Set Inputs
                                println("Please input your input number:\n", "default");
                                bT.setInput(scanner.nextLine());
                                println("\nPlease input your input base:\n", "default");
                                try {
                                    bT.setInBase(Integer.parseInt(scanner.nextLine()));
                                    System.out.println();
                                } catch (Exception e) {
                                    println("\nError! Invalid input base.", "red");
                                }
                                break;
                            case 3: // Set Outbase
                                println("Please input your output's base:\n", "default");
                                try {
                                    bT.setOutBase(Integer.parseInt(scanner.nextLine()));
                                    System.out.println();
                                } catch (Exception e) {
                                    println("\nError! Invalid output base.", "red");
                                }
                                break;
                            case 4: // Exit
                                break;
                        }
                    }
                    break;
                case 3:
                    // ------------------
                    // Curve Approximator
                    // ------------------
                    CurveApproximator ca = new CurveApproximator();
                    input = 999;

                    while (input != 4) {
                        println("Welcome to the curve approximator interface! Choose an option:", "blue");
                        println("(1) Calculate your inputs", "default");
                        println("(2) Set the equation", "default");
                        println("(3) Set the interval", "default");
                        println("(4) Return to the Main Menu\n", "default");
                        input = scanChoice(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));

                        switch (input) {
                            case 1: // Calculate
                                int n = 0;
                                println("\nHow many rectangles would you like to use?", "default");
                                try {
                                    n = Integer.parseInt(scanner.nextLine());
                                } catch (Exception e) {
                                    println("\nError: Not a number.", "red");
                                }
                                double[] endPoints = ca.Calculate(n);
                                try {
                                    println("LEPs: " + endPoints[0], "default");
                                    println("REPs: " + endPoints[1], "default");
                                    println("MEPs: " + endPoints[2] + "\n", "default");
                                } catch (Exception e) {
                                }
                                break;
                            case 2: // Set Equation
                                println("\nPlease input an equation:", "default");
                                ca.setEquation(scanner.nextLine());
                                break;
                            case 3: // Set Interval
                                int l = 0;
                                int r = 0;
                                println("\nWhat is the starting point?", "default");
                                try {
                                    l = Integer.parseInt(scanner.nextLine());
                                } catch (Exception e) {
                                }
                                println("\nWhat is the ending point?", "default");
                                try {
                                    r = Integer.parseInt(scanner.nextLine());
                                } catch (Exception e) {
                                }
                                ca.setInterval(new double[]{l, r});
                                break;
                            case 4: // Exit
                                break;
                        }
                    }
                    break;
                case 4: // Exit
                    println("Goodbye!\n", "purple");
                    System.exit(0);
                    break;
            }
        }
    }

    // ----------
    // Print Line
    // ----------
    private void println(String text, String key) {
        String color = textColors.get(key);

        System.out.println(color + text + textColors.get("default"));
    }

    // -----------
    // Scan Choice
    // -----------
    private int scanChoice(ArrayList<Integer> validInputs) {
        int input = 999;

        do {
            try {
                input = Integer.parseInt(scanner.nextLine());

                if (!validInputs.contains(input) && input != 0) {
                    println("\n" + input + " is not a valid choice! (0 for help)\n", "red");
                }
            } catch (Exception e) {
                println("\nMust enter an integer! (0 for help)\n", "red");
            }

            if (input == 0) // User has asked for the valid choices
            {
                println("\nValid Choices: ", "red");

                for (int i = 0; i < validInputs.size(); i++) {
                    System.out.print(validInputs.get(i));

                    if (i == validInputs.size() - 1) {
                        continue;
                    }

                    System.out.print(", ");
                }

                System.out.println("\n");
            }
        } while (!validInputs.contains(input));

        System.out.println();

        return input;
    }
}
