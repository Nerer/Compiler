package Compiler.Allocator.GlobalRegisterAllocator;

import Compiler.Allocator.PRegister;

import java.util.*;

import Compiler.IR.TempRegister;
import Compiler.IR.VRegister;
import Compiler.NASMRegister;

/**
 * Created by SteinerT on 2017/5/30.
 */
public class InterferenceGraph {
    public static List<PRegister> color = new ArrayList<PRegister>() {{
        add(NASMRegister.rbx);
        add(NASMRegister.rsi);
        add(NASMRegister.rdi);
        add(NASMRegister.r8);
        add(NASMRegister.r9);
        add(NASMRegister.r12);
        add(NASMRegister.r13);
        add(NASMRegister.r14);
        add(NASMRegister.r15);
    }};

    public Set<VRegister> vertices;
    public Map<VRegister, Set<VRegister>> forbids;
    public Map<VRegister, Set<VRegister>> recommends;

    InterferenceGraph() {
        vertices = new HashSet<>();
        forbids = new HashMap<>();
        recommends = new HashMap<>();
    }

    void add(VRegister x) {
        vertices.add(x);
        forbids.put(x, new HashSet<>());
        recommends.put(x, new HashSet<>());
    }

    void forbid(VRegister x, VRegister y) {
        if (x == y) {
            return;
        }
        if (x instanceof TempRegister && y instanceof TempRegister) {
            forbids.get(x).add(y);
            forbids.get(y).add(x);
        }
    }

    void recommend(VRegister x, VRegister y) {
        if (x == y) {
            return;
        }
        if (x instanceof TempRegister && y instanceof TempRegister) {
            recommends.get(x).add(y);
            recommends.get(y).add(x);
        }
    }
}
