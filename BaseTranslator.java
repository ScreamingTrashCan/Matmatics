import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseTranslator {

    // Intialize Characters
    public Character[] letters = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    private String input;
    private String output;

    private int inputBase;
    private int outputBase;


    private BigInteger base10Number;

    private List<BigInteger> digitValues;
    private List<Integer> digitAmounts;

    // ------------------------
    // Create Base Translator
    // ------------------------
    public BaseTranslator()
    {
        input = "";
        output = "";
        inputBase = 10;
        outputBase = 10;
        base10Number = BigInteger.ZERO;
    }

    // ------------------------
    // Calculate
    // ------------------------
    public void calculate()
    {
        // If the input base is within range of 2 - 36
        if (inputBase >= 2 && inputBase <= 36 && outputBase >=2 && outputBase <= 36)
        {
            base10Number = Normalize(input, inputBase);
            // If the number is valid (is positive, contains correct characters, and characters are within scope)
            if (!base10Number.equals(BigInteger.valueOf(-999)) && !base10Number.equals(BigInteger.valueOf(-1)) && !base10Number.equals(BigInteger.valueOf(-67)))
            {
            AddDigitValues(base10Number, outputBase);
            AmountOfDigits(base10Number);

            output = FinalizeString(base10Number);
            }
            // Non-valid character used
            else if (base10Number.equals(BigInteger.valueOf(-999)))
            {
                output = "Error! Not a valid character.";
            }
            // Used a negative number
            else if (base10Number.equals(BigInteger.valueOf(-1)))
            {
                output = "Error! Cannot be a negative number.";
            }
            // Used a character outside of scope
            else if (base10Number.equals(BigInteger.valueOf(-67)))
            {
                output = "Error! Used a character with a higher value than the input base.";
            }
            // Other errors
            else
            {
                output = "Error! Something went wrong :(";
            }
            }
        else if (inputBase < 2 || inputBase > 36)
        {
            output = "Error! Input Base must be in the range of 2 - 36.";
        }
        else
        {
            output = "Error! Output Base must be in the range of 2 - 36.";
        }
    }


    // ------------------------
    // Convert character to a Base 10 Integer
    // ------------------------
    public Long BaseTenValue(char c)
    {
        // Ensure it uses valid characters
        if (c == '-')
        {
            return -1L;
        }
        else if (!Arrays.asList(letters).contains(c))
        {
            return -999L;
        }
        // Returns a Long Value equal to the Base 10 Value of a Character (ex. 0 is 0, 1 is 1... A is 10, B is 11, etc.)
            for (int i = 0; i < letters.length; i++)
            {
                if (letters[i] == c)
                {
                    return (long)i;
                }
            }
        return 0L;
    }

    // ------------------------
    // Operations
    // ------------------------
    String FinalizeString(BigInteger input)
    {
        // Combine all characters into a string
        String out = "";
        for(int i = digitAmounts.size() -1; i >= 0; i--)
        {
            out += letters[(int)digitAmounts.get(i)];
        }
        return out;
    }

    void AmountOfDigits(BigInteger input)
    {
        // Determines the amount of digits that the output will have
        BigInteger number = input;
        for(int i = digitValues.size() - 1; i >= 0; i--)
        {
            while(number.compareTo(digitValues.get(i)) >= 0)
            {
                number = number.subtract(digitValues.get(i));
                digitAmounts.set(i, digitAmounts.get(i) + 1);
            }
        }
    }

    BigInteger Normalize(String input, int inBase)
    {
        // Turns the input into a normalized Base 10 BigInteger
        BigInteger out = BigInteger.ZERO;
        int length = input.length();
        for(int i = length - 1; i >= 0; i--)
        {
            int pow = length -i -1;
            long num = BaseTenValue(input.charAt(i));
            // Ensures the input is valid
            if(num == -999)
            {
                return BigInteger.valueOf(-999);
            }
            else if(num == -1)
            {
                return BigInteger.valueOf(-1);
            }
            else if(num > inputBase)
            {
                return BigInteger.valueOf(-67);
            }
            BigInteger power = BigInteger.valueOf((int)Math.pow(inBase, pow));
            BigInteger adjustedNum = power.multiply(BigInteger.valueOf(num));
            out = out.add(adjustedNum);
        }
        return out;
    }

    void AddDigitValues(BigInteger num, int baseInt)
    {
        // The "amount" of each digit there are in the normalized number (ex. 145 contains one 100, four 10s, and five 1s)
        digitValues = new ArrayList<>();
        digitAmounts = new ArrayList<>();
        BigInteger current = BigInteger.ZERO;
        for(int i = 0; current.compareTo(num) <= 0; i++)
        {
            current = BigInteger.valueOf((long)Math.pow(baseInt, i));
            if(current.compareTo(num) <= 0)
            {
                digitValues.add(current);
                digitAmounts.add(0);
            }
        }
    }

    // ------------------------
    // To String
    // ------------------------
    @Override
    public String toString()
    {
        String str = "";
        str += "+--------BASE TRANSLATOR INFO--------+\n";
        str += "| Current Input: " + input + " /  Input Base: " + inputBase + "\n";
        str += "| Current Output: " + output + " / Output Base: " + outputBase + "\n";
        str += "+------------------------------------+";

        return str;
    }

    
    // ------------------------
    // Getter and Setter
    // ------------------------
    public String getInput()
    {
        return input;
    }  
    public void setInput(String input)
    {
        this.input = input;
    } 
    
    public String getOutput()
    {
        return output;
    }
    public void setOutput(String output)
    {
        this.output = output;
    }

    public int getInBase()
    {
        return inputBase;
    }
    public void setInBase(int inputBase)
    {
        this.inputBase = inputBase;
    }

    public int getOutBase()
    {
        return outputBase;
    }
    public void setOutBase(int outputBase)
    {
        this.outputBase = outputBase;
    }

}