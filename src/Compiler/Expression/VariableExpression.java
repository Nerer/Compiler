package Compiler.Expression;
import Compiler.ErrorMessege.CompilationError;
import Compiler.IR.Instruction;
import Compiler.Symbol.*;
import Compiler.Type.*;
import Compiler.Table.Table;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class VariableExpression extends Expression {
    public Symbol symbol;
    public VariableExpression(Type type, boolean leftValue, Symbol symbol) {
        super(type, leftValue);
        this.symbol = symbol;
    }

    public static Expression getExpression(String name) {
        if (!Table.symbolTable.contains(name)) {
            throw new CompilationError("VarError");
        }
        Symbol symbol = Table.symbolTable.getSymbol(name);
        if (symbol.scope instanceof ClassType) {
            return MemberExpression.getExpression(VariableExpression.getExpression("this"), name);
        } else {
            if (symbol.type instanceof FunctionType) {
                return new VariableExpression(symbol.type, false, symbol);
            } else {
                return new VariableExpression(symbol.type, true, symbol);
            }
        }
    }

    @Override
    public void emit(List<Instruction> instructions) {
        operand = symbol.register;
    }


}
