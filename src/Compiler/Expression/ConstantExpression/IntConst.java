package Compiler.Expression.ConstantExpression;
import Compiler.Table.Table;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class IntConst extends ConstantExpression {
    public int value;
    public IntConst(int value) {
        super(Table.myInt);
        this.value = value;
    }
}
