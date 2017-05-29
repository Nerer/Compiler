package Compiler.Statement;

import Compiler.AST.ASTNode;
import Compiler.IR.Instruction;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/2.
 */
public abstract class Statement implements ASTNode {
    public Statement() {}
    @Override
    public String toString() {
        return "Compiler/Statement";
    }

    public abstract void emit(List<Instruction> instructions);
}
