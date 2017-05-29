package Compiler.Type;

import Compiler.Expression.Expression;

/**
 * Created by SteinerT on 2017/4/4.
 */
public class MemberVar extends Member{
    public Type type;
    public String name;
    public ClassType belong;
    public int offset;
    public Expression expression;

    public MemberVar(ClassType belong, Type type, String name) {
        this.belong = belong;
        this.type = type;
        this.name = name;
        this.offset = belong.allocateSize;
        belong.allocateSize += 8;
    }

    public String toString() {
        return "memberVar";
    }
}
