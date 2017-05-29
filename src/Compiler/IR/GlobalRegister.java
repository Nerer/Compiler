package Compiler.IR;

import Compiler.Symbol.Symbol;

/**
 * Created by SteinerT on 2017/5/18.
 */
public class GlobalRegister extends VarRegister {
    public GlobalRegister(Symbol symbol) {
        super(symbol);
    }

    @Override
    public String toString() {
        return String.format("$g%s", id);
    }
}
