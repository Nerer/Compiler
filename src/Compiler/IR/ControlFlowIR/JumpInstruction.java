package Compiler.IR.ControlFlowIR;

import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

import java.util.Collections;
import java.util.List;

/**
 * Created by SteinerT on 2017/5/20.
 */
public class JumpInstruction extends ControlFlowInstruction {
    public LabelInstruction to;

    public JumpInstruction(LabelInstruction to) {
        this.to = to;
    }

    public static Instruction getInstruction(LabelInstruction toLabel) {
        return new JumpInstruction(toLabel);
    }

    @Override
    public String toString() {
        return "jump " + to;
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
