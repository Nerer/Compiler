package Compiler.Expression.UnaryExpression;

import Compiler.Type.Type;
import Compiler.Expression.Expression;
import Compiler.Type.IntType;
import Compiler.Table.Table;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class SelfAddExpression extends UnaryExpression {
    public SelfAddExpression(Type type, boolean leftValue, Expression expression) {
        super(type, leftValue, expression);
    }
    public static Expression getExpression(Expression expression) {
        if (expression.type instanceof IntType) {
            return new SelfAddExpression(Table.myInt, false, expression);
        }
        throw new Error();
    }
}
