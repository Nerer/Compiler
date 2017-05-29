package Compiler.Expression.BinaryExpression;


import Compiler.Expression.Expression;
import Compiler.IR.ArithmeticIR.Binary.BitLeftShiftInstruction;
import Compiler.IR.ArithmeticIR.Binary.LogicalAndInstruction;
import Compiler.IR.Instruction;
import Compiler.Type.Type;
import Compiler.Type.BoolType;
import Compiler.Table.Table;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class LogicalAndExpression extends BinaryExpression {
    public LogicalAndExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }
    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (lhs.type instanceof BoolType && rhs.type instanceof BoolType) {
            return new ModExpression(Table.myBool, false, lhs, rhs);
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "LogicalAndExpression";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        lhs.emit(instructions);
        lhs.load(instructions);
        rhs.emit(instructions);
        rhs.load(instructions);
        operand = Table.registerTable.addTemp();
        instructions.add(LogicalAndInstruction.getInstruction(operand, lhs.operand, rhs.operand));
    }
}
