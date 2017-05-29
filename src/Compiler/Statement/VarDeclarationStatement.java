package Compiler.Statement;

import Compiler.IR.Instruction;
import Compiler.IR.MemoryIR.MoveInstruction;
import Compiler.Symbol.*;
import Compiler.Expression.Expression;
import Compiler.Type.*;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class VarDeclarationStatement extends Statement{
    public Symbol symbol;
    public Expression expression;

    public VarDeclarationStatement(Symbol symbol, Expression expression) {
        this.symbol = symbol;
        this.expression = expression;
    }

    public static Statement getStatement(Symbol symbol, Expression expression) {
        if (symbol.type instanceof VoidType) {
            throw new Error();
        }
        if (symbol.name.equals("this")) {
            throw new Error();
        }
        if (expression == null || symbol.type.equals(expression.type)) {
            return new VarDeclarationStatement(symbol, expression);
        }
        throw new Error();
    }

    @Override
    public String toString() {
        return "VarDeclarationStatement";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        if (expression != null) {
            expression.emit(instructions);
            expression.load(instructions);
            instructions.add(MoveInstruction.getInstruction(symbol.register, expression.operand));
        }
    }
}
