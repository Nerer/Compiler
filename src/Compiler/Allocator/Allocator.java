package Compiler.Allocator;

import Compiler.IR.VRegister;
import Compiler.Type.FunctionType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by SteinerT on 2017/5/30.
 */
public abstract class Allocator {
    public FunctionType function;
    public Map<VRegister, PRegister> mapping;

    protected Allocator(FunctionType function) {
        this.function = function;
        this.mapping = new HashMap<>();
    }

    public Set<PRegister> getUsedPhysicalRegisters() {
        return new HashSet<PRegister>() {{
            for (VRegister virtual : mapping.keySet()) {
                PRegister physical = mapping.get(virtual);
                if (physical != null) {
                    add(physical);
                }
            }
        }};
    }
}
