package Compiler.Expression.BinaryExpression;

import Compiler.ErrorMessege.CompilationError;
import Compiler.Expression.Expression;
import Compiler.IR.Address;
import Compiler.IR.Instruction;
import Compiler.IR.MemoryIR.LoadInstruction;
import Compiler.IR.MemoryIR.MoveInstruction;
import Compiler.IR.MemoryIR.StoreInstruction;
import Compiler.Table.Table;
import Compiler.Type.Type;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class AssignmentExpression extends BinaryExpression {
    public AssignmentExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }
    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (!lhs.leftValue) {
            throw new Error();
        }
        if (rhs.type.equals(lhs.type)) {
            return new AssignmentExpression(lhs.type, false, lhs, rhs);
        }

        throw new CompilationError("assign error");
    }

    @Override
    public String toString() {
        return "AssignmentExpression";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        lhs.emit(instructions);
        rhs.emit(instructions);
        rhs.load(instructions);
        operand = lhs.operand;
        if (lhs.operand instanceof Address) {
            instructions.add(StoreInstruction.getInstruction(rhs.operand, lhs.operand));
        } else {
            instructions.add(MoveInstruction.getInstruction(lhs.operand, rhs.operand));
        }
    }

    @Override
    public void load(List<Instruction> instructions) {
        if (operand instanceof Address) {
            Address address = (Address)operand;
            operand = Table.registerTable.addTemp();
            instructions.add(LoadInstruction.getInstruction(operand, address));
        }
    }
}
