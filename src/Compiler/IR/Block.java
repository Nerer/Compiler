package Compiler.IR;

import Compiler.IR.ControlFlowIR.LabelInstruction;
import Compiler.Type.FunctionType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by SteinerT on 2017/5/26.
 */
public class Block {
    public FunctionType function;
    public String name;
    public int identity;
    public LabelInstruction label;
    public List<Instruction> instructions, phiFunctions;
    public List<Block> successors, predecessors;
    public Liveliness liveliness;
    Block(FunctionType function, String name, int identity, LabelInstruction label) {
        this.function = function;
        this.name = name;
        this.identity = identity;
        this.label = label;
        this.instructions = new ArrayList<>();
        this.phiFunctions = new ArrayList<>();
        this.successors = new ArrayList<>();
        this.predecessors = new ArrayList<>();
        this.liveliness = new Liveliness();
    }
    public Set<VRegister> getAllRegisters() {
        return new HashSet<VRegister>() {{
            for (Instruction instruction : phiFunctions) {
                addAll(instruction.getDefinedRegisters());
                addAll(instruction.getUsedRegisters());
            }
            for (Instruction instruction : instructions) {
                addAll(instruction.getDefinedRegisters());
                addAll(instruction.getUsedRegisters());
            }
        }};
    }

    public class Liveliness {
        public List<VRegister> used, defined;
        public Set<VRegister> liveIn, liveOut;

        public Liveliness() {
            this.used = new ArrayList<>();
            this.defined = new ArrayList<>();
            this.liveIn = new HashSet<>();
            this.liveOut = new HashSet<>();
        }
    }

}
