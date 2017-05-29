package Compiler.Expression;

import Compiler.AST.ASTNode;
import Compiler.IR.Instruction;
import Compiler.IR.Operand;
import Compiler.Type.Type;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/4.
 */
public abstract class Expression implements ASTNode{
    public Type type;
    public boolean leftValue;
    public Operand operand;
    public Expression() {
        this.type = null;
        this.leftValue = false;
        this.operand = null;
    }
    public Expression(Type type, boolean leftValue) {
        this.type = type;
        this.leftValue = leftValue;
        this.operand = null;
    }


    public abstract void emit(List<Instruction> instructions);

    public void load(List<Instruction> instructions) {
    }
}
