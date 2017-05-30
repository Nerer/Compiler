package Compiler.Allocator.GlobalRegisterAllocator;

import Compiler.Allocator.Allocator;
import Compiler.IR.ArithmeticIR.Binary.BinaryInstruction;
import Compiler.IR.Block;
import Compiler.IR.Instruction;
import Compiler.IR.MemoryIR.MoveInstruction;
import Compiler.IR.VRegister;
import Compiler.Type.FunctionType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by SteinerT on 2017/5/30.
 */
public class GlobalRegisterAllocator extends Allocator{
    public GlobalRegisterAllocator(FunctionType function) {
        super(function);
        function.graph.refresh();
        InterferenceGraph graph = new InterferenceGraph();
        for (Block block : function.graph.blocks) {
            for (Instruction instruction : block.instructions) {
                for (VRegister register : instruction.getUsedRegisters()) {
                    graph.add(register);
                }
                for (VRegister register : instruction.getDefinedRegisters()) {
                    graph.add(register);
                }
            }
        }
        for (Block block : function.graph.blocks) {
            Set<VRegister> live = new HashSet<VRegister>() {{
                block.liveliness.liveOut.forEach(this::add);
            }};
            for (int i = block.instructions.size() - 1; i >= 0; --i) {
                Instruction instruction = block.instructions.get(i);
                if (instruction instanceof BinaryInstruction) {
                    for (VRegister out : live) {
                        graph.forbid(((BinaryInstruction) instruction).target, out);
                    }
                    live.remove(((BinaryInstruction) instruction).target);
                    if (((BinaryInstruction) instruction).source2 instanceof VRegister){
                        live.add((VRegister) ((BinaryInstruction) instruction).source2);
                    }
                    for (VRegister out : live) {
                        graph.forbid(((BinaryInstruction) instruction).target, out);
                    }
                    live.remove(((BinaryInstruction) instruction).target);
                    if (((BinaryInstruction) instruction).source1 instanceof VRegister){
                        live.add((VRegister) ((BinaryInstruction) instruction).source1);
                    }
                } else {
                    for (VRegister register : instruction.getDefinedRegisters()) {
                        for (VRegister living : live) {
                            graph.forbid(register, living);
                        }
                    }
                    instruction.getDefinedRegisters().forEach(live::remove);
                    instruction.getUsedRegisters().forEach(live::add);
                }
            }
        }
        for (Block block : function.graph.blocks) {
            for (Instruction instruction : block.instructions) {
                if (instruction instanceof MoveInstruction) {
                    MoveInstruction i = (MoveInstruction)instruction;
                    if (i.source instanceof VRegister) {
                        graph.recommend(i.target, (VRegister)i.source);
                    }
                }
            }
        }
        mapping = new ChaitinGraphColoring(graph).analysis();
    }
}
