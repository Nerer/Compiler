package Compiler.IR;

import java.util.ArrayList;
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


    public abstract void setDefinedRegister(VRegister from, VRegister to);
    public abstract void setUsedRegister(VRegister from, Operand to);


    public abstract List<Operand> getDefinedOperands();
    public abstract List<Operand> getUsedOperands();

    public List<VRegister> getDefinedRegisters() {
        return new ArrayList<VRegister>() {{
            for (Operand operand : getDefinedOperands()) {
                if (operand instanceof VRegister) {
                    add((VRegister) operand);
                }
            }
        }};
    }

    public List<VRegister> getUsedRegisters() {
        return new ArrayList<VRegister>() {{
            for (Operand operand : getUsedOperands()) {
                if (operand instanceof VRegister) {
                    add((VRegister) operand);
                }
            }
        }};
    }
}
