package Compiler.Expression.BinaryExpression;

import Compiler.Expression.Expression;
import Compiler.Expression.FunctionCallExpression;
import Compiler.IR.ArithmeticIR.Binary.BitLeftShiftInstruction;
import Compiler.IR.ArithmeticIR.Binary.LeInstruction;
import Compiler.IR.Instruction;
import Compiler.Type.FunctionType;
import Compiler.Type.IntType;
import Compiler.Table.Table;
import Compiler.Type.StringType;
import Compiler.Type.Type;

import java.util.ArrayList;
import java.util.List;

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
           // return new LessExpression(Table.myBool, false, lhs, rhs);
            return FunctionCallExpression.getExpression(
                    (FunctionType) Table.symbolTable.getSymbol("Mx_builtin_str_le").type,
                    new ArrayList<Expression>() {{
                        add(lhs);
                        add(rhs);
                    }}
            );
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "LeExpression";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        lhs.emit(instructions);
        lhs.load(instructions);
        rhs.emit(instructions);
        rhs.load(instructions);
        operand = Table.registerTable.addTemp();
        instructions.add(LeInstruction.getInstruction(operand, lhs.operand, rhs.operand));
    }
}