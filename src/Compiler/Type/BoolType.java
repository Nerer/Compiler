package Compiler.Type;

/**
 * Created by SteinerT on 2017/4/1.
 */
public class BoolType extends Type{
    @Override
    public String toString() {
        return "BoolType";
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof BoolType;
    }
}
