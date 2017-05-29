package Compiler.Expression.UnaryExpression;

import Compiler.IR.ArithmeticIR.Unary.SelfMinusInstruction;
import Compiler.IR.Instruction;
import Compiler.Type.Type;
import Compiler.Expression.Expression;
import Compiler.Type.IntType;
import Compiler.Table.Table;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class SelfMinusExpression extends UnaryExpression {
    public SelfMinusExpression(Type type, boolean leftValue, Expression expression) {
        super(type, leftValue, expression);
    }
    public static Expression getExpression(Expression expression) {
        if (expression.type instanceof IntType) {
            return new SelfMinusExpression(Table.myInt, false, expression);
        }
        throw new Error();
    }

    @Override
    public void emit(List<Instruction> instructions) {
        expression.emit(instructions);
        expression.load(instructions);
        operand = Table.registerTable.addTemp();
        instructions.add(SelfMinusInstruction.getInstruction(operand, expression.operand));
    }
}
