package Compiler.IR;

import Compiler.Symbol.Symbol;

/**
 * Created by SteinerT on 2017/5/18.
 */
public class StringRegister extends VRegister {
    public String str;
    public int number;

    public StringRegister(String str) {
        this.str = str;
    }
    public StringRegister(String str, int number) {
        this.str = str;
        this.number = number;
    }

    public String message() {
        return String.format("Mx__mxg#%d", number);
    }



}
