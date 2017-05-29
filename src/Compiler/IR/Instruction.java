package Compiler.IR;

import java.util.List;

/**
 * Created by SteinerT on 2017/5/15.
 */
public abstract class Instruction {
    public Instruction() {}
    public Instruction update() {
        return this;
    }

   // public abstract List<Operand> getDefinedOperands();
   // public abstract List<Operand> getUsedOperands();

    public String x64Name() {
        return null;
    }
}
