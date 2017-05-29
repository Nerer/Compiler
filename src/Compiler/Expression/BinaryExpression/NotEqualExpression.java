package Compiler.Expression.BinaryExpression;

import Compiler.Expression.Expression;
import Compiler.Expression.FunctionCallExpression;
import Compiler.IR.ArithmeticIR.Binary.BitLeftShiftInstruction;
import Compiler.IR.ArithmeticIR.Binary.NotEqualInstruction;
import Compiler.IR.Instruction;
import Compiler.Table.Table;
import Compiler.Type.FunctionType;
import Compiler.Type.StringType;
import Compiler.Type.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class NotEqualExpression extends BinaryExpression{
    public NotEqualExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }
    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (!(lhs.type.equals(rhs.type))) {
            throw new Error();
        }
        if (lhs.type instanceof StringType && rhs.type instanceof StringType) {
            return FunctionCallExpression.getExpression(
                    (FunctionType) Table.symbolTable.getSymbol("Mx_builtin_str_ne").type,
                    new ArrayList<Expression>() {{
                        add(lhs);
                        add(rhs);
                    }}
            );
        }
        return new NotEqualExpression(Table.myBool, false, lhs, rhs);
    }

    @Override
    public String toString() {
        return "NotEqualExpression";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        lhs.emit(instructions);
        lhs.load(instructions);
        rhs.emit(instructions);
        rhs.load(instructions);
        operand = Table.registerTable.addTemp();
        instructions.add(NotEqualInstruction.getInstruction(operand, lhs.operand, rhs.operand));
    }
}
