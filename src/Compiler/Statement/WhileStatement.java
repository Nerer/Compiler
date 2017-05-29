package Compiler.Statement;

import Compiler.Expression.Expression;
import Compiler.IR.ControlFlowIR.BranchInstruction;
import Compiler.IR.ControlFlowIR.JumpInstruction;
import Compiler.IR.ControlFlowIR.LabelInstruction;
import Compiler.IR.Instruction;
import Compiler.Type.*;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */

public class WhileStatement extends LoopStatement {
    public Expression condition;
    public Statement statement;

    public static Statement getStatement() {
        return new WhileStatement();
    }

    public void addCondition(Expression expression) {
        if (expression.type instanceof BoolType) {
            this.condition = expression;
        } else {
            throw new Error();
        }
    }
    public void addStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "WhileStatement";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        LabelInstruction bodyLabel = LabelInstruction.getInstruction("while_body");
        loop = LabelInstruction.getInstruction("while_loop");
        merge = LabelInstruction.getInstruction("while_merge");
		/*
			%...:
				jump %while_loop
			%while_loop:
				****condition****
				branch $condition, %while_body, %while_merge
			%while_body:
				****statement****
				jump %while_loop
			%while_merge:
				...
		 */
        instructions.add(JumpInstruction.getInstruction(loop));
        instructions.add(loop);
        condition.emit(instructions);
        instructions.add(BranchInstruction.getInstruction(condition.operand, bodyLabel, merge));
        instructions.add(bodyLabel);
        statement.emit(instructions);
        instructions.add(JumpInstruction.getInstruction(loop));
        instructions.add(merge);
    }
}
