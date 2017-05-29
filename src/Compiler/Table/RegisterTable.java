package Compiler.Table;

import Compiler.IR.*;
import Compiler.Symbol.Symbol;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SteinerT on 2017/5/18.
 */
public class RegisterTable {
    public Set<VRegister> registers;
    public int stringCount;
    public RegisterTable() {
        stringCount = 0;
        registers = new HashSet<>();
    }

    public VRegister addGlobal(Symbol symbol) {
        VRegister register = new GlobalRegister(symbol);
        registers.add(register);
        return register;
    }

    public VRegister addTemp() {
        return addTemp(null);
    }
    public VRegister addTemp(Symbol symbol) {
        VRegister register = new TempRegister(symbol);
        registers.add(register);
        return register;
    }
    public VRegister addParameter(Symbol symbol) {
        VRegister register = new ParameterRegister(symbol);
        registers.add(register);
        return register;
    }
    public VRegister addString(String str) {
        VRegister register = new StringRegister(str, stringCount);
        stringCount++;
        registers.add(register);
        return register;
    }
}
