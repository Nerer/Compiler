package Compiler.IR.MemoryIR;

import Compiler.IR.Address;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

/**
 * Created by SteinerT on 2017/5/22.
 */
public class LoadInstruction extends MemoryInstruction {
    public VRegister target;
    public Address address;

    public LoadInstruction(VRegister target, Address address) {
        this.target = target;
        this.address = address;
    }

    public static Instruction getInstruction(Operand target, Operand address) {
        if (target instanceof VRegister && address instanceof Address) {
            return new LoadInstruction((VRegister)target, (Address)address);
        }
        throw new InternalError();
    }

    @Override
    public String toString() {
        return String.format("%s = load %s %s %s", target, address.size, address.base, address.offset);
    }
}
