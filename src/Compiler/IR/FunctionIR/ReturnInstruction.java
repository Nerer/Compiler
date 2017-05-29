package Compiler.IR.FunctionIR;

import Compiler.IR.Instruction;
import Compiler.IR.Operand;

/**
 * Created by SteinerT on 2017/5/23.
 */
public class ReturnInstruction extends FunctionInstruction{
    public Operand source;

    public ReturnInstruction(Operand source) {
        this.source = source;
    }

    public static Instruction getInstruction(Operand source) {
        return new ReturnInstruction(source);
    }

    @Override
    public String toString() {
        return "ret " + source;
    }
}
