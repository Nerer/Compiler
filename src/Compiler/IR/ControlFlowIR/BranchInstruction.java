package Compiler.IR.ControlFlowIR;

import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

import java.util.Collections;
import java.util.List;

/**
 * Created by SteinerT on 2017/5/20.
 */
public class BranchInstruction extends ControlFlowInstruction{
    public Operand condition;
    public LabelInstruction trueTo, falseTo;

    public BranchInstruction(Operand condition, LabelInstruction trueTo, LabelInstruction falseTo) {
        this.condition = condition;
        this.trueTo = trueTo;
        this.falseTo = falseTo;
    }

    public static Instruction getInstruction(Operand condition, LabelInstruction trueTo, LabelInstruction falseTo) {
        return new BranchInstruction(condition, trueTo, falseTo).update();
    }

    public Instruction update() {
        if (condition instanceof Immediate) {
            int value = ((Immediate)condition).value;
            if (value == 0) {
                return JumpInstruction.getInstruction(falseTo);
            } else {
                return JumpInstruction.getInstruction(trueTo);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "br " + condition + " " + trueTo.name + " " + falseTo.name;
    }

    @Override
    public List<Operand> getDefinedOperands() {
        return Collections.emptyList();
    }

    @Override
    public List<Operand> getUsedOperands() {
        return Collections.singletonList(condition);
    }

    @Override
    public void setDefinedRegister(VRegister from, VRegister to) {
    }

    @Override
    public void setUsedRegister(VRegister from, Operand to) {
        if (condition == from) {
            condition = to;
        }
    }

}
