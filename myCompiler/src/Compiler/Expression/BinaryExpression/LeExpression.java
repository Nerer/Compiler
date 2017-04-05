package Compiler.Expression.BinaryExpression;

import Compiler.Expression.Expression;
import Compiler.Type.IntType;
import Compiler.Table.Table;
import Compiler.Type.StringType;
import Compiler.Type.Type;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class LeExpression extends BinaryExpression {

    public LeExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }

    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (lhs.type instanceof IntType && rhs.type instanceof IntType) {
            return new LessExpression(Table.myBool,false, lhs, rhs);
        }
        if (lhs.type instanceof StringType && rhs.type instanceof StringType) {
            return new LessExpression(Table.myBool, false, lhs, rhs);
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "LeExpression";
    }
}