package main;
import interfaz.IDecaf;


import java.util.LinkedList;
import java.util.Stack;

import tabla.Const;
import tabla.Const.PasoMetodo;
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
	@SuppressWarnings("unused")

    private interface SymbolConstants 
    {
       final int SYMBOL_EOF                       =  0;  // (EOF)
       final int SYMBOL_ERROR                     =  1;  // (Error)
       final int SYMBOL_WHITESPACE                =  2;  // (Whitespace)
       final int SYMBOL_COMMENTEND                =  3;  // (Comment End)
       final int SYMBOL_COMMENTLINE               =  4;  // (Comment Line)
       final int SYMBOL_COMMENTSTART              =  5;  // (Comment Start)
       final int SYMBOL_MINUS                     =  6;  // '-'
       final int SYMBOL_EXCLAM                    =  7;  // '!'
       final int SYMBOL_EXCLAMEQ                  =  8;  // '!='
       final int SYMBOL_PERCENT                   =  9;  // '%'
       final int SYMBOL_AMPAMP                    = 10;  // '&&'
       final int SYMBOL_LPARAN                    = 11;  // '('
       final int SYMBOL_RPARAN                    = 12;  // ')'
       final int SYMBOL_TIMES                     = 13;  // '*'
       final int SYMBOL_COMMA                     = 14;  // ','
       final int SYMBOL_DOT                       = 15;  // '.'
       final int SYMBOL_DIV                       = 16;  // '/'
       final int SYMBOL_SEMI                      = 17;  // ';'
       final int SYMBOL_LBRACKET                  = 18;  // '['
       final int SYMBOL_RBRACKET                  = 19;  // ']'
       final int SYMBOL_LBRACE                    = 20;  // '{'
       final int SYMBOL_PIPEPIPE                  = 21;  // '||'
       final int SYMBOL_RBRACE                    = 22;  // '}'
       final int SYMBOL_PLUS                      = 23;  // '+'
       final int SYMBOL_LT                        = 24;  // '<'
       final int SYMBOL_LTEQ                      = 25;  // '<='
       final int SYMBOL_EQ                        = 26;  // '='
       final int SYMBOL_EQEQ                      = 27;  // '=='
       final int SYMBOL_GT                        = 28;  // '>'
       final int SYMBOL_GTEQ                      = 29;  // '>='
       final int SYMBOL_BOOLEAN                   = 30;  // boolean
       final int SYMBOL_CHAR                      = 31;  // char
       final int SYMBOL_CHARACTER                 = 32;  // character
       final int SYMBOL_CLASS                     = 33;  // class
       final int SYMBOL_ELSE                      = 34;  // else
       final int SYMBOL_FALSE                     = 35;  // false
       final int SYMBOL_ID                        = 36;  // id
       final int SYMBOL_IF                        = 37;  // if
       final int SYMBOL_INT                       = 38;  // int
       final int SYMBOL_NUM                       = 39;  // num
       final int SYMBOL_PROGRAM                   = 40;  // Program
       final int SYMBOL_RETURN                    = 41;  // return
       final int SYMBOL_STRUCT                    = 42;  // struct
       final int SYMBOL_TRUE                      = 43;  // true
       final int SYMBOL_VOID                      = 44;  // void
       final int SYMBOL_WHILE                     = 45;  // while
       final int SYMBOL_ARG                       = 46;  // <arg>
       final int SYMBOL_ARGDES                    = 47;  // <arg Des>
       final int SYMBOL_ASIGNACION                = 48;  // <asignacion>
       final int SYMBOL_BLOQUE                    = 49;  // <bloque>
       final int SYMBOL_BOOL_LITERAL              = 50;  // <bool_literal>
       final int SYMBOL_CHAR_LITERAL              = 51;  // <char_literal>
       final int SYMBOL_CONDICIONALDES            = 52;  // <Condicional Des>
       final int SYMBOL_DECLARACION               = 53;  // <Declaracion>
       final int SYMBOL_DECLARACIONKLEENE         = 54;  // <Declaracion Kleene>
       final int SYMBOL_DESBLOQUE                 = 55;  // <Des bloque>
       final int SYMBOL_DESEXPRESION              = 56;  // <Des expresion>
       final int SYMBOL_DESPARAMETRO              = 57;  // <Des parametro>
       final int SYMBOL_EXPRESION                 = 58;  // <expresion>
       final int SYMBOL_INT_LITERAL               = 59;  // <int_literal>
       final int SYMBOL_ITERACION                 = 60;  // <iteracion>
       final int SYMBOL_KLEENESTATEMENT           = 61;  // <Kleene statement>
       final int SYMBOL_KLEENEVARIABLEDECLARACION = 62;  // <Kleene variable Declaracion>
       final int SYMBOL_LITERAL                   = 63;  // <literal>
       final int SYMBOL_LLAMADAMETODO             = 64;  // <llamada metodo>
       final int SYMBOL_LOCACION                  = 65;  // <locacion>
       final int SYMBOL_LOCACIONARRAYDES          = 66;  // <locacion Array Des>
       final int SYMBOL_METODODECLARACION         = 67;  // <metodo Declaracion>
       final int SYMBOL_MULTIDES                  = 68;  // <multi Des>
       final int SYMBOL_MULTIEXP                  = 69;  // <Multi Exp>
       final int SYMBOL_MULTIPLEPARAMETROS        = 70;  // <multiple parametros>
       final int SYMBOL_MULTIPLESARGS             = 71;  // <multiples args>
       final int SYMBOL_NEGACIONEXP               = 72;  // <Negacion Exp>
       final int SYMBOL_PARAMETRO                 = 73;  // <parametro>
       final int SYMBOL_PROGRAM2                  = 74;  // <Program>
       final int SYMBOL_RELACIONDES               = 75;  // <Relacion Des>
       final int SYMBOL_RELACIONEXP               = 76;  // <Relacion Exp>
       final int SYMBOL_RETORNO                   = 77;  // <retorno>
       final int SYMBOL_SELECCION                 = 78;  // <seleccion>
       final int SYMBOL_STATEMENT                 = 79;  // <statement>
       final int SYMBOL_STRUCTDECLARACION         = 80;  // <struct Declaracion>
       final int SYMBOL_SUMADES                   = 81;  // <suma Des>
       final int SYMBOL_SUMAEXP                   = 82;  // <suma Exp>
       final int SYMBOL_TIPO                      = 83;  // <Tipo>
       final int SYMBOL_VALOR                     = 84;  // <Valor>
       final int SYMBOL_VARARRAYDECISION          = 85;  // <var Array Decision>
       final int SYMBOL_VARIABLEDECLARACION       = 86;  // <variable Declaracion>
    };

    private interface RuleConstants
    {
       final int RULE_PROGRAM_CLASS_PROGRAM_LBRACE_RBRACE       =  0;  // <Program> ::= class Program '{' <Declaracion Kleene> '}'
       final int RULE_DECLARACIONKLEENE                         =  1;  // <Declaracion Kleene> ::= <Declaracion> <Declaracion Kleene>
       final int RULE_DECLARACIONKLEENE2                        =  2;  // <Declaracion Kleene> ::= 
       final int RULE_DECLARACION                               =  3;  // <Declaracion> ::= <struct Declaracion>
       final int RULE_DECLARACION2                              =  4;  // <Declaracion> ::= <variable Declaracion>
       final int RULE_DECLARACION3                              =  5;  // <Declaracion> ::= <metodo Declaracion>
       final int RULE_VARIABLEDECLARACION_ID_SEMI               =  6;  // <variable Declaracion> ::= <Tipo> id <var Array Decision> ';'
       final int RULE_VARARRAYDECISION_LBRACKET_NUM_RBRACKET    =  7;  // <var Array Decision> ::= '[' num ']'
       final int RULE_VARARRAYDECISION                          =  8;  // <var Array Decision> ::= 
       final int RULE_STRUCTDECLARACION_STRUCT_ID_LBRACE_RBRACE =  9;  // <struct Declaracion> ::= struct id '{' <Kleene variable Declaracion> '}'
       final int RULE_KLEENEVARIABLEDECLARACION                 = 10;  // <Kleene variable Declaracion> ::= <variable Declaracion> <Kleene variable Declaracion>
       final int RULE_KLEENEVARIABLEDECLARACION2                = 11;  // <Kleene variable Declaracion> ::= 
       final int RULE_METODODECLARACION_ID_LPARAN_RPARAN        = 12;  // <metodo Declaracion> ::= <Tipo> id '(' <Des parametro> ')' <bloque>
       final int RULE_DESPARAMETRO                              = 13;  // <Des parametro> ::= <multiple parametros>
       final int RULE_DESPARAMETRO2                             = 14;  // <Des parametro> ::= 
       final int RULE_MULTIPLEPARAMETROS                        = 15;  // <multiple parametros> ::= <parametro>
       final int RULE_MULTIPLEPARAMETROS_COMMA                  = 16;  // <multiple parametros> ::= <multiple parametros> ',' <parametro>
       final int RULE_TIPO_INT                                  = 17;  // <Tipo> ::= int
       final int RULE_TIPO_CHAR                                 = 18;  // <Tipo> ::= char
       final int RULE_TIPO_BOOLEAN                              = 19;  // <Tipo> ::= boolean
       final int RULE_TIPO_VOID                                 = 20;  // <Tipo> ::= void
       final int RULE_TIPO_STRUCT_ID                            = 21;  // <Tipo> ::= struct id
       final int RULE_TIPO                                      = 22;  // <Tipo> ::= <struct Declaracion>
       final int RULE_PARAMETRO_ID                              = 23;  // <parametro> ::= <Tipo> id
       final int RULE_PARAMETRO_ID_LBRACKET_RBRACKET            = 24;  // <parametro> ::= <Tipo> id '[' ']'
       final int RULE_BLOQUE_LBRACE_RBRACE                      = 25;  // <bloque> ::= '{' <Kleene variable Declaracion> <Kleene statement> '}'
       final int RULE_KLEENESTATEMENT                           = 26;  // <Kleene statement> ::= <statement> <Kleene statement>
       final int RULE_KLEENESTATEMENT2                          = 27;  // <Kleene statement> ::= 
       final int RULE_STATEMENT                                 = 28;  // <statement> ::= <bloque>
       final int RULE_STATEMENT2                                = 29;  // <statement> ::= <seleccion>
       final int RULE_STATEMENT3                                = 30;  // <statement> ::= <iteracion>
       final int RULE_STATEMENT_SEMI                            = 31;  // <statement> ::= <retorno> ';'
       final int RULE_STATEMENT_SEMI2                           = 32;  // <statement> ::= <asignacion> ';'
       final int RULE_STATEMENT_SEMI3                           = 33;  // <statement> ::= <Des expresion> ';'
       final int RULE_SELECCION_IF_LPARAN_RPARAN                = 34;  // <seleccion> ::= if '(' <expresion> ')' <bloque> <Des bloque>
       final int RULE_DESBLOQUE_ELSE                            = 35;  // <Des bloque> ::= else <bloque>
       final int RULE_DESBLOQUE                                 = 36;  // <Des bloque> ::= 
       final int RULE_ITERACION_WHILE_LPARAN_RPARAN             = 37;  // <iteracion> ::= while '(' <expresion> ')' <bloque>
       final int RULE_RETORNO_RETURN                            = 38;  // <retorno> ::= return <Des expresion>
       final int RULE_ASIGNACION_EQ                             = 39;  // <asignacion> ::= <locacion> '=' <expresion>
       final int RULE_DESEXPRESION                              = 40;  // <Des expresion> ::= <expresion>
       final int RULE_DESEXPRESION2                             = 41;  // <Des expresion> ::= 
       final int RULE_EXPRESION                                 = 42;  // <expresion> ::= <Relacion Exp> <Condicional Des> <expresion>
       final int RULE_EXPRESION2                                = 43;  // <expresion> ::= <Relacion Exp>
       final int RULE_RELACIONEXP                               = 44;  // <Relacion Exp> ::= <suma Exp> <Relacion Des> <Relacion Exp>
       final int RULE_RELACIONEXP2                              = 45;  // <Relacion Exp> ::= <suma Exp>
       final int RULE_SUMAEXP                                   = 46;  // <suma Exp> ::= <Multi Exp> <suma Des> <suma Exp>
       final int RULE_SUMAEXP2                                  = 47;  // <suma Exp> ::= <Multi Exp>
       final int RULE_MULTIEXP                                  = 48;  // <Multi Exp> ::= <Negacion Exp> <multi Des> <Multi Exp>
       final int RULE_MULTIEXP2                                 = 49;  // <Multi Exp> ::= <Negacion Exp>
       final int RULE_NEGACIONEXP_MINUS                         = 50;  // <Negacion Exp> ::= '-' <Valor>
       final int RULE_NEGACIONEXP_EXCLAM                        = 51;  // <Negacion Exp> ::= '!' <Valor>
       final int RULE_NEGACIONEXP                               = 52;  // <Negacion Exp> ::= <Valor>
       final int RULE_CONDICIONALDES_AMPAMP                     = 53;  // <Condicional Des> ::= '&&'
       final int RULE_CONDICIONALDES_PIPEPIPE                   = 54;  // <Condicional Des> ::= '||'
       final int RULE_RELACIONDES_LTEQ                          = 55;  // <Relacion Des> ::= '<='
       final int RULE_RELACIONDES_LT                            = 56;  // <Relacion Des> ::= '<'
       final int RULE_RELACIONDES_GT                            = 57;  // <Relacion Des> ::= '>'
       final int RULE_RELACIONDES_GTEQ                          = 58;  // <Relacion Des> ::= '>='
       final int RULE_RELACIONDES_EQEQ                          = 59;  // <Relacion Des> ::= '=='
       final int RULE_RELACIONDES_EXCLAMEQ                      = 60;  // <Relacion Des> ::= '!='
       final int RULE_SUMADES_PLUS                              = 61;  // <suma Des> ::= '+'
       final int RULE_SUMADES_MINUS                             = 62;  // <suma Des> ::= '-'
       final int RULE_MULTIDES_TIMES                            = 63;  // <multi Des> ::= '*'
       final int RULE_MULTIDES_DIV                              = 64;  // <multi Des> ::= '/'
       final int RULE_MULTIDES_PERCENT                          = 65;  // <multi Des> ::= '%'
       final int RULE_VALOR                                     = 66;  // <Valor> ::= <literal>
       final int RULE_VALOR_LPARAN_RPARAN                       = 67;  // <Valor> ::= '(' <expresion> ')'
       final int RULE_VALOR2                                    = 68;  // <Valor> ::= <llamada metodo>
       final int RULE_VALOR3                                    = 69;  // <Valor> ::= <locacion>
       final int RULE_LOCACION_DOT                              = 70;  // <locacion> ::= <locacion Array Des> '.' <locacion>
       final int RULE_LOCACION                                  = 71;  // <locacion> ::= <locacion Array Des>
       final int RULE_LOCACIONARRAYDES_ID_LBRACKET_RBRACKET     = 72;  // <locacion Array Des> ::= id '[' <expresion> ']'
       final int RULE_LOCACIONARRAYDES_ID                       = 73;  // <locacion Array Des> ::= id
       final int RULE_LLAMADAMETODO_ID_LPARAN_RPARAN            = 74;  // <llamada metodo> ::= id '(' <arg Des> ')'
       final int RULE_ARGDES                                    = 75;  // <arg Des> ::= <multiples args>
       final int RULE_ARGDES2                                   = 76;  // <arg Des> ::= 
       final int RULE_MULTIPLESARGS                             = 77;  // <multiples args> ::= <arg>
       final int RULE_MULTIPLESARGS_COMMA                       = 78;  // <multiples args> ::= <multiples args> ',' <arg>
       final int RULE_ARG                                       = 79;  // <arg> ::= <expresion>
       final int RULE_LITERAL                                   = 80;  // <literal> ::= <int_literal>
       final int RULE_LITERAL2                                  = 81;  // <literal> ::= <char_literal>
       final int RULE_LITERAL3                                  = 82;  // <literal> ::= <bool_literal>
       final int RULE_INT_LITERAL_NUM                           = 83;  // <int_literal> ::= num
       final int RULE_CHAR_LITERAL_CHARACTER                    = 84;  // <char_literal> ::= character
       final int RULE_BOOL_LITERAL_TRUE                         = 85;  // <bool_literal> ::= true
       final int RULE_BOOL_LITERAL_FALSE                        = 86;  // <bool_literal> ::= false
    };


    public GOLDParser parser;
    private String rutaGramatica= "src/grammar/GoldParser/Decaf2.cgt";
    private boolean huboErrores= true;
    private IDecaf padre;
    
    @SuppressWarnings("rawtypes")
	private Stack stack = new Stack();

    /***************************************************************
 	 * This class will run the engine, and needs a file called config.dat
     * in the current directory. This file should contain two lines,
     * The first should be the absolute path name to the .cgt file, the second
     * should be the source file you wish to parse.
 	 * @param args Array of arguments.
 	 ***************************************************************/
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Main(String textToParse, IDecaf padre)
    {
    	this.padre=padre;
        String compiledGrammar = "";

            compiledGrammar = rutaGramatica;
    		//textToParse = buffR.readLine();


            parser = new GOLDParser();
            parser.setTrimReductions(false);
           // boolean varDeclaration = false;
           // boolean varVariable = false;
           // Literal lit = null;

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
        Expresion expLo = null;
        Object antesdeSigno = null;
        String nombreAsig = null;
        if ( (textToParse != null))  
        {

        	padre.imprimir("-------------------- Proceso Lectura --------------------",2);
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
	                    //Token myTok = parser.currentToken();
	                    //padre.imprimir("gpMsgTokenRead:   "+(String)myTok.getData(),2);
	                    // ************************************** end log
	                	parser.currentToken().setLineNumber(parser.currentLineNumber());
	                	Token myTok = parser.currentToken();
	                	
	                    int cos=myTok.getPSymbol().getTableIndex();
	                    
	                    
	                    switch (myTok.getPSymbol().getTableIndex())
	                    {
	                    	case SymbolConstants.SYMBOL_PROGRAM:
	                    		padre.imprimir("TokenRead:   "+cos+"  "+myTok.getPSymbol()+ "    "+myTok.getText()+ "   Text:  "+myTok.getData().toString()+"\n",2);
	                    		stack.push(myTok.getText());
	                    		break;
	                    	case SymbolConstants.SYMBOL_ID:
	                    	case SymbolConstants.SYMBOL_TRUE:
	                    	case SymbolConstants.SYMBOL_FALSE:
	                    	case SymbolConstants.SYMBOL_CHARACTER:
	                    		padre.imprimir("gpMsgTokenRead:   "+cos+"  "+myTok.getPSymbol()+ "    "+myTok.getText()+ "   Text:  "+myTok.getData().toString()+"\n",2);
	                    		stack.push(myTok.getData().toString());
	                    		break;
	                    	case SymbolConstants.SYMBOL_NUM:
	                    		padre.imprimir("gpMsgTokenRead:   "+cos+"  "+myTok.getPSymbol()+ "    "+myTok.getText()+ "   Text:  "+myTok.getData().toString()+"\n",2);
	                    		int numero = Integer.parseInt(myTok.getData().toString());
	                    		stack.push(numero);
	                    		break;
	                    }

	                	

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
	                            //<Program> ::= class Program '{' <Declaracion Kleene> '}'
		                    	   LinkedList<Declaracion> listaDeclaraciones = (LinkedList<Declaracion>)stack.pop();
		                    	   while ((stack.peek().getClass() == Variable.class) || (stack.peek().getClass() == Estructura.class) || (stack.peek().getClass() == Funcion.class))
		                    	   {
		                    		   listaDeclaraciones.add((Declaracion)stack.pop());
		                    	   }
		                    	   stack.push(listaDeclaraciones);
		                    	   stackPruebas((Stack)stack.clone(), "En el main");
		                    	   stack.push(new Clase(new Cuerpo((LinkedList<Declaracion>)stack.pop()),(String)stack.pop()));
	                            break;
	                         case RuleConstants.RULE_DECLARACIONKLEENE:
	                            //<Declaracion Kleene> ::= <Declaracion> <Declaracion Kleene>
	                        	stackPruebas(stack.clone(), "Declaracionk");
	                 	   		LinkedList<Declaracion> listaVar1 = ((LinkedList<Declaracion>) stack.pop());
                		   		Declaracion variable = (Declaracion)stack.pop();
                		   		listaVar1.add(variable);
                		   		stack.push(listaVar1);
                		   		stackPruebas(stack.clone(), "Declaracionk");
	                        	 
	                            break;
	                         case RuleConstants.RULE_DECLARACIONKLEENE2:
	                            //<Declaracion Kleene> ::= 
	                        	 LinkedList<Declaracion> listaVar = new LinkedList<Declaracion>();
	                        	 stack.push(listaVar);
	                        	 stackPruebas(stack.clone(), "Declaracionk2");
	                            break;
	                         case RuleConstants.RULE_DECLARACION:
	                            //<Declaracion> ::= <struct Declaracion>
	                        	 //!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_DECLARACION2:
	                            //<Declaracion> ::= <variable Declaracion>
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_DECLARACION3:
	                            //<Declaracion> ::= <metodo Declaracion>
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_VARIABLEDECLARACION_ID_SEMI:
	                            //<variable Declaracion> ::= <Tipo> id <var Array Decision> ';'
	                        	 stackPruebas(stack.clone(),"VarDeclarationID_SEMI");
	                        	 if (stack.peek().getClass() == String.class)
	                        	 {
	                        		 nombreAsig = (String)stack.pop();
	                        	 }
	                        	 stack.push(new Variable(null,(Tipo)stack.pop(),(String) stack.pop()));
	                        	 stackPruebas(stack.clone(),"VarDeclarationID_SEMI");
	                            break;
	                         case RuleConstants.RULE_VARARRAYDECISION_LBRACKET_NUM_RBRACKET:
	                            //<var Array Decision> ::= '[' num ']'
	                        	 Tipo tip = new Tipo();
	                        	 	try{
		                    	   int numero = (Integer)stack.pop();
		                    	   if (numero<1)
		                    	   {
		                    		   padre.imprimir("El numero del Array debe ser mayor a 1. Linea: "+parser.currentLineNumber(), 1);
		                    		   done = true;
		                    		   huboErrores = true;
		                    	   }
		                    	   stack.push(tip.crearArregloDesdeTipo(numero,(Tipo)stack.pop()));
	                        	 	}
	                        	 	catch (Exception ex)
	                        	 	{
	                        	 		padre.imprimir("Debe escribir un número y lo que puso no lo es. Linea: "+parser.currentLineNumber(), 1);
			                    		done = true;
			                    		huboErrores = true;
	                        	 	}
		                    	   
	                            break;
	                         case RuleConstants.RULE_VARARRAYDECISION:
	                            //<var Array Decision> ::= 
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_STRUCTDECLARACION_STRUCT_ID_LBRACE_RBRACE:
	                            //<struct Declaracion> ::= struct id '{' <Kleene variable Declaracion> '}'
	                        	 stackPruebas(stack.clone(),"Creacion struct");
	                        	 if (stack.peek().getClass() == String.class)
	                        	 {
	                        		String nombreVar = (String) stack.pop();
	                        		LinkedList<Variable> lista = (LinkedList<Variable>) stack.pop();
		                    		String nombreEstructura = (String)stack.pop();
	                        		stack.push(new Estructura(lista,nombreEstructura));
	                        		stack.push(nombreVar);
	                        		stack.push(new Tipo(nombreEstructura));
	                        	 }
	                        	 else
	                        	 {
	                        	 stack.push(new Estructura((LinkedList<Variable>) stack.pop(),(String)stack.pop()));
	                        	 }
	                        	 stackPruebas(stack.clone(),"Creacion struct");
	                            break;
	                         case RuleConstants.RULE_KLEENEVARIABLEDECLARACION:
	                            //<Kleene variable Declaracion> ::= <variable Declaracion> <Kleene variable Declaracion>
	                        	 stackPruebas(stack.clone(),"Variables antes if 1");
	                        	 LinkedList<Variable> listaVar2 = (LinkedList<Variable>)stack.pop();
	                        	 listaVar2.add((Variable)stack.pop());
	                    	     stack.push(listaVar2);
	                    	     stackPruebas(stack.clone(),"Variables despues if 2");
	                            break;
	                         case RuleConstants.RULE_KLEENEVARIABLEDECLARACION2:
	                            //<Kleene variable Declaracion> ::= 
	                        	 stack.push(new LinkedList<Variable>());
	                            break;
	                         case RuleConstants.RULE_METODODECLARACION_ID_LPARAN_RPARAN:
	                            //<metodo Declaracion> ::= <Tipo> id '(' <Des parametro> ')' <bloque>
	                        	 stack.push(new Funcion((Cuerpo)stack.pop(),(LinkedList<Parametro>)stack.pop(),(Tipo)stack.pop(),(String)stack.pop()));
	                            break;
	                         case RuleConstants.RULE_DESPARAMETRO:
	                            //<Des parametro> ::= <multiple parametros>
	                        	 //!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_DESPARAMETRO2:
	                            //<Des parametro> ::=
	                        	 stack.push(new LinkedList<Parametro>());
	                            break;
	                         case RuleConstants.RULE_MULTIPLEPARAMETROS:
	                            //<multiple parametros> ::= <parametro>
	                        	 LinkedList<Parametro> paramlist = new LinkedList<Parametro>();
	                        	 paramlist.add((Parametro)stack.pop());
	                        	 stack.push(paramlist);
	                            break;
	                         case RuleConstants.RULE_MULTIPLEPARAMETROS_COMMA:
	                            //<multiple parametros> ::= <multiple parametros> ',' <parametro>
	                        	 Parametro parametro = (Parametro)stack.pop();
	                        	 ((LinkedList<Parametro>)stack.peek()).add(parametro);
	                            break;
	                         case RuleConstants.RULE_TIPO_INT:
	                            //<Tipo> ::= int
	                        	 stack.push(new Tipo(TipoPrimitivo.integer));
	                            break;
	                         case RuleConstants.RULE_TIPO_CHAR:
	                            //<Tipo> ::= char
	                        	 stack.push(new Tipo(TipoPrimitivo.character));
	                            break;
	                         case RuleConstants.RULE_TIPO_BOOLEAN:
	                            //<Tipo> ::= boolean
	                        	 stack.push(new Tipo(TipoPrimitivo.bool));
	                            break;
	                         case RuleConstants.RULE_TIPO_VOID:
	                            //<Tipo> ::= void
	                        	 stack.push(new Tipo(TipoPrimitivo.void_));
	                            break;
	                         case RuleConstants.RULE_TIPO_STRUCT_ID:
	                            //<Tipo> ::= struct id
	                        	 stack.push(new Tipo((String)stack.pop()));
	                            break;
	                         case RuleConstants.RULE_TIPO:
	                            //<Tipo> ::= <struct Declaracion>
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_PARAMETRO_ID:
	                            //<parametro> ::= <Tipo> id
	                        	 stack.push(new Parametro((Tipo)stack.pop(),(String)stack.pop(),PasoMetodo.porValor));
	                            break;
	                         case RuleConstants.RULE_PARAMETRO_ID_LBRACKET_RBRACKET:
	                            //<parametro> ::= <Tipo> id '[' ']'
	                        	 Tipo tipo = (Tipo)stack.pop();
		                    	 tipo = tipo.crearArregloTipo(tipo.tipoPrimitivo);
	                    		 stack.push(new Parametro(tipo,(String)stack.pop(),PasoMetodo.porValor));
	                        	 
	                            break;
	                         case RuleConstants.RULE_BLOQUE_LBRACE_RBRACE:
	                            //<bloque> ::= '{' <Kleene variable Declaracion> <Kleene statement> '}'
	                        	 stackPruebas(stack.clone(),"EnBLoque1");
	                        	 LinkedList<Declaracion> lista1 = (LinkedList<Declaracion>)stack.pop();
		                    	   LinkedList<Declaracion> lista2 = (LinkedList<Declaracion>)stack.pop();
		                    	   for (int i=0; i<lista2.size(); i++)
		                    	   {
		                    		   lista1.add(lista2.get(i));
		                    	   }	   
		                    	   stack.push(new Cuerpo(lista1));
	                        	 stackPruebas(stack.clone(),"EnBLoque2");
	                            break;
	                         case RuleConstants.RULE_KLEENESTATEMENT:
	                            //<Kleene statement> ::= <statement> <Kleene statement>
	                        	 stackPruebas(stack.clone(),"Kleene statement1");
	                        	 LinkedList<Declaracion> listaDec = (LinkedList<Declaracion>)stack.pop();
	                        	 listaDec.add((Declaracion)stack.pop());
	                    	     stack.push(listaDec);
	                    	     stackPruebas(stack.clone(),"Kleene statement2");
	                            break;
	                         case RuleConstants.RULE_KLEENESTATEMENT2:
	                            //<Kleene statement> ::= 
	                        	 stack.push(new LinkedList<Declaracion>());
	                            break;
	                         case RuleConstants.RULE_STATEMENT:
	                            //<statement> ::= <bloque>
	                        	 //!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_STATEMENT2:
	                            //<statement> ::= <seleccion>
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_STATEMENT3:
	                            //<statement> ::= <iteracion>
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_STATEMENT_SEMI:
	                            //<statement> ::= <retorno> ';'
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_STATEMENT_SEMI2:
	                            //<statement> ::= <asignacion> ';'
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_STATEMENT_SEMI3:
	                            //<statement> ::= <Des expresion> ';'
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_SELECCION_IF_LPARAN_RPARAN:
	                            //<seleccion> ::= if '(' <expresion> ')' <bloque> <Des bloque>
	                        	 // SE ENCARGA <Des bloque>
	                            break;
	                         case RuleConstants.RULE_DESBLOQUE_ELSE:
	                            //<Des bloque> ::= else <bloque>
	                        	 stackPruebas(stack.clone(),"If con else");
	                        	 stack.push(new If((Cuerpo)stack.pop(),(Cuerpo)stack.pop(),(Expresion)stack.pop()));
	                            break;
	                         case RuleConstants.RULE_DESBLOQUE:
	                            //<Des bloque> ::=
	                        	 stackPruebas(stack.clone(),"If sin else");
	                        	 stack.push(new If(null,(Cuerpo)stack.pop(),(Expresion)stack.pop()));
	                            break;
	                         case RuleConstants.RULE_ITERACION_WHILE_LPARAN_RPARAN:
	                            //<iteracion> ::= while '(' <expresion> ')' <bloque>
	                        	 stack.push(new While( (Cuerpo)stack.pop(),(Expresion)stack.pop()));
	                            break;
	                         case RuleConstants.RULE_RETORNO_RETURN:
	                            //<retorno> ::= return <Des expresion>
	                        	 stack.push(new Return((Expresion)stack.pop()));
	                            break;
	                         case RuleConstants.RULE_ASIGNACION_EQ:
	                            //<asignacion> ::= <locacion> '=' <expresion>
	                        	 stackPruebas(stack.clone(),"<asignacion 1");
	                        	 Literal indice = (Literal)expLo;
	                        	 Literal valor = (Literal)stack.peek();
	                        	 if (indice != null)
	                        	 {
	                        	 System.out.println("Valor de indice = "+indice.valor);
	                        	 }
	                        	 System.out.println("Valor de valor  = "+valor.valor);
	                        	 if (expLo != null)
	                        	 {
	                        		 stack.push( new Asignacion((Expresion)stack.pop(),expLo,(String)stack.pop()));
	                        	 }
	                        	 else
	                        	 {
	                        		 Expresion enAsignaEx = (Expresion)stack.pop();
	                        		 //if (stack.peek().getClass())
	                        		 stack.push( new Asignacion((Expresion)stack.pop(),null,(String)stack.pop()));
	                        	 }
	                        	 stackPruebas(stack.clone(),"<asignacion 2");
	                            break;
	                         case RuleConstants.RULE_DESEXPRESION:
	                            //<Des expresion> ::= <expresion>
	                            break;
	                         case RuleConstants.RULE_DESEXPRESION2:
	                            //<Des expresion> ::= 
	                            break;
	                         case RuleConstants.RULE_EXPRESION:
	                            //<expresion> ::= <Relacion Exp> <Condicional Des> <expresion>
	                            break;
	                         case RuleConstants.RULE_EXPRESION2:
	                            //<expresion> ::= <Relacion Exp>
	                            break;
	                         case RuleConstants.RULE_RELACIONEXP:
	                            //<Relacion Exp> ::= <suma Exp> <Relacion Des> <Relacion Exp>
	                        	 Expresion expDe = (Expresion)stack.pop();
		                    	 int numOperacion = (Integer)stack.pop();
		                    	 stack.push(new ExpresionBinaria(expDe,numOperacion,(Expresion)stack.pop()));
	                            break;
	                         case RuleConstants.RULE_RELACIONEXP2:
	                            //<Relacion Exp> ::= <suma Exp>
	                            break;
	                         case RuleConstants.RULE_SUMAEXP:
	                            //<suma Exp> ::= <Multi Exp> <suma Des> <suma Exp>
	                            break;
	                         case RuleConstants.RULE_SUMAEXP2:
	                            //<suma Exp> ::= <Multi Exp>
	                            break;
	                         case RuleConstants.RULE_MULTIEXP:
	                            //<Multi Exp> ::= <Negacion Exp> <multi Des> <Multi Exp>
	                            break;
	                         case RuleConstants.RULE_MULTIEXP2:
	                            //<Multi Exp> ::= <Negacion Exp>
	                            break;
	                         case RuleConstants.RULE_NEGACIONEXP_MINUS:
	                            //<Negacion Exp> ::= '-' <Valor>
	                        	 stack.push(new ExpresionUnaria((Expresion)stack.pop(),Const.TipoOperadorUnario.negativo));
	                            break;
	                         case RuleConstants.RULE_NEGACIONEXP_EXCLAM:
	                            //<Negacion Exp> ::= '!' <Valor>
	                        	 stack.push(new ExpresionUnaria((Expresion)stack.pop(),Const.TipoOperadorUnario.negacion));
	                            break;
	                         case RuleConstants.RULE_NEGACIONEXP:
	                            //<Negacion Exp> ::= <Valor>
	                            break;
	                         case RuleConstants.RULE_CONDICIONALDES_AMPAMP:
	                            //<Condicional Des> ::= '&&'
	                        	 stack.push(Const.TipoOperadorBinario.and);
	                            break;
	                         case RuleConstants.RULE_CONDICIONALDES_PIPEPIPE:
	                            //<Condicional Des> ::= '||'
	                        	 stack.push(Const.TipoOperadorBinario.or);
	                            break;
	                         case RuleConstants.RULE_RELACIONDES_LTEQ:
	                            //<Relacion Des> ::= '<='
	                        	 antesdeSigno = (Object) stack.pop();
	                        	 stack.push(Const.TipoOperadorBinario.menorIgual);
	                        	 stack.push(antesdeSigno);
	                            break;
	                         case RuleConstants.RULE_RELACIONDES_LT:
	                            //<Relacion Des> ::= '<'
	                        	 antesdeSigno = (Object) stack.pop();
	                        	 stack.push(Const.TipoOperadorBinario.menor);
	                        	 stack.push(antesdeSigno);
	                            break;
	                         case RuleConstants.RULE_RELACIONDES_GT:
	                            //<Relacion Des> ::= '>'
	                        	 //stackPruebas(stack.clone(),"<RULE_RELACIONDES_GT>1");
	                        	 antesdeSigno = (Object) stack.pop();
	                        	 stack.push(Const.TipoOperadorBinario.mayor);
	                        	 stack.push(antesdeSigno);
	                        	 //stackPruebas(stack.clone(),"<RULE_RELACIONDES_GT>2");
	                            break;
	                         case RuleConstants.RULE_RELACIONDES_GTEQ:
	                            //<Relacion Des> ::= '>='
	                        	 antesdeSigno = (Object) stack.pop();
	                        	 stack.push(Const.TipoOperadorBinario.mayorIgual);
	                            break;
	                         case RuleConstants.RULE_RELACIONDES_EQEQ:
	                            //<Relacion Des> ::= '=='
	                        	 antesdeSigno = (Object) stack.pop();
	                        	 stack.push(Const.TipoOperadorBinario.igual);
	                        	 stack.push(antesdeSigno);
	                            break;
	                         case RuleConstants.RULE_RELACIONDES_EXCLAMEQ:
	                            //<Relacion Des> ::= '!='
	                        	 antesdeSigno = (Object) stack.pop();
	                        	 stack.push(Const.TipoOperadorBinario.noIgual);
	                        	 stack.push(antesdeSigno);
	                            break;
	                         case RuleConstants.RULE_SUMADES_PLUS:
	                            //<suma Des> ::= '+'
	                        	 stack.push(Const.TipoOperadorBinario.suma);
	                            break;
	                         case RuleConstants.RULE_SUMADES_MINUS:
	                            //<suma Des> ::= '-'
	                        	 stack.push(Const.TipoOperadorBinario.resta);
	                            break;
	                         case RuleConstants.RULE_MULTIDES_TIMES:
	                            //<multi Des> ::= '*'
	                        	 stack.push(Const.TipoOperadorBinario.multiplicacion);
	                            break;
	                         case RuleConstants.RULE_MULTIDES_DIV:
	                            //<multi Des> ::= '/'
	                        	stack.push(Const.TipoOperadorBinario.division);
	                            break;
	                         case RuleConstants.RULE_MULTIDES_PERCENT:
	                            //<multi Des> ::= '%'
	                        	 stack.push(Const.TipoOperadorBinario.modulo);
	                            break;
	                         case RuleConstants.RULE_VALOR:
	                            //<Valor> ::= <literal>
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_VALOR_LPARAN_RPARAN:
	                            //<Valor> ::= '(' <expresion> ')'
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_VALOR2:
	                            //<Valor> ::= <llamada metodo>
	                        	//!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_VALOR3:
	                            //<Valor> ::= <locacion>
	                        	 //!!! NO HACE NADA
	                            break;
	                         case RuleConstants.RULE_LOCACION_DOT:
	                            //<locacion> ::= <locacion Array Des> '.' <locacion>
	                        	  stackPruebas(stack.clone(), "RULE_LOCATION_DOT1");
		                    	   String id = (String)stack.pop();
		                    	   LinkedList<Variable> lk = null;
		                    	   if (stack.peek().getClass() == LinkedList.class)
		                    	   {
		                    		   System.out.println("Entra aca");
		                    		   lk = (LinkedList<Variable>)stack.pop();
		                    		   if (stack.peek().getClass() == String.class)
		                    		   {
		                    		   String nombrePrincipal = (String)stack.pop();
		                    		   stack.push(lk);
		                    		   stack.push(nombrePrincipal+"."+id);
		                    		   }
		                    		   else
		                    		   {
		                    			   stack.push(lk);
		                    			   stack.push(nombreAsig+"."+id);
		                    			   nombreAsig = null;
		                    		   }
		                    	   }
		                    	   else
		                    	   {
		                    	   stack.push(id+"."+(String)stack.pop());
		                    	   }
		                    	  stackPruebas(stack.clone(), "RULE_LOCATION_DOT2");
	                            break;
	                         case RuleConstants.RULE_LOCACION:
	                            //<locacion> ::= <locacion Array Des>
	                            break;
	                         case RuleConstants.RULE_LOCACIONARRAYDES_ID_LBRACKET_RBRACKET:
	                            //<locacion Array Des> ::= id '[' <expresion> ']'
	                        	 expLo = (Expresion)stack.pop();
	                            break;
	                         case RuleConstants.RULE_LOCACIONARRAYDES_ID:
	                            //<locacion Array Des> ::= id
	                            break;
	                         case RuleConstants.RULE_LLAMADAMETODO_ID_LPARAN_RPARAN:
	                            //<llamada metodo> ::= id '(' <arg Des> ')'
	                            break;
	                         case RuleConstants.RULE_ARGDES:
	                            //<arg Des> ::= <multiples args>
	                            break;
	                         case RuleConstants.RULE_ARGDES2:
	                            //<arg Des> ::= 
	                            break;
	                         case RuleConstants.RULE_MULTIPLESARGS:
	                            //<multiples args> ::= <arg>
	                            break;
	                         case RuleConstants.RULE_MULTIPLESARGS_COMMA:
	                            //<multiples args> ::= <multiples args> ',' <arg>
	                            break;
	                         case RuleConstants.RULE_ARG:
	                            //<arg> ::= <expresion>
	                            break;
	                         case RuleConstants.RULE_LITERAL:
	                            //<literal> ::= <int_literal>
	                        	 //stackPruebas(stack.clone(),"<RULE_LITERAL>1");
	                        	 
	                        	 stack.push(new Literal(""+(Integer)stack.pop(),Const.TipoLiteral.integer));
	                        	 //stackPruebas(stack.clone(),"<RULE_LITERAL>2");
	                            break;
	                         case RuleConstants.RULE_LITERAL2:
	                            //<literal> ::= <char_literal>
	                        	 stack.push(new Literal((String)stack.pop(),Const.TipoLiteral.character));
	                            break;
	                         case RuleConstants.RULE_LITERAL3:
	                            //<literal> ::= <bool_literal>
	                        	 stack.push(new Literal((String)stack.pop(),Const.TipoLiteral.bool));
	                            break;
	                         case RuleConstants.RULE_INT_LITERAL_NUM:
	                            //<int_literal> ::= num
	                        	//Se encarga RULE_LITERAL
	                            break;
	                         case RuleConstants.RULE_CHAR_LITERAL_CHARACTER:
	                            //<char_literal> ::= character
	                        	//Se encarga RULE_LITERAL2
	                            break;
	                         case RuleConstants.RULE_BOOL_LITERAL_TRUE:
	                            //<bool_literal> ::= true
	                        	 //Se encarga RULE_LITERAL3
	                            break;
	                         case RuleConstants.RULE_BOOL_LITERAL_FALSE:
	                            //<bool_literal> ::= false
	                        	//Se encarga RULE_LITERAL3
	                            break;
	                      }

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
	                	padre.imprimir("gpMsgCommentError: El final de la cadena fue alcanzado mientras se leia un comentario",1);
	                	imprimirErrores();
	                    // ************************************** end log

	                    done = true;

						break;

	                case gpMsgInternalError:
	                    /* Something horrid happened inside the parser. You cannot recover */

	                    // ************************************** log file
	                	padre.imprimir("gpMsgInternalError: No debería pasar nunca, algo pasó dentro del parser, murió sin dejar rastros",1);
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
    
    public void stackPruebas(Object stack,String estado)
    {
    	@SuppressWarnings("rawtypes")
		Stack stack1 = (Stack)stack;
    	System.out.println("--------------------------------"+ " "+estado+" -------------------------");
    	while (stack1.size()>0)
    	{
    		System.out.println("Stack: "+stack1.pop());
    	}
    	System.out.println("--------------------------------");
    }
    
    
}
