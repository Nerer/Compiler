package Compiler.Symbol;

import Compiler.IR.VRegister;
import Compiler.Scope.Scope;
import Compiler.Type.Type;
import Compiler.Table.Table;
/**
 * Created by SteinerT on 2017/4/3.
 */
public class Symbol {
    public Type type;
    public String name;
    public Scope scope;
    public VRegister register;

    public Symbol(Type type, String name) {
        this.type = type;
        this.name = name;
        this.scope = Table.scopeTable.getCurrentScope();
        this.register = null;
    }
}
