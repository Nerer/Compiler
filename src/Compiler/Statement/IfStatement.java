package Compiler.Statement;

import Compiler.IR.ControlFlowIR.BranchInstruction;
import Compiler.IR.ControlFlowIR.JumpInstruction;
import Compiler.IR.ControlFlowIR.LabelInstruction;
import Compiler.IR.Instruction;
import Compiler.Scope.Scope;
import Compiler.Expression.Expression;
import Compiler.Type.BoolType;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class IfStatement extends Statement implements Scope{
    public Expression condition;
    public Statement trueStatement, falseStatement;

    public IfStatement(Expression condition, Statement trueStatement, Statement falseStatement) {
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }
    public static Statement getStatement(Expression condition, Statement trueStatement, Statement falseStatement) {
        if (!(condition.type instanceof BoolType)) {
            throw new Error();
        }
        return new IfStatement(condition, trueStatement, falseStatement);
    }

    @Override
    public String toString() {
        return "IfStatement";
    }


    @Override
    public void emit(List<Instruction> instructions) {
        LabelInstruction trueLabel = LabelInstruction.getInstruction("if_true");
        LabelInstruction falseLabel = LabelInstruction.getInstruction("if_false");
        LabelInstruction mergeLabel = LabelInstruction.getInstruction("if_merge");
		/*
			%...:
				****condition****
				branch $condition, %if_true, %if_false
			%if_true:
				****trueStatement****
				jump %if_merge
			%if_false:
				****falseStatement****
				jump %if_merge
			%if_merge:
				...
		 */
        condition.emit(instructions);
        condition.load(instructions);
        instructions.add(BranchInstruction.getInstruction(condition.operand, trueLabel, falseLabel));
        instructions.add(trueLabel);
        if (trueStatement != null) {
            trueStatement.emit(instructions);
        }
        instructions.add(JumpInstruction.getInstruction(mergeLabel));
        instructions.add(falseLabel);
        if (falseStatement != null) {
            falseStatement.emit(instructions);
        }
        instructions.add(JumpInstruction.getInstruction(mergeLabel));
        instructions.add(mergeLabel);
    }
}
