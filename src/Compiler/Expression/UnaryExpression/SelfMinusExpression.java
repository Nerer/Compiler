package Compiler.Expression.UnaryExpression;

import Compiler.Type.Type;
import Compiler.Expression.Expression;
import Compiler.Type.IntType;
import Compiler.Table.Table;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class SelfMinusExpression extends UnaryExpression {
    public SelfMinusExpression(Type type, boolean leftValue, Expression expression) {
        super(type, leftValue, expression);
    }
    public static Expression getExpression(Expression expression) {
        if (expression.type instanceof IntType) {
            return new SelfMinusExpression(Table.myInt, false, expression);
        }
        throw new Error();
    }
}
