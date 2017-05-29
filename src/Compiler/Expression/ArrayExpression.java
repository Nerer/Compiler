package Compiler.Expression;

import Compiler.IR.Address;
import Compiler.IR.ArithmeticIR.Binary.AddInstruction;
import Compiler.IR.ArithmeticIR.Binary.MulInstruction;
import Compiler.IR.ArithmeticIR.Unary.BitNotInstruction;
import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.IR.MemoryIR.LoadInstruction;
import Compiler.IR.VRegister;
import Compiler.Table.Table;
import Compiler.Type.*;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class ArrayExpression extends Expression {
    public Expression expression;
    public Expression index;
    public ArrayExpression(Type type, boolean leftValue, Expression expression, Expression index) {
        super(type, leftValue);
        this.expression = expression;
        this.index = index;
    }

    public static Expression getExpression(Expression expression, Expression index) {
        if (!(expression.type instanceof ArrayType)) {
            throw new Error();
        }
        if (!(index.type instanceof IntType)) {
            throw new Error();
        }
        ArrayType arrayType = (ArrayType)expression.type;
        return new ArrayExpression(arrayType.getReduced(), expression.leftValue, expression, index);
    }

    @Override
    public void emit(List<Instruction> instructions) {
        expression.emit(instructions);
        expression.load(instructions);
        index.emit(instructions);
        index.load(instructions);
        VRegister address = Table.registerTable.addTemp();
        VRegister delta = Table.registerTable.addTemp();
        instructions.add(MulInstruction.getInstruction(delta, index.operand, new Immediate(8)));
        instructions.add(AddInstruction.getInstruction(address, expression.operand, delta));
        operand = new Address(address, new Immediate(8));
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
