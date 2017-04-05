package Compiler.Type;

/**
 * Created by SteinerT on 2017/4/1.
 */
public class IntType extends Type{
    @Override
    public String toString() {
        return "IntType";
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof IntType;
    }
}
