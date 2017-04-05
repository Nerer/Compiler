package Compiler.Expression.BinaryExpression;

import Compiler.Expression.Expression;
import Compiler.Table.Table;
import Compiler.Type.Type;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class NotEqualExpression extends BinaryExpression{
    public NotEqualExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }
    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (!(lhs.type.equals(rhs.type))) {
            throw new Error();
        }
        return new NotEqualExpression(Table.myBool, false, lhs, rhs);
    }

    @Override
    public String toString() {
        return "NotEqualExpression";
    }
}
