package Compiler.Expression.BinaryExpression;

import Compiler.Expression.Expression;
import Compiler.Type.Type;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class AssignmentExpression extends BinaryExpression {
    public AssignmentExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }
    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (!lhs.leftValue) {
            throw new Error();
        }
        if (rhs.type.equals(lhs.type)) {
            return new AssignmentExpression(lhs.type, false, lhs, rhs);
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "AssignmentExpression";
    }
}
