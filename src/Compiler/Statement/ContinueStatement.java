package Compiler.Statement;

import Compiler.Table.Table;
/**
 * Created by SteinerT on 2017/4/5.
 */
public class ContinueStatement extends Statement {
    public LoopStatement belong;
    public ContinueStatement(LoopStatement belong) {
        this.belong = belong;
    }

    public static Statement getStatement() {
        if (Table.scopeTable.getLoopScope() == null) {
            throw new Error();
        }
        return new ContinueStatement(Table.scopeTable.getLoopScope());
    }

    @Override
    public String toString() {
        return "ContinueStatement";
    }
}
