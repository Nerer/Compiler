grammar MotherKnowsBest;

program : (classDeclaration | functionDeclaration | varDeclaration)+;

classDeclaration : 'class' IDENTIFIER '{' (varDeclaration | functionDeclaration)* '}';

functionDeclaration : type IDENTIFIER? '(' (type IDENTIFIER (',' type IDENTIFIER)*)? ')' blockStatement;

varDeclaration : type IDENTIFIER ('=' expression)? (','IDENTIFIER ('=' expression)?)*';';

statement : blockStatement
    | expressionStatement
    | ifStatement
    | loopStatement
    | jumpStatement
    | varDeclaration
    ;


blockStatement: '{' statement* '}';

expressionStatement : expression? ';';

ifStatement: 'if' '(' expression ')' statement ('else' statement)?;

loopStatement : 'while' '(' expression ')' statement                            #whileStatement
        | 'for' '(' expression? ';' expression? ';' expression? ')' statement   #forStatement
        ;

jumpStatement :'return' expression? ';' #returnStatement
        | 'break' ';' #breakStatement
        | 'continue' ';' #continueStatement
        ;

expression : constant   #constantExpression
        | IDENTIFIER   #variableExpression
        | '(' expression ')' #containedExpression
        | expression operator=('++'|'--') #selfExpression
        | expression '(' (expression (',' expression)*) ? ')' #functionCallExpression
        | expression '[' expression ']' #arrayExpression
        | expression '.'  IDENTIFIER #memberExpression
        | operator=('+'|'-'|'~'|'!'|'++'|'--') expression #unaryExpression
        | 'new' type ('[' expression? ']')* #newExpression
        | expression operator=('*'|'/'|'%') expression #muldivmodExpression
        | expression operator=('+'|'-') expression #addminusExpression
        | expression operator=('<<'|'>>') expression #shiftExpression
        | expression operator=('<'|'>'|'<='|'>='|'!='|'==') expression #relationExpression
        | expression operator='&' expression #andExpression
        | expression operator='^' expression #xorExpression
        | expression operator='|' expression #orExpression
        | expression operator='&&' expression #logicalAndExpression
        | expression operator='||' expression #logicalOrExpression
        | <assoc=right> expression operator='=' expression #assignmentExpression
        ;

type : 'void' #voidType
    | 'int' #intType
    | 'bool' #boolType
    | 'string' #stringType
    | IDENTIFIER #classType
    | type '[' ']' #arrayType
    ;

constant : ('true'|'false') #boolConstant
    | INTEGER #intConstant
    | STRING #stringConstant
    | 'null' #nullConstant
    ;

IDENTIFIER : [a-zA-Z_]([a-zA-Z_0-9])*;

INTEGER : [0-9]+;

STRING : '\"' CHAR* '\"';

fragment

CHAR	:	~["\\\r\n]
		|	'\\' ['"?abfnrtv\\]
		;

LINECOMMENT :   '//' ~[\r\n]*   ->  skip;

WHITESPACE  :   [ \t\r\n]+  ->  skip;