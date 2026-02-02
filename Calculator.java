import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
    
    private String input;

    private List<String> inputSegments;

    // Initialize Calculator Object
    public Calculator()
    {
        input = "";
    }

    // ------------------------
    // Calculate
    // ------------------------
    public double Calculate()
    {
        // Initialize operations and ints list
        inputSegments = splitInputs();
        List<String> operations =  new ArrayList<>();
        List<Double> ints = new ArrayList<>();
        // Add all numbers to ints list
        for (String in : inputSegments) {
            try
            {
                ints.add(Double.parseDouble(in));
            }
            catch (Exception e)
            {
                operations.add(in);
            }
        }

        // Combine and calculate until no operations left
        while(!operations.isEmpty())
        {
        // Order of operation - M & D
        if(operations.contains("*") || operations.contains("/"))
        {
            for(int i = 0; i < operations.size(); i++)
            {
                if(operations.get(i).equals("*"))
                {
                    // Combine and multiply numbers to left and right of *
                    operations.remove(i);
                    double num = ints.get(i) * ints.get(i + 1);
                    ints.remove(i); ints.remove(i);
                    ints.add(i, num);
                    break;
                }
                else if(operations.get(i).equals("/"))
                {
                    // Combine and divide numbers to left and right of /
                    operations.remove(i);
                    double num = ints.get(i) / ints.get(i + 1);
                    ints.remove(i); ints.remove(i);
                    ints.add(i, num);
                    break;
                }
            }
        }
        // Order of operation - A & S
        else if(operations.contains("+") || operations.contains("-"))
        {
            for(int i = 0; i < operations.size(); i++)
            {
                if(operations.get(i).equals("+"))
                {
                    // Combine and add numbers to left and right of +
                    operations.remove(i);
                    double num = ints.get(i) + ints.get(i + 1);
                    ints.remove(i); ints.remove(i);
                    ints.add(i, num);
                    break;
                }
                else if(operations.get(i).equals("-"))
                {
                    // Combine and add numbers to left and right of -
                    operations.remove(i);
                    double num = ints.get(i) - ints.get(i + 1);
                    ints.remove(i); ints.remove(i);
                    ints.add(i, num);
                    break;
                }
            }
        }
        }

        return ints.get(0);
    }

    // ------------------------
    // Split Input
    // ------------------------
    private List<String> splitInputs()
    {
        // Split input into operations and numbers
        Character[] equations = new Character[] { '+', '-', '*', '/' };
        List<String> segs = new ArrayList<>();
        String toAdd = "";
        for(int i = 0; i < input.length(); i++)
        {
            if(Arrays.asList(equations).contains(input.charAt(i)))
            {
                // Add current number
                segs.add(toAdd);
                // Add operation
                toAdd = "";
                segs.add(Character.toString(input.charAt(i)));
            }
            else
            {
                // Store current digit
                toAdd += input.charAt(i);
            }
        }
        // Add final number
        segs.add(toAdd);
        return segs;
    }


    // ------------------------
    // Getter & Setter
    // ------------------------
    public String getInput()
    {
        return input;
    }
    public void setInput(String input)
    {
        this.input = input;
    }


}
