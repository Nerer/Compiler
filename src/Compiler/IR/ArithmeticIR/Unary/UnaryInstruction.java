package Compiler.IR.ArithmeticIR.Unary;

import Compiler.IR.ArithmeticIR.ArithmeticInstruction;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

import java.util.Collections;
import java.util.List;

/**
 * Created by SteinerT on 2017/5/20.
 */
public abstract class UnaryInstruction extends ArithmeticInstruction {
    public VRegister target;
    public Operand source;

    public UnaryInstruction(VRegister target, Operand source) {
        this.target = target;
        this.source = source;
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
