package Compiler.IR.MemoryIR;

import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

/**
 * Created by SteinerT on 2017/5/15.
 */
public class MoveInstruction extends MemoryInstruction{
    public VRegister target;
    public Operand source;

    public MoveInstruction(VRegister target, Operand source) {
        this.target = target;
        this.source = source;
    }

    public static Instruction getInstruction(Operand target, Operand source) {
        if (target instanceof VRegister) {
            return new MoveInstruction((VRegister)target, source);
        }
        throw new InternalError();
    }

    @Override
    public String toString() {
        return String.format("%s = move %s", target, source);
    }
}
