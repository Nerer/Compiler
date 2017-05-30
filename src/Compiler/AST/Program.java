package Compiler.AST;

import Compiler.Scope.Scope;
import Compiler.Statement.VarDeclarationStatement;
import Compiler.Symbol.Symbol;
import Compiler.Type.*;

import java.util.ArrayList;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class Program implements ASTNode, Scope {
    public ArrayList<FunctionType> functions;
    public ArrayList<VarDeclarationStatement> variables;
    public ArrayList<Symbol> rebuildVariables;
    public ArrayList<ClassType> classTypes;

    public Program() {
        classTypes = new ArrayList<>();
        variables = new ArrayList<>();
        functions = new ArrayList<>();
        rebuildVariables = new ArrayList<>();
    }

    public static Program getProgram() {
        return new Program();
    }

    public void addFunction(FunctionType function) {
        functions.add(function);
    }

    public void addClass(ClassType classType) {
        classTypes.add(classType);
    }

    public void addVariables(VarDeclarationStatement var) {
        variables.add(var);
    }
}
