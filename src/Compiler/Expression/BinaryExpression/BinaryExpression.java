package Compiler.Expression.BinaryExpression;

import Compiler.Expression.Expression;
import Compiler.Type.Type;

/**
 * Created by SteinerT on 2017/4/4.
 */
public abstract class BinaryExpression extends Expression {
    public Expression lhs, rhs;
    public BinaryExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue);
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
