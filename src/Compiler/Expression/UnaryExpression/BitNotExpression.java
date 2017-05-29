package Compiler.Expression.UnaryExpression;
import Compiler.IR.ArithmeticIR.Unary.BitNotInstruction;
import Compiler.IR.Instruction;
import Compiler.Type.Type;
import Compiler.Type.IntType;
import Compiler.Table.Table;
import Compiler.Expression.Expression;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class BitNotExpression extends UnaryExpression {
    public BitNotExpression(Type type, boolean leftValue, Expression expression) {
        super(type, leftValue, expression);
    }
    public static Expression getExpression(Expression expression) {
        if (expression.type instanceof IntType) {
            return new BitNotExpression(Table.myInt, false, expression);
        }
        throw new Error();
    }
    public void emit(List<Instruction> instructions) {
        expression.emit(instructions);
        expression.load(instructions);
        operand = Table.registerTable.addTemp();
        instructions.add(BitNotInstruction.getInstruction(operand, expression.operand));
    }
}
