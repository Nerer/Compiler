package Compiler.IR.MemoryIR;

import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

import java.util.Collections;
import java.util.List;

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

    @Override
    public List<Operand> getDefinedOperands() {
        return Collections.singletonList(target);
    }

    @Override
    public List<Operand> getUsedOperands() {
        return Collections.singletonList(source);
    }

    @Override
    public void setDefinedRegister(VRegister from, VRegister to) {
        if (target == from) {
            target = to;
        }
    }

    @Override
    public void setUsedRegister(VRegister from, Operand to) {
        if (source == from) {
            source = to;
        }
    }
}
