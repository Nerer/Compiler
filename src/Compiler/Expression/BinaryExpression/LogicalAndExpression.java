package Compiler.Expression.BinaryExpression;


import Compiler.Expression.Expression;
import Compiler.IR.ArithmeticIR.Binary.BitLeftShiftInstruction;
import Compiler.IR.ArithmeticIR.Binary.LogicalAndInstruction;
import Compiler.IR.ControlFlowIR.BranchInstruction;
import Compiler.IR.ControlFlowIR.JumpInstruction;
import Compiler.IR.ControlFlowIR.LabelInstruction;
import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.IR.MemoryIR.MoveInstruction;
import Compiler.Type.Type;
import Compiler.Type.BoolType;
import Compiler.Table.Table;

import java.util.List;

import static org.antlr.v4.analysis.LeftRecursiveRuleAnalyzer.ASSOC.left;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class LogicalAndExpression extends BinaryExpression {
    public LogicalAndExpression(Type type, boolean leftValue, Expression lhs, Expression rhs) {
        super(type, leftValue, lhs, rhs);
    }
    public static Expression getExpression(Expression lhs, Expression rhs) {
        if (lhs.type instanceof BoolType && rhs.type instanceof BoolType) {
            return new LogicalAndExpression(Table.myBool, false, lhs, rhs);
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "LogicalAndExpression";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        LabelInstruction trueLabel = LabelInstruction.getInstruction("logical_true");
        LabelInstruction falseLabel = LabelInstruction.getInstruction("logical_false");
        LabelInstruction mergeLabel = LabelInstruction.getInstruction("logical_merge");
		/*
			%...:
				****left****
				branch $left, %logical_true, %logical_false
			%logical_true:
				****right****
				$operand = move $right
				goto logical_merge
			%logical_false:
				$operand = move 0
				goto logical_merge
			%logical_merge:
				...
		 */
        lhs.emit(instructions);
        lhs.load(instructions);
        instructions.add(BranchInstruction.getInstruction(lhs.operand, trueLabel, falseLabel));
        instructions.add(trueLabel);
        rhs.emit(instructions);
        rhs.load(instructions);
        operand = rhs.operand;
        instructions.add(JumpInstruction.getInstruction(mergeLabel));
        instructions.add(falseLabel);
        instructions.add(MoveInstruction.getInstruction(operand, new Immediate(0)));
        instructions.add(JumpInstruction.getInstruction(mergeLabel));
        instructions.add(mergeLabel);
    }
}
