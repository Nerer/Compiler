package Compiler.Expression;

import Compiler.Table.Table;
import Compiler.Type.*;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class MemberExpression extends Expression {
    public Expression expression;
    public String memberName;
    public MemberExpression(Type type, boolean leftValue, Expression expression, String memberName) {
        super(type, leftValue);
        this.expression = expression;
        this.memberName = memberName;
    }
    public static Expression getExpression(Expression expression, String memberName) {
        if (expression.type instanceof ClassType) {
            Member member = ((ClassType)expression.type).getMember(memberName);
            if (member instanceof MemberFunction) {
                return new MemberExpression(((MemberFunction)member).function, false, expression, memberName);
            } else {
                return new MemberExpression(((MemberVar)member).type, expression.leftValue, expression, memberName);
            }
        } else {
            if (expression.type instanceof ArrayType) {
                if (memberName.equals("size")) {
                    return new MemberExpression(Table.symbolTable.getSymbol("Mx_builtin_arr_size").type, false, expression, memberName);
                }
            } else {
                if (expression.type instanceof StringType) {
                    if (memberName.equals("length")) {
                        return new MemberExpression(Table.symbolTable.getSymbol("Mx_builtin_str_length").type, false, expression, memberName);
                    }
                    if (memberName.equals("parseInt")) {
                        return new MemberExpression(Table.symbolTable.getSymbol("Mx_builtin_str_parseInt").type, false, expression, memberName);
                    }
                    if (memberName.equals("substring")) {
                        return new MemberExpression(Table.symbolTable.getSymbol("Mx_builtin_str_substring").type, false, expression, memberName);
                    }
                    if (memberName.equals("ord")) {
                        return new MemberExpression(Table.symbolTable.getSymbol("Mx_builtin_str_ord").type, false, expression, memberName);
                    }
                }
            }
            throw new Error();
        }
    }
}
