package Compiler.Expression.UnaryExpression;
import Compiler.IR.ArithmeticIR.Binary.BitXorInstruction;
import Compiler.IR.ArithmeticIR.Unary.BitNotInstruction;
import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.Type.Type;
import Compiler.Type.BoolType;
import Compiler.Table.Table;
import Compiler.Expression.Expression;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class LogicalNotExpression extends UnaryExpression {
    public LogicalNotExpression(Type type, boolean leftValue, Expression expression) {
        super(type, leftValue, expression);
    }
    public static Expression getExpression(Expression expression) {
        if (expression.type instanceof BoolType) {
            return new LogicalNotExpression(Table.myBool, false, expression);
        }
        throw new Error();
    }

    @Override
    public void emit(List<Instruction> instructions) {
        expression.emit(instructions);
        expression.load(instructions);
        operand = Table.registerTable.addTemp();
        instructions.add(BitXorInstruction.getInstruction(operand, expression.operand, new Immediate(1)));
    }
}
