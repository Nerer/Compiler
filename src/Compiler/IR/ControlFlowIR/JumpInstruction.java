package Compiler.IR.ControlFlowIR;

import Compiler.IR.Instruction;

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
}
