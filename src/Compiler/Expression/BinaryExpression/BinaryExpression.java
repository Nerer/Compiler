package Compiler.Expression.BinaryExpression;

import Compiler.Expression.Expression;
import Compiler.IR.Instruction;
import Compiler.Type.Type;

import java.util.List;

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
