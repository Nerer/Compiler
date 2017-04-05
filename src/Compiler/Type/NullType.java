package Compiler.Type;

/**
 * Created by SteinerT on 2017/4/1.
 */
public class NullType extends Type{
    @Override
    public String toString() {
        return "NullType";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj instanceof ArrayType || obj instanceof ClassType || obj instanceof NullType) {
                return true;
            }
        }
        return false;
    }
}
