package Compiler.Type;

/**
 * Created by SteinerT on 2017/4/3.
 */
public class VoidType extends Type {
    @Override
    public String toString() {
        return "VoidType";
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof VoidType;
    }
}
