package Compiler.IR.MemoryIR;

import Compiler.IR.Address;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;

import java.util.Collections;
import java.util.List;

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

    @Override
    public List<Operand> getDefinedOperands() {
        return Collections.singletonList(target);
    }

    @Override
    public List<Operand> getUsedOperands() {
        return Collections.singletonList(address.base);
    }

    @Override
    public void setDefinedRegister(VRegister from, VRegister to) {
        if (target == from) {
            target = to;
        }
    }

    @Override
    public void setUsedRegister(VRegister from, Operand to) {
        if (address.base == from) {
            address = new Address((VRegister) to, address.offset);
        }
    }

}
