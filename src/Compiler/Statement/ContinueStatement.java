package Compiler.Statement;

import Compiler.IR.ControlFlowIR.JumpInstruction;
import Compiler.IR.Instruction;
import Compiler.Table.Table;

import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class ContinueStatement extends Statement {
    public LoopStatement belong;
    public ContinueStatement(LoopStatement belong) {
        this.belong = belong;
    }

    public static Statement getStatement() {
        if (Table.scopeTable.getLoopScope() == null) {
            throw new Error();
        }
        return new ContinueStatement(Table.scopeTable.getLoopScope());
    }

    @Override
    public String toString() {
        return "ContinueStatement";
    }

    @Override
    public void emit(List<Instruction> instructions) {
        instructions.add(JumpInstruction.getInstruction(belong.loop));
    }
}
