package Compiler.Statement;

import Compiler.IR.ControlFlowIR.JumpInstruction;
import Compiler.IR.FunctionIR.ReturnInstruction;
import Compiler.IR.Instruction;
import Compiler.Type.FunctionType;
import Compiler.Table.Table;
import Compiler.Type.VoidType;
import Compiler.Expression.Expression;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class ReturnStatement extends Statement {
    public FunctionType belong;
    public Expression expression;
    public ReturnStatement(FunctionType belong, Expression expression) {
        this.belong = belong;
        this.expression = expression;
    }
    public static Statement getStatement(Expression expression) {
        if (Table.scopeTable.getFunctionScope() != null) {
            FunctionType function = Table.scopeTable.getFunctionScope();
            if (function.returnType instanceof VoidType && expression == null) {
                return new ReturnStatement(function, expression);
            }
            if (expression != null && expression.type.equals(function.returnType)) {
                return new ReturnStatement(function, expression);
            }
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "ReturnStatement";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        if (expression != null) {
            expression.emit(instructions);
            expression.load(instructions);
            instructions.add(ReturnInstruction.getInstruction(expression.operand));
        }
        instructions.add(JumpInstruction.getInstruction(belong.exit));
    }
}
