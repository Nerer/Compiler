package Compiler.Type;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class MemberVar extends Member{
    public Type type;
    public String name;
    public ClassType belong;

    public MemberVar(ClassType belong, Type type, String name) {
        this.belong = belong;
        this.type = type;
        this.name = name;
    }

    public String toString() {
        return "memberVar";
    }
}
