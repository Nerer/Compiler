package Compiler.IR;

import Compiler.Symbol.Symbol;

/**
 * Created by SteinerT on 2017/5/18.
 */
public class TempRegister extends VarRegister {
    public TempRegister(Symbol symbol) {
        super(symbol);
    }

    @Override
    public String toString() {
        return String.format("$t%s", id);
    }
}
