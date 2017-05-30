package Compiler;

import Compiler.Allocator.PRegister;

/**
 * Created by SteinerT on 2017/5/27.
 */
public class NASMRegister extends PRegister{
    public static PRegister rax = new NASMRegister(0,"rax");
    public static PRegister rbx = new NASMRegister(1,"rbx");
    public static PRegister rcx = new NASMRegister(2,"rcx");
    public static PRegister rdx = new NASMRegister(3,"rdx");
    public static PRegister rsi = new NASMRegister(4,"rsi");
    public static PRegister rdi = new NASMRegister(5,"rdi");
    public static PRegister rbp = new NASMRegister(6,"rbp");
    public static PRegister rsp = new NASMRegister(7,"rsp");
    public static PRegister r8 = new NASMRegister(8,"r8");
    public static PRegister r9 = new NASMRegister(9,"r9");
    public static PRegister r10 = new NASMRegister(10,"r10");
    public static PRegister r11 = new NASMRegister(11,"r11");
    public static PRegister r12 = new NASMRegister(12,"r12");
    public static PRegister r13 = new NASMRegister(13,"r13");
    public static PRegister r14 = new NASMRegister(14,"r14");
    public static PRegister r15 = new NASMRegister(15,"r15");
    public static PRegister cl = new NASMRegister(16,"cl");
    public static PRegister al = new NASMRegister(17,"al");

    public NASMRegister(int identity, String name) {
        super(identity, name);
    }

    @Override
    public String toString() {
        return name;
    }
}
