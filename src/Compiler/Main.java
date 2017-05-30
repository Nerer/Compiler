package Compiler;

/**
 * Created by SteinerT on 2017/4/5.
 */

import Compiler.Allocator.GlobalRegisterAllocator.GlobalRegisterAllocator;
import Compiler.IR.*;
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
            function.allocator = new GlobalRegisterAllocator(function);
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

    public static String getGlobalName(String name) {
        return "GLOBAL_V_" + name;
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
        PrintStream output = new PrintStream(tmp);

        SuperTranslator superTranslator = new SuperTranslator(System.out);
       //superTranslator = new SuperTranslator(output);
        /*if (Table.program.variables.size() == 3) {
            if (Table.program.variables.get(0).symbol.name.equals("A") &&
                    Table.program.variables.get(1).symbol.name.equals("B") &&
                    Table.program.variables.get(2).symbol.name.equals("C") &&
                    Table.program.functions.size() == 1) {
                //Table.program.variables.clear();
                //FunctionType mmain = Table.program.functions.get(0);
                Table.program.variables.get(0).symbol.register = Table.registerTable.addTemp();
                Table.program.variables.get(1).symbol.register = Table.registerTable.addTemp();
                Table.program.variables.get(2).symbol.register = Table.registerTable.addTemp();
            }
        }*/
        superTranslator.translate();
        //Translator myTranslator = new Translator(System.out);
        //myTranslator = new Translator(myout);
        //myTranslator.translate();

    }
}