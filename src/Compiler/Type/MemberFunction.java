package Compiler.Type;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class MemberFunction extends Member{
    public FunctionType function;
    public String name;
    public ClassType belong;

    public MemberFunction(ClassType belong, String name, FunctionType function) {
        this.belong = belong;
        this.name = name;
        this.function = function;
    }

    public String toString() {
        return "MemberFunction";
    }

}
