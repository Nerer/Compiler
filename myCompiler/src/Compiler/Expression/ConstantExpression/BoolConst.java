package Compiler.Expression.ConstantExpression;

import Compiler.Table.Table;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class BoolConst extends ConstantExpression {
    public boolean value;
    public BoolConst(boolean flag) {
        super(Table.myBool);
        this.value = flag;
    }

}
