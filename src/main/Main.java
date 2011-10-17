package main;


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
import tabla.*;
import tabla.clases.*;
import interfaz.IDecaf;
import goldengine.java.*;

import org.jdom.Comment;



import java.util.LinkedList;



public class Main implements GPMessageConstants
{

    @SuppressWarnings("unused")
	private interface SymbolConstants
    {
        final int SYMBOL_EOF                   =  0;  // (EOF)
        final int SYMBOL_ERROR                 =  1;  // (Error)
        final int SYMBOL_WHITESPACE            =  2;  // (Whitespace)
        final int SYMBOL_COMMENTEND            =  3;  // (Comment End)
        final int SYMBOL_COMMENTLINE           =  4;  // (Comment Line)
        final int SYMBOL_COMMENTSTART          =  5;  // (Comment Start)
        final int SYMBOL_MINUS                 =  6;  // '-'
        final int SYMBOL_EXCLAM                =  7;  // '!'
        final int SYMBOL_EXCLAMEQ              =  8;  // '!='
        final int SYMBOL_PERCENT               =  9;  // '%'
        final int SYMBOL_AMPAMP                = 10;  // '&&'
        final int SYMBOL_LPARAN                = 11;  // '('
        final int SYMBOL_RPARAN                = 12;  // ')'
        final int SYMBOL_TIMES                 = 13;  // '*'
        final int SYMBOL_COMMA                 = 14;  // ','
        final int SYMBOL_DOT                   = 15;  // '.'
        final int SYMBOL_DIV                   = 16;  // '/'
        final int SYMBOL_SEMI                  = 17;  // ';'
        final int SYMBOL_LBRACKET              = 18;  // '['
        final int SYMBOL_RBRACKET              = 19;  // ']'
        final int SYMBOL_LBRACE                = 20;  // '{'
        final int SYMBOL_PIPEPIPE              = 21;  // '||'
        final int SYMBOL_RBRACE                = 22;  // '}'
        final int SYMBOL_PLUS                  = 23;  // '+'
        final int SYMBOL_LT                    = 24;  // '<'
        final int SYMBOL_LTEQ                  = 25;  // '<='
        final int SYMBOL_EQ                    = 26;  // '='
        final int SYMBOL_EQEQ                  = 27;  // '=='
        final int SYMBOL_GT                    = 28;  // '>'
        final int SYMBOL_GTEQ                  = 29;  // '>='
        final int SYMBOL_BOOLEAN               = 30;  // boolean
        final int SYMBOL_CHAR                  = 31;  // char
        final int SYMBOL_CHARACTER             = 32;  // character
        final int SYMBOL_CLASS                 = 33;  // class
        final int SYMBOL_ELSE                  = 34;  // else
        final int SYMBOL_FALSE                 = 35;  // false
        final int SYMBOL_ID                    = 36;  // id
        final int SYMBOL_IF                    = 37;  // if
        final int SYMBOL_INT                   = 38;  // int
        final int SYMBOL_NUM                   = 39;  // num
        final int SYMBOL_PROGRAM               = 40;  // Program
        final int SYMBOL_RETURN                = 41;  // return
        final int SYMBOL_STRUCT                = 42;  // struct
        final int SYMBOL_TRUE                  = 43;  // true
        final int SYMBOL_VOID                  = 44;  // void
        final int SYMBOL_WHILE                 = 45;  // while
        final int SYMBOL_ADDEXP                = 46;  // <Add Exp>
        final int SYMBOL_ADDOP                 = 47;  // <addop>
        final int SYMBOL_ARG                   = 48;  // <arg>
        final int SYMBOL_ARGLIST               = 49;  // <arg list>
        final int SYMBOL_ASSIGN                = 50;  // <assign>
        final int SYMBOL_BLOCK                 = 51;  // <block>
        final int SYMBOL_BOOL_LITERAL          = 52;  // <bool_literal>
        final int SYMBOL_CHAR_LITERAL          = 53;  // <char_literal>
        final int SYMBOL_CONDITIONALOP         = 54;  // <conditionalop>
        final int SYMBOL_DECLARATION           = 55;  // <declaration>
        final int SYMBOL_EXPRESSION            = 56;  // <expression>
        final int SYMBOL_INT_LITERAL           = 57;  // <int_literal>
        final int SYMBOL_ITERATION             = 58;  // <iteration>
        final int SYMBOL_KLEENE_DECLARATION    = 59;  // <kleene_declaration>
        final int SYMBOL_KLEENE_STATEMENT      = 60;  // <kleene_statement>
        final int SYMBOL_KLEENE_VARDECLARATION = 61;  // <kleene_varDeclaration>
        final int SYMBOL_LITERAL               = 62;  // <literal>
        final int SYMBOL_LOCATION              = 63;  // <location>
        final int SYMBOL_METHODCALL            = 64;  // <methodCall>
        final int SYMBOL_METHODDECLARATION     = 65;  // <methodDeclaration>
        final int SYMBOL_MULOP                 = 66;  // <mulop>
        final int SYMBOL_MULTEXP               = 67;  // <Mult Exp>
        final int SYMBOL_NEGATEEXP             = 68;  // <Negate Exp>
        final int SYMBOL_OPT_ARG_SEPARATED     = 69;  // <opt_arg_separated>
        final int SYMBOL_OPT_ELSE_BLOCK        = 70;  // <opt_else_block>
        final int SYMBOL_OPT_EXPRESSION        = 71;  // <opt_expression>
        final int SYMBOL_OPT_PARAMETER         = 72;  // <opt_parameter>
        final int SYMBOL_PARAMETER             = 73;  // <parameter>
        final int SYMBOL_PARAMETERLIST         = 74;  // <parameter list>
        final int SYMBOL_PROGRAM2              = 75;  // <Program>
        final int SYMBOL_RELEXP                = 76;  // <Rel Exp>
        final int SYMBOL_RELOP                 = 77;  // <relop>
        final int SYMBOL_RETURN2               = 78;  // <return>
        final int SYMBOL_SELECTION             = 79;  // <selection>
        final int SYMBOL_SIMPLELOCATION        = 80;  // <simpleLocation>
        final int SYMBOL_STATEMENT             = 81;  // <statement>
        final int SYMBOL_STRUCTDECLARATION     = 82;  // <structDeclaration>
        final int SYMBOL_TYPE                  = 83;  // <Type>
        final int SYMBOL_VALUE                 = 84;  // <Value>
        final int SYMBOL_VARDECL1              = 85;  // <varDecl 1>
        final int SYMBOL_VARDECLARATION        = 86;  // <varDeclaration>
    };

    public interface RuleConstants{
       final int RULE_PROGRAM_CLASS_PROGRAM_LBRACE_RBRACE       =  0;  // <Program> ::= class Program '{' <kleene_declaration> '}'
       final int RULE_KLEENE_DECLARATION                        =  1;  // <kleene_declaration> ::= <declaration> <kleene_declaration>
       final int RULE_KLEENE_DECLARATION2                       =  2;  // <kleene_declaration> ::= 
       final int RULE_DECLARATION                               =  3;  // <declaration> ::= <structDeclaration>
       final int RULE_DECLARATION2                              =  4;  // <declaration> ::= <varDeclaration>
       final int RULE_DECLARATION3                              =  5;  // <declaration> ::= <methodDeclaration>
       final int RULE_VARDECLARATION_ID_SEMI                    =  6;  // <varDeclaration> ::= <Type> id <varDecl 1> ';'
       final int RULE_VARDECL1_LBRACKET_NUM_RBRACKET            =  7;  // <varDecl 1> ::= '[' num ']'
       final int RULE_VARDECL1                                  =  8;  // <varDecl 1> ::= 
       final int RULE_STRUCTDECLARATION_STRUCT_ID_LBRACE_RBRACE =  9;  // <structDeclaration> ::= struct id '{' <kleene_varDeclaration> '}'
       final int RULE_KLEENE_VARDECLARATION                     = 10;  // <kleene_varDeclaration> ::= <varDeclaration> <kleene_varDeclaration>
       final int RULE_KLEENE_VARDECLARATION2                    = 11;  // <kleene_varDeclaration> ::= 
       final int RULE_METHODDECLARATION_ID_LPARAN_RPARAN        = 12;  // <methodDeclaration> ::= <Type> id '(' <opt_parameter> ')' <block>
       final int RULE_OPT_PARAMETER                             = 13;  // <opt_parameter> ::= <parameter list>
       final int RULE_OPT_PARAMETER2                            = 14;  // <opt_parameter> ::= 
       final int RULE_PARAMETERLIST                             = 15;  // <parameter list> ::= <parameter>
       final int RULE_PARAMETERLIST_COMMA                       = 16;  // <parameter list> ::= <parameter list> ',' <parameter>
       final int RULE_TYPE_INT                                  = 17;  // <Type> ::= int
       final int RULE_TYPE_CHAR                                 = 18;  // <Type> ::= char
       final int RULE_TYPE_BOOLEAN                              = 19;  // <Type> ::= boolean
       final int RULE_TYPE_VOID                                 = 20;  // <Type> ::= void
       final int RULE_TYPE_STRUCT_ID                            = 21;  // <Type> ::= struct id
       final int RULE_TYPE                                      = 22;  // <Type> ::= <structDeclaration>
       final int RULE_PARAMETER_ID                              = 23;  // <parameter> ::= <Type> id
       final int RULE_PARAMETER_ID_LBRACKET_RBRACKET            = 24;  // <parameter> ::= <Type> id '[' ']'
       final int RULE_BLOCK_LBRACE_RBRACE                       = 25;  // <block> ::= '{' <kleene_varDeclaration> <kleene_statement> '}'
       final int RULE_KLEENE_STATEMENT                          = 26;  // <kleene_statement> ::= <statement> <kleene_statement>
       final int RULE_KLEENE_STATEMENT2                         = 27;  // <kleene_statement> ::= 
       final int RULE_STATEMENT                                 = 28;  // <statement> ::= <block>
       final int RULE_STATEMENT2                                = 29;  // <statement> ::= <selection>
       final int RULE_STATEMENT3                                = 30;  // <statement> ::= <iteration>
       final int RULE_STATEMENT_SEMI                            = 31;  // <statement> ::= <return> ';'
       final int RULE_STATEMENT_SEMI2                           = 32;  // <statement> ::= <assign> ';'
       final int RULE_STATEMENT_SEMI3                           = 33;  // <statement> ::= <opt_expression> ';'
       final int RULE_SELECTION_IF_LPARAN_RPARAN                = 34;  // <selection> ::= if '(' <expression> ')' <block> <opt_else_block>
       final int RULE_OPT_ELSE_BLOCK_ELSE                       = 35;  // <opt_else_block> ::= else <block>
       final int RULE_OPT_ELSE_BLOCK                            = 36;  // <opt_else_block> ::= 
       final int RULE_ITERATION_WHILE_LPARAN_RPARAN             = 37;  // <iteration> ::= while '(' <expression> ')' <block>
       final int RULE_RETURN_RETURN                             = 38;  // <return> ::= return <opt_expression>
       final int RULE_ASSIGN_EQ                                 = 39;  // <assign> ::= <location> '=' <expression>
       final int RULE_OPT_EXPRESSION                            = 40;  // <opt_expression> ::= <expression>
       final int RULE_OPT_EXPRESSION2                           = 41;  // <opt_expression> ::= 
       final int RULE_EXPRESSION                                = 42;  // <expression> ::= <Rel Exp> <conditionalop> <expression>
       final int RULE_EXPRESSION2                               = 43;  // <expression> ::= <Rel Exp>
       final int RULE_RELEXP                                    = 44;  // <Rel Exp> ::= <Add Exp> <relop> <Rel Exp>
       final int RULE_RELEXP2                                   = 45;  // <Rel Exp> ::= <Add Exp>
       final int RULE_ADDEXP                                    = 46;  // <Add Exp> ::= <Mult Exp> <addop> <Add Exp>
       final int RULE_ADDEXP2                                   = 47;  // <Add Exp> ::= <Mult Exp>
       final int RULE_MULTEXP                                   = 48;  // <Mult Exp> ::= <Negate Exp> <mulop> <Mult Exp>
       final int RULE_MULTEXP2                                  = 49;  // <Mult Exp> ::= <Negate Exp>
       final int RULE_NEGATEEXP_MINUS                           = 50;  // <Negate Exp> ::= '-' <Value>
       final int RULE_NEGATEEXP_EXCLAM                          = 51;  // <Negate Exp> ::= '!' <Value>
       final int RULE_NEGATEEXP                                 = 52;  // <Negate Exp> ::= <Value>
       final int RULE_CONDITIONALOP_AMPAMP                      = 53;  // <conditionalop> ::= '&&'
       final int RULE_CONDITIONALOP_PIPEPIPE                    = 54;  // <conditionalop> ::= '||'
       final int RULE_RELOP_LTEQ                                = 55;  // <relop> ::= '<='
       final int RULE_RELOP_LT                                  = 56;  // <relop> ::= '<'
       final int RULE_RELOP_GT                                  = 57;  // <relop> ::= '>'
       final int RULE_RELOP_GTEQ                                = 58;  // <relop> ::= '>='
       final int RULE_RELOP_EQEQ                                = 59;  // <relop> ::= '=='
       final int RULE_RELOP_EXCLAMEQ                            = 60;  // <relop> ::= '!='
       final int RULE_ADDOP_PLUS                                = 61;  // <addop> ::= '+'
       final int RULE_ADDOP_MINUS                               = 62;  // <addop> ::= '-'
       final int RULE_MULOP_TIMES                               = 63;  // <mulop> ::= '*'
       final int RULE_MULOP_DIV                                 = 64;  // <mulop> ::= '/'
       final int RULE_MULOP_PERCENT                             = 65;  // <mulop> ::= '%'
       final int RULE_VALUE                                     = 66;  // <Value> ::= <literal>
       final int RULE_VALUE_LPARAN_RPARAN                       = 67;  // <Value> ::= '(' <expression> ')'
       final int RULE_VALUE2                                    = 68;  // <Value> ::= <methodCall>
       final int RULE_VALUE3                                    = 69;  // <Value> ::= <location>
       final int RULE_LOCATION_DOT                              = 70;  // <location> ::= <simpleLocation> '.' <location>
       final int RULE_LOCATION                                  = 71;  // <location> ::= <simpleLocation>
       final int RULE_SIMPLELOCATION_ID_LBRACKET_RBRACKET       = 72;  // <simpleLocation> ::= id '[' <expression> ']'
       final int RULE_SIMPLELOCATION_ID                         = 73;  // <simpleLocation> ::= id
       final int RULE_METHODCALL_ID_LPARAN_RPARAN               = 74;  // <methodCall> ::= id '(' <opt_arg_separated> ')'
       final int RULE_OPT_ARG_SEPARATED                         = 75;  // <opt_arg_separated> ::= <arg list>
       final int RULE_OPT_ARG_SEPARATED2                        = 76;  // <opt_arg_separated> ::= 
       final int RULE_ARGLIST                                   = 77;  // <arg list> ::= <arg>
       final int RULE_ARGLIST_COMMA                             = 78;  // <arg list> ::= <arg list> ',' <arg>
       final int RULE_ARG                                       = 79;  // <arg> ::= <expression>
       final int RULE_LITERAL                                   = 80;  // <literal> ::= <int_literal>
       final int RULE_LITERAL2                                  = 81;  // <literal> ::= <char_literal>
       final int RULE_LITERAL3                                  = 82;  // <literal> ::= <bool_literal>
       final int RULE_INT_LITERAL_NUM                           = 83;  // <int_literal> ::= num
       final int RULE_CHAR_LITERAL_CHARACTER                    = 84;  // <char_literal> ::= character
       final int RULE_BOOL_LITERAL_TRUE                         = 85;  // <bool_literal> ::= true
       final int RULE_BOOL_LITERAL_FALSE                        = 86;  // <bool_literal> ::= false
    };
    
    public GOLDParser parser = new GOLDParser();
    private String rutaGramatica= "src/grammar/GoldParser/Decaf2.cgt";
    private static final String gramatica = "src/Grammar/Decaf2.grm";
    private IDecaf padre;
    private String textToParse = "";
    private boolean error = true;
    private boolean comentarios=false;
    
    private TablaSimbolos tablaSimbolos = new TablaSimbolos();
    private MetodoTabla tablaMetodos = new MetodoTabla();
    private EstructuraTabla tablaEstructura = new EstructuraTabla();
    private Verificador ambitoActual = new Verificador();
    private String tipoRetorno = "";
    private boolean methodAmbit = false;
    private LinkedList<IGeneracionCodigo> codigoX = new LinkedList<IGeneracionCodigo>();
    
    //------------------------------------------------
    
    private LinkedList<org.jdom.Content> elementos = new LinkedList<org.jdom.Content>();

    
    public Main(){}
    
    public void codigoIntermedio(String textToParse,IDecaf padre){
        this.padre=padre;
        this.textToParse = textToParse;

        parsea();
        
        try{
            if(this.error == false){

                //crea la tabla de símbolos
                this.crearTabla(parser.currentReduction());


                if(error == true){
                    parser.closeFile();
                    return;
                }

                //println("Method Table: \n"+this.methodTable.toString());
                //println("Struct Table: \n"+this.structTable.toString());
                verificacionMain();

                if(error == true){
                    parser.closeFile();
                    return;
                }

                //setear el ámbito como el ámbito padre
                this.ambitoActual = this.ambitoActual.darPadre();
                //resetear para obtener hijo
                this.ambitoActual.resetearAHijo();

                //Element varGlobales = new Element("VariablesGlobales");
                elementos.add(new Comment("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"));
                codigoX.add(new Comentario("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"));
                
                codigoX.add(new Comentario("               Ámbito global"));
                elementos.add(new Comment("Ambito Global"));
                for(tabla.Simbolo a : this.darTablaSimbolos().darTodosSimbolosGlobales(ambitoActual)){
                    String [] b = GenCodigo.darDesplazamiento(a,this,this.ambitoActual);
                    codigoX.add(new Comentario(a.darId()+" = "+b[0]+"["+b[1]+"]"));
                    elementos.add(new Comment(a.darId()+" = "+b[0]+"["+b[1]+"]"));
                }
                codigoX.add(new Comentario("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n"));
                elementos.add(new Comment("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"));

                padre.imprimir("\n--------------------------------------------\n", 2);
                
                //generación de código intermedio
                //elementos.add(varGlobales);
                generarCodigo(parser.currentReduction(),0);
                new GenXml(elementos);
                
                for(String a : darCodigoString()){

                    System.out.println(a+"\n");
                }
                //parser.getInterCodeStr()
                
            }
            
            parser.closeFile();
        }
        catch(ParserException parse){

            //println("**PARSER ERROR**\n" + "parse.toString()");
            padre.imprimir("** PARSER ERROR ** "+"\n\t"+ parse.toString() +"\n",1);
        }
    }

public void parsea(){
        //GOLDParser parser = new GOLDParser();
        parser.setTrimReductions(false);
        try{
            parser.loadCompiledGrammar(this.rutaGramatica);
            //println(textToParse);
            parser.openFile(textToParse);
        }
       catch(ParserException parse){

           //println("**PARSER ERROR**\n" + parse.toString());
           padre.imprimir("** PARSER ERROR ** "+"\n\t"+ parse.toString() +"\n",1);
       }

       boolean done = false;
       int response = -1;

       while(!done)
       {
           try{
               response = parser.parse();
           }
           catch(ParserException parse){
               //println("**PARSER ERROR**\n" + parse.toString());
               padre.imprimir("** PARSER ERROR ** "+"\n\t"+ parse.toString() +"\n",1);
           }

           switch(response)
           {
               case gpMsgTokenRead:
                   /* A token was read by the parser. The Token Object can be accessed
                      through the CurrentToken() property:  Parser.CurrentToken */
                   parser.currentToken().setLineNumber(parser.currentLineNumber());
                   Token myTok = parser.currentToken();
               	
                   int cos=myTok.getPSymbol().getTableIndex();
                   
                   
                   switch (myTok.getPSymbol().getTableIndex())
                   {
                   	case SymbolConstants.SYMBOL_PROGRAM:
                   		padre.imprimir("TokenRead:   "+cos+"  "+myTok.getPSymbol()+ "    "+myTok.getText()+ "   Text:  "+myTok.getData().toString()+"\n",2);
                   		break;
                   	case SymbolConstants.SYMBOL_ID:
                   	case SymbolConstants.SYMBOL_TRUE:
                   	case SymbolConstants.SYMBOL_FALSE:
                   	case SymbolConstants.SYMBOL_CHARACTER:
                   		padre.imprimir("gpMsgTokenRead:   "+cos+"  "+myTok.getPSymbol()+ "    "+myTok.getText()+ "   Text:  "+myTok.getData().toString()+"\n",2);
                   		break;
                   	case SymbolConstants.SYMBOL_NUM:
                   		padre.imprimir("gpMsgTokenRead:   "+cos+"  "+myTok.getPSymbol()+ "    "+myTok.getText()+ "   Text:  "+myTok.getData().toString()+"\n",2);
                   		//int numero = Integer.parseInt(myTok.getData().toString());
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
                   //Reduction reduction  = parser.currentReduction();
                    //Parser.Reduction = //Object you created to store the rule
                    // ************************************** log file
                    //println("gpMsgReduction");
                   // Reduction myRed = parser.currentReduction();
                    //this.addDebugLog("gpMsgReduction (Line "+parser.currentLineNumber()+"): "+"\n\t"+ myRed.getParentRule().getText() +"\n");
                    //println(myRed.getParentRule().getText());
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
                    this.error = false;
                    padre.imprimir("MsgAccept: El programa fue parseado correctamente",1);
                    padre.imprimir("\n-----------------------------------------------\n",2);
                    //println("gpMsgAccept");
                    // ************************************** end log
                    done = true;
                    break;
                case gpMsgLexicalError:
                    /* Place code here to handle a illegal or unrecognized token
                           To recover, pop the token from the stack: Parser.PopInputToken */
                    // ************************************** log file
                    Token token = parser.popInputToken();
                    padre.imprimir("MsgLexicalError: Linea "+parser.currentLineNumber()+": " +"Token No Esperado/Reconocido: "+ (String)token.getData(),1);
                    //println("gpMsgLexicalError");
                    // ************************************** end log
                    done = true;
                    break;
                case gpMsgNotLoadedError:
                    /* Load the Compiled Grammar Table file first. */
                    // ************************************** log file
                	padre.imprimir("MsgNotLoadedError: Linea "+parser.currentLineNumber()+": " +""+ "Cargue la tabla de la gramática primero.",1);
                    //println("gpMsgNotLoadedError");
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
                    //println("Token not expected: " + (String)theTok.getData());
                    // ************************************** log file
                    padre.imprimir("MsgSyntaxError: Linea "+parser.currentLineNumber()+": " +""+ "Token no esperado: "+ (String)theTok.getData(),1);
                    //println("gpMsgSyntaxError");
                    // ************************************** end log
                    break;
                case gpMsgCommentError:
                    /* The end of the input was reached while reading a comment.
                             This is caused by a comment that was not terminated */
                    // ************************************** log file
                	padre.imprimir("MsgCommentError: Linea "+parser.currentLineNumber()+"): " +""+ "No se ha encontrado fin mientras se leia un comentario",1);
                    //println("gpMsgCommentError");
                    // ************************************** end log
                    done = true;
                    break;
                case gpMsgInternalError:
                    /* Something horrid happened inside the parser. You cannot recover */
                    // ************************************** log file
                	padre.imprimir("MsgInternalError: Linea "+parser.currentLineNumber()+"): " +""+ "Nunca debería pasar, si pasa, estas perdido",1);
                    //println("gpMsgInternalError");
                    // ************************************** end log
                    done = true;
                    break;
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
    	return error;
    }

    
    public TablaSimbolos darTablaSimbolos() {
        return tablaSimbolos;
    }

    public EstructuraTabla darTablaEstructuras() {
        return this.tablaEstructura;
    }

    public MetodoTabla darTablaMetodos() {
        return this.tablaMetodos;
    }

    public boolean isError() {
        return error;
    }

    private LinkedList<Simbolo> darListaParametros(Reduction reduccion){

        LinkedList<Simbolo> lista = new LinkedList<Simbolo>();
        String id = "";
        Reduction redu = null;
        switch(reduccion.getParentRule().getTableIndex())
        {
            case RuleConstants.RULE_OPT_PARAMETER:
                //<opt_parameter> ::= <parameter list>
                lista.addAll(darListaParametros((Reduction)reduccion.getToken(0).getData()));
                break;
            case RuleConstants.RULE_OPT_PARAMETER2:
                //<opt_parameter> ::=
                break;
            case RuleConstants.RULE_PARAMETERLIST_COMMA:
                //<parameter list> ::= <parameter list> ',' <parameter>
                lista.addAll(darListaParametros((Reduction)reduccion.getToken(0).getData()));
                lista.addAll(darListaParametros((Reduction)reduccion.getToken(2).getData()));
                break;
            case RuleConstants.RULE_PARAMETERLIST:
                //<parameter list> ::= <parameter>
                lista.addAll(darListaParametros((Reduction)reduccion.getToken(0).getData()));
                break;
            case RuleConstants.RULE_PARAMETER_ID: case RuleConstants.RULE_PARAMETER_ID_LBRACKET_RBRACKET:
                //<parameter> ::= <Type> id | <Type> id '[' ']'
                //<Type> = <parameterType> ::= 'int' | 'char' | 'boolean'
                redu = (Reduction) reduccion.getToken(0).getData();
                id = (String) reduccion.getToken(1).getData();
                Tipo tipo = null;
                //instanceof es mejor que escribir objecto.getClass() == Clase.class
                if(redu.getToken(0).getData() instanceof String){
                    String a = (String)redu.getToken(0).getData();
                    if(a.equals("int") || a.equals("char") || a.equals("boolean")){
                        tipo = new Tipo(a,Tipo.darTamanioBasico(a));
                        tipo.asigParametro(true);
                    }else{
                        padre.imprimir("\nTipo Parametro Incorrecto: Linea "+redu.getToken(0).getLineNumber()+": "+(String)redu.getToken(0).getData()+" ---> "+id+"",1);
                        break;
                    }
                }else{
                    padre.imprimir("\nTipo Parametro Incorrecto: Linea "+redu.getToken(0).getLineNumber()+": "+redu.getToken(0).getName()+" ---> "+id+"",1);
                    break;
                }
                if(reduccion.getTokenCount()==4){
                    tipo.asigArreglo(true);
                }
                Simbolo simb = new Simbolo(id, tipo, this.ambitoActual);
                lista.add(simb);
                break;
        }
        return lista;
    }

    private LinkedList<Simbolo> darListaListaVars(Reduction reduccion){

        LinkedList<Simbolo> list = new LinkedList<Simbolo>();
        Tipo tipo = null;

        switch(reduccion.getParentRule().getTableIndex())
        {
            case RuleConstants.RULE_KLEENE_VARDECLARATION:
                //<kleene_varDeclaration> ::= <varDeclaration> <kleene_varDeclaration>
                list.addAll(this.darListaListaVars((Reduction)reduccion.getToken(0).getData()));
                list.addAll(this.darListaListaVars((Reduction)reduccion.getToken(1).getData()));
                break;
            case RuleConstants.RULE_KLEENE_VARDECLARATION2:
                //<kleene_varDeclaration> ::=
                break;
            case RuleConstants.RULE_VARDECLARATION_ID_SEMI:
                //<varDeclaration> ::= <Type> id <varDecl 1> ';'
                String idVarD = (String)reduccion.getToken(1).getData();
                Reduction varDec = (Reduction)reduccion.getToken(2).getData();
                Reduction tipo1 =(Reduction)reduccion.getToken(0).getData();
                int numeroTipo = (tipo1).getParentRule().getTableIndex();
                
                if(numeroTipo == RuleConstants.RULE_TYPE_INT || numeroTipo == RuleConstants.RULE_TYPE_CHAR || numeroTipo == RuleConstants.RULE_TYPE_BOOLEAN){
                    //<Type> ::= int | char | boolean
                    String a = (String)tipo1.getToken(0).getData();
                    tipo = new Tipo(a,Tipo.darTamanioBasico(a));
                    tipo.asigSimple(true);
                }
                else if(numeroTipo == RuleConstants.RULE_TYPE_VOID){
                    //<Type> ::= void
                    padre.imprimir("\nNo se puede definir void como variable: Linea "+tipo1.getToken(0).getLineNumber()+"): "+tipo1.getToken(0).getData()+"",1);
                }
                else if(numeroTipo == RuleConstants.RULE_TYPE_STRUCT_ID){
                    //<Type> ::= struct id
                    String id = (String)tipo1.getToken(1).getData();
                    Estructura struct = this.tablaEstructura.darEstructura(id, ambitoActual);
                    if(struct == null){
                        padre.imprimir("\nEn estructura, tipo no definido: Linea "+tipo1.getToken(1).getLineNumber()+": struct "+id+"",1);
                    }else{
                        tipo = struct.darTipo();
                    }
                }
                else if(numeroTipo == RuleConstants.RULE_TYPE){
                    //<Type> ::= <structDeclaration>
                    //type = this.getTypeStructDecl((Reduction)reduction.getToken(0).getData());
                    padre.imprimir("\nEstructura no puede tener una variable tipo estructura adentro: Linea "+reduccion.getToken(0).getLineNumber()+": "+reduccion.getToken(0).getData()+"",1);
                }
                //<varDecl 1> ::= '[' num ']' |
                if(tipo!=null){
                    if(varDec.getTokenCount()==3){
                        int num = Integer.parseInt((String)varDec.getToken(1).getData());
                        if(num==0){
                            padre.imprimir("\nArreglo debe de ser mayor a 0: Linea "+reduccion.getToken(1).getLineNumber()+"",1);
                            break;
                        }
                        tipo.asigArreglo(true, num);
                    }
                    Simbolo simbolo = new Simbolo(idVarD,tipo,this.ambitoActual);
                    list.add(simbolo);
                }
                
                break;
        }
        return list;
    }


    private Estructura darDeclaracionEstructura(Reduction reduccion){
        //<structDeclaration> ::= struct id '{' <kleene_varDeclaration> '}'
        String id = (String)reduccion.getToken(1).getData();
        Reduction kleenevarDecl = (Reduction)reduccion.getToken(3).getData();
        LinkedList<Simbolo> listaVars = new LinkedList<Simbolo>();
        listaVars = this.darListaListaVars(kleenevarDecl);
        Estructura struct = new Estructura(id, this.ambitoActual, listaVars);
        return struct;
    }

    private Tipo darTipo(Reduction reduccion) {
        Tipo tipo = null;
        switch(reduccion.getParentRule().getTableIndex())
        {
            case RuleConstants.RULE_TYPE_INT: case RuleConstants.RULE_TYPE_CHAR: case RuleConstants.RULE_TYPE_BOOLEAN: case RuleConstants.RULE_TYPE_VOID:
                //<Type> ::= int | char | boolean | void
                String a = (String)reduccion.getToken(0).getData();
                tipo = new Tipo(a,Tipo.darTamanioBasico(a));
                tipo.asigSimple(true);
                break;
            case RuleConstants.RULE_TYPE_STRUCT_ID:
                //<Type> ::= struct id
                String id = (String) reduccion.getToken(1).getData();
                boolean existe = (this.tablaEstructura.darEstructura(id, ambitoActual) != null);
                if(existe){
                    tipo = this.tablaEstructura.darEstructura(id, ambitoActual).darTipo();
                }
                else{
                    padre.imprimir("\nEstructura no definida en linea: "+reduccion.getToken(1).getLineNumber()+": struct "+id+"",1);
                }
                break;
            case RuleConstants.RULE_TYPE:
                //<Type> ::= <structDeclaration>
                boolean agregado = agregarEstructuraATabla((Reduction)reduccion.getToken(0).getData());
                if(agregado){
                    tipo = this.tablaEstructura.darUltimaEstructura().darTipo();
                }
                
                break;
        }
        return tipo;
    }

    private boolean agregarEstructuraATabla(Reduction reduccion) {
        //<structDeclaration> ::= struct id '{' <kleene_varDeclaration> '}'
        Estructura estuct = this.darDeclaracionEstructura(reduccion);
        if(estuct == null){
            padre.imprimir("\nError Struct en linea "+reduccion.getToken(1).getLineNumber()+": struct "+((String)reduccion.getToken(1).getData())+"",1);
            return false;
        }
        boolean canAdd = this.tablaEstructura.agregarEstructura(estuct);

        if(!canAdd){
            padre.imprimir("\nYa existe estructura, cambia nombre o revise en linea "+reduccion.getToken(1).getLineNumber()+": struct "+((String)reduccion.getToken(1).getData())+"",1);
        }
        padre.imprimir("Nueva Declaracion de Estructura en linea "+reduccion.getToken(1).getLineNumber()+": struct "+estuct.darId()+"",2);
        return canAdd;
    }

    private void agregarDeclaracionVariableaATabla(Reduction reduccion) {
        //<varDeclaration> ::= <Type> id <varDecl 1> ';'
        //<varDecl 1> ::= '[' num ']' |
        String idlexeme = (String)reduccion.getToken(1).getData();
        Reduction varTipo = (Reduction)reduccion.getToken(0).getData();

        Tipo tipo = this.darTipo(varTipo);
        if(tipo == null){
            return;
        }
        boolean yaExiste = this.tablaSimbolos.existeSimboloEnVerificador(idlexeme, ambitoActual);
        
        if(yaExiste){
            padre.imprimir("\nImposible declarar: Linea "+reduccion.getToken(1).getLineNumber()+": Ya existe variable: "+idlexeme+"",1);
            return;
        }
        Reduction varDecl1 = (Reduction)reduccion.getToken(2).getData();
        Simbolo varSimbolo = new Simbolo(idlexeme, tipo, this.ambitoActual);

        if(varDecl1.getTokenCount()==3){
            int num = Integer.parseInt((String)varDecl1.getToken(1).getData());
            if(num==0){
                padre.imprimir("\nArreglo debe ser mayor a 0: Linea "+varDecl1.getToken(1).getLineNumber()+"",1);
                return;
            }
            varSimbolo.darTipo().asigArreglo(true, num);
            varSimbolo.darTipo().asigSimple(false);
        }
        this.tablaSimbolos.agregarSimbolo(varSimbolo);
        padre.imprimir("Nueva Declaración Variable en linea "+reduccion.getToken(1).getLineNumber()+": "+idlexeme+"",2);

    }


    private boolean existeLocacionEnListaVar(String locacion, LinkedList<Simbolo> listaVars) {
        boolean existe = false;

        for(Simbolo a : listaVars){
            if(locacion.equals(a.darId())){
                existe = true;
                break;
            }
        }

        return existe;
    }


    private Simbolo darLocacionEnListaVar(String locacion, LinkedList<Simbolo> listaVars) {

        for(Simbolo a : listaVars){
            if(locacion.equals(a.darId())){
                return a;
            }
        }

        return null;
    }


    public String darFirmaMetodo(Reduction reduccion) {
        //<methodCall> ::= id '(' <opt_arg_separated> ')'
        String firma = "";
        LinkedList<String> parametros = this.darParametrosActuales((Reduction)reduccion.getToken(2).getData());
        String id = (String)reduccion.getToken(0).getData();

        firma = id+"(";
        if(parametros.size()>0){
            firma+=parametros.get(0)+",";
            for(int i = 1;i<parametros.size(); i++){
                String a  = parametros.get(i);
                firma+=""+a+",";
            }
            firma = firma.substring(0, firma.length()-1);
        }
        firma+=")";

        return firma;
    }

    private LinkedList<String> darParametrosActuales(Reduction reduccion) {
        //<methodCall> ::= id '(' <opt_arg_separated> ')'
        LinkedList<String> paramActual = new LinkedList<String>();

        switch(reduccion.getParentRule().getTableIndex())
        {
            case RuleConstants.RULE_OPT_ARG_SEPARATED:
                //<opt_arg_separated> ::= <arg list>
                paramActual.addAll(this.darParametrosActuales((Reduction)reduccion.getToken(0).getData()));
                break;
            case RuleConstants.RULE_OPT_ARG_SEPARATED2:
                //<opt_arg_separated> ::=
                break;
            case RuleConstants.RULE_ARGLIST:
                //<arg list> ::= <arg>
                paramActual.addAll(this.darParametrosActuales((Reduction)reduccion.getToken(0).getData()));
                break;
            case RuleConstants.RULE_ARGLIST_COMMA:
                //<arg list> ::= <arg list> ',' <arg>
                paramActual.addAll(this.darParametrosActuales((Reduction)reduccion.getToken(0).getData()));
                paramActual.addAll(this.darParametrosActuales((Reduction)reduccion.getToken(2).getData()));
                break;
            case RuleConstants.RULE_ARG:
                //<arg> ::= <expression>
                String arg = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                paramActual.add(arg);
                break;
        }
        return paramActual;
    }

    private void agregarDeclaracionMetodoATabla(Reduction reduction) {
        //<methodDeclaration> ::= <Type> id '(' <opt_parameter> ')' <block>
        //<Type> = <methodType> ::= 'int' | 'char' | 'boolean' | 'void'
        Reduction tipo1 = (Reduction)reduction.getToken(0).getData();
        String id = (String)reduction.getToken(1).getData();
        Reduction parametro = (Reduction)reduction.getToken(3).getData();
        Tipo tipo = null;

        if(tipo1.getTokenCount()==1){

            if(tipo1.getToken(0).getData() instanceof String){
                String a = (String)tipo1.getToken(0).getData();
                if(a.equals("void") || a.equals("int") || a.equals("char") || a.equals("boolean")){
                    tipo = new Tipo(a,Tipo.darTamanioBasico(a));
                }
                else{
                    padre.imprimir("\nTipo de método incorrecto en linea "+reduction.getToken(1).getLineNumber()+": "+(String)tipo1.getToken(0).getData()+"",1);
                    return;
                }
            }
            else{
                padre.imprimir("\nTipo de método incorrecto en linea "+reduction.getToken(1).getLineNumber()+": "+(String)tipo1.getToken(0).getData()+"",1);
                return;
            }
        }
        else{
            padre.imprimir("\nTipo de método incorrecto en linea "+reduction.getToken(1).getLineNumber()+": "+(String)tipo1.getToken(0).getData()+"",1);
            return;
        }
        //<opt_parameter>
        Verificador nuevo = new Verificador(this.ambitoActual);
        this.ambitoActual = nuevo;
        this.methodAmbit = true;
        LinkedList<Simbolo> parametros = this.darListaParametros(parametro);
        Metodo met = new Metodo(id, tipo, parametros);
        boolean agregable = this.tablaMetodos.agregarMetodo(met);
        if(agregable == false){
            padre.imprimir("\nYa existe este método con igual tipo de parametros: Linea "+reduction.getToken(1).getLineNumber()+": "+met.darFirmaMetodo()+"",1);
            return;
        }
        padre.imprimir("Nueva Declaración Método: Linea "+reduction.getToken(1).getLineNumber()+": "+met.darFirmaMetodo()+"",2);
        this.tablaSimbolos.agregarTodosLosSimbolos(parametros);
    }

    private void crearTabla(Reduction reduccion){

        Reduction kleene_declaration;
        Reduction declaration;
        Reduction structDeclaration;
        Reduction varDeclaration;
        Reduction methodDeclaration;
        Reduction kleene_varDeclaration;
        Reduction kleeneStatement;
        Reduction statement;
        Reduction block;
        Reduction selection;
        Reduction iteration;
        Reduction opt_else_block;

        switch(reduccion.getParentRule().getTableIndex())
        {
            case RuleConstants.RULE_PROGRAM_CLASS_PROGRAM_LBRACE_RBRACE:
                //<Program> ::= class Program '{' <kleene_declaration> '}'
                kleene_declaration = (Reduction)reduccion.getToken(3).getData();
                this.crearTabla(kleene_declaration);
                break;
            case RuleConstants.RULE_KLEENE_DECLARATION:
                //<kleene_declaration> ::= <declaration> <kleene_declaration>
                declaration = (Reduction)reduccion.getToken(0).getData();
                this.crearTabla(declaration);
                kleene_declaration = (Reduction)reduccion.getToken(1).getData();
                this.crearTabla(kleene_declaration);
                break;
            case RuleConstants.RULE_KLEENE_DECLARATION2:
                //<kleene_declaration> ::=
                break;
            case RuleConstants.RULE_DECLARATION:
                //<declaration> ::= <structDeclaration>
                structDeclaration = (Reduction)reduccion.getToken(0).getData();
                this.crearTabla(structDeclaration);
                break;
            case RuleConstants.RULE_DECLARATION2:
                //<declaration> ::= <varDeclaration>
                varDeclaration = (Reduction)reduccion.getToken(0).getData();
                this.crearTabla(varDeclaration);
                break;
            case RuleConstants.RULE_DECLARATION3:
                //<declaration> ::= <methodDeclaration>
                methodDeclaration = (Reduction)reduccion.getToken(0).getData();
                this.crearTabla(methodDeclaration);
                break;
            case RuleConstants.RULE_KLEENE_VARDECLARATION:
                //<kleene_varDeclaration> ::= <varDeclaration> <kleene_varDeclaration>
                varDeclaration = (Reduction)reduccion.getToken(0).getData();
                this.crearTabla(varDeclaration);
                kleene_varDeclaration = (Reduction)reduccion.getToken(1).getData();
                this.crearTabla(kleene_varDeclaration);
                break;
            case RuleConstants.RULE_KLEENE_VARDECLARATION2:
                //<kleene_varDeclaration> ::=
                break;
            case RuleConstants.RULE_VARDECLARATION_ID_SEMI:
                //<varDeclaration> ::= <Type> id <varDecl 1> ';'//<varDeclaration> ::= <Type> id <varDecl 1> ';'
                //System.out.println("varDeclaration");
                this.agregarDeclaracionVariableaATabla(reduccion);
                break;
            case RuleConstants.RULE_STRUCTDECLARATION_STRUCT_ID_LBRACE_RBRACE:
                //<structDeclaration> ::= struct id '{' <kleene_varDeclaration> '}'
                //no meterse a kleene varDeclaration
                //entra acá cuando sólo se declara (sin ningún declarar ninguna variable de este tipo)
                //System.out.println("structDeclaration");
                this.agregarEstructuraATabla(reduccion);
                break;
            case RuleConstants.RULE_METHODDECLARATION_ID_LPARAN_RPARAN:
                //<methodDeclaration> ::= <Type> id '(' <opt_parameter> ')' <block>
                //System.out.println("methodDeclaration");
                this.agregarDeclaracionMetodoATabla(reduccion);
                block = (Reduction)reduccion.getToken(5).getData();
                this.crearTabla(block);

                break;
            case RuleConstants.RULE_TYPE:
                //<Type> ::= <structDeclaration>
                structDeclaration = (Reduction)reduccion.getToken(0).getData();
                this.crearTabla(structDeclaration);
                break;
            case RuleConstants.RULE_BLOCK_LBRACE_RBRACE:
                //<block> ::= '{' <kleene_varDeclaration> <kleene_statement> '}'
                if(this.methodAmbit == true){
                    //no cambiar de ámbito porque se acaba de definir un método
                    this.methodAmbit = false;
                }
                else{
                    Verificador nuevo = new Verificador(this.ambitoActual);
                    this.ambitoActual = nuevo;
                }
                kleene_varDeclaration = (Reduction)reduccion.getToken(1).getData();
                this.crearTabla(kleene_varDeclaration);
                kleeneStatement = (Reduction)reduccion.getToken(2).getData();
                this.crearTabla(kleeneStatement);
                this.ambitoActual = this.ambitoActual.darVerificadorPrevio();
                break;
            case RuleConstants.RULE_KLEENE_STATEMENT:
                //<kleene_statement> ::= <statement> <kleene_statement>
                statement = (Reduction)reduccion.getToken(0).getData();
                this.crearTabla(statement);
                kleeneStatement = (Reduction)reduccion.getToken(1).getData();
                this.crearTabla(kleeneStatement);
                break;
            case RuleConstants.RULE_KLEENE_STATEMENT2:
                //<kleene_statement> ::=
                break;
            case RuleConstants.RULE_STATEMENT:
                //<statement> ::= <block>
                block = (Reduction)reduccion.getToken(0).getData();
                this.crearTabla(block);
                break;
            case RuleConstants.RULE_STATEMENT2:
                //<statement> ::= <selection>
                selection = (Reduction)reduccion.getToken(0).getData();
                this.crearTabla(selection);
                break;
            case RuleConstants.RULE_STATEMENT3:
                //<statement> ::= <iteration>
                iteration = (Reduction)reduccion.getToken(0).getData();
                this.crearTabla(iteration);
                break;
            case RuleConstants.RULE_STATEMENT_SEMI:
                //<statement> ::= <return> ';'
                break;
            case RuleConstants.RULE_STATEMENT_SEMI2:
                //<statement> ::= <assign> ';'
                break;
            case RuleConstants.RULE_STATEMENT_SEMI3:
                //<statement> ::= <opt_expression> ';'
                break;
            case RuleConstants.RULE_SELECTION_IF_LPARAN_RPARAN:
                //<selection> ::= if '(' <expression> ')' <block> <opt_else_block>
                block = (Reduction)reduccion.getToken(4).getData();
                this.crearTabla(block);
                opt_else_block = (Reduction)reduccion.getToken(5).getData();
                this.crearTabla(opt_else_block);
                break;
            case RuleConstants.RULE_OPT_ELSE_BLOCK_ELSE:
                //<opt_else_block> ::= else <block>
                block = (Reduction)reduccion.getToken(1).getData();
                this.crearTabla(block);
                break;
            case RuleConstants.RULE_OPT_ELSE_BLOCK:
                //<opt_else_block> ::=
                break;
            case RuleConstants.RULE_ITERATION_WHILE_LPARAN_RPARAN:
                //<iteration> ::= while '(' <expression> ')' <block>
                block = (Reduction)reduccion.getToken(4).getData();
                this.crearTabla(block);
                break;
        }
    }

    

    private String obtenerTipo(Reduction reduccion){
        String retorno = "";

        String kleene_declaration,declaration;
        String declaracionMetodo,Type,block,kleene_statement,statement;
        String opt_else_block,opt_expression,expression,Rel_Exp,Add_Exp;
        String Mult_Exp,Negate_Exp,Value,location,simpleLocation;

        Simbolo simbolo;

        switch(reduccion.getParentRule().getTableIndex())
        {
            case RuleConstants.RULE_PROGRAM_CLASS_PROGRAM_LBRACE_RBRACE:
                //<Program> ::= class Program '{' <kleene_declaration> '}'
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(3).getData());
                break;
            case RuleConstants.RULE_KLEENE_DECLARATION:
                //<kleene_declaration> ::= <declaration> <kleene_declaration>
                declaration = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                kleene_declaration = this.obtenerTipo((Reduction)reduccion.getToken(1).getData());
                if(declaration.equals("error") || kleene_declaration.equals("error")){
                    retorno = "error";
                }else{
                    retorno = "void";
                }
                break;
            case RuleConstants.RULE_KLEENE_DECLARATION2:
                //<kleene_declaration> ::=
                retorno = "void";
                break;
            case RuleConstants.RULE_DECLARATION:
                //<declaration> ::= <structDeclaration>
                retorno = "void"; 
                break;
            case RuleConstants.RULE_DECLARATION2:
                //<declaration> ::= <varDeclaration>
                retorno = "void"; 
                break;
            case RuleConstants.RULE_DECLARATION3:
                //<declaration> ::= <methodDeclaration>
                declaracionMetodo = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                if(declaracionMetodo.equals("error")){
                    retorno ="error";
                }else{
                    retorno = "void";
                }
                break;
            case RuleConstants.RULE_METHODDECLARATION_ID_LPARAN_RPARAN:
                //<methodDeclaration> ::= <Type> id '(' <opt_parameter> ')' <block>
                //<Type> ::= int | char | boolean | void
                Reduction reduccionTipo = (Reduction)reduccion.getToken(0).getData();
                Type = (String)reduccionTipo.getToken(0).getData();
                this.tipoRetorno = Type;
                Reduction red_block = (Reduction)reduccion.getToken(5).getData();
                block = this.obtenerTipo(red_block);
                if(block.equals("error")){
                    retorno = "error";
                    break;
                }
                //si el tipo de block es diferente a type_return_method significa un missing return statement
                if(tipoRetorno.equals(block) || tipoRetorno.equals("void")){
                    retorno = "void";
                }else{
                    retorno = "error";
                    padre.imprimir("Missing return statement"+ red_block.getToken(3).getLineNumber()+ "\n\t"+"}",1);
                }
                break;
            case RuleConstants.RULE_BLOCK_LBRACE_RBRACE:
                //<block> ::= '{' <kleene_varDeclaration> <kleene_statement> '}'
                this.ambitoActual = this.ambitoActual.darHijo();
                //System.out.println("entra a nuevo ámbito: "+this.actual_ambit.getName());
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());
                //System.out.println("sale del ámbito: "+this.actual_ambit.getName());
                this.ambitoActual = this.ambitoActual.darVerificadorPrevio();
                //System.out.println("regresa al ámbito: "+this.actual_ambit.getName());
                break;
            case RuleConstants.RULE_KLEENE_STATEMENT:
                //<kleene_statement> ::= <statement> <kleene_statement>
                Reduction red_statement = (Reduction)reduccion.getToken(0).getData();
                Reduction red_kleene_statement = (Reduction)reduccion.getToken(1).getData();
                statement = this.obtenerTipo(red_statement);
                kleene_statement = this.obtenerTipo(red_kleene_statement);

                if(red_statement.getParentRule().getTableIndex() == RuleConstants.RULE_STATEMENT_SEMI){
                    //<statement> ::= <return> ';'
                    if(red_kleene_statement.getTokenCount() != 0){
                        //unreachable statement (Line: 4)
                        padre.imprimir("No se puede alcanzar Statement: "+" Linea "+(red_statement.getToken(1).getLineNumber()+1)+""+"\n",1);
                    }
                }else if(red_statement.getParentRule().getTableIndex() == RuleConstants.RULE_STATEMENT2){
                    //<statement> ::= <selection>
                    //<selection> ::= if '(' <expression> ')' <block> <opt_else_block>
                    int opt_else_block_len = ((Reduction)((Reduction)red_statement.getToken(0).getData()).getToken(5).getData()).getTokenCount();
                    if(red_kleene_statement.getTokenCount() != 0 && opt_else_block_len!=0 && statement.equals(this.tipoRetorno) && this.tipoRetorno.equals("void")==false){
                        Reduction red_selection = (Reduction)red_statement.getToken(0).getData();
                        padre.imprimir("unreachable statement"+" (Line "+(darUltimaLinea(red_selection)+1)+")"+"\n",1);
                    }
                }

                if(red_kleene_statement.getTokenCount() == 0){
                    retorno = statement;
                }else{
                    retorno = kleene_statement;
                }
                break;
            case RuleConstants.RULE_KLEENE_STATEMENT2:
                //<kleene_statement> ::=
                retorno = "void";
                break;
            case RuleConstants.RULE_STATEMENT:
                //<statement> ::= <block>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_STATEMENT2:
                //<statement> ::= <selection>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_STATEMENT3:
                //<statement> ::= <iteration>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_STATEMENT_SEMI:
                //<statement> ::= <return> ';'
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_STATEMENT_SEMI2:
                //<statement> ::= <assign> ';'
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_STATEMENT_SEMI3:
                //<statement> ::= <opt_expression> ';'
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_SELECTION_IF_LPARAN_RPARAN:
                //<selection> ::= if '(' <expression> ')' <block> <opt_else_block>

                expression = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());
                if(expression.equals("boolean")==false){
                    retorno = "error";
                    padre.imprimir("Tipos Incompatibles: "+ reduccion.getToken(2).getLineNumber() + "\n\t"+"en: "+expression+"\n\t"+"Debe de ser tipo: "+"boolean",1);
                }
                block = this.obtenerTipo((Reduction)reduccion.getToken(4).getData());
                opt_else_block = this.obtenerTipo((Reduction)reduccion.getToken(5).getData());
                if(block.equals("error") || opt_else_block.equals("error")){
                    retorno = "error";
                    break;
                }
                if(block.equals(opt_else_block)){
                    retorno = block;
                }else if(((Reduction)reduccion.getToken(5).getData()).getTokenCount() == 0){
                    retorno = "void";
                }else{
                    retorno = "void";
                }
                break;
            case RuleConstants.RULE_OPT_ELSE_BLOCK_ELSE:
                //<opt_else_block> ::= else <block>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(1).getData());
                break;
            case RuleConstants.RULE_OPT_ELSE_BLOCK:
                //<opt_else_block> ::=
                retorno = "void";
                break;
            case RuleConstants.RULE_ITERATION_WHILE_LPARAN_RPARAN:
                //<iteration> ::= while '(' <expression> ')' <block>

                expression = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());
                if(expression.equals("boolean")==false){
                    retorno = "error";
                    padre.imprimir("Tipos Incompatibles: "+ reduccion.getToken(2).getLineNumber() + "\n\t"+"en: "+expression+"\n\t"+"Debe de ser tipo: "+"boolean",1);
                }
                block = this.obtenerTipo((Reduction)reduccion.getToken(4).getData());
                if(block.equals("error")){
                    retorno = "error";
                }else{
                    retorno = "void";//void porque en un while no se garantiza nunca regresar algo
                }

                break;
            case RuleConstants.RULE_RETURN_RETURN:
                //<return> ::= return <opt_expression>

                opt_expression = this.obtenerTipo((Reduction)reduccion.getToken(1).getData());
                if(tipoRetorno.equals(opt_expression)){
                    retorno = tipoRetorno;
                }else{//El retorno debe de ser del mismo tipo de la declaración del método
                    retorno = "error";
                    padre.imprimir("Tipo Return no el mismo que Tipo Método: "+ reduccion.getToken(0).getLineNumber() + "\n\t"+"en: "+opt_expression+"\n\t"+"necesita: "+tipoRetorno+"",1);
                }

                break;
            case RuleConstants.RULE_ASSIGN_EQ:
                //<assign> ::= <location> '=' <expression>

                location = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                expression = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());

                if(location.equals("error") || expression.equals("error")){
                    retorno = "error";
                    break;
                }

                if(location.equals(expression)){
                    retorno = "void";
                }else{
                   // System.out.println("tipos incompatibles en assign");
                    padre.imprimir("Esta asignando un tipo a otro incorrecto: "+ reduccion.getToken(1).getLineNumber() + "\n\t"+"en: "+expression+"\n\t"+"necesita: "+location+"",1);
                    retorno = "error";
                }
                

                break;
            case RuleConstants.RULE_OPT_EXPRESSION:
                //<opt_expression> ::= <expression>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_OPT_EXPRESSION2:
                //<opt_expression> ::=
                retorno = "void";
                break;
            case RuleConstants.RULE_EXPRESSION:
                //<expression> ::= <Rel Exp> <conditionalop> <expression>

                Rel_Exp = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                expression = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());
                String str_conditionalop = (String)((Reduction)reduccion.getToken(1).getData()).getToken(0).getData();
                if(Rel_Exp.equals("error") ||expression.equals("error")){
                    retorno = "error";
                }
                else if(Rel_Exp.equals("boolean") && expression.equals("boolean")) {
                    retorno = "boolean";
                }else{
                    retorno = "error";
                    padre.imprimir("operator "+str_conditionalop+" cannot be applied"+ reduccion.getToken(1).getLineNumber() + "\n\t"+"to "+Rel_Exp+", "+expression+"",1);
                }

                break;
            case RuleConstants.RULE_EXPRESSION2:
                //<expression> ::= <Rel Exp>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_RELEXP:
                //<Rel Exp> ::= <Add Exp> <relop> <Rel Exp>
                //<relop> ::= '<=' | '<' | '>' | '>=' | '==' | '!='
                Reduction relop_op = (Reduction)reduccion.getToken(1).getData();
                String str_relop = (String)relop_op.getToken(0).getData();

                Add_Exp = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                Rel_Exp = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());

                if(Add_Exp.equals("error") || Rel_Exp.equals("error")){
                    retorno = "error";
                    break;
                }

                if(str_relop.equals("!=") || str_relop.equals("==")){
                    //ambos iguales (int | char | boolean) -> boolean else error
                    if(Add_Exp.equals(Rel_Exp) && (Add_Exp.equals("int") || Add_Exp.equals("char") || Add_Exp.equals("boolean"))){
                        retorno = "boolean";
                    }else{
                        padre.imprimir("El operador "+str_relop+" no es aplicable en "+ relop_op.getToken(0).getLineNumber() + "\n\t"+"a "+Add_Exp+", "+Rel_Exp+"",1);
                        retorno = "error";
                    }

                }else if(str_relop.equals("<") || str_relop.equals("<=") || str_relop.equals(">") || str_relop.equals(">=")){
                    //deben ser de tipo int
                    //si ambos son de tipo int, resultado es boolean else error
                    if(Add_Exp.equals("int") && Rel_Exp.equals("int") ){
                        retorno = "boolean";
                    }else{
                        padre.imprimir("El operador "+str_relop+" no es aplicable en "+ relop_op.getToken(0).getLineNumber() + "\n\t"+"a "+Add_Exp+", "+Rel_Exp+"",1);
                        retorno = "error";
                    }
                }

                break;
            case RuleConstants.RULE_RELEXP2:
                //<Rel Exp> ::= <Add Exp>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_ADDEXP:
                //<Add Exp> ::= <Mult Exp> <addop> <Add Exp>

                Mult_Exp = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                Add_Exp = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());
                if(Add_Exp.equals("error") || Mult_Exp.equals("error")){
                    retorno = "error";
                    break;
                }

                Reduction add_op = (Reduction)reduccion.getToken(1).getData();
                String str_addop = (String)add_op.getToken(0).getData();

                if(Mult_Exp.equals("int") && Add_Exp.equals("int") ){
                    retorno = "int";
                }else{
                    padre.imprimir("El operador "+str_addop+" no es aplicable en "+ add_op.getToken(0).getLineNumber() + "\n\t"+"a "+Mult_Exp+", "+Add_Exp+"",1);
                    retorno = "error";
                }

                break;
            case RuleConstants.RULE_ADDEXP2:
                //<Add Exp> ::= <Mult Exp>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_MULTEXP:
                //<Mult Exp> ::= <Negate Exp> <mulop> <Mult Exp>

                Negate_Exp = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                Mult_Exp = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());

                if(Negate_Exp.equals("error") || Mult_Exp.equals("error")){
                    retorno = "error";
                    break;
                }

                Reduction mul_op = (Reduction)reduccion.getToken(1).getData();
                String str_mulop = (String)mul_op.getToken(0).getData();

                if(Negate_Exp.equals("int") && Mult_Exp.equals("int")){
                    retorno = "int";
                }else{
                    padre.imprimir("El operador  "+str_mulop+" no es aplicable en "+ mul_op.getToken(0).getLineNumber() + "\n\t"+"a "+Negate_Exp+", "+Mult_Exp+"",1);
                    retorno = "error";
                }

                break;
            case RuleConstants.RULE_MULTEXP2:
                //<Mult Exp> ::= <Negate Exp>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_NEGATEEXP_MINUS:
                //<Negate Exp> ::= '-' <Value>

                Value = this.obtenerTipo((Reduction)reduccion.getToken(1).getData());
                if(Value.equals("error")){
                    retorno = "error";
                    break;
                }
                if(Value.equals("int")){
                    retorno = "int";
                }else{
                    padre.imprimir("operator "+"-"+" cannot be applied"+ reduccion.getToken(0).getLineNumber() + "\n\t"+"to "+Value+"",1);
                    retorno = "error";
                }

                break;
            case RuleConstants.RULE_NEGATEEXP_EXCLAM:
                //<Negate Exp> ::= '!' <Value>

                Value = this.obtenerTipo((Reduction)reduccion.getToken(1).getData());
                if(Value.equals("error")){
                    retorno = "error";
                    break;
                }
                if(Value.equals("boolean")){
                    retorno = "boolean";
                }else{
                    padre.imprimir("operator "+"!"+" cannot be applied"+ reduccion.getToken(0).getLineNumber() + "\n\t"+"to "+Value+"",1);
                    retorno = "error";
                }

                break;
            case RuleConstants.RULE_NEGATEEXP:
                //<Negate Exp> ::= <Value>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_VALUE:
                //<Value> ::= <literal>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_VALUE_LPARAN_RPARAN:
                //<Value> ::= '(' <expression> ')'
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(1).getData());
                break;
            case RuleConstants.RULE_VALUE2:
                //<Value> ::= <methodCall>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_VALUE3:
                //<Value> ::= <location>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_LOCATION_DOT:
                //<location> ::= <simpleLocation> '.' <location>

                //obtener a simpleLocation
                simpleLocation = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                
                if(simpleLocation.equals("error")){
                    retorno = "error";
                    break;
                }

                String id_location_1 = (String)((Reduction)reduccion.getToken(0).getData()).getToken(0).getData();
                if(!simpleLocation.startsWith("struct:")){
                    retorno = "error";
                    padre.imprimir("No es aplicable locacion '.' locacion: "+ reduccion.getToken(1).getLineNumber() + "\n\t"+""+id_location_1+" porque no es una estructura "+"\n\ten: "+simpleLocation,1);
                    break;
                }
                
                if(simpleLocation.endsWith("[]")){
                    retorno = "error";
                    padre.imprimir("No es aplicable locacion '.' locacion: "+ reduccion.getToken(1).getLineNumber() + "\n\t"+""+id_location_1+" porque no es una estructura "+"\n\ten: "+simpleLocation,1);
                    break;
                }

                //buscarlo en la tabla de símbolos
                simbolo = this.tablaSimbolos.darSimbolo(id_location_1, ambitoActual);
                retorno = this.darTipoLocacion((Reduction)reduccion.getToken(2).getData(), simbolo.darTipo().darListaVars());
                break;
            case RuleConstants.RULE_LOCATION:
                //<location> ::= <simpleLocation>
                retorno = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                break;
            case RuleConstants.RULE_SIMPLELOCATION_ID_LBRACKET_RBRACKET:
                //<simpleLocation> ::= id '[' <expression> ']'

                //primero revisar que <expression> sea de tipo int
                expression = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());

                if(expression.equals("error")){
                    retorno = "error";
                    break;
                }else if(!expression.equals("int")){
                    retorno = "error";
                    padre.imprimir("Tipos incompatibles en el indice: "+ reduccion.getToken(1).getLineNumber() + "\n\t"+"en: "+expression+"\n\t"+"necesita: "+"int",1);
                    //System.out.println("tipo incompatible.... [!int]");

                }

                String id_simpleLocation_2 = (String)reduccion.getToken(0).getData();
                simbolo = this.tablaSimbolos.darSimbolo(id_simpleLocation_2, ambitoActual);
                if(simbolo == null){
                    //no encontró en la tabla de símbolos
                    padre.imprimir("No se encuentra "+ reduccion.getToken(0).getLineNumber()+ "\n\t"+""+id_simpleLocation_2,1);
                    retorno = "error";
                }else{
                    if(simbolo.darTipo().esArreglo()){
                        retorno = simbolo.darTipo().darNombreTipo().substring(0, simbolo.darTipo().darNombreTipo().length()-2);
                    }else{
                        padre.imprimir("No aplicable "+ reduccion.getToken(0).getLineNumber() + "\n\t"+""+id_simpleLocation_2+" no es un Arreglo"+"\n\t"+" se encontró: "+simbolo.darTipo().darNombreTipo()+"",1);
                        retorno = "error";
                    }
                }

                break;
            case RuleConstants.RULE_SIMPLELOCATION_ID:
                //<simpleLocation> ::= id
                String id_simpleLocation_1 = (String)reduccion.getToken(0).getData();
                simbolo = this.tablaSimbolos.darSimbolo(id_simpleLocation_1, ambitoActual);
                if(simbolo == null){
                    //no encontró en la tabla de símbolos
                    padre.imprimir("No se encuentra "+ reduccion.getToken(0).getLineNumber() + "\n\t"+""+id_simpleLocation_1,1);
                    retorno = "error";
                }else{
                    retorno = simbolo.darTipo().darNombreTipo();
                }

                break;
            case RuleConstants.RULE_METHODCALL_ID_LPARAN_RPARAN:
                //<methodCall> ::= id '(' <opt_arg_separated> ')'
                //obtener la firma de esta llamada a método y buscarla en la tabla de métodos
                String signature = darFirmaMetodo(reduccion);
                //por si los argumentos ya tienen un error...
                if(signature.contains("error") && !signature.startsWith("error")){
                    retorno = "error";
                    break;
                }

                Metodo correct_method = this.tablaMetodos.darMetodo(signature);
                if(correct_method!=null && !signature.isEmpty()){
                    retorno = correct_method.darRet().darNombreTipo();
                }else{
                    padre.imprimir("No se encuentra " + reduccion.getToken(1).getLineNumber() + "\n\t"+signature,1);
                    retorno = "error";
                }

                break;
            case RuleConstants.RULE_LITERAL:
                //<literal> ::= <int_literal>
                //<int_literal> ::= num
                retorno = "int";
                break;
            case RuleConstants.RULE_LITERAL2:
                //<literal> ::= <char_literal>
                //<char_literal> ::= character
                retorno = "char";
                break;
            case RuleConstants.RULE_LITERAL3:
                //<literal> ::= <bool_literal>
                //<bool_literal> ::= true | false
                retorno = "boolean";
                break;
        }
        return retorno;
    }

    private int darUltimaLinea(Reduction reduction){
        int line = 0;
        //<selection> ::= if '(' <expression> ')' <block> <opt_else_block>
        Reduction block, opt_else_block;
        switch(reduction.getParentRule().getTableIndex())
        {
            case RuleConstants.RULE_SELECTION_IF_LPARAN_RPARAN:
                //<selection> ::= if '(' <expression> ')' <block> <opt_else_block>
                opt_else_block = (Reduction)reduction.getToken(5).getData();
                block = (Reduction)reduction.getToken(4).getData();

                if(opt_else_block.getTokenCount()==0){
                    line = darUltimaLinea(block);
                }else{
                    line = darUltimaLinea(opt_else_block);
                }
                break;
            case RuleConstants.RULE_OPT_ELSE_BLOCK_ELSE:
                //<opt_else_block> ::= else <block>
                line = darUltimaLinea((Reduction)reduction.getToken(1).getData());
                break;
            case RuleConstants.RULE_BLOCK_LBRACE_RBRACE:
                //<block> ::= '{' <kleene_varDeclaration> <kleene_statement> '}'
                line = reduction.getToken(3).getLineNumber();
                break;

        }
        return line;
    }


    private String darTipoLocacion(Reduction reduccion, LinkedList<Simbolo> listaVars){
        String retorno = "";

        switch(reduccion.getParentRule().getTableIndex())
        {
            case RuleConstants.RULE_LOCATION_DOT:
                //<location> ::= <simpleLocation> '.' <location>
                String simpleLocation = this.darTipoLocacion((Reduction)reduccion.getToken(0).getData(), listaVars);
                if(simpleLocation.equals("error")){
                    retorno = "error";
                    break;
                }

                String id_location_1 = (String)((Reduction)reduccion.getToken(0).getData()).getToken(0).getData();

                //revisar si es un struct (empieza con "struct:")
                if(!simpleLocation.startsWith("struct:")){
                    retorno = "error";
                    padre.imprimir("No aplica locacion '.' locacion" + reduccion.getToken(1).getLineNumber()  + "\n\t"+""+id_location_1+" no es estructura"+"\n\ten: "+simpleLocation,1);
                    break;
                }

                if(simpleLocation.endsWith("[]")){
                    retorno = "error";
                    padre.imprimir("No aplica locacion '.' locacion" + reduccion.getToken(1).getLineNumber() + "\n\t"+""+id_location_1+" no es estructura"+"\n\ten: "+simpleLocation,1);
                    break;
                }

                //buscarlo entre los miembros
                Simbolo symbol_0 = this.darLocacionEnListaVar(id_location_1, listaVars);
                retorno = this.darTipoLocacion((Reduction)reduccion.getToken(2).getData(), symbol_0.darTipo().darListaVars());
                break;
            case RuleConstants.RULE_LOCATION:
                //---------------------------------------------------------------------------------------------------------
                //<location> ::= <simpleLocation>
                retorno = this.darTipoLocacion((Reduction)reduccion.getToken(0).getData(), listaVars);
                break;
            case RuleConstants.RULE_SIMPLELOCATION_ID_LBRACKET_RBRACKET:
                //---------------------------------------------------------------------------------------------------------
                //<simpleLocation> ::= id '[' <expression> ']'

                String expression = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());
                if(expression.equals("error")){
                    retorno = "error";
                    break;
                }
                else if(!expression.equals("int")) {
                    retorno = "error";
                    padre.imprimir("Tipos Incompatibles "+ reduccion.getToken(1).getLineNumber() + "\n\t"+"en: "+expression+"\n\t"+"necesita: "+"int",1);
                    break;
                }
                String id_2 = (String)reduccion.getToken(0).getData();
                Boolean exist_2 = this.existeLocacionEnListaVar(id_2, listaVars);
                if(exist_2){
                    Simbolo symbol_2 = this.darLocacionEnListaVar(id_2, listaVars);
                    if(symbol_2.darTipo().esArreglo()){
                        //quitar el "[]" del final...
                        retorno = symbol_2.darTipo().darNombreTipo().substring(0, symbol_2.darTipo().darNombreTipo().length()-2);
                    }else{
                        retorno = "error";
                        padre.imprimir("No aplicable "+ reduccion.getToken(1).getLineNumber() + "\n\t"+""+id_2+" no es arreglo"+"\n\t"+", se encontró: "+symbol_2.darTipo().darNombreTipo()+"",1);
                    }
                }else{
                    retorno = "error";
                    padre.imprimir("No se encontró:"+ reduccion.getToken(0).getLineNumber() + "\n\t"+" --> "+id_2+" No está en la estructura",1);
                }

                break;
            case RuleConstants.RULE_SIMPLELOCATION_ID:
                //---------------------------------------------------------------------------------------------------------
                //<simpleLocation> ::= id
                String id = (String)reduccion.getToken(0).getData();
                Boolean exist = this.existeLocacionEnListaVar(id, listaVars);
                if(exist){
                    Simbolo symbol = this.darLocacionEnListaVar(id, listaVars);
                    retorno = symbol.darTipo().darNombreTipo();
                }else{
                    retorno = "error";
                    padre.imprimir("No se encontró" + reduccion.getToken(0).getLineNumber() + "\n\t"+" --> "+id+" No está en la estructura",1);
                }
                break;
        }


        return retorno;
    }

    private int generarCodigo(Reduction reduccion, int cont){

        int retorno = cont;
        
        switch(reduccion.getParentRule().getTableIndex())
        {
            case RuleConstants.RULE_PROGRAM_CLASS_PROGRAM_LBRACE_RBRACE:
                //<Program> ::= class Program '{' <kleene_declaration> '}'
                generarCodigo((Reduction)reduccion.getToken(3).getData(),cont);
                break;
            case RuleConstants.RULE_KLEENE_DECLARATION:
                //<kleene_declaration> ::= <declaration> <kleene_declaration>
                int cont2 = generarCodigo((Reduction)reduccion.getToken(0).getData(),cont);
                retorno = generarCodigo((Reduction)reduccion.getToken(1).getData(),cont2);
                break;
            case RuleConstants.RULE_DECLARATION3:
                //<declaration> ::= <methodDeclaration>
                //<methodDeclaration> ::= <Type> id '(' <opt_parameter> ')' <block>

                Metodo met = this.tablaMetodos.darListaMetodo().get(cont);
                Reduction methodDeclaration = (Reduction)reduccion.getToken(0).getData();
                Verificador ambito_metodo = this.ambitoActual.darHijo();

                padre.imprimir("Método: "+met.darFirmaMetodo()+" - Ambito: "+ambito_metodo.darNombre(),2);


                GenCodigo codigoMetodo = new GenCodigo(met, ambito_metodo, methodDeclaration,this);
                codigoMetodo.generar();
                this.codigoX.addAll(codigoMetodo.darCodigo());
                this.elementos.addAll(codigoMetodo.darElementos());

                retorno++;
                break;
        }
        return retorno;
    }

    public LinkedList<IGeneracionCodigo> darCodigo(){
        return this.codigoX;
    }

    public LinkedList<String> darCodigoString(){
        LinkedList<String> retorno = new LinkedList<String>();
        for(IGeneracionCodigo a : this.codigoX){
        	if (a instanceof Comentario | a.getClass() == Comentario.class)
        	{
        		if (comentarios == true)
        		{
        			
        			retorno.add(a.darCodigo()+"");
        			continue;
        		}
        		else
        		{
        			continue;
        		}
        			
        	}
            retorno.add(a.darCodigo()+"");
        }
        return retorno;
    }

    private void verificacionMain(){
        String signature = "main()";
        Metodo correct_method = this.tablaMetodos.darMetodo(signature);
        if(correct_method==null){
            //cannot find symbol method (Line 2): method(boolean,int)
            padre.imprimir("No se encontró el método MAIN",1);
        }
    }

    public static String darGramatica() {
        return gramatica;
    }
    
}
   
