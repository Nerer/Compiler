package Compiler.IR.ArithmeticIR.Binary;

/**
 * Created by SteinerT on 2017/5/29.
 */
public class CompareInstruction {
    public String operator, code;

    public CompareInstruction(String operator) {
        this.operator = operator;
        this.code = "set" + operator;
    }

    public static CompareInstruction getInstruction(String operator) {
        return new CompareInstruction(operator);
    }
}
