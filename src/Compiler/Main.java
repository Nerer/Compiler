package Compiler;

/**
 * Created by SteinerT on 2017/4/5.
 */

import Compiler.IR.Block;
import Compiler.IR.Graph;
import Compiler.IR.Instruction;
import Compiler.Listener.FirstRoundListener;
import Compiler.Listener.SecondRoundListener;
import Compiler.Listener.TreeBuilderListener;
import Compiler.Parser.MotherKnowsBestLexer;
import Compiler.Parser.MotherKnowsBestParser;
import Compiler.Statement.Statement;
import Compiler.Table.Table;
import Compiler.Type.FunctionType;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void printIR() {
        for (FunctionType function : Table.program.functions) {
            function.graph = new Graph(function);
        }
        //return;
/*
        for (FunctionType function : Table.program.functions) {
            Graph graph = function.graph;
            for (int i = 0; i < graph.blocks.size(); i++) {
                Block block = graph.blocks.get(i);
                System.out.println(block.name);
                for (Instruction instruction : block.instructions) {
                    System.out.println(instruction);
                }
            }
        }
*/
    }
    public static void translate() {
        int totRegisters = Table.registerTable.registers.size();
        int reservedSpace = totRegisters * 8;

    }

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

        printIR();
        System.out.printf("\n\n");
        FileOutputStream tmp = new FileOutputStream("/Users/SteinerT/Desktop/Compiler/myCompiler/src/Compiler/mytrans.asm");
        PrintStream myout = new PrintStream(tmp);
        Translator myTranslator = new Translator(System.out);
      //  myTranslator = new Translator(myout);
        myTranslator.translate();

    }
}