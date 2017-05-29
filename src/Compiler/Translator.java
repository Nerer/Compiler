package Compiler;

import Compiler.IR.*;
import Compiler.IR.ArithmeticIR.ArithmeticInstruction;
import Compiler.IR.ArithmeticIR.Binary.*;
import Compiler.IR.ArithmeticIR.Unary.BitNotInstruction;
import Compiler.IR.ArithmeticIR.Unary.SelfMinusInstruction;
import Compiler.IR.ArithmeticIR.Unary.UnaryInstruction;
import Compiler.IR.ControlFlowIR.BranchInstruction;
import Compiler.IR.ControlFlowIR.ControlFlowInstruction;
import Compiler.IR.ControlFlowIR.JumpInstruction;
import Compiler.IR.FunctionIR.CallInstruction;
import Compiler.IR.FunctionIR.FunctionInstruction;
import Compiler.IR.FunctionIR.ReturnInstruction;
import Compiler.IR.MemoryIR.*;
import Compiler.Table.Table;
import Compiler.Type.FunctionType;
import java.io.PrintStream;

/**
 * Created by SteinerT on 2017/5/27.
 */

public class Translator {
    public PrintStream output;

    public Translator(PrintStream output) {
        this.output = output;
    }

    String getFunctionName(FunctionType function) {
        if (function.name.equals("main") || function.name.startsWith("Mx_builtin")) {
            return function.name;
        } else {
            return String.format("%s", function.name);
        }
    }

    String getBlockName(Block block) {
        return String.format("____%s_%d____%s", block.function.name, block.identity, block.name);
    }

    void load(Graph graph, String reg, Operand operand) {
        if (operand instanceof VRegister) {
            if (operand instanceof GlobalRegister) {
                output.printf("\tmov %s %s\n", reg, String.format("[rel %s]", ((GlobalRegister)operand).symbol.name));
            } else {
                if (operand instanceof StringRegister) {
                    output.printf("\tmov %s, %s\n", reg, ((StringRegister)operand).message());
                } else {
                    output.printf("\tmov %s, %s\n", reg, graph.getMemory((VRegister) operand));
                }
            }
        }
        if (operand instanceof Immediate) {
            output.printf("\tmov %s, %s\n", reg, ((Immediate)operand).toString());
        }
        if (operand instanceof Address) {
            output.printf("\tmov %s, %s\n", reg, graph.getMemory(((Address)operand).base));
            output.printf("\tmov %s, qword[%s+%s]\n", reg, reg, ((Address)operand).offset);
        }

    }

    public void translate() {
        output.printf("default rel\n");
        output.printf("\n");
        output.printf("extern printf, malloc, strcpy, scanf, strlen, sscanf, sprintf, memcpy, strcmp, puts\n");
        for (VRegister register : Table.registerTable.registers) {
            if (register instanceof GlobalRegister) {
                output.printf("global %s\n", ((GlobalRegister)register).symbol.name);
            }
        }

        output.printf("global main\n");


        output.printf("\nSECTION .text\n");
        output.printf("\n");
        output.printf("\n%s\n", BuiltinTranslator.getBuiltinFunction());

        for (FunctionType function : Table.program.functions) {
            int nowRsp = 0;
            Graph graph = function.graph;
            output.printf("%s:\n", getFunctionName(function));
            output.printf("\tpush rbp\n");
            nowRsp += 8;
            output.printf("\tmov rbp, rsp\n");
            output.printf("\tsub rsp, %d\n", graph.getRegisters() * 8 + 16);
            nowRsp += graph.getRegisters() * 8 + 16;
            Boolean hasReturn = false;
            for (int i = 0; i < graph.blocks.size(); i++) {
                Block block = graph.blocks.get(i);
                output.printf("\n%s:\n", getBlockName(block));
                for (Instruction instruction : block.instructions) {
                    if (instruction instanceof ArithmeticInstruction) {
                        if (instruction instanceof BinaryInstruction) {
                            BinaryInstruction instr = (BinaryInstruction)instruction;
                            /*if (instr.source1 instanceof VRegister) {
                                output.printf("\tmov %s %s\n", NASMRegister.r10, graph.getMemory((VRegister) instr.source1));
                            }
                            if (instr.source1 instanceof Immediate) {
                                output.printf("\tmov %s %s\n", NASMRegister.r10, ((Immediate)instr.source1).toString());
                            }
                            if (instr.source1 instanceof Address) {
                                output.printf("\tmov %s %s\n", NASMRegister.r10, graph.getMemory(((Address)instr.source1).base));
                                output.printf("\tmov %s [%s+%s]\n", NASMRegister.r10, NASMRegister.r10, ((Address)instr.source1).offset);
                            }*/
                            load(graph, NASMRegister.r10, instr.source1);
                            /*String source2;
                            source2 = "";
                            if (instr.source2 instanceof VRegister) {
                                source2 = graph.getMemory((VRegister)instr.source2);
                            }
                            if (instr.source2 instanceof Immediate) {
                                source2 = ((Immediate)instr.source2).toString();
                            }
                            if (instr.source2 instanceof Address) {
                                output.printf("\tmov %s %s\n", NASMRegister.r11, graph.getMemory(((Address)instr.source2).base));
                                source2 = "[" + NASMRegister.r11 + "+" + ((Address)instr.source2).offset + "]";
                            }*/
                            load(graph, NASMRegister.r11, instr.source2);
                            if (instr instanceof AddInstruction) {
                                output.printf("\tadd %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                            }
                            if (instr instanceof BitAndInstruction) {
                                output.printf("\tadd %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                            }
                            if (instr instanceof BitLeftShiftInstruction) {
                                output.printf("\tmov %s, %s\n",  NASMRegister.cl, NASMRegister.r11);
                                output.printf("\tsal %s, %s\n", NASMRegister.r10, NASMRegister.cl);
                            }
                            if (instr instanceof BitOrInstruction) {
                                output.printf("\tor %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                            }
                            if (instr instanceof BitRightShiftInstruction) {
                                output.printf("\tmov %s, %s\n",  NASMRegister.cl, NASMRegister.r11);
                                output.printf("\tsar %s, %s\n", NASMRegister.r10, NASMRegister.cl);
                            }
                            if (instr instanceof BitXorInstruction) {
                                output.printf("\txor %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                            }
                            if (instr instanceof DivideInstruction) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rax, NASMRegister.r10);
                                output.printf("\tcqo\n");
                                output.printf("\tmov %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                                output.printf("\tidiv %s\n", NASMRegister.r10);
                                output.printf("\tmov %s, %s\n", NASMRegister.r10, NASMRegister.rax);
                            }
                            if (instr instanceof EqualInstruction) {
                                output.printf("\tcmp %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                                output.printf("\tsete %s\n", NASMRegister.r10);
                            }
                            if (instr instanceof GeInstruction) {
                                output.printf("\tcmp %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                                output.printf("\tsetge %s\n", NASMRegister.r10);
                            }
                            if (instr instanceof GreaterInstruction) {
                                output.printf("\tcmp %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                                output.printf("\tsetg %s\n", NASMRegister.r10);
                            }
                            if (instr instanceof LeInstruction) {
                                output.printf("\tcmp %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                                output.printf("\tsetle %s\n", NASMRegister.r10);
                            }
                            if (instr instanceof LessInstruction) {
                                output.printf("\tcmp %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                                output.printf("\tsetl %s\n", NASMRegister.r10);
                            }
                            if (instr instanceof LogicalAndInstruction) {
                                output.printf("\tand %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                            }
                            if (instr instanceof LogicalOrInstruction) {
                                output.printf("\tor %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                            }
                            if (instr instanceof MinusInstruction) {
                                output.printf("\tsub %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                            }
                            if (instr instanceof ModInstruction) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rax, NASMRegister.r10);
                                output.printf("\tcqo\n");
                                output.printf("\tmov %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                                output.printf("\tidiv %s\n", NASMRegister.r10);
                                output.printf("\tmov %s, %s\n", NASMRegister.r10, NASMRegister.rdx);
                            }
                            if (instr instanceof MulInstruction) {
                                output.printf("\timul %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                            }
                            if (instr instanceof NotEqualInstruction) {
                                output.printf("\tcmp %s, %s\n", NASMRegister.r10, NASMRegister.r11);
                                output.printf("\tsetne %s\n", NASMRegister.r10);
                            }
                            output.printf("\tmov %s, %s\n", graph.getMemory((VRegister)instr.target), NASMRegister.r10);
                        }
                        if (instruction instanceof UnaryInstruction) {
                            UnaryInstruction instr = (UnaryInstruction)instruction;
                            /*String source = "";
                            if (instr.source instanceof VRegister) {
                                source = graph.getMemory((VRegister)instr.source);
                            }
                            if (instr.source instanceof Immediate) {
                                source = ((Immediate)instr.source).toString();
                            }
                            if (instr.source instanceof Address) {
                                output.printf("\tmov %s %s\n", NASMRegister.r11, graph.getMemory(((Address)instr.source).base));
                                source = "[" + NASMRegister.r11 + "+" + ((Address)instr.source).offset + "]";
                            }
                            */
                            load(graph, NASMRegister.r10, instr.source);

                            if (instr instanceof BitNotInstruction) {
                                output.printf("\tnot %s\n", NASMRegister.r10);
                            }
                            if (instr instanceof SelfMinusInstruction) {
                                output.printf("\tneg %s, %s\n", NASMRegister.r10);
                            }
                            output.printf("\tmov %s, %s\n", graph.getMemory((VRegister)instr.target), NASMRegister.r10);
                        }
                    }
                    if (instruction instanceof ControlFlowInstruction) {
                        ControlFlowInstruction instr = (ControlFlowInstruction)instruction;
                        if (instr instanceof BranchInstruction) {
                            //String condition = "";
                            BranchInstruction branch = (BranchInstruction)instr;
                            /*if (branch.condition instanceof VRegister) {
                                condition = graph.getMemory((VRegister)branch.condition);
                            }
                            if (branch.condition instanceof Immediate) {
                                condition = ((Immediate)branch.condition).toString();
                            }
                            if (branch.condition instanceof Address) {
                                output.printf("\tmov %s %s\n", NASMRegister.r11, graph.getMemory(((Address)branch.condition).base));
                                condition = "[" + NASMRegister.r11 + "+" + ((Address)branch.condition).offset + "]";
                            }*/
                            load(graph, NASMRegister.r10, branch.condition);
                            output.printf("\tcmp %s 0\n", NASMRegister.r10);
                            output.printf("\tjz %s\n", getBlockName(branch.falseTo.block));
                            if (i + 1 == graph.blocks.size() || graph.blocks.get(i + 1) != branch.trueTo.block) {
                                output.printf("\tjmp %s\n", getBlockName(branch.trueTo.block));
                            }
                        }
                        if (instr instanceof JumpInstruction) {
                            JumpInstruction jump = (JumpInstruction)instr;
                            if (i + 1 == graph.blocks.size() || graph.blocks.get(i + 1) != jump.to.block) {
                                output.printf("\tjmp %s\n", getBlockName(jump.to.block));
                            }
                        }
                    }

                    if (instruction instanceof FunctionInstruction) {
                        if (instruction instanceof CallInstruction) {
                            CallInstruction call = (CallInstruction)instruction;
                            FunctionType callFunction = call.function;
                            if ((nowRsp + 8) % 16 != 0) {
                                output.printf("\tsub %s 8\n", NASMRegister.rsp);
                                nowRsp += 8;
                            }
                            if (callFunction.name.startsWith("Mx_builtin_")) {
                                if (callFunction.parameters.size() >= 1) {
                                    load(graph, NASMRegister.rdi, call.parameters.get(0));
                                }
                                if (callFunction.parameters.size() >= 2) {
                                    load(graph, NASMRegister.rsi, call.parameters.get(1));
                                }
                                if (callFunction.parameters.size() >= 3) {
                                    load(graph, NASMRegister.rdx, call.parameters.get(2));
                                }
                                if (callFunction.parameters.size() >= 4) {
                                    load(graph, NASMRegister.rcx, call.parameters.get(3));
                                }
                            } else {
                                for (int p = 0; p < callFunction.parameters.size(); p++) {
                                    load(graph, NASMRegister.r10, call.parameters.get(p));
                                    int offset = callFunction.graph.getOffset(callFunction.parameters.get(p).register);
                                    int fuck = callFunction.graph.getRegisters() * 8 + 24;
                                    output.printf("\tmov %s, %s\n", String.format("[rsp-%d]", fuck - offset), NASMRegister.r10);
                                }
                            }
                            output.printf("\tcall %s\n", getFunctionName(callFunction));
                            if (call.target != null) {
                                output.printf("\tmov %s, %s\n", graph.getMemory(call.target), NASMRegister.rax);
                            }
                            output.printf("\txor %s, %s\n", NASMRegister.rax, NASMRegister.rax);
                        }

                        if (instruction instanceof ReturnInstruction) {
                            hasReturn = true;
                            ReturnInstruction ret = (ReturnInstruction)instruction;
                            load(graph, NASMRegister.rax, ret.source);
                            output.printf("\tjmp %s\n", getBlockName(graph.exit));
                        }
                    }

                    if (instruction instanceof MemoryInstruction) {
                        if (instruction instanceof AllocateInstruction) {
                            load(graph, NASMRegister.rdi, ((AllocateInstruction)instruction).size);
                            if ((nowRsp + 8) % 16 != 0) {
                                output.printf("\tsub rsp, 8\n");
                                nowRsp += 8;
                            }
                            output.printf("\tcall melloc\n");
                            output.printf("\tmov %s, %s\n", graph.getMemory(((AllocateInstruction)instruction).target), NASMRegister.rax);
                        }
                        if (instruction instanceof LoadInstruction) {
                            LoadInstruction ld = (LoadInstruction)instruction;

                            load(graph, NASMRegister.r10, ld.address);
                            output.printf("\tmov %s, %s\n", graph.getMemory(ld.target), NASMRegister.r10);
                        }
                        if (instruction instanceof MoveInstruction) {
                            MoveInstruction mov = (MoveInstruction)instruction;
                            load(graph, NASMRegister.r10, mov.source);
                            output.printf("\tmov %s, %s\n", graph.getMemory(mov.target), NASMRegister.r10);
                        }
                        if (instruction instanceof StoreInstruction) {
                            StoreInstruction store = (StoreInstruction)instruction;
                            load(graph, NASMRegister.r10, store.source);
                            load(graph, NASMRegister.r11, store.address.base);
                            String address = "[" + NASMRegister.r11 + "+" + (store.address.offset).toString() + "]";
                            output.printf("\tmov %s, %s\n", address, NASMRegister.r11);
                        }
                    }
                }
            }
          /*  if (graph.function.name.equals("main")) {
                if (!hasReturn) {
                    output.printf("\txor %s %s\n", NASMRegister.rax, NASMRegister.rax);
                    output.printf("\tmov %s %s\n", NASMRegister.rax, "0");
                }
            }*/

            output.printf("\tadd rsp, %d\n", nowRsp - 8);
            output.printf("\tpop rbp\n");
            output.printf("\tret\n");
        }

        output.printf("%s\n", BuiltinTranslator.getDataSection());
        output.printf("%s\n", BuiltinTranslator.getBssSection());

    }
}


