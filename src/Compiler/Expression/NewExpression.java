package Compiler.Expression;

import Compiler.Type.*;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class NewExpression extends Expression{
    public List<Expression> expressions;

    public NewExpression(Type type, boolean leftValue, List<Expression> expressions) {
        super(type, leftValue);
        this.expressions = expressions;
    }

    public static Expression getExpression(Type type, List<Expression> expressions) {
        if(expressions.isEmpty()) {
            if (type instanceof ClassType) {
                return new NewExpression(type, false, expressions);
            }
            throw new Error();
        } else {
            Type arrayType = ArrayType.getType(type, expressions.size());
            return new NewExpression(arrayType, false, expressions);
        }
    }
}
