package Compiler;

import Compiler.IR.ArithmeticIR.Binary.*;
import Compiler.IR.Immediate;
import Compiler.IR.StringRegister;
import Compiler.IR.VRegister;
import Compiler.Statement.VarDeclarationStatement;
import Compiler.Table.Table;
import sun.tools.jconsole.Tab;

import java.io.PrintStream;

/**
 * Created by SteinerT on 2017/5/28.
 */
public class BuiltinTranslator {
    public static int rsp_offset;
    public static PrintStream output;
    public BuiltinTranslator(PrintStream ooutput) {
        output = ooutput;
        rsp_offset = 0;
    }

    public static String getInstruction(String type) {
        return String.format("%8s\n", type);
    }

    public static String getInstruction(String type, String operand) {
        if (type.equals("push")) {
            rsp_offset++;
        }
        if (type.equals("pop")) {
            rsp_offset--;
        }
        return String.format("%8s %20s\n", type, operand);
    }

    public static String getInstruction(String type, String operand1, String operand2) {
        if (type.equals("mov") && operand1.equals(operand2)) {
            return "";
        }
        return String.format("%8s %20s, %20s\n", type, operand1, operand2);
    }

    public static String getCall(String func) {
        StringBuilder str = new StringBuilder();
        if (rsp_offset % 2 == 1) {
            str.append(BuiltinTranslator.getInstruction("sub", "rsp", "8"));
            str.append(BuiltinTranslator.getInstruction("call", func));
            str.append(BuiltinTranslator.getInstruction("add", "rsp", "8"));
        } else {
            str.append(BuiltinTranslator.getInstruction("call", func));
        }
        return str.toString();
    }

    static public String getDataSection() {
        StringBuilder str = new StringBuilder();
        str.append("SECTION .data\n");

        for (VRegister register : Table.registerTable.registers) {
            if (register instanceof StringRegister) {
                String string = ((StringRegister) register).str;
                str.append(BuiltinTranslator.getInstruction("dq", String.valueOf(string.length())));
                str.append(((StringRegister) register).message() + ":\n");
                str.append(BuiltinTranslator.getInstruction("db", BuiltinTranslator.getStringConst(string)));
            }
        }
        str.append("__println_IntFormat:\n");
        str.append(BuiltinTranslator.getInstruction("db", "\"%ld\", 10, 0"));
        str.append("__print_IntFormat:\n");
        str.append(BuiltinTranslator.getInstruction("db", "\"%ld\", 0"));
        str.append("__printFormat:\n");
        str.append(BuiltinTranslator.getInstruction("db", "\"%s\", 0"));
        str.append("__getIntFormat:\n");
        str.append(BuiltinTranslator.getInstruction("db", "\"%ld\", 0"));
        str.append("__getStringFormat:\n");
        str.append(BuiltinTranslator.getInstruction("db", "\"%s\", 0"));
        str.append("__toStringFormat:\n");
        str.append(BuiltinTranslator.getInstruction("db", "\"%ld\", 0"));
        str.append("__parseIntFormat:\n");
        str.append(BuiltinTranslator.getInstruction("db", "\"%ld\", 0"));
        return str.toString();
    }

    static public String getBssSection() {
        StringBuilder str = new StringBuilder();
        str.append("SECTION .bss\n");
        for (VarDeclarationStatement varStatement : Table.program.variables) {
            str.append(String.format("%s:\n", varStatement.symbol.name));
            str.append(BuiltinTranslator.getInstruction("resq", "1"));
        }
        str.append("@getIntBuf:\n");
        str.append(BuiltinTranslator.getInstruction("resq", "1"));
        str.append("@parseIntBuf:\n");
        str.append(BuiltinTranslator.getInstruction("resq", "1"));
        return str.toString();
    }

    static public String getStringConst(String s) {
        StringBuilder str = new StringBuilder();
        str.append('\"');
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\\') {
                str.append("\", ");
                if (s.charAt(i + 1) == 'n')
                    str.append("10");
                if (s.charAt(i + 1) == '\"')
                    str.append("34");
                if (s.charAt(i + 1) == '\\')
                    str.append("92");
                str.append(", \"");
                i++;
            } else {
                str.append(s.charAt(i));
            }
        }
        str.append("\", 0");
        return str.toString();
    }

    static public String getBuiltinFunction() {
        StringBuilder str = new StringBuilder();
        //print_Int
        str.append(BuiltinTranslator.getNASMprint("print_Int"));
        //println_Int
        str.append(BuiltinTranslator.getNASMprint("println_Int"));
        //print
        str.append(BuiltinTranslator.getNASMprint("print"));
        //println
        str.append(BuiltinTranslator.getNASMprintln());
        //getInt
        str.append(BuiltinTranslator.getNASMgetInt());
        //getString
        str.append(BuiltinTranslator.getNASMgetString());
        //toString
        str.append(BuiltinTranslator.getNASMtoString());
        //arraySize
        str.append(BuiltinTranslator.getNASMarraySize());
        //stringLength
        str.append(BuiltinTranslator.getNASMstringLength());
        //stringParseInt
        str.append(BuiltinTranslator.getNASMstringParseInt());
        //stringSubstring
        str.append(BuiltinTranslator.getNASMstringSubstring());
        //stringOrd
        str.append(BuiltinTranslator.getNASMstringOrd());
        //stringConnection
        str.append(BuiltinTranslator.getNASMstringConnection());
        //stringCompare
        VRegister useless = Table.registerTable.addTemp(null);
        Immediate number1 = new Immediate(233);
        Immediate number2 = new Immediate(233);
        str.append(BuiltinTranslator.getNASMstringCompare(CompareInstruction.getInstruction("e")));
        str.append(BuiltinTranslator.getNASMstringCompare(CompareInstruction.getInstruction("ne")));
        str.append(BuiltinTranslator.getNASMstringCompare(CompareInstruction.getInstruction("g")));
        str.append(BuiltinTranslator.getNASMstringCompare(CompareInstruction.getInstruction("ge")));
        str.append(BuiltinTranslator.getNASMstringCompare(CompareInstruction.getInstruction("l")));
        str.append(BuiltinTranslator.getNASMstringCompare(CompareInstruction.getInstruction("le")));
        return str.toString();
    }

    static public String getNASMprint(String Format) {
        StringBuilder str = new StringBuilder();
        str.append(Format + ":\n");
        rsp_offset = 1;
        str.append(BuiltinTranslator.getInstruction("mov", "rsi", "rdi"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "__" + Format + "Format"));
        str.append(BuiltinTranslator.getCall("printf"));
        str.append(BuiltinTranslator.getInstruction("ret"));

        return str.toString();
    }

    static public String getNASMprintln() {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_println:\n");
        str.append(BuiltinTranslator.getCall("puts"));
        str.append(BuiltinTranslator.getInstruction("ret"));

        return str.toString();
    }

    static public String getNASMgetInt() {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_getInt:\n");
        rsp_offset = 1;
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "__getIntFormat"));
        str.append(BuiltinTranslator.getInstruction("mov", "rsi", "@getIntBuf"));
        str.append(BuiltinTranslator.getCall("scanf"));
        str.append(BuiltinTranslator.getInstruction("mov", "rax", "qword [@getIntBuf]"));
        str.append(BuiltinTranslator.getInstruction("ret"));
        return str.toString();
    }

    static public String getNASMgetString() {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_getString:\n");
        rsp_offset = 1;
        str.append(BuiltinTranslator.getInstruction("push", "r15"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "300"));
        str.append(BuiltinTranslator.getCall("malloc"));
        str.append(BuiltinTranslator.getInstruction("mov", "r15", "rax"));
        str.append(BuiltinTranslator.getInstruction("add", "r15", "8"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "__getStringFormat"));
        str.append(BuiltinTranslator.getInstruction("mov", "rsi", "r15"));
        str.append(BuiltinTranslator.getCall("scanf"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "r15"));
        str.append(BuiltinTranslator.getCall("strlen"));
        str.append(BuiltinTranslator.getInstruction("mov", "qword [r15 - 8]", "rax"));
        str.append(BuiltinTranslator.getInstruction("mov", "rax", "r15"));
        str.append(BuiltinTranslator.getInstruction("pop", "r15"));
        str.append(BuiltinTranslator.getInstruction("ret"));
        return str.toString();
    }

    static public String getNASMtoString() {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_toString:\n");
        rsp_offset = 1;
        str.append(BuiltinTranslator.getInstruction("push", "r15"));
        str.append(BuiltinTranslator.getInstruction("push", "rdi"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "20"));
        str.append(BuiltinTranslator.getCall("malloc"));
        str.append(BuiltinTranslator.getInstruction("mov", "r15", "rax"));
        str.append(BuiltinTranslator.getInstruction("add", "r15", "8"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "r15"));
        str.append(BuiltinTranslator.getInstruction("mov", "rsi", "__toStringFormat"));
        str.append(BuiltinTranslator.getInstruction("pop", "rdx"));
        str.append(BuiltinTranslator.getCall("sprintf"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "r15"));
        str.append(BuiltinTranslator.getCall("strlen"));
        str.append(BuiltinTranslator.getInstruction("mov", "qword [r15 - 8]", "rax"));
        str.append(BuiltinTranslator.getInstruction("mov", "rax", "r15"));
        str.append(BuiltinTranslator.getInstruction("pop", "r15"));
        str.append(BuiltinTranslator.getInstruction("ret"));
        return str.toString();
    }

    static public String getNASMarraySize() {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_arr_size:\n");
        str.append(BuiltinTranslator.getInstruction("mov", "rax", "qword [rdi - 8]"));
        str.append(BuiltinTranslator.getInstruction("ret"));
        return str.toString();
    }

    static public String getNASMstringLength() {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_str_length:\n");
        str.append(BuiltinTranslator.getInstruction("mov", "rax", "qword [rdi - 8]"));
        str.append(BuiltinTranslator.getInstruction("ret"));
        return str.toString();
    }

    static public String getNASMstringParseInt() {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_str_parseInt:\n");
        rsp_offset = 1;
        str.append(BuiltinTranslator.getInstruction("mov", "rsi", "__getIntFormat"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdx", "@parseIntBuf"));
        str.append(BuiltinTranslator.getCall("sscanf"));
        str.append(BuiltinTranslator.getInstruction("mov", "rax", "qword [@parseIntBuf]"));
        str.append(BuiltinTranslator.getInstruction("ret"));
        return str.toString();
    }

    static public String getNASMstringSubstring() {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_str_substring:\n");
        rsp_offset = 1;
        str.append(BuiltinTranslator.getInstruction("push", "r15"));
        str.append(BuiltinTranslator.getInstruction("push", "r14"));
        str.append(BuiltinTranslator.getInstruction("mov", "r15", "rdi"));
        str.append(BuiltinTranslator.getInstruction("add", "r15", "rsi"));
        str.append(BuiltinTranslator.getInstruction("mov", "r14", "rdx"));
        str.append(BuiltinTranslator.getInstruction("sub", "r14", "rsi"));
        str.append(BuiltinTranslator.getInstruction("add", "r14", "1"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "r14"));
        str.append(BuiltinTranslator.getInstruction("add", "rdi", "9"));
        str.append(BuiltinTranslator.getCall("malloc"));
        str.append(BuiltinTranslator.getInstruction("add", "rax", "8"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "rax"));
        str.append(BuiltinTranslator.getInstruction("mov", "rsi", "r15"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdx", "r14"));
        str.append(BuiltinTranslator.getCall("memcpy"));
        str.append(BuiltinTranslator.getInstruction("mov", "qword [rax - 8]", "r14"));
        str.append(BuiltinTranslator.getInstruction("mov", "r15", "rax"));
        str.append(BuiltinTranslator.getInstruction("add", "r15", "r14"));
        str.append(BuiltinTranslator.getInstruction("mov", "r15", "0"));
        str.append(BuiltinTranslator.getInstruction("pop", "r14"));
        str.append(BuiltinTranslator.getInstruction("pop", "r15"));
        str.append(BuiltinTranslator.getInstruction("ret"));
        return str.toString();
    }

    static public String getNASMstringOrd() {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_str_ord:\n");
        rsp_offset = 1;
        str.append(BuiltinTranslator.getInstruction("add", "rdi", "rsi"));
        str.append(BuiltinTranslator.getInstruction("movsx", "rax", "byte [rdi]"));
        str.append(BuiltinTranslator.getInstruction("ret"));
        return str.toString();
    }

    static public String getNASMstringConnection() {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_str_concatenate:\n");
        rsp_offset = 1;
        str.append(BuiltinTranslator.getInstruction("push", "r15"));//length -> result
        str.append(BuiltinTranslator.getInstruction("push", "r14"));//left
        str.append(BuiltinTranslator.getInstruction("push", "r13"));//right
        str.append(BuiltinTranslator.getInstruction("mov", "r15", "qword [rdi - 8]"));
        str.append(BuiltinTranslator.getInstruction("add", "r15", "qword [rsi - 8]"));
        str.append(BuiltinTranslator.getInstruction("add", "r15", "9"));
        str.append(BuiltinTranslator.getInstruction("mov", "r14", "rdi"));
        str.append(BuiltinTranslator.getInstruction("mov", "r13", "rsi"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "r15"));
        str.append(BuiltinTranslator.getCall("malloc"));
        str.append(BuiltinTranslator.getInstruction("sub", "r15", "9"));
        str.append(BuiltinTranslator.getInstruction("mov", "qword [rax]", "r15"));
        str.append(BuiltinTranslator.getInstruction("mov", "r15", "rax"));
        str.append(BuiltinTranslator.getInstruction("add", "r15", "8"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "r15"));
        str.append(BuiltinTranslator.getInstruction("mov", "rsi", "r14"));
        str.append(BuiltinTranslator.getCall("strcpy"));
        str.append(BuiltinTranslator.getInstruction("add", "r15", "qword [r14 - 8]"));
        str.append(BuiltinTranslator.getInstruction("mov", "r14", "rax"));
        str.append(BuiltinTranslator.getInstruction("mov", "rdi", "r15"));
        str.append(BuiltinTranslator.getInstruction("mov", "rsi", "r13"));
        str.append(BuiltinTranslator.getCall("strcpy"));
        str.append(BuiltinTranslator.getInstruction("mov", "rax", "r14"));
        str.append(BuiltinTranslator.getInstruction("pop", "r13"));
        str.append(BuiltinTranslator.getInstruction("pop", "r14"));
        str.append(BuiltinTranslator.getInstruction("pop", "r15"));
        str.append(BuiltinTranslator.getInstruction("ret"));
        return str.toString();
    }

    static public String getNASMstringCompare(CompareInstruction compareInstruction) {
        StringBuilder str = new StringBuilder();
        str.append("Mx_builtin_str_" + compareInstruction.operator + ":\n");
        rsp_offset = 1;
        str.append(BuiltinTranslator.getCall("strcmp"));
        str.append(BuiltinTranslator.getInstruction("cmp", "eax", "0"));
        str.append(BuiltinTranslator.getInstruction("mov", "rax", "0"));
        str.append(BuiltinTranslator.getInstruction(compareInstruction.code, "al"));
        str.append(BuiltinTranslator.getInstruction("ret"));
        return str.toString();
    }
}
