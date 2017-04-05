package Compiler.Symbol;

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

    public Symbol(Type type, String name) {
        this.type = type;
        this.name = name;
        this.scope = Table.scopeTable.getCurrentScope();
    }
}
