package Compiler.Statement;

import Compiler.Table.Table;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class BreakStatement extends Statement {
    public LoopStatement belong;
    public BreakStatement(LoopStatement belong) {
        this.belong = belong;
    }

    public static Statement getStatement() {
        if (Table.scopeTable.getLoopScope() == null) {
            throw new Error();
        }
        return new BreakStatement(Table.scopeTable.getLoopScope());
    }

    @Override
    public String toString() {
        return "BreakStatement";
    }
}
