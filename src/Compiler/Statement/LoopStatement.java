package Compiler.Statement;

import Compiler.IR.ControlFlowIR.LabelInstruction;
import Compiler.Scope.Scope;
/**
 * Created by SteinerT on 2017/4/5.
 */
public abstract class LoopStatement extends Statement implements Scope {
    public LabelInstruction loop, merge;
}
