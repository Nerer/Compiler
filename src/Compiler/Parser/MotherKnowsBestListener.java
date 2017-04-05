// Generated from /Users/SteinerT/Desktop/Compiler/myCompiler/src/Compiler/Parser/MotherKnowsBest.g4 by ANTLR 4.6
package Compiler.Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MotherKnowsBestParser}.
 */
public interface MotherKnowsBestListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MotherKnowsBestParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MotherKnowsBestParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MotherKnowsBestParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MotherKnowsBestParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MotherKnowsBestParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(MotherKnowsBestParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MotherKnowsBestParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(MotherKnowsBestParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MotherKnowsBestParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(MotherKnowsBestParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MotherKnowsBestParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(MotherKnowsBestParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MotherKnowsBestParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MotherKnowsBestParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MotherKnowsBestParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MotherKnowsBestParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MotherKnowsBestParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MotherKnowsBestParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MotherKnowsBestParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MotherKnowsBestParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MotherKnowsBestParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(MotherKnowsBestParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MotherKnowsBestParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(MotherKnowsBestParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MotherKnowsBestParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(MotherKnowsBestParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MotherKnowsBestParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(MotherKnowsBestParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MotherKnowsBestParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(MotherKnowsBestParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MotherKnowsBestParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(MotherKnowsBestParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(MotherKnowsBestParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(MotherKnowsBestParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(MotherKnowsBestParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(MotherKnowsBestParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(MotherKnowsBestParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(MotherKnowsBestParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(MotherKnowsBestParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(MotherKnowsBestParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(MotherKnowsBestParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link MotherKnowsBestParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(MotherKnowsBestParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addminusExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddminusExpression(MotherKnowsBestParser.AddminusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addminusExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddminusExpression(MotherKnowsBestParser.AddminusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(MotherKnowsBestParser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constantExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(MotherKnowsBestParser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code muldivmodExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMuldivmodExpression(MotherKnowsBestParser.MuldivmodExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code muldivmodExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMuldivmodExpression(MotherKnowsBestParser.MuldivmodExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code shiftExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(MotherKnowsBestParser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code shiftExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(MotherKnowsBestParser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpression(MotherKnowsBestParser.ArrayExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpression(MotherKnowsBestParser.ArrayExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xorExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterXorExpression(MotherKnowsBestParser.XorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xorExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitXorExpression(MotherKnowsBestParser.XorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(MotherKnowsBestParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(MotherKnowsBestParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignmentExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(MotherKnowsBestParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(MotherKnowsBestParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relationExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelationExpression(MotherKnowsBestParser.RelationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relationExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelationExpression(MotherKnowsBestParser.RelationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOrExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(MotherKnowsBestParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOrExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(MotherKnowsBestParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code memberExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMemberExpression(MotherKnowsBestParser.MemberExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code memberExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMemberExpression(MotherKnowsBestParser.MemberExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariableExpression(MotherKnowsBestParser.VariableExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariableExpression(MotherKnowsBestParser.VariableExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(MotherKnowsBestParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(MotherKnowsBestParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(MotherKnowsBestParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(MotherKnowsBestParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selfExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSelfExpression(MotherKnowsBestParser.SelfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selfExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSelfExpression(MotherKnowsBestParser.SelfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code containedExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterContainedExpression(MotherKnowsBestParser.ContainedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code containedExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitContainedExpression(MotherKnowsBestParser.ContainedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalAndExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(MotherKnowsBestParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalAndExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(MotherKnowsBestParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(MotherKnowsBestParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(MotherKnowsBestParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(MotherKnowsBestParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression}
	 * labeled alternative in {@link MotherKnowsBestParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(MotherKnowsBestParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(MotherKnowsBestParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(MotherKnowsBestParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(MotherKnowsBestParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(MotherKnowsBestParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringType(MotherKnowsBestParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringType(MotherKnowsBestParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code voidType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void enterVoidType(MotherKnowsBestParser.VoidTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code voidType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void exitVoidType(MotherKnowsBestParser.VoidTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(MotherKnowsBestParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(MotherKnowsBestParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void enterClassType(MotherKnowsBestParser.ClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classType}
	 * labeled alternative in {@link MotherKnowsBestParser#type}.
	 * @param ctx the parse tree
	 */
	void exitClassType(MotherKnowsBestParser.ClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterBoolConstant(MotherKnowsBestParser.BoolConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitBoolConstant(MotherKnowsBestParser.BoolConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterIntConstant(MotherKnowsBestParser.IntConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitIntConstant(MotherKnowsBestParser.IntConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterStringConstant(MotherKnowsBestParser.StringConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitStringConstant(MotherKnowsBestParser.StringConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterNullConstant(MotherKnowsBestParser.NullConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullConstant}
	 * labeled alternative in {@link MotherKnowsBestParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitNullConstant(MotherKnowsBestParser.NullConstantContext ctx);
}