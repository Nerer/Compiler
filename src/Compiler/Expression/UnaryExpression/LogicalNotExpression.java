package Compiler.Expression.UnaryExpression;
import Compiler.Type.Type;
import Compiler.Type.BoolType;
import Compiler.Table.Table;
import Compiler.Expression.Expression;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class LogicalNotExpression extends UnaryExpression {
    public LogicalNotExpression(Type type, boolean leftValue, Expression expression) {
        super(type, leftValue, expression);
    }
    public static Expression getExpression(Expression expression) {
        if (expression.type instanceof BoolType) {
            return new LogicalNotExpression(Table.myBool, false, expression);
        }
        throw new Error();
    }

}
