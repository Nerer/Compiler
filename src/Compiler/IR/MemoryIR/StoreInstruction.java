package Compiler.IR.MemoryIR;

import Compiler.IR.Address;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.IR.VRegister;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by SteinerT on 2017/5/23.
 */
public class StoreInstruction extends MemoryInstruction {
    public Operand source;
    public Address address;

    public StoreInstruction(Operand source, Address address) {
        this.source = source;
        this.address = address;
    }

    public static Instruction getInstruction(Operand source, Operand address) {
        if (address instanceof Address) {
            return new StoreInstruction(source, (Address)address);
        }
        throw new InternalError();
    }

    @Override
    public String toString() {
        return "store " + source + " to" + " [" + address +"]";
    }

    @Override
    public List<Operand> getDefinedOperands() {
        return Collections.emptyList();
    }

    @Override
    public List<Operand> getUsedOperands() {
        return Arrays.asList(source, address.base);
    }

    @Override
    public void setDefinedRegister(VRegister from, VRegister to) {
    }

    @Override
    public void setUsedRegister(VRegister from, Operand to) {
        if (source == from) {
            source = to;
        }
        if (address.base == from) {
            address = new Address((VRegister) to, address.offset);
        }
    }

}
