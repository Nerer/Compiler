package Compiler.Expression.UnaryExpression;

import Compiler.Type.Type;
import Compiler.Expression.Expression;
import Compiler.Type.IntType;
import Compiler.Table.Table;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class PreAddExpression extends UnaryExpression {
    public PreAddExpression(Type type, boolean leftValue, Expression expression) {
        super(type, leftValue, expression);
    }

    public static Expression getExpression(Expression expression) {
        if (expression.type instanceof IntType && expression.leftValue) {
            return new PreAddExpression(Table.myInt, false ,expression);
        }
        throw new Error();
    }

}
