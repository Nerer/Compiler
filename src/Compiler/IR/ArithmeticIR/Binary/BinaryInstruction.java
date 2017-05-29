package Compiler.IR.ArithmeticIR.Binary;

import Compiler.IR.ArithmeticIR.ArithmeticInstruction;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

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

}
