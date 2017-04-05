package Compiler.Statement;

import Compiler.AST.ASTNode;

/**
 * Created by SteinerT on 2017/4/2.
 */
public abstract class Statement implements ASTNode {
    public Statement() {}
    @Override
    public String toString() {
        return "Compiler/Statement";
    }

}
