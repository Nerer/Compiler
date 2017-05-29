package Compiler.Expression;

import Compiler.IR.*;
import Compiler.IR.ArithmeticIR.Binary.*;
import Compiler.IR.ControlFlowIR.BranchInstruction;
import Compiler.IR.ControlFlowIR.JumpInstruction;
import Compiler.IR.ControlFlowIR.LabelInstruction;
import Compiler.IR.FunctionIR.CallInstruction;
import Compiler.IR.MemoryIR.AllocateInstruction;
import Compiler.IR.MemoryIR.MoveInstruction;
import Compiler.IR.MemoryIR.StoreInstruction;
import Compiler.Statement.ForStatement;
import Compiler.Table.Table;
import Compiler.Type.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class NewExpression extends Expression{
    public List<Expression> expressions;
    public FunctionType constructor;
    public NewExpression(Type type, boolean leftValue, List<Expression> expressions) {
        super(type, leftValue);
        this.expressions = expressions;
        this.constructor = null;
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
    public Operand newClass(Type x, List<Instruction> instructions) {
        VRegister dest = Table.registerTable.addTemp();
        ClassType classType = (ClassType)type;
        instructions.add(AllocateInstruction.getInstruction(dest, new Immediate(classType.allocateSize)));
        classType.memberVars.forEach((name, member) -> {
            Address address = new Address((VRegister)dest, new Immediate(member.offset));
            if (member.expression != null) {
                member.expression.emit(instructions);
                member.expression.load(instructions);
                instructions.add(StoreInstruction.getInstruction(member.expression.operand, address));
            }
        });

        if (constructor == null) {
            if (classType.constructor != null) {
                constructor = classType.constructor;
            }
        }
        if (constructor != null) {
            List<Operand> operands = new ArrayList<Operand>() {{
                add(dest);
            }};
            instructions.add(CallInstruction.getInstruction(null, constructor, operands));
        }
        return dest;
    }
    public Operand boomshakalaka(List<Instruction> instructions, int dim) {
        Operand size = Table.registerTable.addTemp();
        Operand dest = Table.registerTable.addTemp();
        instructions.add(MoveInstruction.getInstruction(size, expressions.get(dim).operand));
        instructions.add(AddInstruction.getInstruction(size, size, Immediate.getImmediate(1)));
        instructions.add(MulInstruction.getInstruction(size, size, Immediate.getImmediate(8)));
        instructions.add(AllocateInstruction.getInstruction(dest, size));
        Address address = new Address((VRegister)dest);
        instructions.add(StoreInstruction.getInstruction(expressions.get(dim).operand, address));
        instructions.add(AddInstruction.getInstruction(dest, dest, Immediate.getImmediate(8)));

        int flag = 0;
        if (type instanceof ClassType && dim + 1 == expressions.size() && expressions.get(dim) != null) {
            flag = 1;
        }
        if (dim + 1 < expressions.size()) {
            flag = 2;
        }

        if (flag != 0) {
            instructions.add(MoveInstruction.getInstruction(size, expressions.get(dim).operand));
            LabelInstruction loop = new LabelInstruction("while_loop");
            instructions.add(loop);
            instructions.add(MinusInstruction.getInstruction(size, size, Immediate.getImmediate(1)));

            VRegister tsize = Table.registerTable.addTemp();
            instructions.add(MoveInstruction.getInstruction(tsize, size));
            instructions.add(MulInstruction.getInstruction(size, size, Immediate.getImmediate(8)));
            instructions.add(AddInstruction.getInstruction(size, size, dest));
            address = new Address((VRegister)size);
            if (flag == 1) {
                instructions.add(StoreInstruction.getInstruction(address, newClass(type, instructions)));
            } else {
                instructions.add(StoreInstruction.getInstruction(address, boomshakalaka(instructions, dim + 1)));
            }
            LabelInstruction merge = new LabelInstruction("while_merge");
            VRegister conditon = Table.registerTable.addTemp();
            instructions.add(NotEqualInstruction.getInstruction(conditon, tsize, Immediate.getImmediate(0)));
            instructions.add(BranchInstruction.getInstruction(conditon, loop, merge));
            instructions.add(merge);
        }
        return dest;
    }

    @Override
    public void emit(List<Instruction> instructions) {
        expressions.stream().filter(expression -> expression != null).forEach(expression -> {
            expression.emit(instructions);
            expression.load(instructions);
        });

        if (type instanceof ClassType) {
            /*ClassType classType = (ClassType)type;
            instructions.add(AllocateInstruction.getInstruction(operand, new Immediate(classType.allocateSize)));
            classType.memberVars.forEach((name, member) -> {
                Address address = new Address((VRegister)operand, new Immediate(member.offset));
                if (member.expression != null) {
                    member.expression.emit(instructions);
                    member.expression.load(instructions);
                    instructions.add(StoreInstruction.getInstruction(member.expression.operand, address));
                }
            });

            if (constructor == null) {
                if (classType.constructor != null) {
                    constructor = classType.constructor;
                }
            }
            if (constructor != null) {
                List<Operand> operands = new ArrayList<Operand>() {{
                    add(operand);
                }};
                instructions.add(CallInstruction.getInstruction(null, constructor, operands));
            }*/
            operand = newClass(type, instructions);
        } else {
            if (type instanceof ArrayType) {
                operand = boomshakalaka(instructions, 0);
            }
        }
    }

}
