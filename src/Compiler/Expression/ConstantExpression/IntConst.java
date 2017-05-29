package Compiler.Expression.ConstantExpression;
import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.Table.Table;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class IntConst extends ConstantExpression {
    public int value;
    public IntConst(int value) {
        super(Table.myInt);
        this.value = value;
    }

    @Override
    public void emit(List<Instruction> instructions) {
        operand = new Immediate(value);
    }
}
