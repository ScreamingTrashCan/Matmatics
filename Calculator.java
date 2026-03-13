
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    private ArrayList<Oper> opers;
    private int index;

    public Calculator() {

    }

    // -------------------------
    // Public Calculate Function
    // -------------------------
    public Double Calculate(String in) {
        index = 0;
        opers = tokenizeInput(in);
        // Debug Info
        try {
            Runner.debugText("----------\nInput was:");
            for (Oper oper : opers) {
                Runner.debugText(oper.toString());
            }
            Runner.debugText("----------");
        } catch (NullPointerException e) {
            Runner.debugText("The input was null.");
        }
        // End Debug Info
        Double output = null;
        try {
            output = calc(opers);
        } catch (Exception e) {
            Runner.statusFatalError();
            System.out.println("Ran into an unknown error!");
        }
        if (output != null && opers != null) {
            return output;
        } else {
            System.out.println("Sorry, we couldn't compute the expression.");
            Runner.statusError();
            return null;
        }
    }

    // --------------
    // Calculate Loop
    // --------------
    private Double calc(ArrayList<Oper> input) {
        index++;
        if (input != null) {
            Runner.debugText("Iteration #" + index);
            Runner.debugText("Total values: " + input.size());
            for (Oper oper : input) {
                Runner.debugText("Contains " + oper.toString());
            }

            int nested = 0;
            for (Oper oper : input) {
                if (oper.operator() != null) {
                    if (oper.operator() == "(") {
                        nested++;
                        oper.setNested(nested);
                    } else if (oper.operator() == ")") {
                        oper.setNested(nested);
                        nested--;
                    } else {
                        oper.setNested(nested);
                    }
                } else {
                    oper.setNested(nested);
                }
            }

            if (input.size() > 1) {
                ArrayList<Oper> newInput = new ArrayList<>(input);
                int highestNested = 0;
                for (Oper oper : newInput) {
                    if (oper.nested() >= highestNested) {
                        highestNested = oper.nested();
                    }
                }
                Runner.debugText("Highest nested is " + highestNested);
                if (contains(input, "(") && contains(input, ")")) {
                    // Remove Single Parenthesis
                    Runner.debugText("Contains a parenthesis.");
                    int firstAt = FindAt("(", highestNested, newInput);
                    Runner.debugText("Contains ( at " + firstAt);
                    int lastAt = -1;
                    int inOrder = 1;
                    while (lastAt < firstAt) {
                        lastAt = FindAt(")", inOrder, newInput);
                        inOrder++;
                    }
                    Runner.debugText("Contains ) at " + lastAt);
                    while (lastAt - firstAt == 2) {
                        newInput.remove(firstAt);
                        newInput.remove(firstAt + 1);
                        if (!contains(newInput, "(")) {
                            break;
                        }
                        // Recalculate after stripping
                        highestNested = 0;
                        for (Oper oper : newInput) {
                            if (oper.nested() >= highestNested) {
                                highestNested = oper.nested();
                            }
                        }
                        firstAt = FindAt("(", highestNested, newInput);
                        lastAt = -1;
                        inOrder = 1;
                        while (lastAt < firstAt) {
                            lastAt = FindAt(")", inOrder, newInput);
                            inOrder++;
                        }
                    }
                    if (contains(newInput, "(")) {
                        highestNested = 0;
                        for (Oper oper : newInput) {
                            if (oper.nested() >= highestNested) {
                                highestNested = oper.nested();
                            }
                        }
                        Runner.debugText("Highest nested is " + highestNested);
                        int deepestAt = FindAtNested("(", highestNested, newInput);
                        if (deepestAt == -1) {
                            return calc(newInput);
                        }
                        Runner.debugText("Opening of deepest at " + deepestAt);
                        int deepestLast = -1;
                        for (int i = deepestAt + 1; i < newInput.size(); i++) {
                            if (newInput.get(i).operator() != null
                                    && newInput.get(i).operator().equals(")")) {
                                deepestLast = i;
                                break;
                            }
                        }
                        if (deepestLast == -1) {
                            newInput.remove(deepestAt);
                            return calc(newInput);
                        }
                        Runner.debugText("Closing of deepest at " + deepestLast);
                        ArrayList<Oper> parenEquation = new ArrayList<>(newInput.subList(deepestAt + 1, deepestLast));
                        Double parenValue = null;
                        try {
                            parenValue = calc(parenEquation);
                        } catch (Exception e) {
                            System.out.println("Ran into an unknown error!");
                            Runner.statusFatalError();
                        }
                        Runner.debugText("Parenthesis Value is " + parenValue);
                        deepestAt = FindAtNested("(", highestNested, newInput);
                        if (deepestAt == -1) {
                            return calc(newInput);
                        }
                        deepestLast = -1;
                        for (int i = deepestAt + 1; i < newInput.size(); i++) {
                            if (newInput.get(i).operator() != null
                                    && newInput.get(i).operator().equals(")")) {
                                deepestLast = i;
                                break;
                            }
                        }
                        if (deepestLast == -1) {
                            newInput.remove(deepestAt);
                            return calc(newInput);
                        }
                        int allToRemove = deepestLast - deepestAt + 1;
                        Runner.debugText("Going to remove " + allToRemove);
                        try {
                            newInput.add(deepestAt, new Oper(parenValue, highestNested - 1));
                            Runner.debugText("Adding " + newInput.get(deepestAt).toString() + " at position " + deepestAt);
                        } catch (IndexOutOfBoundsException e) {
                            newInput.add(0, new Oper(parenValue, highestNested - 1));
                        }
                        for (int i = 0; i < allToRemove; i++) {
                            Runner.debugText("Removing " + newInput.get((deepestAt + 1)).toString());
                            newInput.remove(deepestAt + 1);
                        }
                        for (Oper oper : newInput) {
                            Runner.debugText("Now is " + oper.toString());
                        }
                    }
                } else if (contains(newInput, "(") && !contains(newInput, ")")) {
                    newInput.remove(newInput.get(FindAt("(", 1, newInput)));
                } else if (!contains(newInput, "(") && contains(newInput, ")")) {
                    newInput.remove(newInput.get(FindAt(")", 1, newInput)));
                } else {
                    int foundAt = 0;
                    Double newAt = 0.0;

                    if (contains(newInput, "^")) {
                        foundAt = FindAt("^", 1, newInput);
                        newAt = power(foundAt, newInput);
                    } else if (contains(newInput, "*") || contains(newInput, "/")) {
                        int multfoundAt = FindAt("*", 1, newInput);
                        int divfoundAt = FindAt("/", 1, newInput);
                        foundAt = multfoundAt < divfoundAt ? multfoundAt : divfoundAt;
                        Runner.debugText("Found MD at " + foundAt);
                        newAt = multfoundAt < divfoundAt ? mult(foundAt, newInput) : div(foundAt, newInput);
                    } else if (contains(newInput, "+") || contains(newInput, "-")) {
                        int addfoundAt = FindAt("+", 1, newInput);
                        int subfoundAt = FindAt("-", 1, newInput);
                        foundAt = subfoundAt < addfoundAt ? subfoundAt : addfoundAt;
                        Runner.debugText("Found AS at " + foundAt);
                        newAt = subfoundAt < addfoundAt ? sub(foundAt, newInput) : add(foundAt, newInput);
                    }

                    if (foundAt != 0) {
                        newInput.add(foundAt - 1, new Oper(newAt, input.get(foundAt).nested()));
                        newInput.remove(foundAt);
                        newInput.remove(foundAt);
                        newInput.remove(foundAt);
                    }

                }

                if (index < 99) {
                    return calc(newInput);
                } else {
                    return Double.NaN;
                }

            } else {
                Runner.debugText("Done!");
                try {
                    return input.get(0).operand();
                } catch (IndexOutOfBoundsException e) {
                    return null;
                }
            }
            //return Double.NaN;
        } else {
            Runner.statusError();
            return Double.NaN;
        }
    }

    // -----------------
    // Find At Method(s)
    // -----------------
    private int FindAt(String operator, int inOrder, ArrayList<Oper> allIns) {
        int at = 0;
        for (int i = 0; i < allIns.size(); i++) {
            if (allIns.get(i).operator() != null && allIns.get(i).operator().equals(operator)) {
                at++;
                if (at == inOrder) {
                    return i;
                }
            }
        }
        return 999;
    }

    private int FindAtNested(String operator, int nestedLevel, ArrayList<Oper> allIns) {
        for (int i = 0; i < allIns.size(); i++) {
            if (allIns.get(i).operator() != null
                    && allIns.get(i).operator().equals(operator)
                    && allIns.get(i).nested() == nestedLevel) {
                return i;
            }
        }
        return 999;
    }

    // ----------
    // Operations
    // ----------
    public Double[] leftAndRight(int at, ArrayList<Oper> opers) {
        Runner.debugText("Checking " + opers.get(at).toString() + " at " + at);
        Double lefthand, righthand;
        try {
            lefthand = opers.get(at - 1).operand();
            if (lefthand == null) {
                return new Double[]{Double.NaN};
            }
        } catch (IndexOutOfBoundsException e) {
            Runner.debugText("Error: No lefthand operand found.");
            return new Double[]{Double.NaN};
        }
        try {
            righthand = opers.get(at + 1).operand();
            if (righthand == null) {
                return new Double[]{Double.NaN};
            }
        } catch (IndexOutOfBoundsException e) {
            Runner.debugText("Error: No righthand operand found.");
            return new Double[]{Double.NaN};
        }
        Runner.debugText("Lefthand is " + lefthand + " Righthand is " + righthand);
        return new Double[]{lefthand, righthand};
    }

    public Double power(int at, ArrayList<Oper> opers) {
        Double[] leftAndRight = leftAndRight(at, opers);
        if (leftAndRight[0].isNaN()) {
            return null;
        }
        Runner.debugText("Exponentiating " + leftAndRight[0] + " and " + leftAndRight[1]);
        return Math.pow(leftAndRight[0], leftAndRight[1]);
    }

    public Double mult(int at, ArrayList<Oper> opers) {
        Double[] leftAndRight = leftAndRight(at, opers);
        if (leftAndRight[0].isNaN()) {
            return null;
        }
        Runner.debugText("Multiplying " + leftAndRight[0] + " and " + leftAndRight[1]);
        return leftAndRight[0] * leftAndRight[1];
    }

    public Double div(int at, ArrayList<Oper> opers) {
        Double[] leftAndRight = leftAndRight(at, opers);
        if (leftAndRight[0].isNaN()) {
            return null;
        }
        Runner.debugText("Dividing " + leftAndRight[0] + " and " + leftAndRight[1]);
        return leftAndRight[0] / leftAndRight[1];
    }

    public Double add(int at, ArrayList<Oper> opers) {
        Double[] leftAndRight = leftAndRight(at, opers);
        if (leftAndRight[0].isNaN()) {
            return null;
        }
        Runner.debugText("Adding " + leftAndRight[0] + " and " + leftAndRight[1]);
        return leftAndRight[0] + leftAndRight[1];
    }

    public Double sub(int at, ArrayList<Oper> opers) {
        Double[] leftAndRight = leftAndRight(at, opers);
        if (leftAndRight[0].isNaN()) {
            return null;
        }
        Runner.debugText("Subtracting " + leftAndRight[0] + " and " + leftAndRight[1]);
        return leftAndRight[0] - leftAndRight[1];
    }

    // --------------
    // Tokenize Input
    // --------------
    private ArrayList<Oper> tokenizeInput(String in) {
        ArrayList<Oper> inputs = new ArrayList<>();
        Runner.debugText("Tokenizing input: " + in);
        // Operators
        Character[] operators = new Character[]{'+', '-', '*', '/', '^'};
        Character[] openParen = new Character[]{'(', '[', '{'};
        Character[] closeParen = new Character[]{')', ']', '}'};
        String toAdd = "";
        int nested = 0;

        try {
            for (int i = 0; i < in.length(); i++) {
                //Runner.debugText("Current nesting level: " + nested);
                if (Arrays.asList(operators).contains(in.charAt(i))) // If Char @ i is an operator
                {
                    if ((in.charAt(i) == '-' && i == 0) || (in.charAt(i) == '-' && Arrays.asList(operators).contains(in.charAt(i - 1))) || (in.charAt(i) == '-' && Arrays.asList(openParen).contains(in.charAt(i - 1))) ) {
                        toAdd += "-";
                    } else {
                        if (!toAdd.equals("")) {
                            inputs.add(new Oper(Double.parseDouble(toAdd), nested));
                            toAdd = "";
                        }
                        try {

                            inputs.add(new Oper(String.valueOf(in.charAt(i)), nested)); // Operator

                        } catch (IndexOutOfBoundsException e) {
                            inputs.add(new Oper(String.valueOf(in.charAt(i)), nested)); // Operator
                        }
                    }
                } else if (Arrays.asList(openParen).contains(in.charAt(i))) { // If Char @ i is an Open Parenthesis
                    nested++;
                    if (!toAdd.equals("")) {
                        inputs.add(new Oper(Double.parseDouble(toAdd), nested));
                        toAdd = "";
                    }
                    try {
                        if (!Arrays.asList(operators).contains(in.charAt(i - 1)) && !Arrays.asList(openParen).contains(in.charAt(i - 1))) {
                            inputs.add(new Oper("*", nested));
                            inputs.add(new Oper("(", nested));
                        } else {
                            inputs.add(new Oper("(", nested));
                        }
                    } catch (IndexOutOfBoundsException e) {
                        inputs.add(new Oper("(", nested));
                    }
                } else if (Arrays.asList(closeParen).contains(in.charAt(i))) // If Char @ i is a Closed Parenthesis
                {
                    if (!toAdd.equals("")) {
                        inputs.add(new Oper(Double.parseDouble(toAdd), nested));
                        toAdd = "";
                    }
                    inputs.add(new Oper(")", nested));
                    nested--;
                } else if (in.charAt(i) != 'a') { // If Char @ i is a number
                    toAdd += in.charAt(i);
                }
            }
            if (!toAdd.equals("")) {
                inputs.add(new Oper(Double.parseDouble(toAdd), nested));
            }
            Runner.statusNormal();
            return inputs;
        } catch (Exception e) {
            Runner.statusError();
            Runner.debugText("Could not tokenize input.");
            Runner.debugText(e.toString());
            return null;
        }
    }

    // -------------
    // Contains Oper
    // -------------
    private boolean contains(ArrayList<Oper> ins, String check) {
        boolean contain = false;
        for (Oper oper : ins) {
            if (oper.operator() != null) {
                if (oper.operator().equals(check)) {
                    contain = true;
                }
            }
        }
        return contain;
    }

}
