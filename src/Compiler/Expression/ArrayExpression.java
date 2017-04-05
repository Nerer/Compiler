package Compiler.Expression;

import Compiler.Type.*;
/**
 * Created by SteinerT on 2017/4/5.
 */
public class ArrayExpression extends Expression {
    public Expression expression;
    public Expression index;
    public ArrayExpression(Type type, boolean leftValue, Expression expression, Expression index) {
        super(type, leftValue);
        this.expression = expression;
        this.index = index;
    }

    public static Expression getExpression(Expression expression, Expression index) {
        if (!(expression.type instanceof ArrayType)) {
            throw new Error();
        }
        if (!(index.type instanceof IntType)) {
            throw new Error();
        }
        ArrayType arrayType = (ArrayType)expression.type;
        return new ArrayExpression(arrayType.getReduced(), expression.leftValue, expression, index);
    }
}
