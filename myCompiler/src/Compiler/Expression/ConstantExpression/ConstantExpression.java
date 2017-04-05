package Compiler.Expression.ConstantExpression;

import Compiler.Expression.Expression;
import Compiler.Type.Type;
/**
 * Created by SteinerT on 2017/4/4.
 */
public abstract class ConstantExpression extends Expression{
    public ConstantExpression() {}
    public ConstantExpression(Type type) {
        super(type, false);
    }

}
