package Compiler.Table;

import Compiler.Type.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class ClassTable {
    public Map<String, ClassType> classMap;

    public ClassTable() {
        classMap = new HashMap<>();
    }

    public void put(String name, ClassType classType) {
        if (classMap.containsKey(name)) {
            throw new Error();
        }
        classMap.put(name, classType);
    }

    public ClassType getClass(String name) {
        return classMap.get(name);
    }

    public boolean contains(String name) {
        return classMap.containsKey(name);
    }
}
