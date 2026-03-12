import java.util.HashMap;

public class CLI {
    public HashMap<String, String> textColors = new HashMap<>();
    
    public CLI()
    {
        textColors.put("default", "\u001B[0m");
        textColors.put("red", "\u001B[31m");
        textColors.put("green", "\u001B[32m");
        textColors.put("blue", "\u001B[34m");

        // Start of interface

        println("fro", "blue");
        println("wasabi", "green");
        println("hellokitty123\n", "red");

        println("Welcome to Matmatics! Choose an option:", "blue");
        println("(1) Calculator", "default");
        println("(2) Base Translator", "default");
        println("(3) Exit", "default");
    }

    private void println(String text, String key)
    {
        String color = textColors.get(key);

        System.out.println(color + text + textColors.get("default"));
    }
}
