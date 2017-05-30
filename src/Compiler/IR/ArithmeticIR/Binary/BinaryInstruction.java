package Compiler.IR.ArithmeticIR.Binary;

import Compiler.IR.ArithmeticIR.ArithmeticInstruction;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by SteinerT on 2017/5/15.
 */
public abstract class BinaryInstruction extends ArithmeticInstruction {
    public VRegister target;
    public Operand source1, source2;

    public BinaryInstruction(VRegister target, Operand source1, Operand source2) {
        this.target = target;
        this.source1 = source1;
        this.source2 = source2;
    }


    @Override
    public List<Operand> getDefinedOperands() {
        return Collections.singletonList(target);
    }

    @Override
    public List<Operand> getUsedOperands() {
        return Arrays.asList(source1, source2);
    }

    @Override
    public void setDefinedRegister(VRegister from, VRegister to) {
        if (target == from) {
            target = to;
        }
    }

    @Override
    public void setUsedRegister(VRegister from, Operand to) {
        if (source1 == from) {
            source1 = to;
        }
        if (source2 == from) {
            source2 = to;
        }
    }
}
