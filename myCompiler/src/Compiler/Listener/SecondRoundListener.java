package Compiler.Listener;

import Compiler.Parser.MotherKnowsBestParser;
import Compiler.Table.Table;
import Compiler.Symbol.Symbol;
import Compiler.Type.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class SecondRoundListener extends BaseListener {
    @Override
    public void exitFunctionDeclaration(MotherKnowsBestParser.FunctionDeclarationContext ctx) {
        boolean isConstructor = (ctx.type().size() != ctx.IDENTIFIER().size());
        String functionName;
        Type returnType = Table.myVoid;
        ClassType classType = Table.scopeTable.getClassScope();
        if (!isConstructor)  {
            functionName = ctx.IDENTIFIER(0).getText();
            returnType = (Type)mapping.get(ctx.type(0));
        } else {
            if (classType == null) {
                throw new Error();
            }
            functionName = ctx.type(0).getText();
            if (!functionName.equals(classType.className)) {
                throw new Error();
            }
        }
        List<Symbol> parameters = new ArrayList<Symbol>();
        if (classType != null) {
            parameters.add(new Symbol(classType, "this"));
        }
        if (!isConstructor) {
            for (int i = 1; i < ctx.type().size(); i++) {
                String paraName = ctx.IDENTIFIER(i).getText();
                Type paraType = (Type) mapping.get(ctx.type(i));
                parameters.add(new Symbol(paraType, paraName));
            }
        }
        FunctionType function = FunctionType.getFunction(functionName, returnType, parameters);
        if (classType != null) {
            if (!isConstructor) {
                classType.addMember(function, functionName);
            } else {
                classType.addConstructor(function);
            }
        } else {
            Table.symbolTable.addSymbol(functionName, function);
        }

        Table.program.addFunction(function);
        mapping.put(ctx, function);
    }

    @Override
    public void enterClassDeclaration(MotherKnowsBestParser.ClassDeclarationContext ctx) {
        ClassType classType = (ClassType)mapping.get(ctx);
        Table.addScope(classType);
    }

    @Override
    public void exitClassDeclaration(MotherKnowsBestParser.ClassDeclarationContext ctx) {
        ClassType classType = (ClassType)mapping.get(ctx);
        ctx.varDeclaration().forEach(varDeclarationContext -> {
            String memberName = varDeclarationContext.IDENTIFIER(0).getText();
            Type memberType = (Type)mapping.get(varDeclarationContext.type());
            classType.addMember(memberType, memberName);
        });
        Table.popScope();
    }

    @Override
    public void exitIntType(MotherKnowsBestParser.IntTypeContext ctx) {
        mapping.put(ctx, Table.myInt);
    }

    @Override
    public void exitBoolType(MotherKnowsBestParser.BoolTypeContext ctx) {
        mapping.put(ctx, Table.myBool);
    }

    @Override
    public void exitStringType(MotherKnowsBestParser.StringTypeContext ctx) {
        mapping.put(ctx, Table.myString);
    }

    @Override
    public void exitVoidType(MotherKnowsBestParser.VoidTypeContext ctx) {
        mapping.put(ctx, Table.myVoid);
    }

    @Override
    public void exitClassType(MotherKnowsBestParser.ClassTypeContext ctx) {
        String className = ctx.getText();
        if (!Table.classTable.contains(className)) {
            throw new Error();
        }
        mapping.put(ctx, Table.classTable.getClass(className));
    }

    @Override
    public void exitArrayType(MotherKnowsBestParser.ArrayTypeContext ctx) {
        Type baseType = (Type)mapping.get(ctx.type());
        mapping.put(ctx, ArrayType.getType(baseType));
    }
}
