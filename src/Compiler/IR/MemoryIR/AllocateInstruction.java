package Compiler.IR.MemoryIR;

import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

/**
 * Created by SteinerT on 2017/5/22.
 */
public class AllocateInstruction extends MemoryInstruction {
    public VRegister target;
    public Operand size;

    public AllocateInstruction(VRegister target, Operand size) {
        this.target = target;
        this.size = size;
    }

    public static Instruction getInstruction(Operand target, Operand size) {
        if (target instanceof VRegister) {
            return new AllocateInstruction((VRegister)target, size);
        }
        throw new InternalError();
    }

    @Override
    public String toString() {
        return String.format("%s = alloc %s", target, size);
    }
}
