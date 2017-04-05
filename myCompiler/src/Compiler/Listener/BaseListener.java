package Compiler.Listener;

import Compiler.AST.ASTNode;
import Compiler.Parser.MotherKnowsBestBaseListener;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

/**
 * Created by SteinerT on 2017/4/5.
 */
public abstract class BaseListener extends MotherKnowsBestBaseListener {
    public static ParseTreeProperty<ASTNode> mapping = new ParseTreeProperty<>();
}
