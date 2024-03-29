package Compiler.Statement;


import Compiler.IR.Instruction;
import Compiler.Scope.Scope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteinerT on 2017/4/2.
 */
public class BlockStatement extends Statement implements Scope{
    public ArrayList<Statement> statements;

    public BlockStatement() {
        statements = new ArrayList<>();
    }
    public static Statement getStatement() {
        return new BlockStatement();
    }
    public void addStatement(Statement statement) {
        statements.add(statement);
    }
    @Override
    public String toString() {
        String ret = "Block{\n";
        for (int i = 0; i < statements.size(); i++) {
            ret += statements.get(i).toString();
            ret += "\n";
        }
        ret += "}";
        return ret;
    }

    @Override
    public void emit(List<Instruction> instructions) {
        statements.forEach(statement -> statement.emit(instructions));
    }
}
