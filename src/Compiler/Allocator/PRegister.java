package Compiler.Allocator;

/**
 * Created by SteinerT on 2017/5/30.
 */
public abstract class PRegister {
    public int identity;
    public String name;

    protected PRegister(int identity, String name) {
        this.identity = identity;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
