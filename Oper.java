public class Oper {

    private String operator;
    private Double operand;
    private int nested;

    public Oper(String operator, int nested)
    {
        this.operator = operator;
        operand = null;
        this.nested = nested;
    }
    public Oper(Double operand, int nested)
    {
        this.operand = operand;
        operator = null;
        this.nested = nested;
    }

    // ---------------
    // Getter & Setter
    // ---------------
    public String operator()
    {
        return operator;
    }
    public void setOperator(String operator)
    {
        this.operator = operator;
    }
    public Double operand()
    {
        return operand;
    }
    public void setOperand(Double operand)
    {
        this.operand = operand;
    }
    public int nested()
    {
        return nested;
    }
    public void setNested(int nested)
    {
        this.nested = nested;
    }

    // ---------
    // To String
    // ---------
    @Override
    public String toString()
    {
        String str = operator == null ? "Operand: " + operand : "Operator: " + operator;
        str += " Nested Level: " + nested;
        return str;
    }
    
}
