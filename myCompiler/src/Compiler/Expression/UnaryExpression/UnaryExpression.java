package Compiler.Expression.UnaryExpression;

import Compiler.Expression.Expression;
import Compiler.Type.Type;
/**
 * Created by SteinerT on 2017/4/4.
 */
public abstract class UnaryExpression extends Expression{
    public Expression expression;
    public UnaryExpression() {}
    public UnaryExpression(Type type, boolean leftValue, Expression expression) {
        super(type, leftValue);
        this.expression = expression;
    }

}
