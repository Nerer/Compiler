package Compiler.IR;

/**
 * Created by SteinerT on 2017/5/15.
 */
public class Immediate extends Operand {
    public int value;
    public Immediate(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
