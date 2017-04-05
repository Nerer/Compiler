package Compiler.Table;

import Compiler.Scope.ScopeTable;
import Compiler.Scope.Scope;
import Compiler.Symbol.Symbol;
import Compiler.Symbol.SymbolTable;
import Compiler.Type.*;
import Compiler.AST.Program;
import java.util.ArrayList;

public class Table {
    public static Program program;
    public static ScopeTable scopeTable;
    public static SymbolTable symbolTable;
    public static ClassTable classTable;
    public static IntType myInt;
    public static StringType myString;
    public static BoolType myBool;
    public static NullType myNull;
    public static VoidType myVoid;

    public static void init() {
        scopeTable = new ScopeTable();
        symbolTable = new SymbolTable();
        classTable = new ClassTable();
        myInt = new IntType();
        myString = new StringType();
        myBool = new BoolType();
        myNull = new NullType();
        myVoid = new VoidType();
        program = new Program();
        addScope(program);
        addDefaultFunctions();
    }

    public static void addScope(Scope scope) {
        scopeTable.addScope(scope);
        symbolTable.addScope();
    }

    public static void popScope() {
        scopeTable.popScope();
        symbolTable.popScope();
    }

    public static void addDefaultFunctions() {
        ArrayList<Symbol> parameters = new ArrayList<>();
        parameters.clear();
        parameters.add(new Symbol(Table.myString, "string"));
        symbolTable.addSymbol("print", FunctionType.getFunction("print", Table.myVoid, parameters));
        parameters.clear();
        parameters.add(new Symbol(Table.myString, "string"));
        symbolTable.addSymbol("println", FunctionType.getFunction("println", Table.myVoid, parameters));
        parameters.clear();
        symbolTable.addSymbol("getString", FunctionType.getFunction("getString", Table.myString, parameters));
        symbolTable.addSymbol("getInt", FunctionType.getFunction("getInt", Table.myInt, parameters));
        parameters.add(new Symbol(Table.myInt,"int"));
        symbolTable.addSymbol("toString", FunctionType.getFunction("toString", Table.myString, parameters));
        parameters.clear();
        symbolTable.addSymbol("Mx_builtin_str_length", FunctionType.getFunction("Mx_builtin_str_length", Table.myInt, parameters));
        symbolTable.addSymbol("Mx_builtin_str_parseInt", FunctionType.getFunction("Mx_builtin_str_parseInt", Table.myInt, parameters));
        symbolTable.addSymbol("Mx_builtin_arr_size", FunctionType.getFunction("Mx_builtin_arr_size", Table.myInt, parameters));
        parameters.add(new Symbol(Table.myInt, "left"));
        parameters.add(new Symbol(Table.myInt, "right"));
        symbolTable.addSymbol("Mx__builtin_str_substring", FunctionType.getFunction("Mx_builtin_str_substring", Table.myString, parameters));
        parameters.clear();
        parameters.add(new Symbol(Table.myInt, "i"));
        symbolTable.addSymbol("Mx_builtin_str_ord", FunctionType.getFunction("Mx_builtin_str_ord", Table.myInt, parameters));
    }



}
