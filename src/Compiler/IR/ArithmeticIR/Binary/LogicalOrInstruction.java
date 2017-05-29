package Compiler.IR.ArithmeticIR.Binary;

import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.IR.MemoryIR.MoveInstruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

/**
 * Created by SteinerT on 2017/5/15.
 */
public class LogicalOrInstruction extends BinaryInstruction {
    public LogicalOrInstruction(VRegister target, Operand source1, Operand suorce2) {
        super(target, source1, suorce2);
    }
    public static Instruction getInstruction(Operand target, Operand source1, Operand source2) {
        if (target instanceof VRegister) {
            return new LogicalOrInstruction((VRegister)target, source1, source2).update();
        }
        throw new Error();
    }

    public Instruction update() {
        if (source1 instanceof Immediate && source2 instanceof Immediate) {
            Boolean val1 = ((Immediate)source1).value == 1 ? true : false;
            Boolean val2 = ((Immediate)source2).value == 1 ? true : false;
            return MoveInstruction.getInstruction(target, new Immediate((val1 || val2) ? 1 : 0));
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s = lor %s %s", target, source1, source2);
    }
}
