import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntToDoubleFunction;

public class Calculator {
    
    private String input;

    private List<String> inputSegments;

    public Calculator()
    {
        input = "";
    }

    // ------------------------
    // Calculate
    // ------------------------
    public double Calculate()
    {
        inputSegments = splitInputs();
        List<String> operations =  new ArrayList<>();
        List<Double> ints = new ArrayList<>();
        for (String in : inputSegments) {
            try
            {
                ints.add(Double.valueOf(Double.parseDouble(in)));
            }
            catch (Exception e)
            {
                operations.add(in);
            }
        }

        while(!operations.isEmpty())
        {
        if(operations.contains("*") || operations.contains("/"))
        {
            for(int i = 0; i < operations.size(); i++)
            {
                if(operations.get(i).equals("*"))
                {
                    operations.remove(i);
                    double num = ints.get(i) * ints.get(i + 1);
                    ints.remove(i); ints.remove(i);
                    ints.add(i, num);
                    break;
                }
                else if(operations.get(i).equals("/"))
                {
                    operations.remove(i);
                    double num = ints.get(i) / ints.get(i + 1);
                    ints.remove(i); ints.remove(i);
                    ints.add(i, num);
                    break;
                }
            }
        }
        else if(operations.contains("+") || operations.contains("-"))
        {
            for(int i = 0; i < operations.size(); i++)
            {
                if(operations.get(i).equals("+"))
                {
                    operations.remove(i);
                    double num = ints.get(i) + ints.get(i + 1);
                    ints.remove(i); ints.remove(i);
                    ints.add(i, num);
                    break;
                }
                else if(operations.get(i).equals("-"))
                {
                    operations.remove(i);
                    double num = ints.get(i) - ints.get(i + 1);
                    ints.remove(i); ints.remove(i);
                    ints.add(i, num);
                    break;
                }
            }
        }

        }

        //Debug
        return ints.get(0);
    }

    // ------------------------
    // Split Input
    // ------------------------
    private List<String> splitInputs()
    {
        Character[] equations = new Character[] { '+', '-', '*', '/' };
        List<String> segs = new ArrayList<>();
        String toAdd = "";
        for(int i = 0; i < input.length(); i++)
        {
            if(Arrays.asList(equations).contains(input.charAt(i)))
            {
                segs.add(toAdd);
                toAdd = "";
                segs.add(Character.toString(input.charAt(i)));
            }
            else
            {
                toAdd += input.charAt(i);
            }
        }
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
