package Compiler.Statement;

import Compiler.Expression.Expression;
import Compiler.Type.*;
/**
 * Created by SteinerT on 2017/4/5.
 */

public class WhileStatement extends LoopStatement {
    public Expression condition;
    public Statement statement;

    public static Statement getStatement() {
        return new WhileStatement();
    }

    public void addCondition(Expression expression) {
        if (expression.type instanceof BoolType) {
            this.condition = expression;
        } else {
            throw new Error();
        }
    }
    public void addStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "WhileStatement";
    }
}
