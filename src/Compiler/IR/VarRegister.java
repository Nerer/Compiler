package Compiler.IR;

import Compiler.Symbol.Symbol;

/**
 * Created by SteinerT on 2017/5/18.
 */
public class VarRegister extends VRegister {
    public Symbol symbol;
    public VarRegister(Symbol symbol) {
        this.symbol = symbol;
    }

}
