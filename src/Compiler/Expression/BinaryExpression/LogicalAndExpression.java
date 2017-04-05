package Compiler.Expression.BinaryExpression;


import Compiler.Expression.Expression;
import Compiler.Type.Type;
import Compiler.Type.BoolType;
import Compiler.Table.Table;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class LogicalAndExpression extends BinaryExpression {
    public LogicalAndExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }
    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (lhs.type instanceof BoolType && rhs.type instanceof BoolType) {
            return new ModExpression(Table.myBool, false, lhs, rhs);
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "LogicalAndExpression";
    }
}
