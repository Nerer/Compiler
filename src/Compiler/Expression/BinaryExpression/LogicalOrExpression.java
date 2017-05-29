package Compiler.Expression.BinaryExpression;


import Compiler.Expression.Expression;
import Compiler.IR.ArithmeticIR.Binary.BitLeftShiftInstruction;
import Compiler.IR.ArithmeticIR.Binary.LogicalOrInstruction;
import Compiler.IR.Instruction;
import Compiler.Type.Type;
import Compiler.Type.BoolType;
import Compiler.Table.Table;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class LogicalOrExpression extends BinaryExpression {
    public LogicalOrExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }
    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (lhs.type instanceof BoolType && rhs.type instanceof BoolType) {
            return new LogicalOrExpression(Table.myBool, false, lhs, rhs);
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "LogicalOrExpression";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        lhs.emit(instructions);
        lhs.load(instructions);
        rhs.emit(instructions);
        rhs.load(instructions);
        operand = Table.registerTable.addTemp();
        instructions.add(LogicalOrInstruction.getInstruction(operand, lhs.operand, rhs.operand));
    }
}
