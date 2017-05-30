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
        InterferenceGraph interGraph = new InterferenceGraph();
        for (Block block : function.graph.blocks) {
            for (Instruction instruction : block.instructions) {
                for (VRegister register : instruction.getDefinedRegisters()) {
                    interGraph.add(register);
                }
                for (VRegister register : instruction.getUsedRegisters()) {
                    interGraph.add(register);
                }
            }
        }
        for (Block block : function.graph.blocks) {
            Set<VRegister> living = new HashSet<>();
            for (VRegister register : block.liveliness.liveOut) {
                living.add(register);
            }
            for (int i = block.instructions.size() - 1; i >= 0; i--) {
                Instruction instruction = block.instructions.get(i);
                if (instruction instanceof BinaryInstruction) {
                    for (VRegister livingRegister : living) {
                        interGraph.forbid(((BinaryInstruction) instruction).target, livingRegister);
                    }
                    living.remove(((BinaryInstruction) instruction).target);
                    if (((BinaryInstruction) instruction).source2 instanceof VRegister) {
                        living.add((VRegister) ((BinaryInstruction) instruction).source2);
                    }

                    for (VRegister livingRegister : living) {
                        interGraph.forbid(((BinaryInstruction) instruction).target, livingRegister);
                    }
                    living.remove(((BinaryInstruction) instruction).target);
                    if (((BinaryInstruction) instruction).source1 instanceof VRegister) {
                        living.add((VRegister) ((BinaryInstruction) instruction).source1);
                    }
                } else {
                    for (VRegister register : instruction.getDefinedRegisters()) {
                        for (VRegister livingRegister : living) {
                            interGraph.forbid(register, livingRegister);
                        }
                    }
                    for (VRegister register : instruction.getDefinedRegisters()) {
                        living.remove(register);
                    }
                    for (VRegister register : instruction.getUsedRegisters()) {
                        living.add(register);
                    }
                }
            }
        }
        for (Block block : function.graph.blocks) {
            for (Instruction instruction : block.instructions) {
                if (instruction instanceof MoveInstruction) {
                    if (((MoveInstruction) instruction).source instanceof VRegister) {
                        interGraph.recommend(((MoveInstruction) instruction).target,
                                (VRegister) ((MoveInstruction) instruction).source);
                    }
                }
            }
        }
        mapping = new ChaitinGraphColoring(interGraph).analysis();
    }
}
