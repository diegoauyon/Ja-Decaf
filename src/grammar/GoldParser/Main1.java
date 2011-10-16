package grammar.GoldParser;


import java.io.*;
import goldengine.java.*;

/*
 * Licensed Material - Property of Matthew Hawkins (hawkini@4email.net) 
 */
 
public class Main1 implements GPMessageConstants
{
 
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

   private static BufferedReader buffR;

    /***************************************************************
     * This class will run the engine, and needs a file called config.dat
     * in the current directory. This file should contain two lines,
     * The first should be the absolute path name to the .cgt file, the second
     * should be the source file you wish to parse.
     * @param args Array of arguments.
     ***************************************************************/
    public static void main(String[] args)
    {
       String textToParse = "", compiledGrammar = "";

       try
       {
           buffR = new BufferedReader(new FileReader(new File("./config.dat")));
           compiledGrammar = buffR.readLine();
           textToParse = buffR.readLine();

           buffR.close();
       }
       catch(FileNotFoundException fnfex)
       {
           System.out.println("Config File was not found.\n\n" +
                              "Please place it in the current directory.");
           System.exit(1);
       }
       catch(IOException ioex)
       {
          System.out.println("An error occured while reading config.dat.\n\n" +
                             "Please re-try ensuring the file can be read.");
          System.exit(1);
       }

       GOLDParser parser = new GOLDParser();

       try
       {
          parser.loadCompiledGrammar(compiledGrammar);
          parser.openFile(textToParse);
       }
       catch(ParserException parse)
       {
          System.out.println("**PARSER ERROR**\n" + parse.toString());
          System.exit(1);
       }

       boolean done = false;
       int response = -1;

       while(!done)
       {
          try
            {
                  response = parser.parse();
            }
            catch(ParserException parse)
            {
                System.out.println("**PARSER ERROR**\n" + parse.toString());
                System.exit(1);
            }

            switch(response)
            {
               case gpMsgTokenRead:
                   /* A token was read by the parser. The Token Object can be accessed
                      through the CurrentToken() property:  Parser.CurrentToken */
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
                            break;
                         case RuleConstants.RULE_DECLARACIONKLEENE:
                            //<Declaracion Kleene> ::= <Declaracion> <Declaracion Kleene>
                            break;
                         case RuleConstants.RULE_DECLARACIONKLEENE2:
                            //<Declaracion Kleene> ::= 
                            break;
                         case RuleConstants.RULE_DECLARACION:
                            //<Declaracion> ::= <struct Declaracion>
                            break;
                         case RuleConstants.RULE_DECLARACION2:
                            //<Declaracion> ::= <variable Declaracion>
                            break;
                         case RuleConstants.RULE_DECLARACION3:
                            //<Declaracion> ::= <metodo Declaracion>
                            break;
                         case RuleConstants.RULE_VARIABLEDECLARACION_ID_SEMI:
                            //<variable Declaracion> ::= <Tipo> id <var Array Decision> ';'
                            break;
                         case RuleConstants.RULE_VARARRAYDECISION_LBRACKET_NUM_RBRACKET:
                            //<var Array Decision> ::= '[' num ']'
                            break;
                         case RuleConstants.RULE_VARARRAYDECISION:
                            //<var Array Decision> ::= 
                            break;
                         case RuleConstants.RULE_STRUCTDECLARACION_STRUCT_ID_LBRACE_RBRACE:
                            //<struct Declaracion> ::= struct id '{' <Kleene variable Declaracion> '}'
                            break;
                         case RuleConstants.RULE_KLEENEVARIABLEDECLARACION:
                            //<Kleene variable Declaracion> ::= <variable Declaracion> <Kleene variable Declaracion>
                            break;
                         case RuleConstants.RULE_KLEENEVARIABLEDECLARACION2:
                            //<Kleene variable Declaracion> ::= 
                            break;
                         case RuleConstants.RULE_METODODECLARACION_ID_LPARAN_RPARAN:
                            //<metodo Declaracion> ::= <Tipo> id '(' <Des parametro> ')' <bloque>
                            break;
                         case RuleConstants.RULE_DESPARAMETRO:
                            //<Des parametro> ::= <multiple parametros>
                            break;
                         case RuleConstants.RULE_DESPARAMETRO2:
                            //<Des parametro> ::= 
                            break;
                         case RuleConstants.RULE_MULTIPLEPARAMETROS:
                            //<multiple parametros> ::= <parametro>
                            break;
                         case RuleConstants.RULE_MULTIPLEPARAMETROS_COMMA:
                            //<multiple parametros> ::= <multiple parametros> ',' <parametro>
                            break;
                         case RuleConstants.RULE_TIPO_INT:
                            //<Tipo> ::= int
                            break;
                         case RuleConstants.RULE_TIPO_CHAR:
                            //<Tipo> ::= char
                            break;
                         case RuleConstants.RULE_TIPO_BOOLEAN:
                            //<Tipo> ::= boolean
                            break;
                         case RuleConstants.RULE_TIPO_VOID:
                            //<Tipo> ::= void
                            break;
                         case RuleConstants.RULE_TIPO_STRUCT_ID:
                            //<Tipo> ::= struct id
                            break;
                         case RuleConstants.RULE_TIPO:
                            //<Tipo> ::= <struct Declaracion>
                            break;
                         case RuleConstants.RULE_PARAMETRO_ID:
                            //<parametro> ::= <Tipo> id
                            break;
                         case RuleConstants.RULE_PARAMETRO_ID_LBRACKET_RBRACKET:
                            //<parametro> ::= <Tipo> id '[' ']'
                            break;
                         case RuleConstants.RULE_BLOQUE_LBRACE_RBRACE:
                            //<bloque> ::= '{' <Kleene variable Declaracion> <Kleene statement> '}'
                            break;
                         case RuleConstants.RULE_KLEENESTATEMENT:
                            //<Kleene statement> ::= <statement> <Kleene statement>
                            break;
                         case RuleConstants.RULE_KLEENESTATEMENT2:
                            //<Kleene statement> ::= 
                            break;
                         case RuleConstants.RULE_STATEMENT:
                            //<statement> ::= <bloque>
                            break;
                         case RuleConstants.RULE_STATEMENT2:
                            //<statement> ::= <seleccion>
                            break;
                         case RuleConstants.RULE_STATEMENT3:
                            //<statement> ::= <iteracion>
                            break;
                         case RuleConstants.RULE_STATEMENT_SEMI:
                            //<statement> ::= <retorno> ';'
                            break;
                         case RuleConstants.RULE_STATEMENT_SEMI2:
                            //<statement> ::= <asignacion> ';'
                            break;
                         case RuleConstants.RULE_STATEMENT_SEMI3:
                            //<statement> ::= <Des expresion> ';'
                            break;
                         case RuleConstants.RULE_SELECCION_IF_LPARAN_RPARAN:
                            //<seleccion> ::= if '(' <expresion> ')' <bloque> <Des bloque>
                            break;
                         case RuleConstants.RULE_DESBLOQUE_ELSE:
                            //<Des bloque> ::= else <bloque>
                            break;
                         case RuleConstants.RULE_DESBLOQUE:
                            //<Des bloque> ::= 
                            break;
                         case RuleConstants.RULE_ITERACION_WHILE_LPARAN_RPARAN:
                            //<iteracion> ::= while '(' <expresion> ')' <bloque>
                            break;
                         case RuleConstants.RULE_RETORNO_RETURN:
                            //<retorno> ::= return <Des expresion>
                            break;
                         case RuleConstants.RULE_ASIGNACION_EQ:
                            //<asignacion> ::= <locacion> '=' <expresion>
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
                            break;
                         case RuleConstants.RULE_NEGACIONEXP_EXCLAM:
                            //<Negacion Exp> ::= '!' <Valor>
                            break;
                         case RuleConstants.RULE_NEGACIONEXP:
                            //<Negacion Exp> ::= <Valor>
                            break;
                         case RuleConstants.RULE_CONDICIONALDES_AMPAMP:
                            //<Condicional Des> ::= '&&'
                            break;
                         case RuleConstants.RULE_CONDICIONALDES_PIPEPIPE:
                            //<Condicional Des> ::= '||'
                            break;
                         case RuleConstants.RULE_RELACIONDES_LTEQ:
                            //<Relacion Des> ::= '<='
                            break;
                         case RuleConstants.RULE_RELACIONDES_LT:
                            //<Relacion Des> ::= '<'
                            break;
                         case RuleConstants.RULE_RELACIONDES_GT:
                            //<Relacion Des> ::= '>'
                            break;
                         case RuleConstants.RULE_RELACIONDES_GTEQ:
                            //<Relacion Des> ::= '>='
                            break;
                         case RuleConstants.RULE_RELACIONDES_EQEQ:
                            //<Relacion Des> ::= '=='
                            break;
                         case RuleConstants.RULE_RELACIONDES_EXCLAMEQ:
                            //<Relacion Des> ::= '!='
                            break;
                         case RuleConstants.RULE_SUMADES_PLUS:
                            //<suma Des> ::= '+'
                            break;
                         case RuleConstants.RULE_SUMADES_MINUS:
                            //<suma Des> ::= '-'
                            break;
                         case RuleConstants.RULE_MULTIDES_TIMES:
                            //<multi Des> ::= '*'
                            break;
                         case RuleConstants.RULE_MULTIDES_DIV:
                            //<multi Des> ::= '/'
                            break;
                         case RuleConstants.RULE_MULTIDES_PERCENT:
                            //<multi Des> ::= '%'
                            break;
                         case RuleConstants.RULE_VALOR:
                            //<Valor> ::= <literal>
                            break;
                         case RuleConstants.RULE_VALOR_LPARAN_RPARAN:
                            //<Valor> ::= '(' <expresion> ')'
                            break;
                         case RuleConstants.RULE_VALOR2:
                            //<Valor> ::= <llamada metodo>
                            break;
                         case RuleConstants.RULE_VALOR3:
                            //<Valor> ::= <locacion>
                            break;
                         case RuleConstants.RULE_LOCACION_DOT:
                            //<locacion> ::= <locacion Array Des> '.' <locacion>
                            break;
                         case RuleConstants.RULE_LOCACION:
                            //<locacion> ::= <locacion Array Des>
                            break;
                         case RuleConstants.RULE_LOCACIONARRAYDES_ID_LBRACKET_RBRACKET:
                            //<locacion Array Des> ::= id '[' <expresion> ']'
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
                            break;
                         case RuleConstants.RULE_LITERAL2:
                            //<literal> ::= <char_literal>
                            break;
                         case RuleConstants.RULE_LITERAL3:
                            //<literal> ::= <bool_literal>
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
                    System.out.println("gpMsgReduction");
                    Reduction myRed = parser.currentReduction();
                    System.out.println(myRed.getParentRule().getText());
                    // ************************************** end log

                    break;

                case gpMsgAccept:
                    /* The program was accepted by the parsing engine */

                    // ************************************** log file
                    System.out.println("gpMsgAccept");
                    // ************************************** end log

                    done = true;

                    break;

                case gpMsgLexicalError:
                    /* Place code here to handle a illegal or unrecognized token
                           To recover, pop the token from the stack: Parser.PopInputToken */

                    // ************************************** log file
                    System.out.println("gpMsgLexicalError");
                    // ************************************** end log

                    parser.popInputToken();

                    break;

                case gpMsgNotLoadedError:
                    /* Load the Compiled Grammar Table file first. */

                    // ************************************** log file
                    System.out.println("gpMsgNotLoadedError");
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
                    System.out.println("Token not expected: " + (String)theTok.getData());

                    // ************************************** log file
                    System.out.println("gpMsgSyntaxError");
                    // ************************************** end log

                    break;

                case gpMsgCommentError:
                    /* The end of the input was reached while reading a comment.
                             This is caused by a comment that was not terminated */

                    // ************************************** log file
                    System.out.println("gpMsgCommentError");
                    // ************************************** end log

                    done = true;

                              break;

                case gpMsgInternalError:
                    /* Something horrid happened inside the parser. You cannot recover */

                    // ************************************** log file
                    System.out.println("gpMsgInternalError");
                    // ************************************** end log

                    done = true;

                    break;
            }
        }
        try
        {
              parser.closeFile();
        }
        catch(ParserException parse)
        {
            System.out.println("**PARSER ERROR**\n" + parse.toString());
            System.exit(1);
        }
    }
}
