package Compiler.Allocator.GlobalRegisterAllocator;

import Compiler.Allocator.PRegister;
import Compiler.IR.VRegister;

import java.util.*;

/**
 * Created by SteinerT on 2017/5/30.
 */
public class ChaitinGraphColoring extends GraphColoring {
    private Set<VRegister> vertices;
    private Map<VRegister, Integer> degree;

    public ChaitinGraphColoring(InterferenceGraph graph) {
        super(graph);
        vertices = new HashSet<>();
        degree = new HashMap<>();
    }

    public Map<VRegister, PRegister> analysis() {
        for (VRegister vertex : graph.vertices) {
            vertices.add(vertex);
            degree.put(vertex, graph.forbids.get(vertex).size());
        }
        Stack<VRegister> stack = new Stack<>();
        while (stack.size() < graph.vertices.size()) {
            int origin = stack.size();
            for (VRegister vertex : vertices) {
                if (degree.get(vertex) < InterferenceGraph.color.size()) {
                    stack.add(vertex);
                    remove(vertex);
                    break;
                }
            }
            if (stack.size() != origin) {
                continue;
            }
            for (VRegister vertex : vertices) {
                if (degree.get(vertex) >= InterferenceGraph.color.size()) {
                    stack.add(vertex);
                    remove(vertex);
                    break;
                }
            }
        }
        while (!stack.isEmpty()) {
            color(stack.pop());
        }
        return mapping;
    }

    private void remove(VRegister vertex) {
        vertices.remove(vertex);
        for (VRegister neighbor : graph.forbids.get(vertex)) {
            degree.put(neighbor, degree.get(neighbor) - 1);
        }
    }
}
