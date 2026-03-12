import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CLI {
    public HashMap<String, String> textColors = new HashMap<>();
    
    public Scanner scanner = new Scanner(System.in);

    public int input;
    
    public CLI()
    {
        textColors.put("default", "\u001B[0m");
        textColors.put("red", "\u001B[31m");
        textColors.put("green", "\u001B[32m");
        textColors.put("blue", "\u001B[34m");

        // Start of interface

        println("\nWelcome to Matmatics! Choose an option:", "blue");
        println("(1) Calculator", "default");
        println("(2) Base Translator", "default");
        println("(3) Exit\n", "default");

        input = scanInput(new ArrayList<>(Arrays.asList(1,2,3))); // Takes in possible integer values
    }

    private void println(String text, String key)
    {
        String color = textColors.get(key);

        System.out.println(color + text + textColors.get("default"));
    }

    private int scanInput(ArrayList<Integer> validInputs)
    {
        int input = 999;

        do
        {
            try
            {
                input = Integer.parseInt(scanner.nextLine());

                if (!validInputs.contains(input) && input != 0)
                {
                    System.out.println("\n" + input + " is not a valid choice! (0 for choices)\n");
                }
            }
            catch (Exception e)
            {
                System.out.println("\nMust enter an integer! (0 for choices)\n");
            }

            if (input == 0) // User has asked for the valid choices
            {
                System.out.print("\nValid Choices: ");

                for (int i = 0; i < validInputs.size(); i++)
                {
                    System.out.print(validInputs.get(i));

                    if (i == validInputs.size() - 1) continue;

                    System.out.print(", ");
                }

                System.out.println("\n");
            }
        } while (!validInputs.contains(input));

        return input;
    }
}
