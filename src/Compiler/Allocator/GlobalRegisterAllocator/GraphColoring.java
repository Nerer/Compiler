package Compiler.Allocator.GlobalRegisterAllocator;

import Compiler.Allocator.PRegister;
import Compiler.IR.VRegister;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by SteinerT on 2017/5/30.
 */
public abstract class GraphColoring {
    protected InterferenceGraph graph;
    Map<VRegister, PRegister> mapping;

    GraphColoring(InterferenceGraph graph) {
        this.graph = graph;
        this.mapping = new HashMap<>();
    }

    void color(VRegister vertex) {
        Set<PRegister> used = new HashSet<PRegister>() {{
            for (VRegister neighbor : graph.forbids.get(vertex)) {
                if (mapping.containsKey(neighbor)) {
                    add(mapping.get(neighbor));
                }
            }
            add(null);
        }};
        for (VRegister neighbor : graph.recommends.get(vertex)) {
            if (!mapping.containsKey(neighbor)) {
                continue;
            }
            PRegister color = mapping.get(neighbor);
            if (!mapping.containsKey(vertex) && !used.contains(color)) {
                mapping.put(vertex, color);
                break;
            }
        }
        for (PRegister color : InterferenceGraph.color) {
            if (!mapping.containsKey(vertex) && !used.contains(color)) {
                mapping.put(vertex, color);
                break;
            }
        }
        if (!mapping.containsKey(vertex)) {
            mapping.put(vertex, null);
        }
    }
}
