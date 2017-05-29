package Compiler.IR;

import Compiler.IR.ArithmeticIR.Binary.AddInstruction;
import Compiler.IR.ArithmeticIR.Binary.BinaryInstruction;
import Compiler.IR.ArithmeticIR.Unary.UnaryInstruction;
import Compiler.IR.ControlFlowIR.BranchInstruction;
import Compiler.IR.ControlFlowIR.ControlFlowInstruction;
import Compiler.IR.ControlFlowIR.JumpInstruction;
import Compiler.IR.ControlFlowIR.LabelInstruction;
import Compiler.IR.FunctionIR.CallInstruction;
import Compiler.IR.FunctionIR.FunctionInstruction;
import Compiler.IR.FunctionIR.ReturnInstruction;
import Compiler.IR.MemoryIR.*;
import Compiler.Statement.VarDeclarationStatement;
import Compiler.Table.Table;
import Compiler.Type.FunctionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SteinerT on 2017/5/26.
 */
public class Graph {
    public FunctionType function;
    public List<Block> blocks;
    public Block enter, entry, exit;
    public Frame frame;
    public Map<VRegister, Integer> mapping;
    public Graph(FunctionType function) {
        this.function = function;
        this.buildGraph();
        this.calcRegisters();
    }

    public void buildGraph() {
        List<Instruction> instructions = new ArrayList<>();
        function.enter = LabelInstruction.getInstruction("enter");
        function.entry = LabelInstruction.getInstruction("entry");
        function.exit = LabelInstruction.getInstruction("exit");
        instructions.add(function.enter);
        if (function.name.equals("main")) {
            for (VarDeclarationStatement declaration : Table.program.variables) {
                declaration.emit(instructions);
            }
        }
        instructions.add(JumpInstruction.getInstruction(function.entry));
        instructions.add(function.entry);
        function.statements.emit(instructions);
        instructions.add(JumpInstruction.getInstruction(function.exit));
        instructions.add(function.exit);
        blocks = new ArrayList<>();
        for (int i = 0, j; i < instructions.size(); i = j) {
            if (!(instructions.get(i) instanceof LabelInstruction)) {
                j = i + 1;
            } else {
                LabelInstruction label = (LabelInstruction)instructions.get(i);
                Block block = label.block = addBlock(label.name, label);
                for (j = i + 1; j < instructions.size(); ++j) {
                    if (instructions.get(j) instanceof LabelInstruction) {
                        break;
                    }
                    block.instructions.add(instructions.get(j));
                    if (instructions.get(j) instanceof ControlFlowInstruction) {
                        break;
                    }
                }
            }
        }
        for (Block block : blocks) {
            if (block.name.equals("enter")) {
                enter = block;
            } else if (block.name.equals("entry")) {
                entry = block;
            } else if (block.name.equals("exit")) {
                exit = block;
            }
        }
    }
    public int getRegisters() {
        return mapping.size();
    }
    public int getOffset(VRegister reg) {
        return mapping.get(reg) * 8 + 8;
    }
    public String getGlobalName(String name) {
        return "GLOBAL_V_" + name;
    }
    public String getMemory(VRegister reg) {
        if (reg instanceof GlobalRegister) {
            return String.format("qword [rel %s]", getGlobalName(((GlobalRegister)reg).symbol.name));
        } else {
            return String.format("qword [rsp+%d]", getOffset(reg));
        }
    }

    public void addRegister(VRegister reg) {
        if (mapping.containsKey(reg)) {
            return;
        }
        mapping.put(reg, mapping.size());
    }
    public void calcRegisters() {
        mapping = new HashMap<>();
        for (int i = 0; i < function.parameters.size(); i++) {
            VRegister preg = function.parameters.get(i).register;
            addRegister(preg);
        }
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            for (int j = 0; j < block.instructions.size(); j++) {
                Instruction instruction = block.instructions.get(j);
                if (instruction instanceof BinaryInstruction) {
                    BinaryInstruction instr = (BinaryInstruction)instruction;
                    if (instr.target instanceof VRegister) {
                        addRegister((VRegister) (instr.target));
                    }
                    if (instr.source1 instanceof VRegister) {
                        addRegister((VRegister) (instr.source1));
                    }
                    if (instr.source2 instanceof VRegister) {
                        addRegister((VRegister) (instr.source2));
                    }
                }
                if (instruction instanceof UnaryInstruction) {
                    UnaryInstruction instr = (UnaryInstruction)instruction;
                    if (instr.target instanceof VRegister) {
                        addRegister((VRegister)(instr.target));
                    }
                    if (instr.source instanceof VRegister) {
                        addRegister((VRegister)(instr.source));
                    }
                }
                if (instruction instanceof ControlFlowInstruction) {
                    if (instruction instanceof BranchInstruction) {
                        BranchInstruction instr = (BranchInstruction)instruction;
                        if (instr.condition instanceof VRegister) {
                            addRegister((VRegister)(instr.condition));
                        }
                    }
                }
                if (instruction instanceof FunctionInstruction) {
                    if (instruction instanceof CallInstruction) {
                        CallInstruction instr = (CallInstruction)instruction;
                        if (instr.target instanceof VRegister) {
                            addRegister((VRegister)(instr.target));
                        }
                        for (int k = 0; k < instr.parameters.size(); k++) {
                            Operand operand = instr.parameters.get(k);
                            if (operand instanceof VRegister) {
                                addRegister((VRegister)operand);
                            }
                        }
                    }
                    if (instruction instanceof ReturnInstruction) {
                        ReturnInstruction instr = (ReturnInstruction)instruction;
                        if (instr.source instanceof VRegister) {
                            addRegister((VRegister)(instr.source));
                        }
                    }
                }
                if (instruction instanceof MemoryInstruction) {
                    if (instruction instanceof AllocateInstruction) {
                        AllocateInstruction instr = (AllocateInstruction)instruction;
                        if (instr.target instanceof VRegister) {
                            addRegister((VRegister)(instr.target));
                        }
                        if (instr.size instanceof VRegister) {
                            addRegister((VRegister)(instr.size));
                        }
                    }
                    if (instruction instanceof LoadInstruction) {
                        LoadInstruction instr = (LoadInstruction)instruction;
                        if (instr.target instanceof VRegister) {
                            addRegister((VRegister)(instr.target));
                        }
                    }
                    if (instruction instanceof MoveInstruction) {
                        MoveInstruction instr = (MoveInstruction)instruction;
                        addRegister(instr.target);
                        if (instr.source instanceof VRegister) {
                            addRegister((VRegister)(instr.source));
                        }
                    }
                    if (instruction instanceof StoreInstruction) {
                        StoreInstruction instr = (StoreInstruction)instruction;
                        if (instr.source instanceof VRegister) {
                            addRegister((VRegister)(instr.source));
                        }
                    }
                }
            }
        }
    }
    public Block addBlock(String name, LabelInstruction label) {
        Block block = new Block(function, name, blocks.size(), label);
        blocks.add(block);
        return block;
    }

    public class Frame {
        public int size;
        public Map<VRegister, Integer> temporary, parameter;

        public Frame() {
            size = 0;
            temporary = new HashMap<>();
            parameter = new HashMap<>();
        }

        public int getOffset(Operand operand) {
            if (operand instanceof VRegister) {
                if (temporary.containsKey(operand)) {
                    return temporary.get(operand);
                }
                if (parameter.containsKey(operand)) {
                    return parameter.get(operand);
                }
            }
            throw new InternalError();
        }
    }

}
