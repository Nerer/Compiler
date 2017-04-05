package Compiler.Listener;

import Compiler.Parser.MotherKnowsBestParser;
import Compiler.Type.*;
import Compiler.Table.Table;
/**
 * Created by SteinerT on 2017/4/5.
 */
public class FirstRoundListener extends BaseListener{
    @Override
    public void exitClassDeclaration(MotherKnowsBestParser.ClassDeclarationContext ctx) {
        String className = ctx.IDENTIFIER().getText();
        ClassType classType = (ClassType)ClassType.getType(className);
        Table.program.addClass(classType);
        Table.classTable.put(className, classType);
        mapping.put(ctx, classType);

    }
}
