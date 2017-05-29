package Compiler.IR.ArithmeticIR.Unary;

import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.IR.MemoryIR.MoveInstruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

/**
 * Created by SteinerT on 2017/5/22.
 */
public class BitNotInstruction extends UnaryInstruction {
    public BitNotInstruction(VRegister target, Operand source) {
        super(target, source);
    }
    public static Instruction getInstruction(Operand target, Operand source) {
        return new BitNotInstruction((VRegister)target, source).update();
    }

    public Instruction update() {
        if (source instanceof Immediate) {
            int value = ((Immediate)source).value;
            return MoveInstruction.getInstruction(target, new Immediate(~value));
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s = not %s", target, source);
    }
}
