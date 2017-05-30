package Compiler;

import Compiler.Allocator.Allocator;
import Compiler.Allocator.PRegister;
import Compiler.Expression.FunctionCallExpression;
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
import java.util.*;

/**
 * Created by SteinerT on 2017/5/30.
 */
public class SuperTranslator {
    public PrintStream output;
    Graph graph;
    Allocator allocator;
    Set<PRegister> callee;
    public SuperTranslator(PrintStream output) {
        this.output = output;
        callee = new HashSet<>();
        callee.add(NASMRegister.rbx);
        callee.add(NASMRegister.rbp);
        callee.add(NASMRegister.r12);
        callee.add(NASMRegister.r13);
        callee.add(NASMRegister.r14);
        callee.add(NASMRegister.r15);
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

    String getGlobalName(String name) {
        return "GLOBAL_V_" + name;
    }

    public PRegister superloadToRead(Operand from, PRegister to) {
        if (from instanceof VRegister) {
            if (from instanceof VarRegister) {
                if (from instanceof GlobalRegister) {
                    output.printf("\tmov %s, qword[rel %s]\n", to, getGlobalName(((GlobalRegister)from).symbol.name));
                } else if (from instanceof ParameterRegister){
                    output.printf("\tmov %s, qword[rsp + %d]\n", to, graph.frame.getOffset(from));
                } else if (from instanceof TempRegister) {
                    PRegister register = allocator.mapping.get(from);
                    if (register != null) {
                        output.printf("\tmov %s, %s\n", to, register);
                    } else {
                        output.printf("\tmov %s, qword[rsp + %d]\n", to, graph.frame.getOffset(from));
                    }
                }
            } else {
                if (from instanceof StringRegister) {
                    output.printf("\tmov %s, %s\n", to, ((StringRegister) from).message());
                }
            }
        } else if (from instanceof Address) {
            throw new InternalError();
        } else if (from instanceof Immediate) {
            output.printf("\tmov %s, %s\n", to, from);
        } else {
            throw new InternalError();
        }

        return to;
    }

    public PRegister loadToRead(Operand from, PRegister to) {
        if (from instanceof VRegister) {
            if (from instanceof VarRegister) {
                if (from instanceof GlobalRegister) {
                    output.printf("\tmov %s, qword[rel %s]\n", to, getGlobalName(((GlobalRegister)from).symbol.name));
                } else if (from instanceof ParameterRegister){
                    /*if (graph != null) {
                        System.out.println("ffffff");
                    }*/
                    output.printf("\tmov %s, qword[rsp + %d]\n", to, graph.frame.getOffset(from));
                } else if (from instanceof TempRegister) {
                    PRegister register = allocator.mapping.get(from);
                    if (register != null) {
                        return register;
                    } else {
                        output.printf("\tmov %s, qword[rsp + %d]\n", to, graph.frame.getOffset(from));
                    }
                }
            } else {
                if (from instanceof StringRegister) {
                    output.printf("\tmov %s, %s\n", to, ((StringRegister) from).message());
                }
            }
        } else if (from instanceof Address) {
            throw new InternalError();
        } else if (from instanceof Immediate) {
            output.printf("\tmov %s, %s\n", to, from);
        } else {
            throw new InternalError();
        }

        return to;
    }

    public PRegister loadToWrite(VRegister from, PRegister to) {
        if (from instanceof TempRegister) {
            PRegister register = allocator.mapping.get(from);
            if (register != null) {
                return register;
            }
        }
        return to;
    }

    public void store(VRegister from, PRegister to) {
        if (from instanceof VarRegister) {
            if (from instanceof GlobalRegister) {
                output.printf("\tmov qword[rel %s], %s\n", getGlobalName(((GlobalRegister)from).symbol.name), to);
            } else if (from instanceof ParameterRegister) {
                output.printf("\tmov qword[rsp + %d], %s\n", graph.frame.getOffset(from), to);
            } else if (from instanceof TempRegister) {
                PRegister register = allocator.mapping.get(from);
                if (register == null) {
                    output.printf("\tmov qword[rsp + %d], %s\n", graph.frame.getOffset(from), to);
                }
            } else {
                throw new InternalError();
            }
        } else if (from instanceof StringRegister) {
                throw new InternalError();
        } else {
            throw new InternalError();
        }
    }

    public void move(Operand from, PRegister to) {
        if (from instanceof VRegister) {
            if (from instanceof VarRegister) {
                if (from instanceof GlobalRegister) {
                    output.printf("\tmov %s, qword[rel %s]\n", to, getGlobalName(((GlobalRegister)from).symbol.name));
                } else if (from instanceof ParameterRegister) {
                    output.printf("\tmov %s, qword[rsp + %d]\n", to, graph.frame.getOffset(from));
                } else if (from instanceof TempRegister) {
                    PRegister register = allocator.mapping.get(from);
                    if (register != null) {
                        if (register != to) {
                            output.printf("\tmov %s, %s\n", to, register);
                        }
                    } else {
                        output.printf("\tmov %s, qword[rsp + %d]\n", to, graph.frame.getOffset(from));
                    }
                } else {
                    throw new InternalError();
                }
            } else {
                if (from instanceof StringRegister) {
                    output.printf("\tmov %s, %s\n", to, ((StringRegister) from).message());
                } else {
                    throw new InternalError();
                }
            }
        } else {
            if (from instanceof Address) {
                throw new InternalError();
            } else if (from instanceof Immediate) {
                output.printf("\tmov %s, %s\n", to, from);
            } else {
                throw new InternalError();
            }
        }
    }
    private void move(PRegister from, VRegister to) {
        if (to instanceof VarRegister) {
            if (to instanceof GlobalRegister) {
                output.printf("\tmov qword[rel %s], %s\n", getGlobalName(((GlobalRegister)to).symbol.name), from);
            } else if (to instanceof ParameterRegister) {
                output.printf("\tmov qword[rsp+%d], %s\n", graph.frame.getOffset(to), from);
            } else if (to instanceof TempRegister) {
                PRegister register = allocator.mapping.get(to);
                if (register != null) {
                    if (register != from) {
                        output.printf("\tmov %s, %s\n", register, from);
                    }
                } else {
                    output.printf("\tmov qword[%s+%d], %s\n", NASMRegister.rsp, graph.frame.getOffset(to), from);
                }
            } else {
                throw new InternalError();
            }
        } else if (to instanceof StringRegister) {
            throw new InternalError();
        } else {
            throw new InternalError();
        }
    }

    public void translate() {
        output.printf("default rel\n");
        output.printf("\n");
        output.printf("extern printf, malloc, strcpy, scanf, strlen, sscanf, sprintf, memcpy, strcmp, puts\n");
        for (VRegister register : Table.registerTable.registers) {
            if (register instanceof GlobalRegister) {
                output.printf("global %s\n", getGlobalName(((GlobalRegister)register).symbol.name));
            }
        }

        output.printf("global main\n");


        output.printf("\nSECTION .text\n");
        output.printf("\n");
        output.printf("\n%s\n", BuiltinTranslator.getBuiltinFunction());

        for (FunctionType function : Table.program.functions) {
            this.allocator = function.allocator;
            translate(function.graph);
        }

        output.printf("%s\n", BuiltinTranslator.getDataSection());
        output.printf("%s\n", BuiltinTranslator.getBssSection());
    }

    public void translate(Graph graph) {
        int nowRsp = 0;
        this.graph = graph;
        Set<PRegister> regs = allocator.getUsedPhysicalRegisters();
        List<PRegister> callerRegs = new ArrayList<>();
        List<PRegister> calleeRegs = new ArrayList<>();
        for (PRegister reg : regs) {
            if (!callee.contains(reg)) {
                callerRegs.add(reg);
            } else {
                calleeRegs.add(reg);
            }
        }

        output.printf("%s:\n", getFunctionName(graph.function));
        output.printf("\tpush rbp\n");
        nowRsp += 8;
        output.printf("\tmov rbp, rsp\n");
        output.printf("\tsub rsp, %d\n", graph.frame.size);


        nowRsp += graph.frame.size;
        System.out.println(nowRsp);

        if (!graph.function.name.equals("main")) {
            for (int kk = 0; kk < (int)calleeRegs.size(); kk++) {
                output.printf("\tmov qword[rsp + %d], %s\n", calleeRegs.get(kk).identity * 8, calleeRegs.get(kk));
            }
        }

        graph.refresh();
        Instruction reserved = null;
        for (int k = 0; k < graph.blocks.size(); k++) {
            Block block = graph.blocks.get(k);
            output.printf("\n%s:\n", getBlockName(block));
            for (int l = 0; l < block.instructions.size(); l++) {
                Instruction instruction = block.instructions.get(l);
                //System.out.println(instruction);
                if (instruction instanceof ArithmeticInstruction) {
                    if (instruction instanceof BinaryInstruction) {
                        BinaryInstruction i = (BinaryInstruction)instruction;
                        if (l + 1 < block.instructions.size() && block.instructions.get(l + 1) instanceof BranchInstruction) {
                            BranchInstruction j = (BranchInstruction)block.instructions.get(l + 1);
                            if (j.condition == i.target && !block.liveliness.liveOut.contains(i.target)) {
                                reserved = instruction;
                                continue;
                            }
                        }
                        if (i.source2 instanceof Immediate) {
                            PRegister a = superloadToRead(i.source1, NASMRegister.tmp1);
                            PRegister c = loadToRead(i.target, NASMRegister.tmp2);
                            if (i instanceof AddInstruction) {
                                output.printf("\tadd %s, %s\n", a, i.source2);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof BitAndInstruction) {
                                output.printf("\tand %s, %s\n", a, i.source2);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof BitLeftShiftInstruction) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rcx, i.source2);
                                output.printf("\tshl %s, %s\n", a, NASMRegister.cl);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof BitOrInstruction) {
                                output.printf("\tor %s, %s\n", a, i.source2);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof BitRightShiftInstruction) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rcx, i.source2);
                                output.printf("\tsar %s, %s\n", a, NASMRegister.cl);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof BitXorInstruction) {
                                output.printf("\txor %s, %s\n", a, i.source2);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof DivideInstruction) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rax, a);
                                output.printf("\txor %s, %s\n", NASMRegister.rdx, NASMRegister.rdx);
                                output.printf("\tcqo\n");
                                output.printf("\tmov %s, %s\n", a, i.source2);
                                output.printf("\tidiv %s\n", a);
                                output.printf("\tmov %s, %s\n", c, NASMRegister.rax);
                            }
                            if (i instanceof EqualInstruction) {
                                output.printf("\tcmp %s, %s\n", a, i.source2);
                                output.printf("\tsete %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);
                            }
                            if (i instanceof GeInstruction) {
                                output.printf("\tcmp %s, %s\n", a, i.source2);
                                output.printf("\tsetge %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);                                }
                            if (i instanceof GreaterInstruction) {
                                output.printf("\tcmp %s, %s\n", a, i.source2);
                                output.printf("\tsetg %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);
                            }
                            if (i instanceof LeInstruction) {
                                output.printf("\tcmp %s, %s\n", a, i.source2);
                                output.printf("\tsetle %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);
                            }
                            if (i instanceof LessInstruction) {
                                output.printf("\tcmp %s, %s\n", a, i.source2);
                                output.printf("\tsetl %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);
                            }
                            if (i instanceof LogicalAndInstruction) {
                                output.printf("\tand %s, %s\n", a, i.source2);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof LogicalOrInstruction) {
                                output.printf("\tor %s, %s\n", a, i.source2);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof MinusInstruction) {
                                output.printf("\tsub %s, %s\n", a, i.source2);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof ModInstruction) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rax, a);
                                output.printf("\txor %s, %s\n", NASMRegister.rdx, NASMRegister.rdx);
                                output.printf("\tcqo\n");
                                output.printf("\tmov %s, %s\n", a, i.source2);
                                output.printf("\tidiv %s\n", a);
                                output.printf("\tmov %s, %s\n", c, NASMRegister.rdx);
                            }
                            if (i instanceof MulInstruction) {
                                output.printf("\timul %s, %s\n", a, i.source2);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof NotEqualInstruction) {
                                output.printf("\tcmp %s, %s\n", a, i.source2);
                                output.printf("\tsetne %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);
                            }
                            store(i.target, c);
                        } else {
                            PRegister a = superloadToRead(i.source1, NASMRegister.tmp1);
                            PRegister b = loadToRead(i.source2, NASMRegister.tmp2);
                            PRegister c = loadToWrite(i.target, NASMRegister.tmp2);
                            if (i instanceof AddInstruction) {
                                output.printf("\tadd %s, %s\n", a, b);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof BitAndInstruction) {
                                output.printf("\tand %s, %s\n", a, b);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof BitLeftShiftInstruction) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rcx, b);
                                output.printf("\tshl %s, %s\n", a, NASMRegister.cl);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof BitOrInstruction) {
                                output.printf("\tor %s, %s\n", a, b);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof BitRightShiftInstruction) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rcx, b);
                                output.printf("\tsar %s, %s\n", a, NASMRegister.cl);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof BitXorInstruction) {
                                output.printf("\txor %s, %s\n", a, b);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof DivideInstruction) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rax, a);
                                output.printf("\txor %s, %s\n", NASMRegister.rdx, NASMRegister.rdx);
                                output.printf("\tcqo\n");
                                output.printf("\tmov %s, %s\n", a, b);
                                output.printf("\tidiv %s\n", a);
                                output.printf("\tmov %s, %s\n", c, NASMRegister.rax);
                            }
                            if (i instanceof EqualInstruction) {
                                output.printf("\tcmp %s, %s\n", a, b);
                                output.printf("\tsete %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);
                            }
                            if (i instanceof GeInstruction) {
                                output.printf("\tcmp %s, %s\n", a, b);
                                output.printf("\tsetge %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);                                }
                            if (i instanceof GreaterInstruction) {
                                output.printf("\tcmp %s, %s\n", a, b);
                                output.printf("\tsetg %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);
                            }
                            if (i instanceof LeInstruction) {
                                output.printf("\tcmp %s, %s\n", a, b);
                                output.printf("\tsetle %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);
                            }
                            if (i instanceof LessInstruction) {
                                output.printf("\tcmp %s, %s\n", a, b);
                                output.printf("\tsetl %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);
                            }
                            if (i instanceof LogicalAndInstruction) {
                                output.printf("\tand %s, %s\n", a, b);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof LogicalOrInstruction) {
                                output.printf("\tor %s, %s\n", a, b);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof MinusInstruction) {
                                output.printf("\tsub %s, %s\n", a, b);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof ModInstruction) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rax, a);
                                output.printf("\txor %s, %s\n", NASMRegister.rdx, NASMRegister.rdx);
                                output.printf("\tcqo\n");
                                output.printf("\tmov %s, %s\n", a, b);
                                output.printf("\tidiv %s\n", a);
                                output.printf("\tmov %s, %s\n", c, NASMRegister.rdx);
                            }
                            if (i instanceof MulInstruction) {
                                output.printf("\timul %s, %s\n", a, b);
                                output.printf("\tmov %s, %s\n", c, a);
                            }
                            if (i instanceof NotEqualInstruction) {
                                output.printf("\tcmp %s, %s\n", a, b);
                                output.printf("\tsetne %s\n", NASMRegister.al);
                                output.printf("\tmovzx %s, %s\n", c, NASMRegister.al);
                            }
                            store(i.target, c);
                        }
                    }

                    if (instruction instanceof UnaryInstruction) {
                        UnaryInstruction i = (UnaryInstruction)instruction;
                        PRegister a = superloadToRead(i.source, NASMRegister.tmp1);
                        PRegister b = loadToWrite(i.target, NASMRegister.tmp2);
                        if (i instanceof BitNotInstruction) {
                            output.printf("\tnot %s\n", a);
                            output.printf("\tmov %s, %s\n", b, a);
                        }
                        if (i instanceof SelfMinusInstruction) {
                            output.printf("\tneg %s\n", a);
                            output.printf("\tmov %s, %s\n", b, a);
                        }
                        store(i.target, b);
                    }
                }
                if (instruction instanceof ControlFlowInstruction) {
                    ControlFlowInstruction i = (ControlFlowInstruction)instruction;
                    if (i instanceof BranchInstruction) {
                        BranchInstruction branch = (BranchInstruction)i;
                        PRegister a = loadToRead(branch.condition, NASMRegister.tmp1);
                        output.printf("\tcmp %s, 0\n", a);
                        output.printf("\tjz %s\n", getBlockName(branch.falseTo.block));
                        if (k + 1 == graph.blocks.size() || graph.blocks.get(k + 1) != branch.trueTo.block) {
                            output.printf("\tjmp %s\n", getBlockName(branch.trueTo.block));
                        }
                    }
                    if (i instanceof JumpInstruction) {
                        JumpInstruction jump = (JumpInstruction)i;
                        if (k + 1 == graph.blocks.size() || graph.blocks.get(k + 1) != jump.to.block) {
                            output.printf("\tjmp %s\n", getBlockName(jump.to.block));
                        }
                    }
                }

                if (instruction instanceof FunctionInstruction) {
                    FunctionInstruction i = (FunctionInstruction)instruction;
                    if (i instanceof CallInstruction) {
                        CallInstruction call = (CallInstruction)i;
                        FunctionType callFunction = call.function;


                        if (callFunction.name.startsWith("Mx_builtin_")) {
                            if (callFunction.parameters.size() >= 1) {
                                move(call.parameters.get(0), NASMRegister.tmp1);
                            }
                            if (callFunction.parameters.size() >= 2) {
                                move(call.parameters.get(1), NASMRegister.tmp2);
                            }

                            if (callFunction.parameters.size() >= 3) {
                                move(call.parameters.get(2), NASMRegister.rdx);
                            }

                            for (int kk = 0; kk < (int)callerRegs.size(); kk++) {
                                output.printf("\tmov qword[rsp+%d], %s\n", callerRegs.get(kk).identity * 8, callerRegs.get(kk));
                            }
                            if (callFunction.parameters.size() >= 1) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rdi, NASMRegister.tmp1);
                            }
                            if (callFunction.parameters.size() >= 2) {
                                output.printf("\tmov %s, %s\n", NASMRegister.rsi, NASMRegister.tmp2);
                            }

                            if ((nowRsp + 8) % 16 != 0) {
                                output.printf("\tsub %s, 8\n", NASMRegister.rsp);
                                nowRsp += 8;
                                output.printf("\tcall %s\n", getFunctionName(callFunction));
                                nowRsp -= 8;
                                output.printf("\tadd %s, 8\n", NASMRegister.rsp);
                            } else {
                                output.printf("\tcall %s\n", getFunctionName(callFunction));
                            }
                            for (int kk = 0; kk < (int)callerRegs.size(); kk++) {
                                output.printf("\tmov %s, qword[rsp+%d]\n", callerRegs.get(kk), callerRegs.get(kk).identity * 8);
                            }
                        } else {
                            for (int p = 0; p < callFunction.parameters.size(); p++) {
                                PRegister a = loadToRead(call.parameters.get(p), NASMRegister.tmp1);
                                int offset = callFunction.graph.frame.getOffset(callFunction.parameters.get(p).register);
                                int fuck = callFunction.graph.getRegisters() * 8 + 24 + 8;
                                fuck = callFunction.graph.frame.size + 16;
                                if ((nowRsp + 8) % 16 != 0) {
                                    output.printf("\tmov %s, %s\n", String.format("qword[rsp-%d]", fuck - offset + 8), a);
                                } else {
                                    output.printf("\tmov %s, %s\n", String.format("qword[rsp-%d]", fuck - offset), a);
                                }
                            }
                            for (int kk = 0; kk < (int)callerRegs.size(); kk++) {
                                output.printf("\tmov qword[rsp + %d], %s\n", callerRegs.get(kk).identity * 8, callerRegs.get(kk));
                            }
                            if ((nowRsp + 8) % 16 != 0) {
                                output.printf("\tsub %s, 8\n", NASMRegister.rsp);
                                nowRsp += 8;
                                output.printf("\tcall %s\n", getFunctionName(callFunction));
                                nowRsp -= 8;
                                output.printf("\tadd %s, 8\n", NASMRegister.rsp);
                            }

                            for (int kk = 0; kk < (int)callerRegs.size(); kk++) {
                                output.printf("\tmov %s, qword[rsp+%d]\n", callerRegs.get(kk), callerRegs.get(kk).identity * 8);
                            }
                        }
                        if (call.target != null) {
                            move(NASMRegister.rax, call.target);
                        }
                        output.printf("\txor %s, %s\n", NASMRegister.rax, NASMRegister.rax);
                    }

                    if (i instanceof ReturnInstruction) {
                        ReturnInstruction ret = (ReturnInstruction)i;
                        if (ret.source == null) {
                            output.printf("\txor %s, %s\n", NASMRegister.rax);
                        } else {
                            move(ret.source, NASMRegister.rax);
                        }
                        output.printf("\tjmp %s\n", getBlockName(graph.exit));
                    }
                }

                if (instruction instanceof MemoryInstruction) {
                    MemoryInstruction i = (MemoryInstruction)instruction;
                    if (i instanceof AllocateInstruction) {
                        AllocateInstruction alloc = (AllocateInstruction)i;

                        for (int kk = 0; kk < (int)callerRegs.size(); kk++) {
                            output.printf("\tmov qword[rsp+%d], %s\n", callerRegs.get(kk).identity * 8, callerRegs.get(kk));
                        }
                       // output.printf("\tadd rsp %d\n", callerRegs.size() * 8);
                        move(alloc.size, NASMRegister.rdi);
                       // output.printf("\tsub rsp %d\n", callerRegs.size() * 8);
                        if ((nowRsp + 8) % 16 != 0) {
                            output.printf("\tsub rsp, 8\n");
                            nowRsp += 8;
                            output.printf("\tcall malloc\n");
                            nowRsp -= 8;
                            output.printf("\tadd rsp, 8\n");
                        } else {
                            output.printf("\tcall malloc\n");
                        }
                        for (int kk = 0; kk < (int)callerRegs.size(); kk++) {
                            output.printf("\tmov %s, qword[rsp+%d]\n", callerRegs.get(kk), callerRegs.get(kk).identity * 8);
                        }
                    }

                    if (i instanceof LoadInstruction) {
                        LoadInstruction ld = (LoadInstruction)instruction;
                        PRegister a = loadToRead(ld.address.base, NASMRegister.tmp1);
                        PRegister b = loadToWrite(ld.target, NASMRegister.tmp2);
                        output.printf("\tmov %s, qword[%s+%d]\n", b, a, ld.address.offset.value);
                        store(ld.target, b);
                    }
                    if (i instanceof StoreInstruction) {
                        StoreInstruction st = (StoreInstruction)i;
                        PRegister a = loadToRead(st.address.base, NASMRegister.tmp1);
                        PRegister b = loadToRead(st.source, NASMRegister.tmp2);
                        output.printf("\tmov qword[%s + %d], %s\n", a, st.address.offset.value, b);
                    }

                    if (i instanceof MoveInstruction) {
                        MoveInstruction mv = (MoveInstruction)i;
                        if (mv.source instanceof Immediate) {
                            PRegister a = loadToWrite(mv.target, NASMRegister.tmp1);
                            output.printf("\tmov %s, %s\n", a, mv.source);
                            store(mv.target, a);
                        } else {
                            PRegister a = loadToRead(mv.source, NASMRegister.tmp1);
                            move(a, mv.target);
                        }
                    }
                }
            }
        }

        if (!graph.function.name.equals("main")) {
            for (int kk = 0; kk < (int)calleeRegs.size(); kk++) {
                output.printf("\tmov %s, qword[rsp + %d]\n", calleeRegs.get(kk), calleeRegs.get(kk).identity * 8);
            }
        }

        output.printf("\tadd rsp, %d\n", graph.frame.size);
        output.printf("\tpop rbp\n");
        output.printf("\tret\n");
    }
}
