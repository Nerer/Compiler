package Compiler.IR.ArithmeticIR.Unary;

import Compiler.IR.ArithmeticIR.ArithmeticInstruction;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

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

}
