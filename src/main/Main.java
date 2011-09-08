package main;
import interfaz.IDecaf;

import java.io.*;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import tabla.Const;
import tabla.Const.PasoMetodo;
import tabla.Const.TipoLiteral;
import tabla.Const.TipoPrimitivo;
import tabla.Tipo;
import tabla.clases.*;


import goldengine.java.*;

/*
 * Licensed Material - Property of Matthew Hawkins (hawkini@myrealbox.com)
 *
 * GOLDParser - code ported from VB - Author Devin Cook. All rights reserved.
 *
 * Modifications to this code are allowed as it is a helper class to use the engine.
 */
/**---------------------------------------------------------------------------------------<br>
 *
 *      Source File:    AppleSample.java<br>
 *
 *      Author:         Matthew Hawkins<br>
 *
 *      Description:    A Sample class, takes in a set of files and runs the GOLDParser
 *						engine on them.<br>
 *
 *
 *---------------------------------------------------------------------------------------<br>
 *
 *      Revision List<br>
 *<pre>
 *      Author          Version         Description
 *      ------          -------         -----------
 *      MPH             1.0             First Issue</pre><br>
 *
 *---------------------------------------------------------------------------------------<br>
 *
 *      IMPORT: java.io <br>
 *              goldengine.java <br>
 *
 *---------------------------------------------------------------------------------------<br>
 */
public class Main implements GPMessageConstants
{
	
	private interface SymbolConstants 
    {
       final int SYMBOL_EOF               =  0;  // (EOF)
       final int SYMBOL_ERROR             =  1;  // (Error)
       final int SYMBOL_WHITESPACE        =  2;  // (Whitespace)
       final int SYMBOL_COMMENTEND        =  3;  // (Comment End)
       final int SYMBOL_COMMENTLINE       =  4;  // (Comment Line)
       final int SYMBOL_COMMENTSTART      =  5;  // (Comment Start)
       final int SYMBOL_MINUS             =  6;  // '-'
       final int SYMBOL_EXCLAM            =  7;  // '!'
       final int SYMBOL_EXCLAMEQ          =  8;  // '!='
       final int SYMBOL_PERCENT           =  9;  // '%'
       final int SYMBOL_AMPAMP            = 10;  // '&&'
       final int SYMBOL_LPARAN            = 11;  // '('
       final int SYMBOL_RPARAN            = 12;  // ')'
       final int SYMBOL_TIMES             = 13;  // '*'
       final int SYMBOL_COMMA             = 14;  // ','
       final int SYMBOL_DOT               = 15;  // '.'
       final int SYMBOL_DIV               = 16;  // '/'
       final int SYMBOL_SEMI              = 17;  // ';'
       final int SYMBOL_LBRACKET          = 18;  // '['
       final int SYMBOL_RBRACKET          = 19;  // ']'
       final int SYMBOL_LBRACE            = 20;  // '{'
       final int SYMBOL_PIPEPIPE          = 21;  // '||'
       final int SYMBOL_RBRACE            = 22;  // '}'
       final int SYMBOL_PLUS              = 23;  // '+'
       final int SYMBOL_LT                = 24;  // '<'
       final int SYMBOL_LTEQ              = 25;  // '<='
       final int SYMBOL_EQ                = 26;  // '='
       final int SYMBOL_EQEQ              = 27;  // '=='
       final int SYMBOL_GT                = 28;  // '>'
       final int SYMBOL_GTEQ              = 29;  // '>='
       final int SYMBOL_BOOLEAN           = 30;  // boolean
       final int SYMBOL_CHAR              = 31;  // char
       final int SYMBOL_CHARACTER         = 32;  // character
       final int SYMBOL_CLASS             = 33;  // class
       final int SYMBOL_ELSE              = 34;  // else
       final int SYMBOL_FALSE             = 35;  // false
       final int SYMBOL_ID                = 36;  // id
       final int SYMBOL_IF                = 37;  // if
       final int SYMBOL_INT               = 38;  // int
       final int SYMBOL_NUM               = 39;  // num
       final int SYMBOL_PROGRAM           = 40;  // Program
       final int SYMBOL_RETURN            = 41;  // return
       final int SYMBOL_STRUCT            = 42;  // struct
       final int SYMBOL_TRUE              = 43;  // true
       final int SYMBOL_VOID              = 44;  // void
       final int SYMBOL_WHILE             = 45;  // while
       final int SYMBOL_ARG               = 46;  // <arg>
       final int SYMBOL_ARG1              = 47;  // <arg1>
       final int SYMBOL_ARG2              = 48;  // <arg2>
       final int SYMBOL_BLOCK             = 49;  // <block>
       final int SYMBOL_BLOCKL            = 50;  // <blockL>
       final int SYMBOL_BOOL_LITERAL      = 51;  // <bool_literal>
       final int SYMBOL_CHAR_LITERAL      = 52;  // <char_literal>
       final int SYMBOL_COND_OP           = 53;  // <cond_op>
       final int SYMBOL_DECLARATION       = 54;  // <declaration>
       final int SYMBOL_DECLARATIONK      = 55;  // <declarationK>
       final int SYMBOL_EQ_OP             = 56;  // <eq_op>
       final int SYMBOL_EXPRESSION        = 57;  // <expression>
       final int SYMBOL_EXPRESSIONL       = 58;  // <expressionL>
       final int SYMBOL_INT_LITERAL       = 59;  // <int_literal>
       final int SYMBOL_LITERAL           = 60;  // <literal>
       final int SYMBOL_LOCATION          = 61;  // <location>
       final int SYMBOL_LOCATION1         = 62;  // <location1>
       final int SYMBOL_LOCATION2         = 63;  // <location2>
       final int SYMBOL_METHODCALL        = 64;  // <methodCall>
       final int SYMBOL_METHODDECLARATION = 65;  // <methodDeclaration>
       final int SYMBOL_MUL_OP            = 66;  // <mul_op>
       final int SYMBOL_MULTIEXP          = 67;  // <multiExp>
       final int SYMBOL_NEGADOREXP        = 68;  // <negadorExp>
       final int SYMBOL_PARAMETER         = 69;  // <parameter>
       final int SYMBOL_PARAMETER1        = 70;  // <parameter1>
       final int SYMBOL_PARAMETER2        = 71;  // <parameter2>
       final int SYMBOL_PARAMETERTYPE     = 72;  // <parameterType>
       final int SYMBOL_PROGRAM2          = 73;  // <Program>
       final int SYMBOL_REL_OP            = 74;  // <rel_op>
       final int SYMBOL_RELEXP            = 75;  // <relExp>
       final int SYMBOL_STATEMENT         = 76;  // <statement>
       final int SYMBOL_STATEMENTK        = 77;  // <statementK>
       final int SYMBOL_STRUCTDECLARATION = 78;  // <structDeclaration>
       final int SYMBOL_SUMA_OP           = 79;  // <suma_op>
       final int SYMBOL_SUMAEXP           = 80;  // <sumaExp>
       final int SYMBOL_VAL               = 81;  // <val>
       final int SYMBOL_VARDECLARATION    = 82;  // <varDeclaration>
       final int SYMBOL_VARDECLARATION1   = 83;  // <varDeclaration1>
       final int SYMBOL_VARDECLARATIONK   = 84;  // <varDeclarationK>
       final int SYMBOL_VARMETHODTYPE     = 85;  // <varmethodType>
    };

    private interface RuleConstants
    {
       final int RULE_PROGRAM_CLASS_PROGRAM_LBRACE_RBRACE       =  0;  // <Program> ::= class Program '{' <declarationK> '}'
       final int RULE_DECLARATIONK                              =  1;  // <declarationK> ::= <declaration> <declarationK>
       final int RULE_DECLARATIONK2                             =  2;  // <declarationK> ::= 
       final int RULE_DECLARATION                               =  3;  // <declaration> ::= <structDeclaration>
       final int RULE_DECLARATION2                              =  4;  // <declaration> ::= <varDeclaration>
       final int RULE_DECLARATION3                              =  5;  // <declaration> ::= <methodDeclaration>
       final int RULE_VARDECLARATION_ID_SEMI                    =  6;  // <varDeclaration> ::= <varmethodType> id <varDeclaration1> ';'
       final int RULE_VARDECLARATION1_LBRACKET_NUM_RBRACKET     =  7;  // <varDeclaration1> ::= '[' num ']'
       final int RULE_VARDECLARATION1                           =  8;  // <varDeclaration1> ::= 
       final int RULE_STRUCTDECLARATION_STRUCT_ID_LBRACE_RBRACE =  9;  // <structDeclaration> ::= struct id '{' <varDeclarationK> '}'
       final int RULE_VARDECLARATIONK                           = 10;  // <varDeclarationK> ::= <varDeclaration> <varDeclarationK>
       final int RULE_VARDECLARATIONK2                          = 11;  // <varDeclarationK> ::= 
       final int RULE_VARMETHODTYPE_INT                         = 12;  // <varmethodType> ::= int
       final int RULE_VARMETHODTYPE_CHAR                        = 13;  // <varmethodType> ::= char
       final int RULE_VARMETHODTYPE_BOOLEAN                     = 14;  // <varmethodType> ::= boolean
       final int RULE_VARMETHODTYPE_VOID                        = 15;  // <varmethodType> ::= void
       final int RULE_VARMETHODTYPE_STRUCT_ID                   = 16;  // <varmethodType> ::= struct id
       final int RULE_VARMETHODTYPE                             = 17;  // <varmethodType> ::= <structDeclaration>
       final int RULE_METHODDECLARATION_ID_LPARAN_RPARAN        = 18;  // <methodDeclaration> ::= <varmethodType> id '(' <parameter1> ')' <block>
       final int RULE_PARAMETER1                                = 19;  // <parameter1> ::= <parameter2>
       final int RULE_PARAMETER12                               = 20;  // <parameter1> ::= 
       final int RULE_PARAMETER2                                = 21;  // <parameter2> ::= <parameter>
       final int RULE_PARAMETER2_COMMA                          = 22;  // <parameter2> ::= <parameter2> ',' <parameter>
       final int RULE_PARAMETER_ID                              = 23;  // <parameter> ::= <parameterType> id
       final int RULE_PARAMETER_ID_LBRACKET_RBRACKET            = 24;  // <parameter> ::= <parameterType> id '[' ']'
       final int RULE_PARAMETERTYPE_INT                         = 25;  // <parameterType> ::= int
       final int RULE_PARAMETERTYPE_CHAR                        = 26;  // <parameterType> ::= char
       final int RULE_PARAMETERTYPE_BOOLEAN                     = 27;  // <parameterType> ::= boolean
       final int RULE_BLOCK_LBRACE_RBRACE                       = 28;  // <block> ::= '{' <varDeclarationK> <statementK> '}'
       final int RULE_STATEMENTK                                = 29;  // <statementK> ::= <statement> <statementK>
       final int RULE_STATEMENTK2                               = 30;  // <statementK> ::= 
       final int RULE_STATEMENT                                 = 31;  // <statement> ::= <block>
       final int RULE_STATEMENT_IF_LPARAN_RPARAN                = 32;  // <statement> ::= if '(' <expression> ')' <block> <blockL>
       final int RULE_STATEMENT_WHILE_LPARAN_RPARAN             = 33;  // <statement> ::= while '(' <expression> ')' <block>
       final int RULE_STATEMENT_RETURN_SEMI                     = 34;  // <statement> ::= return <expressionL> ';'
       final int RULE_STATEMENT_EQ                              = 35;  // <statement> ::= <location> '=' <expression>
       final int RULE_STATEMENT_SEMI                            = 36;  // <statement> ::= <expressionL> ';'
       final int RULE_BLOCKL_ELSE                               = 37;  // <blockL> ::= else <block>
       final int RULE_BLOCKL                                    = 38;  // <blockL> ::= 
       final int RULE_EXPRESSIONL                               = 39;  // <expressionL> ::= <expression>
       final int RULE_EXPRESSIONL2                              = 40;  // <expressionL> ::= 
       final int RULE_EXPRESSION                                = 41;  // <expression> ::= <relExp> <cond_op> <expression>
       final int RULE_EXPRESSION2                               = 42;  // <expression> ::= <relExp>
       final int RULE_RELEXP                                    = 43;  // <relExp> ::= <sumaExp> <rel_op> <relExp>
       final int RULE_RELEXP2                                   = 44;  // <relExp> ::= <sumaExp>
       final int RULE_SUMAEXP                                   = 45;  // <sumaExp> ::= <multiExp> <suma_op> <sumaExp>
       final int RULE_SUMAEXP2                                  = 46;  // <sumaExp> ::= <multiExp>
       final int RULE_MULTIEXP                                  = 47;  // <multiExp> ::= <negadorExp> <mul_op> <multiExp>
       final int RULE_MULTIEXP2                                 = 48;  // <multiExp> ::= <negadorExp>
       final int RULE_NEGADOREXP_MINUS                          = 49;  // <negadorExp> ::= '-' <val>
       final int RULE_NEGADOREXP_EXCLAM                         = 50;  // <negadorExp> ::= '!' <val>
       final int RULE_NEGADOREXP                                = 51;  // <negadorExp> ::= <val>
       final int RULE_COND_OP_AMPAMP                            = 52;  // <cond_op> ::= '&&'
       final int RULE_COND_OP_PIPEPIPE                          = 53;  // <cond_op> ::= '||'
       final int RULE_REL_OP_LTEQ                               = 54;  // <rel_op> ::= '<='
       final int RULE_REL_OP_LT                                 = 55;  // <rel_op> ::= '<'
       final int RULE_REL_OP_GT                                 = 56;  // <rel_op> ::= '>'
       final int RULE_REL_OP_GTEQ                               = 57;  // <rel_op> ::= '>='
       final int RULE_REL_OP                                    = 58;  // <rel_op> ::= <eq_op>
       final int RULE_EQ_OP_EQEQ                                = 59;  // <eq_op> ::= '=='
       final int RULE_EQ_OP_EXCLAMEQ                            = 60;  // <eq_op> ::= '!='
       final int RULE_SUMA_OP_PLUS                              = 61;  // <suma_op> ::= '+'
       final int RULE_SUMA_OP_MINUS                             = 62;  // <suma_op> ::= '-'
       final int RULE_MUL_OP_TIMES                              = 63;  // <mul_op> ::= '*'
       final int RULE_MUL_OP_DIV                                = 64;  // <mul_op> ::= '/'
       final int RULE_MUL_OP_PERCENT                            = 65;  // <mul_op> ::= '%'
       final int RULE_VAL                                       = 66;  // <val> ::= <location>
       final int RULE_VAL2                                      = 67;  // <val> ::= <methodCall>
       final int RULE_VAL3                                      = 68;  // <val> ::= <literal>
       final int RULE_VAL_LPARAN_RPARAN                         = 69;  // <val> ::= '(' <expression> ')'
       final int RULE_LOCATION                                  = 70;  // <location> ::= <location1>
       final int RULE_LOCATION_DOT                              = 71;  // <location> ::= <location1> '.' <location>
       final int RULE_LOCATION1_ID                              = 72;  // <location1> ::= id <location2>
       final int RULE_LOCATION2_LBRACKET_RBRACKET               = 73;  // <location2> ::= '[' <expression> ']'
       final int RULE_LOCATION2                                 = 74;  // <location2> ::= 
       final int RULE_METHODCALL_ID_LPARAN_RPARAN               = 75;  // <methodCall> ::= id '(' <arg1> ')'
       final int RULE_ARG1                                      = 76;  // <arg1> ::= <arg2>
       final int RULE_ARG12                                     = 77;  // <arg1> ::= 
       final int RULE_ARG2                                      = 78;  // <arg2> ::= <arg>
       final int RULE_ARG2_COMMA                                = 79;  // <arg2> ::= <arg2> ',' <arg>
       final int RULE_ARG                                       = 80;  // <arg> ::= <expression>
       final int RULE_LITERAL                                   = 81;  // <literal> ::= <int_literal>
       final int RULE_LITERAL2                                  = 82;  // <literal> ::= <char_literal>
       final int RULE_LITERAL3                                  = 83;  // <literal> ::= <bool_literal>
       final int RULE_INT_LITERAL_NUM                           = 84;  // <int_literal> ::= num
       final int RULE_CHAR_LITERAL_CHARACTER                    = 85;  // <char_literal> ::= character
       final int RULE_BOOL_LITERAL_TRUE                         = 86;  // <bool_literal> ::= true
       final int RULE_BOOL_LITERAL_FALSE                        = 87;  // <bool_literal> ::= false
    };

	
    public GOLDParser parser;
    private String rutaGramatica= "src/grammar/GoldParser/Decaf.cgt";
    private boolean huboErrores= true;
    private IDecaf padre;
    private Clase clase;
    
    private Stack stack = new Stack();

    /***************************************************************
 	 * This class will run the engine, and needs a file called config.dat
     * in the current directory. This file should contain two lines,
     * The first should be the absolute path name to the .cgt file, the second
     * should be the source file you wish to parse.
 	 * @param args Array of arguments.
 	 ***************************************************************/
    public Main(String textToParse, IDecaf padre)
    {
    	this.padre=padre;
        String compiledGrammar = "";

            compiledGrammar = rutaGramatica;
    		//textToParse = buffR.readLine();

	    
        parser = new GOLDParser();
        boolean varDeclaration = false;
        boolean varVariable = false;

        try
        {
            parser.loadCompiledGrammar(compiledGrammar);
            
            if ( (textToParse != null))  
            {
            	parser.openFile(textToParse);	
            }
            else
            {
            	padre.imprimir("Por favor intente poniendo un archivo",1);
            }
        	
        	
        	
        }
        catch(ParserException parse)
        {
        	padre.imprimir("**PARSER ERROR**\n" + parse.toString(),1);
        	padre.imprimir("Error de Parser: No cargó gramática o el archivo a parsear",1);
            System.exit(1);
        }

        boolean done = false;
        int response = -1;
        if ( (textToParse != null))  
        {
	        while(!done)
	        {
	            try
	            {
	            	response = parser.parse();
	            }
	            catch(ParserException parse)
	            {
	            	padre.imprimir("**PARSER ERROR**\n" + parse.toString() ,1);
	            	padre.imprimir(""+parse.getCause() + "  "+parse.getMessage(),1);
	            	padre.imprimir("Linea: "+parse.getMessage(),1);
	            	imprimirErrores();
	                System.exit(1);
	            }
	
	            switch(response)
	            {
	                case gpMsgTokenRead:
	                    /* A token was read by the parser. The Token Object can be accessed 
						   through the CurrentToken() property:  Parser.CurrentToken */
	
	                    // ************************************** log file
	                    //System.out.println("gpMsgTokenRead");
	                    Token myTok = parser.currentToken();
	                    int cos=myTok.getPSymbol().getTableIndex();
	                   // padre.imprimir("gpMsgTokenRead:   "+cos+"  "+myTok.getPSymbol()+ "    "+myTok.getText()+ "\n",2);
	                    switch (myTok.getPSymbol().getTableIndex())
	                    {
	                    	case SymbolConstants.SYMBOL_PROGRAM:
	                    	   	//System.out.println("Simbolo ID: "+myTok.getText());
	                    		stack.push(myTok.getText());
	                    		break;
	                    	case SymbolConstants.SYMBOL_ID:
	                    		stack.push(myTok.getData().toString());
	                    		break;
	                    	case SymbolConstants.SYMBOL_NUM:
	                    		int numero = Integer.parseInt(myTok.getData().toString());
	                    		stack.push(numero);
	                    	
	                    }

	                    // ************************************** end log
	
	                    break;
	
	                case gpMsgReduction:
	                    /* This message is returned when a rule was reduced by the parse engine.
	                    The CurrentReduction property is assigned a Reduction object
	                    containing the rule and its related tokens. You can reassign this
	                    property to your own customized class. If this is not the case,
	                    this message can be ignored and the Reduction object will be used
	                    to store the parse tree.  */
	
	                    switch(parser.currentReduction().getParentRule().getTableIndex())
	                    {
	                       case RuleConstants.RULE_PROGRAM_CLASS_PROGRAM_LBRACE_RBRACE:
	                          //<Program> ::= class Program '{' <declarationK> '}'
	                    	   padre.imprimir("<Program> ::= class Program '{' <declarationK> '}'", 2);
	                    	   // stack.push(new Clase(new Cuerpo((LinkedList<Declaracion>)stack.pop()),(String)stack.pop()));
	                    	   while (stack.size()>0)
	                    	   {
	                    		   System.out.println("Stack: "+stack.pop());
	                    	   }
	                    	   //padre.imprimir(parser.currentToken().getText(),2);
	                          break;
	                       case RuleConstants.RULE_DECLARATIONK:
	                          //<declarationK> ::= <declaration> <declarationK>
	                    	   try
		                    	  {
		                    		   Declaracion variable = (Declaracion)stack.peek();
		                    		   stack.pop();
		                    	  	   LinkedList<Declaracion> listaVar = new LinkedList<Declaracion>();
		                    	       listaVar.add(variable);
		                    	       stack.push(listaVar);
		                    	  }
		                    	  catch (Exception e)
		                    	  {
		                    		 LinkedList<Declaracion> listaVar = ((LinkedList<Declaracion>) stack.pop());
		                    		 Declaracion variable = (Declaracion)stack.pop();
		                    		 listaVar.add(variable);
		                    		 stack.push(listaVar);
		                    	  }   //inked
		                    	   
	                          break;
	                       case RuleConstants.RULE_DECLARATIONK2:
	                          //<declarationK> ::= 
	                    	   stack.push(new LinkedList<Declaracion>());
	                    	   break;
	                       case RuleConstants.RULE_DECLARATION:
	                          //<declaration> ::= <structDeclaration>
	                          break;
	                       case RuleConstants.RULE_DECLARATION2:
	                          //<declaration> ::= <varDeclaration>
	                          break;
	                       case RuleConstants.RULE_DECLARATION3:
	                          //<declaration> ::= <methodDeclaration>
	                          break;
	                       case RuleConstants.RULE_VARDECLARATION_ID_SEMI:
	                          //<varDeclaration> ::= <varmethodType> id <varDeclaration1> ';'
	                    	   //padre.imprimir("<varDeclaration> ::= <varmethodType> id <varDeclaration1> ';'", 2);
	                    	   if (varVariable == false)
	                    		  stack.push(new Variable(null,(Tipo)stack.pop(),(String) stack.pop()));
	                    	   else
	                    		   varVariable = true;
	                          break;
	                       case RuleConstants.RULE_VARDECLARATION1_LBRACKET_NUM_RBRACKET:
	                          //<varDeclaration1> ::= '[' num ']'
	                    	   Tipo tip = new Tipo();
	                    	   int numero = (Integer)stack.pop();
	                    	   if (numero<1)
	                    	   {
	                    		   padre.imprimir("El numero del Array debe ser mayor a 1. Linea: "+parser.currentLineNumber(), 1);
	                    		   done = true;
	                    		   huboErrores = true;
	                    	   }
	                    	   stack.push(tip.crearArregloDesdeTipo(numero,(Tipo)stack.pop()));
	                          break;
	                       case RuleConstants.RULE_VARDECLARATION1:
	                          //<varDeclaration1> ::= 
	                    	   //padre.imprimir("<varDeclaration1> ::=", 2);
	                    	   stack.push(new Variable(null,(Tipo)stack.pop(),(String)stack.pop()));
	                    	   varVariable = true;
	                          break;
	                       case RuleConstants.RULE_STRUCTDECLARATION_STRUCT_ID_LBRACE_RBRACE:
	                          //<structDeclaration> ::= struct id '{' <varDeclarationK> '}'
	                    	   stack.push(new Estructura((LinkedList<Variable>) stack.pop(),(String)stack.pop()));
	                          break;
	                       case RuleConstants.RULE_VARDECLARATIONK:
	                          //<varDeclarationK> ::= <varDeclaration> <varDeclarationK>
	                    	  try
	                    	  {
	                    		   Variable variable = (Variable)stack.peek();
	                    		   stack.pop();
	                    	  	   LinkedList<Variable> listaVar = new LinkedList<Variable>();
	                    	       listaVar.add(variable);
	                    	       stack.push(listaVar);
	                    	  }
	                    	  catch (Exception e)
	                    	  {
	                    		 LinkedList<Variable> listaVar = ((LinkedList<Variable>) stack.pop());
	                    		 Variable variable = (Variable)stack.pop();
	                    		 listaVar.add(variable);
	                    		 stack.push(listaVar);
	                    	  }   //inked
	                    	   
	                    	   
	                    	   
	                    	   
	                    	   
	                          break;
	                       case RuleConstants.RULE_VARDECLARATIONK2:
	                          //<varDeclarationK> ::= 
	                    	   stack.push(new LinkedList<Variable>());
	                          break;
	                       case RuleConstants.RULE_VARMETHODTYPE_INT:
	                          //<varmethodType> ::= int
	                    	   stack.push(new Tipo(TipoPrimitivo.integer));
	                          break;
	                       case RuleConstants.RULE_VARMETHODTYPE_CHAR:
	                          //<varmethodType> ::= char
	                    	   stack.push(new Tipo(TipoPrimitivo.character));
	                          break;
	                       case RuleConstants.RULE_VARMETHODTYPE_BOOLEAN:
	                          //<varmethodType> ::= boolean
	                    	   stack.push(new Tipo(TipoPrimitivo.bool));
	                          break;
	                       case RuleConstants.RULE_VARMETHODTYPE_VOID:
	                          //<varmethodType> ::= void
	                    	   //padre.imprimir("<varmethodType> ::= void",2);
	                    	   stack.push(new Tipo(TipoPrimitivo.void_));
	                          break;
	                       case RuleConstants.RULE_VARMETHODTYPE_STRUCT_ID:
	                          //<varmethodType> ::= struct id
	                    	   
	                          break;
	                       case RuleConstants.RULE_VARMETHODTYPE:
	                          //<varmethodType> ::= <structDeclaration>
	                          break;
	                       case RuleConstants.RULE_METHODDECLARATION_ID_LPARAN_RPARAN:
	                          //<methodDeclaration> ::= <varmethodType> id '(' <parameter1> ')' <block>
	                    	   //padre.imprimir("<methodDeclaration> ::= <varmethodType> id '(' <parameter1> ')' <block>",2);
	                    	   stack.push(new Funcion((Cuerpo)stack.pop(),(LinkedList<Variable>)stack.pop(),(LinkedList<Parametro>)stack.pop(),(Tipo)stack.pop(),(String)stack.pop()));
	                          break;
	                       case RuleConstants.RULE_PARAMETER1:
	                          //<parameter1> ::= <parameter2>
	                    	   //padre.imprimir("Entra aqui?",2);
	                          break;
	                       case RuleConstants.RULE_PARAMETER12:
	                          //<parameter1> ::= 
	                    	  	stack.push(new LinkedList<Parametro>());
	                          break;
	                       case RuleConstants.RULE_PARAMETER2:
	                          //<parameter2> ::= <parameter>
	                    	   padre.imprimir("Entra aqui? dfdfdfdf",2);
	                    	   LinkedList<Parametro> listaParam =new LinkedList<Parametro>();
	                    	   listaParam.add((Parametro)stack.pop());
	                    	   stack.push(listaParam);
	                          break;
	                       case RuleConstants.RULE_PARAMETER2_COMMA:
	                          //<parameter2> ::= <parameter2> ',' <parameter>
	                    	   LinkedList<Parametro> para=(LinkedList<Parametro>)stack.pop();
	                    	   LinkedList<Parametro> para2= (LinkedList<Parametro>)stack.pop();
	                    	   //System.out.println("Tama:"+para2.size());
	                    	   for (int i=0; i<para.size();i++)
	                    	   {
	                    		   para2.add((Parametro)para.get(i));
	                    	   }
	                    	   //System.out.println("Tama:"+para2.size());
	                    	   stack.push(para2);
	                          break;
	                       case RuleConstants.RULE_PARAMETER_ID:
	                          //<parameter> ::= <parameterType> id
	                    	   try
	                    	   {
	                    		   LinkedList<Parametro> valor = (LinkedList<Parametro>) stack.peek();
	                    		   stack.pop();
	                    		   valor.add(new Parametro((Tipo)stack.pop(),(String)stack.pop(),PasoMetodo.porValor));
	                    		   stack.push(valor);
	                    	   }
	                    	   catch (Exception ex)
	                    	   {
	                    	   	   LinkedList<Parametro> listaParam1 =new LinkedList<Parametro>();
	                    	   	   listaParam1.add(new Parametro((Tipo)stack.pop(),(String)stack.pop(),PasoMetodo.porValor));
	                    	   	   stack.push(listaParam1);
	                    	   }
	                          break;
	                       case RuleConstants.RULE_PARAMETER_ID_LBRACKET_RBRACKET:
	                          //<parameter> ::= <parameterType> id '[' ']'
	                    	   try
	                    	   {
	                    		   LinkedList<Parametro> valor = (LinkedList<Parametro>) stack.peek();
	                    		   stack.pop();
	                    		   Tipo tipo = (Tipo)stack.pop();
		                    	   String id = (String)stack.pop();
		                    	   
		                    	   tipo = tipo.crearArregloTipo(tipo.tipoPrimitivo);
	                    		   valor.add(new Parametro(tipo,id,PasoMetodo.porValor));
	                    		   stack.push(valor);
	                    	   }
	                    	   catch (Exception ex)
	                    	   {
	                    	   	   LinkedList<Parametro> listaParam1 =new LinkedList<Parametro>();
	                    	   	   Tipo tipo = (Tipo)stack.pop();
		                    	   String id = (String)stack.pop();
		                    	   tipo = tipo.crearArregloTipo(tipo.tipoPrimitivo);
		                    	   listaParam1.add(new Parametro(tipo,id,PasoMetodo.porValor));
	                    	   	   stack.push(listaParam1);
	                    	   }
	                    	   

	                          break;
	                       case RuleConstants.RULE_PARAMETERTYPE_INT:
	                          //<parameterType> ::= int
	                    	   stack.push(new Tipo(TipoPrimitivo.integer));
	                          break;
	                       case RuleConstants.RULE_PARAMETERTYPE_CHAR:
	                          //<parameterType> ::= char
	                    	   stack.push(new Tipo(TipoPrimitivo.character));
	                          break;
	                       case RuleConstants.RULE_PARAMETERTYPE_BOOLEAN:
	                          //<parameterType> ::= boolean
	                    	   stack.push(new Tipo(TipoPrimitivo.bool));
	                          break;
	                       case RuleConstants.RULE_BLOCK_LBRACE_RBRACE:
	                          //<block> ::= '{' <varDeclarationK> <statementK> '}'
	                    	   padre.imprimir("<block> ::= '{' <varDeclarationK> <statementK> '}'", 2);
	                    	   stack.push(new Cuerpo((LinkedList<Declaracion>)stack.pop()));
	                          break;
	                       case RuleConstants.RULE_STATEMENTK:
	                          //<statementK> ::= <statement> <statementK>
	                    	   try
		                    	  {
		                    		   Declaracion variable = (Declaracion)stack.peek();
		                    		   stack.pop();
		                    	  	   LinkedList<Declaracion> listaVar = new LinkedList<Declaracion>();
		                    	       listaVar.add(variable);
		                    	       stack.push(listaVar);
		                    	  }
		                    	  catch (Exception e)
		                    	  {
		                    		 LinkedList<Declaracion> listaVar = ((LinkedList<Declaracion>) stack.pop());
		                    		 Declaracion variable = (Declaracion)stack.pop();
		                    		 listaVar.add(variable);
		                    		 stack.push(listaVar);
		                    	  }   //inked
		                    	   
	                          break;
	                       case RuleConstants.RULE_STATEMENTK2:
	                          //<statementK> ::= 
	                    	  // padre.imprimir("<statementK> ::=", 2);
	                    	   LinkedList<Declaracion> listaDeclaracion = new LinkedList<Declaracion>();
	                    	   //listaDeclaracion.add((Declaracion)null);
	                    	   stack.push(listaDeclaracion);
	                          break;
	                       case RuleConstants.RULE_STATEMENT:
	                          //<statement> ::= <block>
	                          break;
	                       case RuleConstants.RULE_STATEMENT_IF_LPARAN_RPARAN:
	                          //<statement> ::= if '(' <expression> ')' <block> <blockL>
	                          break;
	                       case RuleConstants.RULE_STATEMENT_WHILE_LPARAN_RPARAN:
	                          //<statement> ::= while '(' <expression> ')' <block>
	                          break;
	                       case RuleConstants.RULE_STATEMENT_RETURN_SEMI:
	                          //<statement> ::= return <expressionL> ';'
	                          break;
	                       case RuleConstants.RULE_STATEMENT_EQ:
	                          //<statement> ::= <location> '=' <expression>
	                          break;
	                       case RuleConstants.RULE_STATEMENT_SEMI:
	                          //<statement> ::= <expressionL> ';'
	                          break;
	                       case RuleConstants.RULE_BLOCKL_ELSE:
	                          //<blockL> ::= else <block>
	                          break;
	                       case RuleConstants.RULE_BLOCKL:
	                          //<blockL> ::= 
	                          break;
	                       case RuleConstants.RULE_EXPRESSIONL:
	                          //<expressionL> ::= <expression>
	                          break;
	                       case RuleConstants.RULE_EXPRESSIONL2:
	                          //<expressionL> ::= 
	                          break;
	                       case RuleConstants.RULE_EXPRESSION:
	                          //<expression> ::= <relExp> <cond_op> <expression>
	                          break;
	                       case RuleConstants.RULE_EXPRESSION2:
	                          //<expression> ::= <relExp>
	                          break;
	                       case RuleConstants.RULE_RELEXP:
	                          //<relExp> ::= <sumaExp> <rel_op> <relExp>
	                          break;
	                       case RuleConstants.RULE_RELEXP2:
	                          //<relExp> ::= <sumaExp>
	                          break;
	                       case RuleConstants.RULE_SUMAEXP:
	                          //<sumaExp> ::= <multiExp> <suma_op> <sumaExp>
	                          break;
	                       case RuleConstants.RULE_SUMAEXP2:
	                          //<sumaExp> ::= <multiExp>
	                          break;
	                       case RuleConstants.RULE_MULTIEXP:
	                          //<multiExp> ::= <negadorExp> <mul_op> <multiExp>
	                          break;
	                       case RuleConstants.RULE_MULTIEXP2:
	                          //<multiExp> ::= <negadorExp>
	                          break;
	                       case RuleConstants.RULE_NEGADOREXP_MINUS:
	                          //<negadorExp> ::= '-' <val>
	                          break;
	                       case RuleConstants.RULE_NEGADOREXP_EXCLAM:
	                          //<negadorExp> ::= '!' <val>
	                          break;
	                       case RuleConstants.RULE_NEGADOREXP:
	                          //<negadorExp> ::= <val>
	                          break;
	                       case RuleConstants.RULE_COND_OP_AMPAMP:
	                          //<cond_op> ::= '&&'
	                          break;
	                       case RuleConstants.RULE_COND_OP_PIPEPIPE:
	                          //<cond_op> ::= '||'
	                          break;
	                       case RuleConstants.RULE_REL_OP_LTEQ:
	                          //<rel_op> ::= '<='
	                          break;
	                       case RuleConstants.RULE_REL_OP_LT:
	                          //<rel_op> ::= '<'
	                          break;
	                       case RuleConstants.RULE_REL_OP_GT:
	                          //<rel_op> ::= '>'
	                          break;
	                       case RuleConstants.RULE_REL_OP_GTEQ:
	                          //<rel_op> ::= '>='
	                          break;
	                       case RuleConstants.RULE_REL_OP:
	                          //<rel_op> ::= <eq_op>
	                          break;
	                       case RuleConstants.RULE_EQ_OP_EQEQ:
	                          //<eq_op> ::= '=='
	                          break;
	                       case RuleConstants.RULE_EQ_OP_EXCLAMEQ:
	                          //<eq_op> ::= '!='
	                          break;
	                       case RuleConstants.RULE_SUMA_OP_PLUS:
	                          //<suma_op> ::= '+'
	                          break;
	                       case RuleConstants.RULE_SUMA_OP_MINUS:
	                          //<suma_op> ::= '-'
	                          break;
	                       case RuleConstants.RULE_MUL_OP_TIMES:
	                          //<mul_op> ::= '*'
	                          break;
	                       case RuleConstants.RULE_MUL_OP_DIV:
	                          //<mul_op> ::= '/'
	                          break;
	                       case RuleConstants.RULE_MUL_OP_PERCENT:
	                          //<mul_op> ::= '%'
	                          break;
	                       case RuleConstants.RULE_VAL:
	                          //<val> ::= <location>
	                          break;
	                       case RuleConstants.RULE_VAL2:
	                          //<val> ::= <methodCall>
	                          break;
	                       case RuleConstants.RULE_VAL3:
	                          //<val> ::= <literal>
	                          break;
	                       case RuleConstants.RULE_VAL_LPARAN_RPARAN:
	                          //<val> ::= '(' <expression> ')'
	                          break;
	                       case RuleConstants.RULE_LOCATION:
	                          //<location> ::= <location1>
	                          break;
	                       case RuleConstants.RULE_LOCATION_DOT:
	                          //<location> ::= <location1> '.' <location>
	                          break;
	                       case RuleConstants.RULE_LOCATION1_ID:
	                          //<location1> ::= id <location2>
	                          break;
	                       case RuleConstants.RULE_LOCATION2_LBRACKET_RBRACKET:
	                          //<location2> ::= '[' <expression> ']'
	                          break;
	                       case RuleConstants.RULE_LOCATION2:
	                          //<location2> ::= 
	                          break;
	                       case RuleConstants.RULE_METHODCALL_ID_LPARAN_RPARAN:
	                          //<methodCall> ::= id '(' <arg1> ')'
	                          break;
	                       case RuleConstants.RULE_ARG1:
	                          //<arg1> ::= <arg2>
	                          break;
	                       case RuleConstants.RULE_ARG12:
	                          //<arg1> ::= 
	                          break;
	                       case RuleConstants.RULE_ARG2:
	                          //<arg2> ::= <arg>
	                          break;
	                       case RuleConstants.RULE_ARG2_COMMA:
	                          //<arg2> ::= <arg2> ',' <arg>
	                          break;
	                       case RuleConstants.RULE_ARG:
	                          //<arg> ::= <expression>
	                          break;
	                       case RuleConstants.RULE_LITERAL:
	                          //<literal> ::= <int_literal>
	                    	 //  stack.push(new Literal((String)stack.pop(),Const.TipoLiteral.integer));
	                          break;
	                       case RuleConstants.RULE_LITERAL2:
	                          //<literal> ::= <char_literal>
	                    	 //  stack.push(new Literal((String)stack.pop(),Const.TipoLiteral.character));
	                          break;
	                       case RuleConstants.RULE_LITERAL3:
	                          //<literal> ::= <bool_literal>
	                    	 //  stack.push(new Literal((String)stack.pop(),Const.TipoLiteral.bool));
	                          break;
	                       case RuleConstants.RULE_INT_LITERAL_NUM:
	                          //<int_literal> ::= num
	                          break;
	                       case RuleConstants.RULE_CHAR_LITERAL_CHARACTER:
	                          //<char_literal> ::= character
	                          break;
	                       case RuleConstants.RULE_BOOL_LITERAL_TRUE:
	                          //<bool_literal> ::= true
	                          break;
	                       case RuleConstants.RULE_BOOL_LITERAL_FALSE:
	                          //<bool_literal> ::= false
	                  
	                          break;
	                    }
	
	                        //Parser.Reduction = //Object you created to store the rule
	
	                  // ************************************** log file
	                  
	                  Reduction myRed = parser.currentReduction();
	                  //Token tok = parser.currentToken();
	                  //String info = (String)tok.getData();
	                  String info = "  ---> Linea:  "+parser.currentLineNumber();
	                  padre.imprimir("gpMsgReduction:   "+myRed.getParentRule().getText()+info,2);
	                  // ************************************** end log
	
	                  break;
	
	                case gpMsgAccept:
	                    /* The program was accepted by the parsing engine */
	
	                    // ************************************** log file
	                   padre.imprimir(" El archivo dado fue parseado",1);
	                  // clase = (Clase)stack.pop();
	                   //System.out.println("Clase: "+clase.nombre+ "  Cuerpo:  "+clase.cuerpo);
	                    // ************************************** end log
	                    huboErrores=false;
	                    done = true;
	
	                    break;
	
	                case gpMsgLexicalError:
	                    /* Place code here to handle a illegal or unrecognized token
			               To recover, pop the token from the stack: Parser.PopInputToken */
	
	                    // ************************************** log file
	                   padre.imprimir("gpMsgLexicalError",1);
	                   imprimirErrores();
	                    // ************************************** end log
	
	                    parser.popInputToken();
	
	                    break;
	
	                case gpMsgNotLoadedError:
	                    /* Load the Compiled Grammar Table file first. */
	
	                    // ************************************** log file
	                	padre.imprimir("gpMsgNotLoadedError",1);
	                	imprimirErrores();
	                    // ************************************** end log
	
	                    done = true;
	
	                    break;
	
	                case gpMsgSyntaxError:
	                    /* This is a syntax error: the source has produced a token that was
	        		       not expected by the LALR State Machine. The expected tokens are stored
			               into the Tokens() list. To recover, push one of the
	   		               expected tokens onto the parser's input queue (the first in this case):
	        		       You should limit the number of times this type of recovery can take
			               place. */
	
	                    done = true;
	
	                    Token theTok = parser.currentToken();
	                    
	                    padre.imprimir("gp-Msg-SyntaxError: Token not expected: " + (String)theTok.getData(),1);
	                    //padre.imprimir("Tipo token: "+theTok.getText());
	                    //padre.imprimir("" +parser.currentLineNumber());
	                    // ************************************** log file
	                    imprimirErrores();
	                    // ************************************** end log
	
	                    break;
	
	                case gpMsgCommentError:
	                    /* The end of the input was reached while reading a comment.
				           This is caused by a comment that was not terminated */
	
	                    // ************************************** log file
	                	padre.imprimir("gpMsgCommentError",1);
	                	imprimirErrores();
	                    // ************************************** end log
	
	                    done = true;
	
						break;
	
	                case gpMsgInternalError:
	                    /* Something horrid happened inside the parser. You cannot recover */
	
	                    // ************************************** log file
	                	padre.imprimir("gpMsgInternalError",1);
	                	imprimirErrores();
	                    // ************************************** end log
	
	                    done = true;
	
	                    break;
	            }
	        }
    	} 
        if ( (textToParse != null))  
         {
	        try
	        {
	        	parser.closeFile();
	        }
	        catch(ParserException parse)
	        {
	        	padre.imprimir("**PARSER ERROR**\n" + parse.toString(),1);
	        	padre.imprimir(""+parse.getCause() + "  "+parse.getMessage(),1);
	        	padre.imprimir("Linea: "+parse.getMessage(),1);
	        	parse.printStackTrace();
	        	imprimirErrores();
	            System.exit(1);
	        }
          }  
	    }
    
    public void imprimirErrores()
    {
    	
    	padre.imprimir("Linea de Error: "+parser.currentLineNumber(),1);
    	Token tok = parser.currentToken();
    	String infoTok = "Token: " +tok.getName();
    	Reduction reduccion = parser.currentReduction();
    	Rule regla = null;
    	if (reduccion !=null)
    	{
    		regla = reduccion.getParentRule();
    	}
    	String parenRule="";
    	if (regla!= null)
    	{
    		parenRule = regla.definition() + "\n";
    	}
		//System.out.println(regla.getText());

    	padre.imprimir("---> Token: "+infoTok+ "     Reduction:  "+ parenRule,1);
    	padre.imprimir("------------------------------------------------------------",1);
    }
    
    public boolean huboErrores()
    {
    	return huboErrores;
    }
    
    
}
