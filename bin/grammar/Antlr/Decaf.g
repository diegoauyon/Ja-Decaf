grammar Decaf;

options{
 language=Java;
 backtrack=true;
 }

@header
{
  package gramar;
} 

@lexer::header
{
  package gramar;
}

/*------------------------------------------------------------------
 * LEXER RULES 
 *------------------------------------------------------------------*/
 
ID	:	 LETTER (LETTER|DIGIT)*    		;

NUM	:	DIGIT(DIGIT)*			;

CHAR                   :	'\''  LETTER  '\'' 			;
 
fragment DIGIT	 :	'0'..'9'			;

fragment  LETTER  :	('a'..'z'|'A'..'Z'|'_')		;

//COMMENT	     :	 '//' (~('\n'|'\r'))*    { $setType(Token.SKIP);  $channel = HIDDEN; }   ;

WHITESPACE	 : 	( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;




/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
 
 program:	'class'  'Program' '{' (declaration)* '}'		;
  
declaration
	:	 varDeclaration| methodDeclaration 	;
	
varDeclaration 
	:	mType  ID  varDeclaration1 ';'  ;
	
varDeclaration1
	:	 '[' NUM ']' | 	;
	
methodDeclaration
	:	mType ID '(' (parameter (',' parameter)*)*   ')'  block;
			
mType
	:	('int' )| ('char' )| ('boolean') |( 'void');
	
parameter
	:	 mType ID ('['  ']')?;
	
block	:	'{'  (varDeclaration)*  (statement)* '}';

statement
	:	block 
		| 'if' '(' expression ')' block blockL
		| 'while' '(' expression ')' block
		| 'return' expressionL ':'
		| location '=' expression
		| expressionL ';'
	;
	
blockL	:	'else' block;	
expressionL	:	expression | ;

expression 
	:	relExp (cond_op expression | );
	
relExp	
	:	sumaExp (rel_op relExp | );

sumaExp	
	:	multiExp ( suma_op sumaExp | );
	
multiExp	
	:	negadorExp (mul_op multiExp | );
	
negadorExp
	:	'-' val | '!' val | val;
	
cond_op	
	:	'&&' | '||';
	
rel_op	
	:	'<=' | '<'  | '>' | '>='  | eq_op;
	
eq_op	:	'==' | '!=';

suma_op	:	'+' | '-';

mul_op	:	'*' | '/' | '%';

val	
	:	location |  methodCall | literal | '(' expression ')'; //location|

location 
	:	location1 | location1 '.' location;
	
location1
	:	ID location2;

location2
	:	'[' expression ']' | ;
	
methodCall
	:	ID '(' arg1 ')' ;
	
arg1	:	arg2 | ;

arg2 	:	(arg) (',' arg)* ;

arg	:	expression;
	
literal	:	int_literal | char_literal | bool_literal ;

int_literal
	:	NUM;
	
char_literal
	:	CHAR;
	
bool_literal
	:	'true' | 'false';
/*statement
	:	'if' '('  expression ')' block ('else' block)?
		| 'while' '(' expression ')' block
		| 'return' (expression)? ';'
		| block
		| location '=' expression
		| (expression)? ';'
	;	
	
	
	
location	:	ID (  '[' expression ']' )? ('.' location )? ;		


expression	:	(location | methodcall | literal | '-' expression | '!' expression | '(' expression ')') (op expression)* ;	

methodcall
	:	(ID)  '('  (arg (',' arg)*)*  ')';

arg	:	expression;

op	:	arith_op | rel_op | eq_op | cond_op ;

arith_op	:	('+')  |( '-') |( '*') |( '/') |( '%');

rel_op	:	('<') | ( '>') | ('<=') |( '>=') ;

eq_op	:	('==') | ('!=') ;

cond_op	:	('&&') | ('||') ;
	
literal	:	int_literal | char_literal | bool_literal ;

int_literal
	:	NUM;
	

char_literal
	:	CHAR;
		
bool_literal
	:	('true' )| ('false' ) ;	
	
*/
	