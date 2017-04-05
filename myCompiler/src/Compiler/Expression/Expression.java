package Compiler.Expression;

import Compiler.AST.ASTNode;
import Compiler.Type.Type;
/**
 * Created by SteinerT on 2017/4/4.
 */
public abstract class Expression implements ASTNode{
    public Type type;
    public boolean leftValue;
    public Expression() {
        this.type = null;
        this.leftValue = false;
    }
    public Expression(Type type, boolean leftValue) {
        this.type = type;
        this.leftValue = leftValue;
    }
}
