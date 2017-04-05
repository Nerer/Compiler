package Compiler.Type;

/**
 * Created by SteinerT on 2017/4/1.
 */
public class StringType extends Type{
    @Override
    public String toString() {
        return "StringType";
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof StringType;
    }
}
