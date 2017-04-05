package Compiler.Statement;

import Compiler.Expression.Expression;
import Compiler.Type.*;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class ForStatement extends LoopStatement {
    public Expression init, condition, operation;
    public Statement statement;

    public static Statement getStatement() {
        return new ForStatement();
    }

    public void addInit(Expression expression) {
        this.init = expression;
    }

    public void addCondition(Expression expression) {
        if (expression.type instanceof BoolType) {
            this.condition = expression;
        }
    }
    public void addOperation(Expression expression) {
        this.operation = expression;
    }

    public void addStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "ForStatement";
    }
}
