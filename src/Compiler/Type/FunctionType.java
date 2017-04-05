package Compiler.Type;

import Compiler.AST.ASTNode;
import Compiler.Scope.Scope;
import Compiler.Statement.BlockStatement;
import Compiler.Symbol.Symbol;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by SteinerT on 2017/4/2.
 */
public class FunctionType extends Type implements ASTNode, Scope {
    public Type returnType;
    public String name;
    public List<Symbol> parameters;
    public BlockStatement statements;

    public FunctionType(String name, Type returnType, List<Symbol> parameters) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
    }
    public static FunctionType getFunction(String name, Type returnType, List<Symbol> parameters) {
        if (name == "main") {
            if (!(returnType instanceof IntType)) {
                throw new Error();
            }
            if (parameters.size() != 0) {
                throw new Error();
            }
        }
        if (name.equals("this")) {
            throw new Error();
        }
        Set set = new HashSet<>();
        for (int i = 0; i < parameters.size(); i++) {
            if (set.contains(parameters.get(i))) {
                throw new Error();
            }
            set.add(parameters.get(i));
        }
        return new FunctionType(name, returnType, parameters);
    }

    public void addStatement(BlockStatement statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "FunctionType";
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
