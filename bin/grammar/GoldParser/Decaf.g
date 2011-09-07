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

ID  :  LETTER (LETTER|DIGIT)*       ;

NUM : DIGIT(DIGIT)*     ;

CHAR                   :  '\''  LETTER  '\''      ;
 
fragment DIGIT   :  '0'..'9'      ;

fragment  LETTER  : ('a'..'z'|'A'..'Z'|'_')   ;

COMMENT      :   '//' (~('\n'|'\r'))*    { $setType(Token.SKIP);  $channel = HIDDEN; }   ;

WHITESPACE   :  ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+   { $channel = HIDDEN; } ;




/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
 
 program: 'class'  'Program' '{' (declaration)* '}'   ;
  
declaration
  :  varDeclaration| structDeclaration  | methodDeclaration   ;
  
structDeclaration
  : 'struct' ID '{' (varDeclaration)* '}' ; 
  
varDeclaration 
  : varType  ID  '[' NUM ']' ';' | varType  ID ';' ;
  
varType : ('int') | 'char' | 'boolean'  | 'void' | 'struct' ID;

methodDeclaration
  : methodType ID '(' (parameter (',' parameter)*)*   ')'  block;
      
methodType
  : ('int' )| ('char' )| ('boolean') |( 'void');
  
parameter
  :  parameterType ID ('['  ']')?;
  
parameterType
  : ('int')  | ( 'char')  | ( 'boolean'); 
  
block : '{'  (varDeclaration)*  (statement)* '}';

statement
  : 'if' '('  expression ')' block ('else' block)?
    | 'while' '(' expression ')' block
    | 'return' (expression)? ';'
    | block
    | location '=' expression
    | (expression)? ';'
  ; 
  
location  : ID (  '[' expression ']' )? ('.' location )? ;    


expression  : (location | methodcall | literal | '-' expression | '!' expression | '(' expression ')') (op expression)* ; 

methodcall
  : (ID)  '('  (arg (',' arg)*)*  ')';

arg : expression;

op  : arith_op | rel_op | eq_op | cond_op ;

arith_op  : ('+')  |( '-') |( '*') |( '/') |( '%');

rel_op  : ('<') | ( '>') | ('<=') |( '>=') ;

eq_op : ('==') | ('!=') ;

cond_op : ('&&') | ('||') ;
  
literal : int_literal | char_literal | bool_literal ;

int_literal
  : NUM;
  

char_literal
  : CHAR;
    
bool_literal
  : ('true' )| ('false' ) ; 