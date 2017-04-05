package Compiler.Statement;

import Compiler.Type.FunctionType;
import Compiler.Table.Table;
import Compiler.Type.VoidType;
import Compiler.Expression.Expression;
/**
 * Created by SteinerT on 2017/4/5.
 */
public class ReturnStatement extends Statement {
    public FunctionType belong;
    public Expression expression;
    public ReturnStatement(FunctionType belong, Expression expression) {
        this.belong = belong;
        this.expression = expression;
    }
    public static Statement getStatement(Expression expression) {
        if (Table.scopeTable.getFunctionScope() != null) {
            FunctionType function = Table.scopeTable.getFunctionScope();
            if (function.returnType instanceof VoidType && expression == null) {
                return new ReturnStatement(function, expression);
            }
            if (expression != null && expression.type.equals(function.returnType)) {
                return new ReturnStatement(function, expression);
            }
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "ReturnStatement";
    }
}
