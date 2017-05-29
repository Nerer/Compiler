package Compiler.IR;

import Compiler.IR.ControlFlowIR.LabelInstruction;
import Compiler.Type.FunctionType;

import java.util.ArrayList;
import java.util.List;

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
    Block(FunctionType function, String name, int identity, LabelInstruction label) {
        this.function = function;
        this.name = name;
        this.identity = identity;
        this.label = label;
        this.instructions = new ArrayList<>();
        this.phiFunctions = new ArrayList<>();
        this.successors = new ArrayList<>();
        this.predecessors = new ArrayList<>();
    }

}
