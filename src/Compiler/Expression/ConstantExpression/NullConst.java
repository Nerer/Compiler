package Compiler.Expression.ConstantExpression;
import Compiler.Table.Table;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class NullConst extends ConstantExpression {
    public NullConst() {
        super(Table.myNull);
    }
}
