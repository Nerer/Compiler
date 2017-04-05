package Compiler.Type;

import Compiler.AST.ASTNode;
import Compiler.Scope.Scope;

import java.util.HashMap;

/**
 * Created by SteinerT on 2017/4/1.
 */
public class ClassType extends Type implements Scope, ASTNode{
    public String className;
    public HashMap<String, MemberFunction> memberFunctions;
    public HashMap<String, MemberVar> memberVars;
    public FunctionType constructor;

    public ClassType(String className) {
        this.className = className;
        memberFunctions = new HashMap<>();
        memberVars = new HashMap<>();
        constructor = null;
    }

    public void addMember(Type type, String memberName) {
        if (memberFunctions.containsKey(memberName) || memberVars.containsKey(memberName)) {
            throw new Error();
        }
        if (type instanceof FunctionType) {
            FunctionType function = (FunctionType) type;
            function.name = this.className + "." + function.name;
            MemberFunction member = new MemberFunction(this, memberName, function);
            memberFunctions.put(memberName, member);
        } else {
            MemberVar member = new MemberVar(this, type, memberName);
            memberVars.put(memberName, member);
        }

    }

    public static Type getType(String name) {
        return new ClassType(name);
    }
    public void addConstructor(FunctionType function) {
        if (constructor != null) {
            throw new Error();
        }
        function.name = className + ".constructor";
        constructor = function;
    }

    public Member getMember(String name) {
        Member member = null;
        if (memberFunctions.containsKey(name)) {
            member = memberFunctions.get(name);
        }
        if (memberVars.containsKey(name)) {
            member = memberVars.get(name);
        }
        if (member == null) throw new Error();
        return member;
    }

}
