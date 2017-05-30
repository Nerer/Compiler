package Compiler.IR.ControlFlowIR;

import Compiler.IR.Block;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

import java.util.Collections;
import java.util.List;

/**
 * Created by SteinerT on 2017/5/20.
 */
public class LabelInstruction extends Instruction {
    public String name;
    public Block block;

    public LabelInstruction(String str) {
        this.name = str;
    }
    public static LabelInstruction getInstruction(String str) {
        return new LabelInstruction(str);
    }

    @Override
    public String toString() {
        if (name != null) {
            return "%" + name;
        }
        throw new InternalError();
    }

    @Override
    public List<Operand> getDefinedOperands() {
        return Collections.emptyList();
    }

    @Override
    public List<Operand> getUsedOperands() {
        return Collections.emptyList();
    }

    @Override
    public void setDefinedRegister(VRegister from, VRegister to) {
    }

    @Override
    public void setUsedRegister(VRegister from, Operand to) {
    }

}
