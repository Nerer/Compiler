package Compiler.IR.FunctionIR;

import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;
import Compiler.Type.FunctionType;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

import java.util.List;


/**
 * Created by SteinerT on 2017/5/23.
 */
public class CallInstruction extends FunctionInstruction {
    public VRegister target;
    public FunctionType function;
    public List<Operand> parameters;

    public CallInstruction(VRegister target, FunctionType function, List<Operand> parameters) {
        this.target = target;
        this.function = function;
        this.parameters = parameters;
    }

    public static Instruction getInstruction(Operand target, FunctionType function, List<Operand> parameters) {
        if (target == null) {
            return new CallInstruction(null, function, parameters);
        } else if (target instanceof VRegister) {
            return new CallInstruction((VRegister)target, function, parameters);
        }
        throw new InternalError();
    }

    @Override
    public String toString() {
        return target +" = call " + function.name;
    }
}
