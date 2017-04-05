package Compiler.Statement;

import Compiler.Scope.Scope;
import Compiler.Expression.Expression;
import Compiler.Type.BoolType;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class IfStatement extends Statement implements Scope{
    public Expression condition;
    public Statement trueStatement, falseStatement;

    public IfStatement(Expression condition, Statement trueStatement, Statement falseStatement) {
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }
    public static Statement getStatement(Expression condition, Statement trueStatement, Statement falseStatement) {
        if (!(condition.type instanceof BoolType)) {
            throw new Error();
        }
        return new IfStatement(condition, trueStatement, falseStatement);
    }

    @Override
    public String toString() {
        return "IfStatement";
    }
}
