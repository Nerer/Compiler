package Compiler.IR;

import Compiler.Table.Table;

/**
 * Created by SteinerT on 2017/5/15.
 */
public class VRegister extends Operand{
    int id;
    public VRegister() {
        this.id = Table.registerTable.registers.size();
    }
    public VRegister(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("$%d", id);
    }
}
