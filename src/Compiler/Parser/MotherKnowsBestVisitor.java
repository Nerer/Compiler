// Generated from /Users/SteinerT/Desktop/Compiler/myCompiler/src/Compiler.Parser/MotherKnowsBest.g4 by ANTLR 4.6
package Compiler.Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MotherKnowsBestParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MotherKnowsBestVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MotherKnowsBestParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MotherKnowsBestParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MotherKnowsBestParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(MotherKnowsBestParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MotherKnowsBestParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(MotherKnowsBestParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MotherKnowsBestParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(MotherKnowsBestParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MotherKnowsBestParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MotherKnowsBestParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MotherKnowsBestParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(MotherKnowsBestParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MotherKnowsBestParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(MotherKnowsBestParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MotherKnowsBestParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(MotherKnowsBestParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(MotherKnowsBestParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(MotherKnowsBestParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(MotherKnowsBestParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(MotherKnowsBestParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(MotherKnowsBestParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addminusExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddminusExpression(MotherKnowsBestParser.AddminusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(MotherKnowsBestParser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code muldivmodExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMuldivmodExpression(MotherKnowsBestParser.MuldivmodExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code shiftExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftExpression(MotherKnowsBestParser.ShiftExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpression(MotherKnowsBestParser.ArrayExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xorExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorExpression(MotherKnowsBestParser.XorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpression(MotherKnowsBestParser.NewExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignmentExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(MotherKnowsBestParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code relationExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationExpression(MotherKnowsBestParser.RelationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalOrExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(MotherKnowsBestParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code memberExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExpression(MotherKnowsBestParser.MemberExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableExpression(MotherKnowsBestParser.VariableExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(MotherKnowsBestParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(MotherKnowsBestParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selfExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfExpression(MotherKnowsBestParser.SelfExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code containedExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContainedExpression(MotherKnowsBestParser.ContainedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalAndExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(MotherKnowsBestParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpression(MotherKnowsBestParser.FunctionCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(MotherKnowsBestParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(MotherKnowsBestParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(MotherKnowsBestParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringType(MotherKnowsBestParser.StringTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code voidType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidType(MotherKnowsBestParser.VoidTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(MotherKnowsBestParser.BoolTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassType(MotherKnowsBestParser.ClassTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolConstant(MotherKnowsBestParser.BoolConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntConstant(MotherKnowsBestParser.IntConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringConstant(MotherKnowsBestParser.StringConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullConstant(MotherKnowsBestParser.NullConstantContext ctx);
}