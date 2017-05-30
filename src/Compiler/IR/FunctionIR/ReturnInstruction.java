package Compiler.IR.FunctionIR;

import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

import java.util.Collections;
import java.util.List;

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

    @Override
    public List<Operand> getDefinedOperands() {
        return Collections.emptyList();
    }

    @Override
    public List<Operand> getUsedOperands() {
        return Collections.singletonList(source);
    }

    @Override
    public void setDefinedRegister(VRegister from, VRegister to) {
    }

    @Override
    public void setUsedRegister(VRegister from, Operand to) {
        if (source == from) {
            source = to;
        }
    }
}
