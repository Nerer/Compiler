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
    public static RegisterTable registerTable;
    public static IntType myInt;
    public static StringType myString;
    public static BoolType myBool;
    public static NullType myNull;
    public static VoidType myVoid;

    public static void init() {
        scopeTable = new ScopeTable();
        symbolTable = new SymbolTable();
        registerTable = new RegisterTable();
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
        parameters.add(new Symbol(Table.myString, "string"));
        symbolTable.addSymbol("print", FunctionType.getFunction("Mx_builtin_print", Table.myVoid, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "string"));
        symbolTable.addSymbol("println", FunctionType.getFunction("Mx_builtin_println", Table.myVoid, parameters));

        parameters = new ArrayList<>();
        symbolTable.addSymbol("getString", FunctionType.getFunction("Mx_builtin_getString", Table.myString, parameters));

        parameters = new ArrayList<>();
        symbolTable.addSymbol("getInt", FunctionType.getFunction("Mx_builtin_getInt", Table.myInt, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myInt,"int"));
        symbolTable.addSymbol("toString", FunctionType.getFunction("Mx_builtin_toString", Table.myString, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "this"));
        symbolTable.addSymbol("Mx_builtin_str_length", FunctionType.getFunction("Mx_builtin_str_length", Table.myInt, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "this"));
        symbolTable.addSymbol("Mx_builtin_str_parseInt", FunctionType.getFunction("Mx_builtin_str_parseInt", Table.myInt, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myVoid, "this"));
        symbolTable.addSymbol("Mx_builtin_arr_size", FunctionType.getFunction("Mx_builtin_arr_size", Table.myInt, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "this"));
        parameters.add(new Symbol(Table.myInt, "left"));
        parameters.add(new Symbol(Table.myInt, "right"));
        symbolTable.addSymbol("Mx_builtin_str_substring", FunctionType.getFunction("Mx_builtin_str_substring", Table.myString, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "this"));
        parameters.add(new Symbol(Table.myInt, "i"));
        symbolTable.addSymbol("Mx_builtin_str_ord", FunctionType.getFunction("Mx_builtin_str_ord", Table.myInt, parameters));


        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "left"));
        parameters.add(new Symbol(Table.myString, "right"));
        symbolTable.addSymbol("Mx_builtin_str_concatenate", FunctionType.getFunction("Mx_builtin_str_concatenate", Table.myString, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "left"));
        parameters.add(new Symbol(Table.myString, "right"));
        symbolTable.addSymbol("Mx_builtin_str_e", FunctionType.getFunction("Mx_builtin_str_e", Table.myBool, parameters));


        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "left"));
        parameters.add(new Symbol(Table.myString, "right"));
        symbolTable.addSymbol("Mx_builtin_str_l", FunctionType.getFunction("Mx_builtin_str_l", Table.myBool, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "left"));
        parameters.add(new Symbol(Table.myString, "right"));
        symbolTable.addSymbol("Mx_builtin_str_g", FunctionType.getFunction("Mx_builtin_str_g", Table.myBool, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "left"));
        parameters.add(new Symbol(Table.myString, "right"));
        symbolTable.addSymbol("Mx_builtin_str_ge", FunctionType.getFunction("Mx_builtin_str_ge", Table.myBool, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "left"));
        parameters.add(new Symbol(Table.myString, "right"));
        symbolTable.addSymbol("Mx_builtin_str_le", FunctionType.getFunction("Mx_builtin_str_le", Table.myBool, parameters));

        parameters = new ArrayList<>();
        parameters.add(new Symbol(Table.myString, "left"));
        parameters.add(new Symbol(Table.myString, "right"));
        symbolTable.addSymbol("Mx_builtin_str_ne", FunctionType.getFunction("Mx_builtin_str_ne", Table.myBool, parameters));

    }



}
