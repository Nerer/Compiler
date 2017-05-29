package Compiler.Symbol;

import java.util.HashMap;
import java.util.Stack;

import Compiler.Table.Table;
import Compiler.Type.Type;
/**
 * Created by SteinerT on 2017/4/3.
 */
public class SymbolTable {
    public Stack<HashMap<String, Symbol> > tableStack;
    public HashMap<String, Stack<Symbol> > conameSymbols;

    public SymbolTable() {
        conameSymbols = new HashMap<>();
        tableStack = new Stack<>();
    }
    public boolean contains(String name) {
        return conameSymbols.containsKey(name) && !conameSymbols.get(name).empty();
    }

    public Symbol addSymbol(String name, Type type) {
        if (tableStack.peek().containsKey(name)) {
            throw new Error(name + "has been used previously");
        }
        if (!conameSymbols.containsKey(name)) {
            conameSymbols.put(name, new Stack<>());
        }
        Symbol symbol = new Symbol(type, name);
        tableStack.peek().put(name, symbol);
        conameSymbols.get(name).push(symbol);
        return symbol;
    }

    public Symbol addGlobalVariable(String name, Type type) {
        Symbol symbol = addSymbol(name, type);
        symbol.register = Table.registerTable.addGlobal(symbol);
        return symbol;
    }

    public Symbol addTemporaryVariable(String name, Type type) {
        Symbol symbol = addSymbol(name, type);
        symbol.register = Table.registerTable.addTemp(symbol);
        return symbol;
    }

    public Symbol addParameterVariable(String name, Type type) {
        Symbol symbol = addSymbol(name, type);
        symbol.register = Table.registerTable.addParameter(symbol);
        return symbol;
    }

    public Symbol getSymbol(String name) {
        return conameSymbols.get(name).peek();
    }

    public boolean exist(String name) {
        return (conameSymbols.containsKey(name)) && (!conameSymbols.get(name).empty());
    }

    public void addScope() {
        tableStack.push(new HashMap<>());
    }

    public void popScope() {
        HashMap<String, Symbol > popTable = tableStack.pop();
        popTable.forEach((name, symbol) -> conameSymbols.get(name).pop());
    }



}
