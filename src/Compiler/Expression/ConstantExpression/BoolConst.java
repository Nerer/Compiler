package Compiler.Expression.ConstantExpression;

import Compiler.IR.Immediate;
import Compiler.IR.Instruction;
import Compiler.Table.Table;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class BoolConst extends ConstantExpression {
    public boolean value;
    public BoolConst(boolean flag) {
        super(Table.myBool);
        this.value = flag;
    }
    public static BoolConst getConst(boolean flag) {
        return new BoolConst(flag);
    }
    @Override
    public void emit(List<Instruction> instructions) {
        operand = new Immediate(value ? 1 : 0);
    }
}
