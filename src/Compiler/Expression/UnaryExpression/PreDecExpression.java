package Compiler.Expression.UnaryExpression;


import Compiler.IR.Address;
import Compiler.IR.ArithmeticIR.Binary.AddInstruction;
import Compiler.IR.ArithmeticIR.Binary.MinusInstruction;
import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.IR.MemoryIR.StoreInstruction;
import Compiler.Type.Type;
import Compiler.Expression.Expression;
import Compiler.Type.IntType;
import Compiler.Table.Table;
import com.sun.tools.corba.se.idl.constExpr.Minus;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class PreDecExpression extends UnaryExpression {
    public PreDecExpression(Type type, boolean leftValue, Expression expression) {
        super(type, leftValue, expression);
    }

    public static Expression getExpression(Expression expression) {
        if (expression.type instanceof IntType && expression.leftValue) {
            return new PreDecExpression(Table.myInt, false, expression);
        }
        throw new Error();
    }

    @Override
    public void emit(List<Instruction> instructions) {
        expression.emit(instructions);
        operand = Table.registerTable.addTemp();
        if (expression.operand instanceof Address) {
            Address address = (Address)expression.operand;
            address = new Address(address.base, address.offset);
            expression.load(instructions);
            operand = expression.operand;
            instructions.add(MinusInstruction.getInstruction(operand, operand, new Immediate(1)));
            instructions.add(StoreInstruction.getInstruction(operand, address));
        } else {
            expression.load(instructions);
            operand = expression.operand;
            instructions.add(MinusInstruction.getInstruction(operand, operand, new Immediate(1)));
        }
    }
}

