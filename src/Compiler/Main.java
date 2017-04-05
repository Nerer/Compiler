package Compiler;

/**
 * Created by SteinerT on 2017/4/5.
 */

import Compiler.Listener.FirstRoundListener;
import Compiler.Listener.SecondRoundListener;
import Compiler.Listener.TreeBuilderListener;
import Compiler.Parser.MotherKnowsBestLexer;
import Compiler.Parser.MotherKnowsBestParser;
import Compiler.Table.Table;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream is = new FileInputStream("/Users/SteinerT/Desktop/Compiler/myCompiler/src/Compiler/test.txt");
        InputStreamReader Src = new InputStreamReader(is);
        ANTLRInputStream input = new ANTLRInputStream(Src);
        MotherKnowsBestLexer lexer = new MotherKnowsBestLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MotherKnowsBestParser parser = new MotherKnowsBestParser(tokens);
        Table.init();
        ParseTree tree = parser.program();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new Error("Syntax Error");
        }
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new FirstRoundListener(), tree);
        walker.walk(new SecondRoundListener(), tree);
        walker.walk(new TreeBuilderListener(), tree);
        if (!Table.symbolTable.contains("main"))
            throw new Error("You should have main function");
    }
}