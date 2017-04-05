package Compiler.Type;

import Compiler.AST.ASTNode;

/**
 * Created by SteinerT on 2017/4/1.
 */
public abstract class Type implements ASTNode{
    public Type() { }

    @Override
    public String toString() {
        return "Compiler/Type";
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Type;
    }
}
