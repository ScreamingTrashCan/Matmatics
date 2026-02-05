import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    private int index;

    public Double Calculate(String input)
    {
        index = 0;
        return calc(splitInputs(input));
    }

    // ------------------------
    // Looping Calculate
    // ------------------------
    private Double calc(ArrayList<String> ins)
    {
        index += 1;

        // Check for "single parenthesis" and remove them || ex. (10) -> 10
        if(ins.contains("("))
        {
            int start = FindAt("(", ins);
            System.out.println(start);
            int end = FindAt(")", ins);
            if(end == -1)
            {
                System.out.println("Error: No closing parenthesis.");
                return null;
            }
            System.out.println(end);
            if(end - start <= 2)
            {
                ins.remove(end); 
                ins.remove(start);
            }
        }
        if(ins.contains("["))
        {
            int start = FindAt("[", ins);
            System.out.println(start);
            int end = FindAt("]", ins);
            if(end == -1)
            {
                System.out.println("Error: No closing parenthesis.");
                return null;
            }
            System.out.println(end);
            if(end - start <= 2)
            {
                ins.remove(end); 
                ins.remove(start);
            }
        }
        if(ins.contains("{"))
        {
            int start = FindAt("{", ins);
            System.out.println(start);
            int end = FindAt("}", ins);
            if(end == -1)
            {
                System.out.println("Error: No closing parenthesis.");
                return null;
            }
            System.out.println(end);
            if(end - start <= 2)
            {
                ins.remove(end); 
                ins.remove(start);
            }
        }

        // Check for single ) parenthesis
        if(!ins.contains("(") && ins.contains(")"))
        {
            ins.remove(FindAt(")", ins));
        }
        if(!ins.contains("[") && ins.contains("]"))
        {
            ins.remove(FindAt("]", ins));
        }
        if(!ins.contains("{") && ins.contains("}"))
        {
            ins.remove(FindAt("}", ins));
        }

        /*for (String string : ins) {
            System.out.println(string);
        }
        System.out.println(ins.size());*/

        if(ins.size() != 1)
        {
            String newNum = "";
            int at = 0;

            if(index % 4 == 1 || index % 4 == 3)
            {
                // Exponent
            if(ins.contains("^"))
            {
                at = FindAt("^", ins);
                System.out.println("Found a ^ at " + at);
                newNum = power(at, ins);
            }
            // Mult or Div
            else if(ins.contains("*") || ins.contains("/"))
            {
                int at1 = FindAt("*", ins);
                int at2 = FindAt("/", ins);
                at = at1 > at2 ? at1 : at2;
                if(ins.get(at).equals("*"))
                {
                    System.out.println("Found a * at " + at);
                    newNum = mult(at, ins);
                }
                else
                {
                    System.out.println("Found a / at " + at);
                    newNum = div(at, ins);
                }
            }
            // Add or Sub
            else if(ins.contains("+") || ins.contains("-"))
            {
                int at1 = FindAt("+", ins);
                int at2 = FindAt("-", ins);
                at = at1 > at2 ? at1 : at2;
                if(ins.get(at).equals("+"))
                {
                    System.out.println("Found a + at " + at);
                    newNum = add(at, ins);
                }
                else
                {
                    System.out.println("Found a - at " + at);
                    newNum = sub(at, ins);
                }
            }
            else {
                newNum = null;
            }
            }
            else if(index % 4 == 2 || index % 4 == 0)
            {
                // Add or Sub
            if(ins.contains("+") || ins.contains("-"))
            {
                int at1 = FindAt("+", ins);
                int at2 = FindAt("-", ins);
                at = at1 > at2 ? at1 : at2;
                if(ins.get(at).equals("+"))
                {
                    System.out.println("Found a + at " + at);
                    newNum = add(at, ins);
                }
                else
                {
                    System.out.println("Found a - at " + at);
                    newNum = sub(at, ins);
                }
            }
            // Mult or Div
            else if(ins.contains("*") || ins.contains("/"))
            {
                int at1 = FindAt("*", ins);
                int at2 = FindAt("/", ins);
                at = at1 > at2 ? at1 : at2;
                if(ins.get(at).equals("*"))
                {
                    System.out.println("Found a * at " + at);
                    newNum = mult(at, ins);
                }
                else
                {
                    System.out.println("Found a / at " + at);
                    newNum = div(at, ins);
                }
            }
                            // Exponent
            else if(ins.contains("^"))
            {
                at = FindAt("^", ins);
                System.out.println("Found a ^ at " + at);
                newNum = power(at, ins);
            }
            else {
                newNum = null;
            }
            }

            if(newNum != null)
            {
                ins.add(at - 1, newNum);
                ins.remove(at); ins.remove(at); ins.remove(at);
            }

            return calc(ins);
        }
        else
        {
            return Double.parseDouble(ins.get(0));
        }
    }

    // ------------------------
    // Operations
    // ------------------------
    private Double[] leftAndRight(int at, ArrayList<String> all)
    {
        Double lefthand, righthand;
                try {
                    lefthand = Double.parseDouble(all.get(at - 1));
                } catch (Exception e) {
                    System.err.println("Error: Non-Number as lefthand operand.");
                    return new Double[] {Double.NaN};
                }
                try {
                    righthand = Double.parseDouble(all.get(at + 1));
                } catch (Exception e) {
                    System.err.println("Error: Non-Number as righthand operand.");
                    return new Double[] {Double.NaN};
                }
        return new Double[] { lefthand, righthand };
    }

    private String power(int at, ArrayList<String> all)
    {
        Double[] leftAndRight = leftAndRight(at, all);
        if(leftAndRight[0].isNaN())
        {
            return null;
        }
        return Double.toString(Math.pow(leftAndRight[0], leftAndRight[1]));
    }

    private String mult(int at, ArrayList<String> all)
    {
        Double[] leftAndRight = leftAndRight(at, all);
        if(leftAndRight[0].isNaN())
        {
            return null;
        }
        return Double.toString(leftAndRight[0] * leftAndRight[1]);
    }

    private String div(int at, ArrayList<String> all)
    {
        Double[] leftAndRight = leftAndRight(at, all);
        if(leftAndRight[0].isNaN())
        {
            return null;
        }
        return Double.toString(leftAndRight[0] / leftAndRight[1]);
    }

    private String add(int at, ArrayList<String> all)
    {
        Double[] leftAndRight = leftAndRight(at, all);
        if(leftAndRight[0].isNaN())
        {
            return null;
        }
        return Double.toString(leftAndRight[0] + leftAndRight[1]);
    }

    private String sub(int at, ArrayList<String> all)
    {
        Double[] leftAndRight = leftAndRight(at, all);
        if(leftAndRight[0].isNaN())
        {
            return null;
        }
        return Double.toString(leftAndRight[0] - leftAndRight[1]);
    }

    // ------------------------
    // Tokenizer
    // ------------------------
    private ArrayList<String> splitInputs(String in)
    {
        // Split input into operations and numbers
        Character[] newLines = new Character[] { '+', '-', '*', '/', '^', '(', ')', '[', ']', '{', '}' };
        ArrayList<String> segs = new ArrayList<>();
        String toAdd = "";
        for(int i = 0; i < in.length(); i++)
        {
            if(Arrays.asList(newLines).contains(in.charAt(i)))
            {
                // Add current number
                if(!toAdd.isEmpty()){
                segs.add(toAdd);}
                // Add operation
                toAdd = "";
                segs.add(Character.toString(in.charAt(i)));
            }
            else
            {
                // Store current digit
                toAdd += in.charAt(i);
            }
        }
        // Add final number
        if(!toAdd.isEmpty()){
        segs.add(toAdd);}
        return segs;
    }

    // ------------------------
    // Find At Method
    // ------------------------
    private int FindAt(String in, ArrayList<String> allIns)
    {
        if(index % 4 == 1 || index % 4 == 3)
        {
            for(int i = 0; i < allIns.size(); i++)
        {
            if(allIns.get(i).equals(in))
            {
                return i;
            }
        }
        }
        else
        {
            for(int i = allIns.size() -1; i >= 0; i--)
        {
            if(allIns.get(i).equals(in))
            {
                return i;
            }
        }
        }
        return -1;
    }

}