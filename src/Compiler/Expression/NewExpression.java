package Compiler.Expression;

import Compiler.IR.Address;
import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.IR.MemoryIR.AllocateInstruction;
import Compiler.IR.MemoryIR.StoreInstruction;
import Compiler.IR.VRegister;
import Compiler.Table.Table;
import Compiler.Type.*;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class NewExpression extends Expression{
    public List<Expression> expressions;

    public NewExpression(Type type, boolean leftValue, List<Expression> expressions) {
        super(type, leftValue);
        this.expressions = expressions;
    }

    public static Expression getExpression(Type type, List<Expression> expressions) {
        if(expressions.isEmpty()) {
            if (type instanceof ClassType) {
                return new NewExpression(type, false, expressions);
            }
            throw new Error();
        } else {
            Type arrayType = ArrayType.getType(type, expressions.size());
            return new NewExpression(arrayType, false, expressions);
        }
    }

    @Override
    public void emit(List<Instruction> instructions) {
        expressions.stream().filter(expression -> expression != null).forEach(expression -> {
            expression.emit(instructions);
            expression.load(instructions);
        });

        operand = Table.registerTable.addTemp();
        if (type instanceof ClassType) {
            ClassType classType = (ClassType)type;
            instructions.add(AllocateInstruction.getInstruction(operand, new Immediate(classType.allocateSize)));
            classType.memberVars.forEach((name, member) -> {
                Address address = new Address((VRegister)operand, new Immediate(member.offset));
                if (member.expression != null) {
                    member.expression.emit(instructions);
                    member.expression.load(instructions);
                    instructions.add(StoreInstruction.getInstruction(member.expression.operand, address));
                }
            });
        }
    }

}
