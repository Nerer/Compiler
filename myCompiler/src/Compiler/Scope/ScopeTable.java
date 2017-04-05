package Compiler.Scope;


import Compiler.Statement.LoopStatement;
import Compiler.Type.ClassType;
import Compiler.Type.FunctionType;
import Compiler.Statement.IfStatement;

import java.util.Stack;

/**
 * Created by SteinerT on 2017/4/2.
 */
public class ScopeTable {
    public Stack<Scope> scope;
    public Stack<ClassType> classScope;
    public Stack<FunctionType> functionScope;
    public Stack<LoopStatement> loopScope;
    public ScopeTable() {
        scope = new Stack<>();
        classScope = new Stack<>();
        functionScope = new Stack<>();
        loopScope = new Stack<>();
    }
    public void addScope(Scope now) {
        scope.push(now);
        if (now instanceof ClassType) {
            classScope.push((ClassType) now);
        }
        if (now instanceof FunctionType) {
            functionScope.push((FunctionType) now);
        }
        if (now instanceof IfStatement) {
            loopScope.push((LoopStatement) now);
        }
    }

    public void popScope() {
        if (scope.empty()) {
            //...
        }
        Scope now = scope.pop();
        if (now instanceof ClassType) {
            classScope.pop();
        }
        if (now instanceof FunctionType) {
            functionScope.pop();
        }
        if (now instanceof LoopStatement) {
            loopScope.pop();
        }
    }
    public ClassType getClassScope() {
        if (classScope.empty()) {
            return null;
        } else {
            return classScope.peek();
        }
    }
    public FunctionType getFunctionScope() {
        if (functionScope.empty()) {
            return null;
        } else {
            return functionScope.peek();
        }
    }
    public LoopStatement getLoopScope() {
        if (loopScope.empty()) {
            return null;
        } else {
            return loopScope.peek();
        }
    }
    public Scope getCurrentScope() {
        if (scope.empty()) {
            return null;
        }
        return scope.peek();
    }

}
