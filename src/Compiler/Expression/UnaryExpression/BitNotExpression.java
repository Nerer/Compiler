package Compiler.Expression.UnaryExpression;
import Compiler.Type.Type;
import Compiler.Type.IntType;
import Compiler.Table.Table;
import Compiler.Expression.Expression;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class BitNotExpression extends UnaryExpression {
    public BitNotExpression(Type type, boolean leftValue, Expression expression) {
        super(type, leftValue, expression);
    }
    public static Expression getExpression(Expression expression) {
        if (expression.type instanceof IntType) {
            return new BitNotExpression(Table.myInt, false, expression);
        }
        throw new Error();
    }
}
