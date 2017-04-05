package Compiler.Expression.ConstantExpression;

import Compiler.Table.Table;
/**
 * Created by SteinerT on 2017/4/4.
 */
public class StringConst extends ConstantExpression {
    public String value;
    public StringConst(String str) {
        super(Table.myString);
        this.value = str;
    }
}
