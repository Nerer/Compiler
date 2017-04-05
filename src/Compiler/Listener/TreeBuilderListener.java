package Compiler.Listener;

import Compiler.ErrorMessege.CompilationError;
import Compiler.Expression.*;
import Compiler.Expression.BinaryExpression.*;
import Compiler.Expression.ConstantExpression.BoolConst;
import Compiler.Expression.ConstantExpression.IntConst;
import Compiler.Expression.ConstantExpression.NullConst;
import Compiler.Expression.ConstantExpression.StringConst;
import Compiler.Expression.UnaryExpression.*;
import Compiler.Parser.MotherKnowsBestParser;
import Compiler.Statement.BlockStatement;
import Compiler.Statement.ExpressionStatement;
import Compiler.Statement.IfStatement;
import Compiler.Statement.VarDeclarationStatement;
import Compiler.Table.Table;
import Compiler.Type.*;
import Compiler.Statement.*;
import Compiler.Symbol.Symbol;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteinerT on 2017/4/5.
 */
public class TreeBuilderListener extends BaseListener {
    @Override
    public void exitProgram(MotherKnowsBestParser.ProgramContext ctx) {
        ctx.varDeclaration().forEach(varDeclarationContext -> {
            VarDeclarationStatement varDeclaration = (VarDeclarationStatement)mapping.get(varDeclarationContext);
            Table.program.addVariables(varDeclaration);
        });
    }


    @Override
    public void enterFunctionDeclaration(MotherKnowsBestParser.FunctionDeclarationContext ctx) {
        FunctionType function = (FunctionType)mapping.get(ctx);
        Table.addScope(function);
    }

    @Override
    public void exitFunctionDeclaration(MotherKnowsBestParser.FunctionDeclarationContext ctx) {
        FunctionType function = (FunctionType)mapping.get(ctx);
        function.addStatement((BlockStatement)mapping.get(ctx.blockStatement()));
        Table.popScope();
    }

    @Override
    public void enterClassDeclaration(MotherKnowsBestParser.ClassDeclarationContext ctx) {
        ClassType classType = (ClassType)mapping.get(ctx);
        Table.addScope(classType);
        classType.memberFunctions.forEach((name, member)-> Table.symbolTable.addSymbol(name, member.function));
        classType.memberVars.forEach((name, member) -> Table.symbolTable.addSymbol(name, member.type));
    }

    @Override
    public void exitClassDeclaration(MotherKnowsBestParser.ClassDeclarationContext ctx) {
        ClassType classType = (ClassType)mapping.get(ctx);
        Table.popScope();
    }

    @Override
    public void enterStatement(MotherKnowsBestParser.StatementContext ctx) {
        if (ctx.parent instanceof MotherKnowsBestParser.IfStatementContext) {
            Table.addScope(null);
        }
    }

    @Override
    public void exitStatement(MotherKnowsBestParser.StatementContext ctx) {
        if (ctx.parent instanceof MotherKnowsBestParser.IfStatementContext) {
            Table.popScope();
        }
        mapping.put(ctx, mapping.get(ctx.getChild(0)));
    }

    @Override
    public void enterBlockStatement(MotherKnowsBestParser.BlockStatementContext ctx) {
        BlockStatement blockStatement = (BlockStatement)BlockStatement.getStatement();
        Table.addScope(blockStatement);
        if (ctx.parent instanceof MotherKnowsBestParser.FunctionDeclarationContext) {
            FunctionType function = (FunctionType)mapping.get(ctx.parent);
            for (int i = 0; i < function.parameters.size(); i++) {
                Symbol parameter = function.parameters.get(i);
                Table.symbolTable.addSymbol(parameter.name, parameter.type);
            }
        }
        mapping.put(ctx, blockStatement);
    }

    @Override
    public void exitBlockStatement(MotherKnowsBestParser.BlockStatementContext ctx) {
        ctx.statement().forEach(statementContext -> {
            ((BlockStatement)mapping.get(ctx)).addStatement((Statement)mapping.get(statementContext));
        });
        Table.popScope();
    }

    @Override
    public void exitExpressionStatement(MotherKnowsBestParser.ExpressionStatementContext ctx) {
        mapping.put(ctx, ExpressionStatement.getStatement((Expression)mapping.get(ctx.expression())));
    }

    @Override
    public void exitIfStatement(MotherKnowsBestParser.IfStatementContext ctx) {
        mapping.put(ctx, IfStatement.getStatement(
                (Expression)mapping.get(ctx.expression()),
                (Statement)mapping.get(ctx.statement(0)),
                (Statement)mapping.get(ctx.statement(1))
        ));
    }

    @Override
    public void enterWhileStatement(MotherKnowsBestParser.WhileStatementContext ctx) {
        WhileStatement whileStatement = (WhileStatement)WhileStatement.getStatement();
        Table.addScope(whileStatement);
        mapping.put(ctx, whileStatement);
    }

    @Override
    public void exitWhileStatement(MotherKnowsBestParser.WhileStatementContext ctx) {
        WhileStatement whileStatement = (WhileStatement)mapping.get(ctx);
        whileStatement.addCondition((Expression)mapping.get(ctx.expression()));
        whileStatement.addStatement((Statement)mapping.get(ctx.statement()));
        Table.popScope();
    }

    @Override
    public void enterForStatement(MotherKnowsBestParser.ForStatementContext ctx) {
        ForStatement forStatement = (ForStatement)ForStatement.getStatement();
        Table.addScope(forStatement);
        mapping.put(ctx, forStatement);
    }

    @Override
    public void exitForStatement(MotherKnowsBestParser.ForStatementContext ctx) {
        ForStatement forStatement = (ForStatement)mapping.get(ctx);
        forStatement.addStatement((Statement)mapping.get(ctx.statement()));

        int separate = 0;
        for (ParseTree parseTree : ctx.children) {
            if (parseTree.getText().equals(";")) {
                separate++;
            }
            if (parseTree instanceof MotherKnowsBestParser.ExpressionContext) {
                Expression expression = (Expression)mapping.get(parseTree);
                if (separate == 0) {
                    forStatement.addInit(expression);
                }
                if (separate == 1) {
                    forStatement.addCondition(expression);
                }
                if (separate == 2) {
                    forStatement.addOperation(expression);
                }
                if (separate > 2) {
                    throw new Error();
                }
            }
        }
        Table.popScope();
    }

    @Override
    public void exitReturnStatement(MotherKnowsBestParser.ReturnStatementContext ctx) {
        mapping.put(ctx, ReturnStatement.getStatement((Expression)mapping.get(ctx.expression())));
    }

    @Override
    public void exitBreakStatement(MotherKnowsBestParser.BreakStatementContext ctx) {
        mapping.put(ctx, BreakStatement.getStatement());
    }

    @Override
    public void exitContinueStatement(MotherKnowsBestParser.ContinueStatementContext ctx) {
        mapping.put(ctx, ContinueStatement.getStatement());
    }


    @Override
    public void exitVarDeclaration(MotherKnowsBestParser.VarDeclarationContext ctx) {
        if (!(ctx.parent instanceof MotherKnowsBestParser.ClassDeclarationContext)) {
            Type type = (Type)mapping.get(ctx.type());
            String name = ctx.IDENTIFIER(0).getText();
            Symbol symbol = Table.symbolTable.addSymbol(name, type);
            Expression expression = (Expression)mapping.get(ctx.expression(0));
            mapping.put(ctx, VarDeclarationStatement.getStatement(symbol, expression));
        }
    }

    @Override
    public void exitConstantExpression(MotherKnowsBestParser.ConstantExpressionContext ctx) {
        mapping.put(ctx, mapping.get(ctx.constant()));
    }

    @Override
    public void exitVariableExpression(MotherKnowsBestParser.VariableExpressionContext ctx) {
        mapping.put(ctx, VariableExpression.getExpression(ctx.getText()));
    }

    @Override
    public void exitArrayExpression(MotherKnowsBestParser.ArrayExpressionContext ctx) {
        mapping.put(ctx, ArrayExpression.getExpression(
                (Expression)mapping.get(ctx.expression(0)),
                (Expression)mapping.get(ctx.expression(1))
        ));
    }

    @Override
    public void exitMemberExpression(MotherKnowsBestParser.MemberExpressionContext ctx) {
        mapping.put(ctx, MemberExpression.getExpression(
                (Expression)mapping.get(ctx.expression()),
                ctx.IDENTIFIER().getText()
        ));
    }

    @Override
    public void exitContainedExpression(MotherKnowsBestParser.ContainedExpressionContext ctx) {
        mapping.put(ctx, mapping.get(ctx.expression()));
    }

    @Override
    public void exitSelfExpression(MotherKnowsBestParser.SelfExpressionContext ctx) {
        Expression expression = (Expression)mapping.get(ctx.expression());
        if (ctx.operator.getText().equals("++")) {
            mapping.put(ctx, SuffAddExpression.getExpression(expression));
        } else if (ctx.operator.getText().equals("--")) {
            mapping.put(ctx, SuffDecExpression.getExpression(expression));
        } else {
            throw new CompilationError("Internal Error");
        }
    }

    @Override
    public void exitUnaryExpression(MotherKnowsBestParser.UnaryExpressionContext ctx) {
        Expression expression = (Expression)mapping.get(ctx.expression());
        if (ctx.operator.getText().equals("+")) {
            mapping.put(ctx, SelfAddExpression.getExpression(expression));
        } else {
            if (ctx.operator.getText().equals("-")) {
                mapping.put(ctx, SelfMinusExpression.getExpression(expression));
            } else {
                if (ctx.operator.getText().equals("!")) {
                    mapping.put(ctx, LogicalNotExpression.getExpression(expression));
                } else {
                    if (ctx.operator.getText().equals("~")) {
                        mapping.put(ctx, BitNotExpression.getExpression(expression));
                    } else {
                        if (ctx.operator.getText().equals("++")) {
                            mapping.put(ctx, PreAddExpression.getExpression(expression));
                        } else {
                            if (ctx.operator.getText().equals("--")) {
                                mapping.put(ctx, PreDecExpression.getExpression(expression));
                            } else {
                                throw new Error();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void exitNewExpression(MotherKnowsBestParser.NewExpressionContext ctx) {
        List<Expression> expressions = new ArrayList<>();
        boolean space = false;
        boolean lastNull = false;
        int nullCounter = 0;
        for (int i = 0; i < ctx.children.size(); i++) {
            if (!(ctx.children.get(i) instanceof TerminalNode)) {
                space = false;
                continue;
            }
            Token token = ((TerminalNode)ctx.children.get(i)).getSymbol();
            if (token.getText().equals("]")) {
                if (space) {
                    lastNull = true;
                    nullCounter++;
                } else {
                    if (lastNull) {
                        throw new Error();
                    } else {
                        lastNull = false;
                    }
                }
            }
            if (token.getText().equals("[")) {
                space = true;
            } else {
                space = false;
            }
        }
        ctx.expression().forEach(expressionContext -> {
            Expression expression = (Expression)mapping.get(expressionContext);
            expressions.add(expression);
        });
        for (int i = 0; i < nullCounter; i++) {
            expressions.add(null);
        }

        Type baseType = (Type)mapping.get(ctx.type());
        mapping.put(ctx, NewExpression.getExpression(baseType, expressions));
    }

    @Override
    public void exitFunctionCallExpression(MotherKnowsBestParser.FunctionCallExpressionContext ctx) {
        Expression function = (Expression)mapping.get(ctx.expression(0));
        List<Expression> parameters = new ArrayList<> ();
        for (int i = 1; i < ctx.expression().size(); i++) {
            Expression parameter = (Expression)mapping.get(ctx.expression(i));
            parameters.add(parameter);
        }
        mapping.put(ctx, FunctionCallExpression.getExpression(function, parameters));
    }

    @Override
    public void exitMuldivmodExpression(MotherKnowsBestParser.MuldivmodExpressionContext ctx) {
        Expression lhs = (Expression)mapping.get(ctx.expression(0));
        Expression rhs = (Expression)mapping.get(ctx.expression(1));
        if (ctx.operator.getText().equals("*")) {
            mapping.put(ctx, MulExpression.getExpression(lhs, rhs));
        } else {
            if (ctx.operator.getText().equals("/")) {
                mapping.put(ctx, DivideExpression.getExpression(lhs, rhs));
            } else {
                if (ctx.operator.getText().equals("%")) {
                    mapping.put(ctx, ModExpression.getExpression(lhs, rhs));
                } else {
                    throw new Error();
                }
            }
        }
    }

    @Override
    public void exitAddminusExpression(MotherKnowsBestParser.AddminusExpressionContext ctx) {
        Expression lhs = (Expression)mapping.get(ctx.expression(0));
        Expression rhs = (Expression)mapping.get(ctx.expression(1));
        if (ctx.operator.getText().equals("+")) {
            mapping.put(ctx, AddExpression.getExpression(lhs, rhs));
        } else {
            if (ctx.operator.getText().equals("-")) {
                mapping.put(ctx, MinusExpression.getExpression(lhs, rhs));
            } else {
                throw new Error();
            }
        }
    }

    @Override
    public void exitShiftExpression(MotherKnowsBestParser.ShiftExpressionContext ctx) {
        Expression lhs = (Expression)mapping.get(ctx.expression(0));
        Expression rhs = (Expression)mapping.get(ctx.expression(1));
        if (ctx.operator.getText().equals("<<")) {
            mapping.put(ctx, BitLeftShiftExpression.getExpression(lhs, rhs));
        } else {
            if (ctx.operator.getText().equals(">>")) {
                mapping.put(ctx, BitRightShiftExpression.getExpression(lhs, rhs));
            } else {
                throw new Error();
            }
        }
    }

    @Override
    public void exitRelationExpression(MotherKnowsBestParser.RelationExpressionContext ctx) {
        Expression lhs = (Expression)mapping.get(ctx.expression(0));
        Expression rhs = (Expression)mapping.get(ctx.expression(1));
        if (ctx.operator.getText().equals("<")) {
            mapping.put(ctx, LessExpression.getExpression(lhs, rhs));
        } else {
            if (ctx.operator.getText().equals(">")) {
                mapping.put(ctx, GreaterExpression.getExpression(lhs, rhs));
            } else {
                if (ctx.operator.getText().equals("<=")) {
                    mapping.put(ctx, LeExpression.getExpression(lhs, rhs));
                } else {
                    if (ctx.operator.getText().equals(">=")) {
                        mapping.put(ctx, GeExpression.getExpression(lhs, rhs));
                    } else {
                        if (ctx.operator.getText().equals("==")) {
                            mapping.put(ctx, EqualExpression.getExpression(lhs, rhs));
                        } else {
                            if (ctx.operator.getText().equals("!=")) {
                                mapping.put(ctx, NotEqualExpression.getExpression(lhs, rhs));
                            } else {
                                throw new Error();
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void exitXorExpression(MotherKnowsBestParser.XorExpressionContext ctx) {
        Expression lhs = (Expression)mapping.get(ctx.expression(0));
        Expression rhs = (Expression)mapping.get(ctx.expression(1));
        mapping.put(ctx, BitXorExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitAssignmentExpression(MotherKnowsBestParser.AssignmentExpressionContext ctx) {
        Expression lhs = (Expression)mapping.get(ctx.expression(0));
        Expression rhs = (Expression)mapping.get(ctx.expression(1));
        mapping.put(ctx, AssignmentExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitLogicalOrExpression(MotherKnowsBestParser.LogicalOrExpressionContext ctx) {
        Expression lhs = (Expression)mapping.get(ctx.expression(0));
        Expression rhs = (Expression)mapping.get(ctx.expression(1));
        mapping.put(ctx, LogicalOrExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitOrExpression(MotherKnowsBestParser.OrExpressionContext ctx) {
        Expression lhs = (Expression)mapping.get(ctx.expression(0));
        Expression rhs = (Expression)mapping.get(ctx.expression(1));
        mapping.put(ctx, BitOrExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitAndExpression(MotherKnowsBestParser.AndExpressionContext ctx) {
        Expression lhs = (Expression)mapping.get(ctx.expression(0));
        Expression rhs = (Expression)mapping.get(ctx.expression(1));
        mapping.put(ctx, BitAndExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitLogicalAndExpression(MotherKnowsBestParser.LogicalAndExpressionContext ctx) {
        Expression lhs = (Expression)mapping.get(ctx.expression(0));
        Expression rhs = (Expression)mapping.get(ctx.expression(1));
        mapping.put(ctx, LogicalAndExpression.getExpression(lhs, rhs));
    }

    @Override
    public void exitBoolConstant(MotherKnowsBestParser.BoolConstantContext ctx) {
        mapping.put(ctx, new BoolConst(Boolean.valueOf(ctx.getText())));
    }

    @Override
    public void exitIntConstant(MotherKnowsBestParser.IntConstantContext ctx) {
        mapping.put(ctx, new IntConst(Integer.valueOf(ctx.getText())));
    }

    @Override
    public void exitStringConstant(MotherKnowsBestParser.StringConstantContext ctx) {
        mapping.put(ctx, new StringConst(ctx.getText().substring(1, ctx.getText().length() - 1)));
    }

    @Override
    public void exitNullConstant(MotherKnowsBestParser.NullConstantContext ctx) {
        mapping.put(ctx, new NullConst());
    }
}
