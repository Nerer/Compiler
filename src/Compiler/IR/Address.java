package Compiler.IR;

/**
 * Created by SteinerT on 2017/5/18.
 */
public class Address extends Operand {
    public VRegister base;
    public Immediate offset;
    public int size;
    public Address(VRegister base, Immediate offset) {
        this.base = base;
        this.offset = offset;
        this.size = 8;
    }

    public Address(VRegister base) {
        this.base = base;
        this.offset = new Immediate(0);
        this.size = 8;
    }


    @Override
    public String toString() {
        return this.base + "+" + this.offset;
    }
}
