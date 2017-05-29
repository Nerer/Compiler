package Compiler.Statement;

import Compiler.Expression.Expression;
import Compiler.IR.Instruction;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class ExpressionStatement extends Statement {
    public Expression expression;
    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    public static Statement getStatement(Expression expression) {
        return new ExpressionStatement(expression);
    }

    @Override
    public String toString() {
        return "ExpressionStatement";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        if (expression != null) {
            expression.emit(instructions);
        }
    }
}
