package Compiler.Expression.ConstantExpression;

import Compiler.IR.Instruction;
import Compiler.Table.Table;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class StringConst extends ConstantExpression {
    public String value;
    public StringConst(String str) {
        super(Table.myString);
        this.value = str;
    }

    public static StringConst getConst(String value) {
        return new StringConst(value);
    }

    @Override
    public void emit(List<Instruction> instructions) {
        operand = Table.registerTable.addString(value);
    }
}
