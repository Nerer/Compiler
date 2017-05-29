package Compiler.Expression.BinaryExpression;

import Compiler.Expression.Expression;
import Compiler.Expression.FunctionCallExpression;
import Compiler.IR.ArithmeticIR.Binary.BitLeftShiftInstruction;
import Compiler.IR.ArithmeticIR.Binary.EqualInstruction;
import Compiler.IR.Instruction;
import Compiler.Table.Table;
import Compiler.Type.FunctionType;
import Compiler.Type.StringType;
import Compiler.Type.Type;
import com.sun.tools.corba.se.idl.constExpr.Equal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class EqualExpression extends BinaryExpression{
    public EqualExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }
    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (!(lhs.type.equals(rhs.type))) {
            throw new Error();
        }
        if (lhs.type instanceof StringType && rhs.type instanceof StringType) {
            return FunctionCallExpression.getExpression(
                    (FunctionType) Table.symbolTable.getSymbol("Mx_builtin_str_e").type,
                    new ArrayList<Expression>() {{
                        add(lhs);
                        add(rhs);
                    }}
            );
        }
        return new EqualExpression(Table.myBool, false, lhs, rhs);
    }

    @Override
    public String toString() {
        return "EqualExpression";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        lhs.emit(instructions);
        lhs.load(instructions);
        rhs.emit(instructions);
        rhs.load(instructions);
        operand = Table.registerTable.addTemp();
        instructions.add(EqualInstruction.getInstruction(operand, lhs.operand, rhs.operand));
    }
}
