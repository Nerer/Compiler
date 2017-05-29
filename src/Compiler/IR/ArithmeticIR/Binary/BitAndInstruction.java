package Compiler.IR.ArithmeticIR.Binary;

import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.IR.MemoryIR.MoveInstruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

/**
 * Created by SteinerT on 2017/5/15.
 */
public class BitAndInstruction extends BinaryInstruction{
    public BitAndInstruction(VRegister target, Operand source1, Operand suorce2) {
        super(target, source1, suorce2);
    }

    public static Instruction getInstruction(Operand target, Operand source1, Operand source2) {
        if (target instanceof VRegister) {
            return new BitAndInstruction((VRegister)target, source1, source2).update();
        }
        throw new Error();
    }
    public Instruction update() {
        if (source1 instanceof Immediate && source2 instanceof Immediate) {
            int val1 = ((Immediate)source1).value;
            int val2 = ((Immediate)source2).value;
            return MoveInstruction.getInstruction(target, new Immediate(val1 + val2));
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s = and %s %s", target, source1, source2);
    }
}
