
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

        while (mainInput != 3) // Not the Exit function
        {
            // ------------------
            // Start of interface
            // ------------------
            println("\nWelcome to Matmatics! Choose an option:", "purple");
            println("(1) Calculator", "default");
            println("(2) Base Translator", "default");
            println("(3) Exit\n", "default");
            mainInput = scanChoice(new ArrayList<>(Arrays.asList(1, 2, 3))); // Takes in possible integer values
            switch (mainInput) {
                case 1: // Calculator
                    input = 0;
                    println("Welcome to the calculator interface! Choose an option:", "blue");
                    println("(1) Calculate an Equation", "default");
                    println("(2) Exit\n", "default");

                    while (input != 2) {
                        println("\nChoose an option:", "default");
                        input = scanChoice(new ArrayList<>(Arrays.asList(1, 2)));

                        switch (input) {
                            case 1: // Calculate
                                Calculator c = new Calculator();
                                boolean calculated = false;
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
                case 2: // Base Translator
                    break;
                case 3: // Exit
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
                println("Valid Choices: ", "default");

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

        System.out.println("\n");

        return input;
    }
}
