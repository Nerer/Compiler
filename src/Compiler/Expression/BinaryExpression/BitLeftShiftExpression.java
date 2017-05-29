package Compiler.Expression.BinaryExpression;

import Compiler.Expression.Expression;
import Compiler.IR.ArithmeticIR.Binary.AddInstruction;
import Compiler.IR.ArithmeticIR.Binary.BitLeftShiftInstruction;
import Compiler.IR.Instruction;
import Compiler.Type.Type;
import Compiler.Type.IntType;
import Compiler.Table.Table;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class BitLeftShiftExpression extends BinaryExpression {
    public BitLeftShiftExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }
    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (lhs.type instanceof IntType && rhs.type instanceof IntType) {
            return new BitLeftShiftExpression(Table.myInt, false, lhs, rhs);
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "BitShiftExpression";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        lhs.emit(instructions);
        lhs.load(instructions);
        rhs.emit(instructions);
        rhs.load(instructions);
        operand = Table.registerTable.addTemp();
        instructions.add(BitLeftShiftInstruction.getInstruction(operand, lhs.operand, rhs.operand));
    }
}
