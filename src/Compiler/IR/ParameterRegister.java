package Compiler.IR;

import Compiler.Symbol.Symbol;

/**
 * Created by SteinerT on 2017/5/18.
 */
public class ParameterRegister extends VarRegister {
    public ParameterRegister(Symbol symbol) {
        super(symbol);
    }

    @Override
    public String toString() {
        return String.format("$p%s", id);
    }
}
