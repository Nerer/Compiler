package Compiler.Expression.BinaryExpression;


import Compiler.Expression.Expression;
import Compiler.Type.Type;
import Compiler.Type.IntType;
import Compiler.Table.Table;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class DivideExpression extends BinaryExpression{
    public DivideExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }

    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (lhs.type instanceof IntType && rhs.type instanceof IntType) {
            return new DivideExpression(Table.myInt, false, lhs, rhs);
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "DivideExpression";
    }
}
