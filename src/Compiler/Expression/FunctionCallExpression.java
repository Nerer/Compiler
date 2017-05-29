package Compiler.Expression;

import Compiler.ErrorMessege.CompilationError;
import Compiler.IR.FunctionIR.CallInstruction;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.Table.Table;
import Compiler.Type.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class FunctionCallExpression extends Expression {
    public FunctionType function;
    public List<Expression> parameters;

    public FunctionCallExpression(Type type, boolean leftValue, FunctionType function, List<Expression> parameters) {
        super(type, leftValue);
        this.function = function;
        this.parameters = parameters;
    }

    public static Expression getExpression(FunctionType function, List<Expression> parameters) {
        return new FunctionCallExpression(function.returnType, false, function, parameters);
    }

    public static Expression getExpression(Expression expression, List<Expression> parameters) {
        if (expression.type instanceof FunctionType) {
            FunctionType function = (FunctionType)expression.type;
            if (expression instanceof MemberExpression) {
                parameters.add(0, ((MemberExpression)expression).expression);
            }
            if (parameters.size() != function.parameters.size()) {
                throw new CompilationError("FunctionCallError");
            }
            for (int i = 0; i < (int)parameters.size(); i++) {
                if (i == 0 && expression instanceof MemberExpression) {
                    continue;
                }
                if (!parameters.get(i).type.equals(function.parameters.get(i).type)) {
                    throw new Error();
                }
            }
            return new FunctionCallExpression(function.returnType, false, function, parameters);
        }
        throw new Error();
    }

    @Override
    public void emit(List<Instruction> instructions) {
        if (!peephole(instructions)) {
            List<Operand> operands = new ArrayList<>();
            for (Expression parameter : parameters) {
                parameter.emit(instructions);
                parameter.load(instructions);
                operands.add(parameter.operand);
            }
            if (type instanceof VoidType) {
                instructions.add(CallInstruction.getInstruction(null, function, operands));
            } else {
                operand = Table.registerTable.addTemp();
                instructions.add(CallInstruction.getInstruction(operand, function, operands));
            }
        }
    }

    public boolean peephole(List<Instruction> instructions) {
        return false;
    }
}
