package Compiler.Listener;

import Compiler.AST.ASTNode;
import Compiler.Parser.MotherKnowsBestBaseListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

/**
 * Created by SteinerT on 2017/4/5.
 */
public abstract class BaseListener extends MotherKnowsBestBaseListener {
    public static int row, column;

    public static ParseTreeProperty<ASTNode> mapping = new ParseTreeProperty<>();

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        row = ctx.getStart().getLine();
        column = ctx.getStart().getCharPositionInLine();
    }
}
