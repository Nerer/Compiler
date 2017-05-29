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
public class ForStatement extends LoopStatement {
    public Expression init, condition, operation;
    public Statement statement;

    public static Statement getStatement() {
        return new ForStatement();
    }

    public void addInit(Expression expression) {
        this.init = expression;
    }

    public void addCondition(Expression expression) {
        if (expression.type instanceof BoolType) {
            this.condition = expression;
        } else {
            throw new Error();
        }
    }
    public void addOperation(Expression expression) {
        this.operation = expression;
    }

    public void addStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "ForStatement";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        LabelInstruction conditionLabel = LabelInstruction.getInstruction("for_condition");
        LabelInstruction bodyLabel = LabelInstruction.getInstruction("for_body");
        loop = LabelInstruction.getInstruction("for_loop");
        merge = LabelInstruction.getInstruction("for_merge");
		/*
			%...:
				****initialization****
				jump %condition
			%for_condition:
				****condition****
				branch $condition, %for_body, %for_merge
			%for_body:
				****statement****
				jump %for_loop
			%for_loop:
				****increment****
				jump %for_condition
			%for_merge:
				...
		 */
        if (init != null) {
            init.emit(instructions);
        }
        instructions.add(JumpInstruction.getInstruction(conditionLabel));
        instructions.add(conditionLabel);
        if (condition == null) {
            addCondition(null);
        }
        condition.emit(instructions);
        instructions.add(BranchInstruction.getInstruction(condition.operand, bodyLabel, merge));
        instructions.add(bodyLabel);
        if (statement != null) {
            statement.emit(instructions);
        }
        instructions.add(JumpInstruction.getInstruction(loop));
        instructions.add(loop);
        if (operation != null) {
            operation.emit(instructions);
        }
        instructions.add(JumpInstruction.getInstruction(conditionLabel));
        instructions.add(merge);
    }
}
