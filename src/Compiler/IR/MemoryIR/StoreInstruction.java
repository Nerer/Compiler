package Compiler.IR.MemoryIR;

import Compiler.IR.Address;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

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
        return "store";
    }
}
