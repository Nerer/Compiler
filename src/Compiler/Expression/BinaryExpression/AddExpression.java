package Compiler.Expression.BinaryExpression;

import Compiler.Expression.ConstantExpression.StringConst;
import Compiler.Expression.Expression;
import Compiler.Expression.FunctionCallExpression;
import Compiler.IR.Address;
import Compiler.IR.ArithmeticIR.Binary.AddInstruction;
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
public class AddExpression extends BinaryExpression {

    public AddExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }

    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (lhs.type instanceof IntType && rhs.type instanceof IntType) {
            return new AddExpression((Type)Table.myInt,false, lhs, rhs);
        }

        if (lhs.type instanceof StringType && rhs.type instanceof StringType) {
            if (lhs instanceof StringConst && rhs instanceof StringConst) {
                String literal1 = ((StringConst) lhs).value;
                String literal2 = ((StringConst) rhs).value;
                return StringConst.getConst(literal1 + literal2);
            }
            return FunctionCallExpression.getExpression(
                    (FunctionType) Table.symbolTable.getSymbol("Mx_builtin_str_concatenate").type,
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
        return "AddExpression";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        lhs.emit(instructions);
        lhs.load(instructions);
        rhs.emit(instructions);
        rhs.load(instructions);
        operand = Table.registerTable.addTemp();
        instructions.add(AddInstruction.getInstruction(operand, lhs.operand, rhs.operand));
    }
}
