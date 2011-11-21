package main;

import tabla.*;
import tabla.clases.Asignacion;
import tabla.clases.Comentario;
import tabla.clases.Condicional;
import tabla.clases.Etiqueta;
import tabla.clases.IGeneracionCodigo;
import tabla.clases.Jmp;
import tabla.clases.Llamada;
import tabla.clases.OperacionBinaria;
import tabla.clases.OperacionUnaria;
import tabla.clases.Parametro;
import tabla.clases.Plano;
import tabla.clases.Retorno;
import goldengine.java.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

import org.jdom.Comment;
import org.jdom.Element;


public class GenCodigo {

    
    private Metodo metodo;
    private Verificador verificador;
    private Verificador ambitoActual;
    private Reduction red;
    private LinkedList<IGeneracionCodigo> codigo = new LinkedList<IGeneracionCodigo>();
    private LinkedList<String> temporales = new LinkedList<String>();
  //  private LinkedList<String> locacionTemporales = new LinkedList<String>();
    private Main parser;
    private boolean primerBloque = false;
    private int inicio_dir_temp = 0;
    private int numEtiqueta = 0;
    private int numTemp = 1;
    
    LinkedHashMap<String,String> hashTemp = new LinkedHashMap<String,String>();
    private LinkedList<org.jdom.Content> elementos = new LinkedList<org.jdom.Content>();
    Element op,elePlano,cond,salto,retorno,asignacion,llamada,param,instrucciones;

    public GenCodigo(Metodo met, Verificador verificador, Reduction red, Main parser){

        this.metodo = met;
        this.verificador = verificador;
        this.ambitoActual = verificador;
        this.ambitoActual.resetearAHijo();
        this.red = red;
        this.parser = parser;
        
    }

    public LinkedList<IGeneracionCodigo> darCodigo(){
        return this.codigo;
    }
    
    public LinkedList<org.jdom.Content> darElementos()
    {
    
    	return this.elementos;
    }

    public String nuevaEtiqueta(){
        //E_main|0_1
        return "E_"+this.metodo.darSubNombre()+"_"+(numEtiqueta++);
    }

    public String darFinalEtiqueta(){
        //E__main|0__$
        return "E_"+this.metodo.darSubNombre()+"_$";
    }


    public String getTemporal(){
        if(temporales.isEmpty()){
            return "t|_"+(this.numTemp++);
        }
        return temporales.pop();
    }
    
    public static boolean esTemporal(String temp)
    {
    	return temp.startsWith("t|_");
    }

    public void sacarTemp(String temp){
        if(temp.startsWith("t|_")){
            this.temporales.push(temp);
            
            Collections.sort(temporales,new Comparator<String>(){

                public int compare(String o1, String o2) {

                    int a1 = Integer.parseInt(o1.substring(3));
                    int a2 = Integer.parseInt(o2.substring(3));
                    return a1-a2;
                }

            });
        }
    }
    
    public String darTipoJ(Tipo tipo)
    {
    	System.out.println("Tipo: " + tipo.darNombreTipo());
    	if (tipo.darNombreSimple().equalsIgnoreCase("int"))
    		return "I"; 
    	if (tipo.darNombreSimple().equalsIgnoreCase("void"))
    		return "V";
    	if (tipo.darNombreSimple().equalsIgnoreCase("boolean"))
    		return "Z";
    	if (tipo.darNombreSimple().equalsIgnoreCase("char"))
    		return "C";
    	
    	return tipo.darNombreSimple();
    }


   public void generar(){
        //<metodoDeclaration> ::= <Type> id '(' <opt_parameter> ')' <block>
	    
        String nombre = this.metodo.darSubNombre();
        elePlano = new Element("method");
        elePlano.setAttribute("name", nombre.replace('|', '_'));
       // Element plano = new Element("Plano");
        
        Element propiedades = new Element("properties");
        instrucciones = new Element("instructions");
        
        Element retorno = new Element("return");
        retorno.setAttribute("type",darTipoJ(this.metodo.darRet()));
        retorno.setAttribute("array",""+this.metodo.darRet().esArreglo());
        retorno.setAttribute("estructure",""+this.metodo.darRet().esEstructura());
        propiedades.addContent(retorno);

        this.agregarACodigo(new Comentario("*******************************************"));
        this.agregarACodigo(new Comentario("Método: "+nombre));
        this.agregarACodigo(new Comentario("Tipo de retorno: "+this.metodo.darRet().darNombreTipo()));
        
        this.elementos.add(new Comment("Método:"+nombre));
        this.elementos.add(new Comment("Tipo de retorno: "+this.metodo.darRet().darNombreTipo()));

        int suma=0;
        int numeroParametros=0;
        int numeroLocales = 0;
        Element parametros = new Element("parameters");
        Element locales = new Element("locals");
        for(tabla.Simbolo a : this.parser.darTablaSimbolos().darTodoLosSimbolosEnAmbitoYSubSiguiente(verificador)){
        	@SuppressWarnings("static-access")
			String [] b = this.darDesplazamiento(a,parser,this.verificador);
        	String param = "";
            if(a.darTipo().esParametro()){
                param = "param: ";
                numeroParametros++;
                Element parame = new Element("parameter");
                parame.setAttribute("nombre",a.darId());
                parame.setAttribute("type",darTipoJ(a.darTipo()));
                parame.setAttribute("array",""+a.darTipo().esArreglo());
                parame.setAttribute("estructure",""+a.darTipo().esEstructura());
                parame.setAttribute("annotation",""+b[0]+"["+b[1]+"]");
                parametros.addContent(parame);
            }
            else
            {
            	numeroLocales++;
        		Element locale = new Element("local");
        		locale.setAttribute("nombre",a.darId());
        		locale.setAttribute("type",darTipoJ(a.darTipo()));
        		locale.setAttribute("array",""+a.darTipo().esArreglo());
        		locale.setAttribute("estructure",""+a.darTipo().esEstructura());
        		locale.setAttribute("annotation",""+b[0]+"["+b[1]+"]");
        		locale.setAttribute("size",""+a.darTipo().darTamanioTipo());
                locales.addContent(locale);
            }
            
            this.agregarACodigo(new Comentario(param+a.darId()+" = "+b[0]+"["+b[1]+"]"));
            this.elementos.add(new Comment(param+a.darId()+" = "+b[0]+"["+b[1]+"]"));

            suma += a.darTipo().darTamanioTipo();
        }
        
        parametros.setAttribute("num",""+numeroParametros);
        locales.setAttribute("num",""+numeroLocales);
        propiedades.addContent(parametros);
        propiedades.addContent(locales);
        elePlano.addContent(propiedades);
        this.inicio_dir_temp = suma;
        
        this.agregarACodigo(new Comentario("*******************************************"));
        this.agregarACodigo(new Plano(nombre+" proc near"));
        
        
        

        primerBloque = true;
        this.generarStatement((Reduction)red.getToken(5).getData());


        this.agregarACodigo(new Etiqueta(this.darFinalEtiqueta()));
        elePlano.addContent(new Element("Etiqueta").setAttribute("nombre",this.darFinalEtiqueta()));
        this.agregarACodigo(new Plano("\tret"));
        Element retornon = new Element("ret");
        elePlano.addContent(retornon);
       // plano.addContent(new Element("ret"));
       // plano.addContent(new Element(nombre.replace('|', '_')+ "endp"));
       // elePlano.addContent(plano);
        this.agregarACodigo(new Plano(nombre+" endp"));
        
        this.agregarACodigo(new Comentario("*******************************************\n\n"));



        //agregar las direcciones de las temporales
        int cont = this.parser.darTablaSimbolos().darTodoLosSimbolosEnAmbitoYSubSiguiente(verificador).size();
        int tamnioIndice = Tipo.darTamanioBasico("int");

        for(int i = 1; i < this.numTemp; i++){
            this.codigo.add(cont+i+2,new Comentario(("t|_"+i)+" = "+"stack["+(i*tamnioIndice-tamnioIndice+this.inicio_dir_temp)+"]"));
            hashTemp.put(("t$_"+i), "stack["+(i*tamnioIndice-tamnioIndice+this.inicio_dir_temp)+"]");
            elementos.add(new Comment(("t|_"+i)+" = "+"stack["+(i*tamnioIndice-tamnioIndice+this.inicio_dir_temp)+"]"));
        }
        
        int param_len=0, dato_loc_len=0;

        for(Simbolo a : this.parser.darTablaSimbolos().darTodoLosSimbolosEnAmbitoYSubSiguiente(verificador)){
            if(a.darTipo().esParametro()){
                param_len+=a.darTipo().darTamanioTipo();
            }else{
                dato_loc_len+=a.darTipo().darTamanioTipo();
            }
        }
        
        this.metodo.tam_parametros = param_len;
        this.metodo.tam_datos_locales = dato_loc_len;
        this.metodo.tam_temporales = tamnioIndice * hashTemp.size();
        this.metodo.tam_dir_ret = 2;//TODO ver tamaño de la dirección de retorno
        this.metodo.tam_val_ret = this.metodo.darRet().darTamanioTipo();
        elementos.add(new Comment("Tamaño Parametros: "+ this.metodo.tam_parametros + "  Locales: "+this.metodo.tam_datos_locales+
        		"  Temporales:  "+ this.metodo.tam_temporales));

        //plano.addContent(elePlano);
        //elePlano.addContent(instrucciones);
        elementos.add(elePlano);
        elementos.add(new Comment("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"));
    }


    public void agregarACodigo(IGeneracionCodigo cod){
        this.codigo.add(cod);
    }

    public void generarStatement(Reduction reduccion){

        Etiqueta L1;
        Etiqueta L2;
        String t1,t2;
        //boolean linea = true;

        switch(reduccion.getParentRule().getTableIndex())
        {
            case Main.RuleConstants.RULE_BLOCK_LBRACE_RBRACE:
                //<block> ::= '{' <kleene_varDeclaration> <kleene_statement> '}'

                boolean regresar = false;
                if(!primerBloque){
                    this.ambitoActual = this.ambitoActual.darHijo();
                    regresar = true;
                }else{
                    primerBloque = false;
                }
                
                this.generarStatement((Reduction)reduccion.getToken(2).getData());

                if(regresar){
                    //System.out.println("sale del ámbito: "+this.actual_ambit.getName());
                    this.ambitoActual = this.ambitoActual.darVerificadorPrevio();
                    //System.out.println("regresa al ámbito: "+this.actual_ambit.getName());
                }

                break;
            case Main.RuleConstants.RULE_KLEENE_STATEMENT:
                //<kleene_statement> ::= <statement> <kleene_statement>
                this.generarStatement((Reduction)reduccion.getToken(0).getData());
                this.generarStatement((Reduction)reduccion.getToken(1).getData());
                break;
            case Main.RuleConstants.RULE_KLEENE_STATEMENT2:
                //<kleene_statement> ::=
                break;
            case Main.RuleConstants.RULE_STATEMENT:
                //<statement> ::= <block>
                this.generarStatement((Reduction)reduccion.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_STATEMENT2: case Main.RuleConstants.RULE_STATEMENT3: case Main.RuleConstants.RULE_STATEMENT_SEMI:
            case Main.RuleConstants.RULE_STATEMENT_SEMI2:  case Main.RuleConstants.RULE_STATEMENT_SEMI3:
                //<statement> ::= <selection>
                //<statement> ::= <iteration>
                //<statement> ::= <return> ';'
                //<statement> ::= <assign> ';'
                //<statement> ::= <opt_expression> ';'
                this.generarStatement((Reduction)reduccion.getToken(0).getData());
                break;

            case Main.RuleConstants.RULE_SELECTION_IF_LPARAN_RPARAN:
                //<selection> ::= if '(' <expression> ')' <block> <opt_else_block>
                //<opt_else_block> ::= else <block>
                //<opt_else_block> ::=

                Reduction opt_else_block = (Reduction)reduccion.getToken(5).getData();

                if(  opt_else_block.getTokenCount() == 0 ){

                    /*-------------------------------------------------------------------------
                    if ( <exp> ) <block1>
                    Código:
                            cod(<exp>) -> temp1
                            if temp1 == 0 goto L1
                            cod(<block1>)
                    L1:
                    -------------------------------------------------------------------------*/

                    this.agregarACodigo(new Comentario(" if(<exp>) <block1> ",false,'#',40));
                    elePlano.addContent(new Comment("if (exp) bloque1"));
                   // this.elementos.add(new Comment("if(expresion) bloque1"));

                    L1 = new Etiqueta(this.nuevaEtiqueta());

                    t1 = this.generarExpresion((Reduction)reduccion.getToken(2).getData());

                    this.agregarACodigo(new Condicional(t1,"==",0+"",L1.etiqueta));
                    cond = new Element("if");
                    cond.setAttribute("name", L1.etiqueta);
                    cond.setAttribute("dir1",t1);
                    cond.setAttribute("op","==");
                    cond.setAttribute("dir2","0");
                    elePlano.addContent(cond);

                    this.generarStatement((Reduction)reduccion.getToken(4).getData());

                    this.agregarACodigo(L1);
                    elePlano.addContent(new Element("Etiqueta").setAttribute("nombre",L1.etiqueta));

                    this.agregarACodigo(new Comentario("",false,'#',40));

                    this.sacarTemp(t1);

                    break;
                }

                /*-------------------------------------------------------------------------
                if ( <exp> ) <block1> else <block2>
                Código:
                        cod(<exp>) -> temp1
                        if temp1 == 0 goto L1
                        cod(<block1>)
                        goto L2
                L1:
                        cod(<block2>)
                L2:
                -------------------------------------------------------------------------*/

                this.agregarACodigo(new Comentario(" if(<exp>) <block1> else <block2> ",false,'#',40));
                elePlano.addContent(new Comment("if (exp) bloque1 else bloque2"));
                L1 = new Etiqueta(this.nuevaEtiqueta());
                L2 = new Etiqueta(this.nuevaEtiqueta());

                t1 = this.generarExpresion((Reduction)reduccion.getToken(2).getData());

                this.agregarACodigo(new Condicional(t1,"==",0+"",L1.etiqueta));
                cond = new Element("Condicional");
                cond.setAttribute("nombre", L1.etiqueta);
                cond.setAttribute("dir1",t1);
                cond.setAttribute("operador","==");
                cond.setAttribute("dir2","0");
                elePlano.addContent(cond);
                

                this.agregarACodigo(new Comentario(" <block1> ",false,'#',40));

                this.generarStatement((Reduction)reduccion.getToken(4).getData());

                this.agregarACodigo(new Jmp(L2.etiqueta));
                salto = new Element("Jmp");
                salto.setAttribute("nombre",L2.etiqueta);
                elePlano.addContent(salto);

                this.agregarACodigo(L1);
                elePlano.addContent(new Element("Etiqueta").setAttribute("nombre",L1.etiqueta));

                this.agregarACodigo(new Comentario(" <block2> - else ",false,'#',40));

                this.generarStatement((Reduction)opt_else_block.getToken(1).getData());

                this.agregarACodigo(L2);
                elePlano.addContent(new Element("Etiqueta").setAttribute("nombre",L2.etiqueta));

                this.agregarACodigo(new Comentario("",false,'#',40));

                this.sacarTemp(t1);

                break;
            case Main.RuleConstants.RULE_ITERATION_WHILE_LPARAN_RPARAN:
                //<iteration> ::= while '(' <expression> ')' <block>


                /*-------------------------------------------------------------------------
                while ( <exp> ) <block>

                Código:

                L1:
                        cod(<exp>) -> temp1
                        if temp1 == 0 goto L2
                        cod(<block1>)
                        goto L1:
                L2:
                -------------------------------------------------------------------------*/

                this.agregarACodigo(new Comentario(" while (<exp>) <block> ",false,'#',40));
                elePlano.addContent(new Comment("while (exp) bloque"));
                L1 = new Etiqueta(this.nuevaEtiqueta());
                L2 = new Etiqueta(this.nuevaEtiqueta());


                this.agregarACodigo(L1);
                elePlano.addContent(new Element("Etiqueta").setAttribute("nombre",L1.etiqueta));

                t1 = this.generarExpresion((Reduction)reduccion.getToken(2).getData());

                this.agregarACodigo(new Condicional(t1,"==",0+"",L2.etiqueta));
                cond = new Element("Condicional");
                cond.setAttribute("nombre", L2.etiqueta);
                cond.setAttribute("dir1",t1);
                cond.setAttribute("operador","==");
                cond.setAttribute("dir2","0");
                elePlano.addContent(cond);

                this.generarStatement((Reduction)reduccion.getToken(4).getData());

                this.agregarACodigo(new Jmp(L1.etiqueta));
                salto = new Element("Jmp");
                salto.setAttribute("nombre",L1.etiqueta);
                elePlano.addContent(salto);

                this.agregarACodigo(L2);
                elePlano.addContent(new Element("Etiqueta").setAttribute("nombre",L2.etiqueta));

                this.agregarACodigo(new Comentario("",false,'#',40));

                this.sacarTemp(t1);
                
                break;
            case Main.RuleConstants.RULE_RETURN_RETURN:
                //<return> ::= return <opt_expression>
                //<opt_expression> ::= <expression> |
                Reduction opt_expression = (Reduction)reduccion.getToken(1).getData();
                if(opt_expression.getTokenCount() == 0){

                    /*-------------------------------------------------------------------------
                    return  ;
                    Código:
                            goto L_metodo_end
                    -------------------------------------------------------------------------*/
                    this.agregarACodigo(new Jmp(this.darFinalEtiqueta()));
                    salto = new Element("Jmp");
                    salto.setAttribute("nombre",this.darFinalEtiqueta());
                    elePlano.addContent(salto);

                    break;
                }

                /*-------------------------------------------------------------------------
                return <exp> ;
                Código:
                        cod(<exp>)-> temp1
                        return temp1
                        goto L_metodo_end
                -------------------------------------------------------------------------*/

                t1 = this.generarExpresion((Reduction)opt_expression.getToken(0).getData());

                this.agregarACodigo(new Retorno(t1));
                retorno = new Element("Retorno");
                retorno.setAttribute("t1",t1);
                elePlano.addContent(retorno);

                this.agregarACodigo(new Jmp(this.darFinalEtiqueta()));
                salto = new Element("Jmp");
                salto.setAttribute("nombre",this.darFinalEtiqueta());
                elePlano.addContent(salto);

                this.sacarTemp(t1);
                
                break;
            case Main.RuleConstants.RULE_ASSIGN_EQ:
                //<assign> ::= <location> '=' <expression>

                /*-------------------------------------------------------------------------
                <location>  =  <exp>
                Código:
                        cod(<exp>) -> temp1
                        cod(<location>) -> temp2
                        temp2 = temp1
                -------------------------------------------------------------------------*/
                
                this.agregarACodigo(new Comentario(" <assign> ",false,'-',40));
                elePlano.addContent(new Comment("assign"));

                t1 = this.generarExpresion((Reduction)reduccion.getToken(2).getData());

                t2 = this.generarLocacion((Reduction)reduccion.getToken(0).getData());

                this.agregarACodigo(new Asignacion(t2,t1));
                asignacion = new Element("Asignacion");
                asignacion.setAttribute("dir2",t2);
                asignacion.setAttribute("dir1",t1);
                elePlano.addContent(asignacion);

                this.sacarTemp(t1);

                this.agregarACodigo(new Comentario("",false,'-',40));
                
                break;
            case Main.RuleConstants.RULE_OPT_EXPRESSION:
                //<opt_expression> ::= <expression>
                
                /*-------------------------------------------------------------------------
                <exp> ;
                Código:
                        cod(<exp>)
                -------------------------------------------------------------------------*/

                t1 = this.generarExpresion((Reduction)reduccion.getToken(0).getData());

                this.sacarTemp(t1);

                break;
        }

        

    }



    
    public String generarExpresion(Reduction reduccion){
        String retorno = "";

        String t1,t2,t3, operando;
        Etiqueta L1,L2;

        switch(reduccion.getParentRule().getTableIndex())
        {
            case Main.RuleConstants.RULE_EXPRESSION:
                //<expression> ::= <Rel Exp> <conditionalop> <expression>
                
                /*-------------------------------------------------------------------------
                <Rel Exp> <conditionalop> <exp1>
                Código:
                        //&& ||
                        cod(<rel exp>) -> t1
                        cod(<exp1>) -> t2
                        t3 = t1 AND|OR t2
                Resultado en: t3
                -------------------------------------------------------------------------*/

                operando = (String)((Reduction)reduccion.getToken(1).getData()).getToken(0).getData();

                if(operando.equals("&&")){

                    operando = "and";

                }else if(operando.equals("||")){

                    operando = "or";
                }

                this.agregarACodigo(new Comentario(" <boolean exp> ("+operando+") ",true,'=',30));
                elePlano.addContent(new Comment("boolean exp ("+operando+")"));

                t1 = this.generarExpresion((Reduction)reduccion.getToken(0).getData());

                t2 = this.generarExpresion((Reduction)reduccion.getToken(2).getData());

                this.sacarTemp(t1);
                this.sacarTemp(t2);

                t3 = this.getTemporal();

                this.agregarACodigo(new OperacionBinaria(t3,t1,operando,t2));
                op = new Element("OB");
                op.setAttribute("t3", t3);
                op.setAttribute("t1",t1);
                op.setAttribute("operando",operando);
                op.setAttribute("t2",t2);
                elePlano.addContent(op);
                
                retorno = t3;

                //this.liberarTemporal(t1);
                //this.liberarTemporal(t2);

                this.agregarACodigo(new Comentario("",true,'=',30));//===================================

                break;
            case Main.RuleConstants.RULE_EXPRESSION2:

                //<expression> ::= <Rel Exp>
                retorno = this.generarExpresion((Reduction)reduccion.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_RELEXP:
                //<Rel Exp> ::= <Add Exp> <relop> <Rel Exp>

                /*-------------------------------------------------------------------------
                <Add Exp> <relop> <Rel exp1>
                Código:
                        //<= < > >= == !=
                        cod(<Add Exp>) -> t1
                        cod(<Rel exp1>) -> t2
                        if t1 <relop> t2 goto L1
                        t3 =0
                        goto L2
                L1:
                        t3=1
                L2:
                Resultado en: t3
                -------------------------------------------------------------------------*/

                L1 = new Etiqueta(this.nuevaEtiqueta());
                L2 = new Etiqueta(this.nuevaEtiqueta());

                t1 = this.generarExpresion((Reduction)reduccion.getToken(0).getData());
                t2 = this.generarExpresion((Reduction)reduccion.getToken(2).getData());

                this.sacarTemp(t1);
                this.sacarTemp(t2);

                t3 = this.getTemporal();

                operando = (String)((Reduction)reduccion.getToken(1).getData()).getToken(0).getData();

                this.agregarACodigo(new Condicional(t1,operando,t2,L1.etiqueta));
                cond = new Element("Condicional");
                cond.setAttribute("nombre", L1.etiqueta);
                cond.setAttribute("dir1",t1);
                cond.setAttribute("operador",operando);
                cond.setAttribute("dir2",t2);
                elePlano.addContent(cond);

                this.agregarACodigo(new Asignacion(t3,"0"));
                asignacion = new Element("Asignacion");
                asignacion.setAttribute("dir1",t3);
                asignacion.setAttribute("dir2","0");
                elePlano.addContent(asignacion);

                this.agregarACodigo(new Jmp(L2.etiqueta));
                salto = new Element("Jmp");
                salto.setAttribute("nombre",L2.etiqueta);
                elePlano.addContent(salto);

                this.agregarACodigo(L1);
                elePlano.addContent(new Element("Etiqueta").setAttribute("nombre",L1.etiqueta));

                this.agregarACodigo(new Asignacion(t3,"1"));
                asignacion = new Element("Asignacion");
                asignacion.setAttribute("dir1",t3);
                asignacion.setAttribute("dir2","1");
                elePlano.addContent(asignacion);

                this.agregarACodigo(L2);
                elePlano.addContent(new Element("Etiqueta").setAttribute("nombre",L2.etiqueta));

                retorno = t3;

                //this.liberarTemporal(t1);
                //this.liberarTemporal(t2);


                break;
            case Main.RuleConstants.RULE_RELEXP2:
                //<Rel Exp> ::= <Add Exp>
                retorno = this.generarExpresion((Reduction)reduccion.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_ADDEXP:
                //<Add Exp> ::= <Mult Exp> <addop> <Add Exp>


                /*-------------------------------------------------------------------------
                <Mult Exp> <addop> <Add exp1>
                Código:
                        //+ -
                        cod(<Mult Exp>) -> t1
                        cod(<Add exp1>) -> t2
                        t3 = t1 <addop> t2

                Resultado en: t3
                -------------------------------------------------------------------------*/

                t1 = this.generarExpresion((Reduction)reduccion.getToken(0).getData());
                t2 = this.generarExpresion((Reduction)reduccion.getToken(2).getData());

                this.sacarTemp(t1);
                this.sacarTemp(t2);

                t3 = this.getTemporal();

                operando = (String)((Reduction)reduccion.getToken(1).getData()).getToken(0).getData();

                this.agregarACodigo(new OperacionBinaria(t3,t1,operando,t2));
                op = new Element("OB");
                op.setAttribute("t3", t3);
                op.setAttribute("t1",t1);
                op.setAttribute("operando",operando);
                op.setAttribute("t2",t2);
                elePlano.addContent(op);

                retorno = t3;

                //this.liberarTemporal(t1);
                //this.liberarTemporal(t2);


                break;
            case Main.RuleConstants.RULE_ADDEXP2:
                //<Add Exp> ::= <Mult Exp>
                retorno = this.generarExpresion((Reduction)reduccion.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_MULTEXP:
                //<Mult Exp> ::= <Negate Exp> <mulop> <Mult Exp>

                /*-------------------------------------------------------------------------
                <Negate Exp> <mulop> <Mult exp1>
                Código:
                        //* /
                        cod(<Negate Exp>) -> t1
                        cod(<Mult exp1>) -> t2
                        t3 = t1 <mulop> t2

                Resultado en: t3
                -------------------------------------------------------------------------*/

                t1 = this.generarExpresion((Reduction)reduccion.getToken(0).getData());
                t2 = this.generarExpresion((Reduction)reduccion.getToken(2).getData());

                this.sacarTemp(t1);
                this.sacarTemp(t2);

                t3 = this.getTemporal();

                operando = (String)((Reduction)reduccion.getToken(1).getData()).getToken(0).getData();

                this.agregarACodigo(new OperacionBinaria(t3,t1,operando,t2));
                op = new Element("OB");
                op.setAttribute("t3", t3);
                op.setAttribute("t1",t1);
                op.setAttribute("operando",operando);
                op.setAttribute("t2",t2);
                elePlano.addContent(op);

                retorno = t3;

                //this.liberarTemporal(t1);
                //this.liberarTemporal(t2);

                break;
            case Main.RuleConstants.RULE_MULTEXP2:
                //<Mult Exp> ::= <Negate Exp>
                retorno = this.generarExpresion((Reduction)reduccion.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_NEGATEEXP_MINUS:
                //<Negate Exp> ::= '-' <Value>


                /*-------------------------------------------------------------------------
                '-' <Value>
                Código:
                        //cambiar de signo
                        cod(<Value>) -> t1
                        t2 = 0 - t1

                Resultado en: t2
                -------------------------------------------------------------------------*/

                t1 = this.generarExpresion((Reduction)reduccion.getToken(1).getData());

                this.sacarTemp(t1);

                t2 = this.getTemporal();

                this.agregarACodigo(new OperacionBinaria(t2,"0","-",t1));
                op = new Element("OB");
                op.setAttribute("t1", t2);
                op.setAttribute("t2","0");
                op.setAttribute("operando","-");
                op.setAttribute("t3",t1);
                elePlano.addContent(op);

                retorno = t2;

                //this.liberarTemporal(t1);

                break;
            case Main.RuleConstants.RULE_NEGATEEXP_EXCLAM:
                //<Negate Exp> ::= '!' <Value>


                /*-------------------------------------------------------------------------
                '!' <Value>
                Código:
                        //cambiar de 0 a 1
                        cod(<Value>) -> t1
                        if t1 == 0 goto L1
                        t2 = 0
                        goto L2
                L1:
                        t2 = 1
                L2:
                -------------------------------------------------
                 * cod(<Value>) -> t1
                 * t2 = not t1
                 * t2 = 1 and t2
                Resultado en: t2
                -------------------------------------------------------------------------*/
                

                t1 = this.generarExpresion((Reduction)reduccion.getToken(1).getData());

                this.sacarTemp(t1);

                t2 = this.getTemporal();

                this.agregarACodigo(new OperacionUnaria(t2,"not",t1));
                op = new Element("OU");
                op.setAttribute("t1",t2);
                op.setAttribute("operando","not");
                op.setAttribute("t2",t1);
                elePlano.addContent(op);

                this.agregarACodigo(new OperacionBinaria(t2,"1","and",t2));
                op = new Element("OB");
                op.setAttribute("t3", t2);
                op.setAttribute("t1",t2);
                op.setAttribute("operando","and");
                op.setAttribute("t2","1");
                elePlano.addContent(op);

                
                retorno = t2;

                //this.liberarTemporal(t1);

                break;
            case Main.RuleConstants.RULE_NEGATEEXP:
                //<Negate Exp> ::= <Value>
                retorno = this.generarExpresion((Reduction)reduccion.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_VALUE:
                //<Value> ::= <literal>
                retorno = this.generarExpresion((Reduction)reduccion.getToken(0).getData());
                break;

            case Main.RuleConstants.RULE_VALUE_LPARAN_RPARAN:
                //<Value> ::= '(' <expression> ')'
                retorno = this.generarExpresion((Reduction)reduccion.getToken(1).getData());
                break;
            case Main.RuleConstants.RULE_VALUE2:
                //<Value> ::= <metodoCall>
                retorno = this.generarLlamada((Reduction)reduccion.getToken(0).getData(),"",0);
                break;
            case Main.RuleConstants.RULE_VALUE3:
                //<Value> ::= <location>
                
                
                t2 = this.generarLocacion((Reduction)reduccion.getToken(0).getData());

                //this.liberarTemporalLocation(t2);

                t1 = this.getTemporal();

                this.agregarACodigo(new Asignacion(t1,t2));
                asignacion = new Element("Asignacion");
                asignacion.setAttribute("dir1",t1);
                asignacion.setAttribute("dir2",t2);
                elePlano.addContent(asignacion);


                retorno = t1;



                break;
            case Main.RuleConstants.RULE_LITERAL: case Main.RuleConstants.RULE_LITERAL2:
            case Main.RuleConstants.RULE_LITERAL3:
                 //<literal> ::= <int_literal>
                //<literal> ::= <char_literal>
                //<literal> ::= <bool_literal>
                
                retorno = this.generarExpresion((Reduction)reduccion.getToken(0).getData());

                break;
            case Main.RuleConstants.RULE_INT_LITERAL_NUM: case Main.RuleConstants.RULE_CHAR_LITERAL_CHARACTER:
                 //<int_literal> ::= num
                //<char_literal> ::= character
                
                retorno =(String)reduccion.getToken(0).getData();

                break;
            case Main.RuleConstants.RULE_BOOL_LITERAL_TRUE:
                //<bool_literal> ::= true
                retorno = "1";
                break;
            case Main.RuleConstants.RULE_BOOL_LITERAL_FALSE:
                //<bool_literal> ::= false
                retorno = "0";
                break;
        }

        return retorno;
    }


    public String darDesplazarEstructura(Reduction reduction, LinkedList<tabla.Simbolo> listaVars){
        String retorno = "",id,t1,t2;
        int cont=0;
        tabla.Simbolo miembro = null;
        switch(reduction.getParentRule().getTableIndex()){

            case Main.RuleConstants.RULE_LOCATION_DOT:
                //<location> ::= <simpleLocation> '.' <location>

                Reduction simpleLoc = (Reduction)reduction.getToken(0).getData();
                Reduction location = (Reduction)reduction.getToken(2).getData();

                id = (String)simpleLoc.getToken(0).getData();
                for(tabla.Simbolo a : listaVars){

                    if(id.equals(a.darId())){
                        miembro = a;
                        break;
                    }
                    cont+=a.darTipo().darTamanioTipo();
                }

                t1 = this.darDesplazarEstructura(simpleLoc, listaVars);
                t2 = this.darDesplazarEstructura(location, miembro.darTipo().darListaVars());

                String t3 = this.getTemporal();

                this.agregarACodigo(new OperacionBinaria(t3,t1,"+",t2));
                op = new Element("OB");
                op.setAttribute("t3", t2);
                op.setAttribute("t1",t3);
                op.setAttribute("operando","+");
                op.setAttribute("t2",t1);
                elePlano.addContent(op);


                retorno = t3;
                
                this.sacarTemp(t1);
                this.sacarTemp(t2);

                break;
            case Main.RuleConstants.RULE_LOCATION:
                //<location> ::= <simpleLocation>
                retorno = this.darDesplazarEstructura((Reduction)reduction.getToken(0).getData(), listaVars);
                break;
            case Main.RuleConstants.RULE_SIMPLELOCATION_ID_LBRACKET_RBRACKET:
                //<simpleLocation> ::= id '[' <expression> ']'

                id = (String)reduction.getToken(0).getData();
                for(tabla.Simbolo a : listaVars){

                    if(id.equals(a.darId())){
                        miembro = a;
                        break;
                    }
                    cont+=a.darTipo().darTamanioTipo();
                }


                t1 = this.generarExpresion((Reduction)reduction.getToken(2).getData());

                int tam_ind = miembro.darTipo().darTamanioTipoSimple();

                t2 = this.getTemporal();

                this.agregarACodigo(new OperacionBinaria(t2,t1,"*",tam_ind+""));
                op = new Element("OB");
                op.setAttribute("t3", tam_ind+"");
                op.setAttribute("t1",t2);
                op.setAttribute("operando","*");
                op.setAttribute("t2",t1);
                elePlano.addContent(op);

                this.agregarACodigo(new OperacionBinaria(t2,t2,"+",cont+""));
                op = new Element("OB");
                op.setAttribute("t1", t2);
                op.setAttribute("t2",t2);
                op.setAttribute("operando","+");
                op.setAttribute("t3",cont+"");
                elePlano.addContent(op);


                retorno = t2;

                this.sacarTemp(t1);

                break;
            case Main.RuleConstants.RULE_SIMPLELOCATION_ID:
                //<simpleLocation> ::= id

                id = (String)reduction.getToken(0).getData();

                for(tabla.Simbolo a : listaVars){

                    if(id.equals(a.darId())){
                        break;
                    }
                    cont+=a.darTipo().darTamanioTipo();
                }

                retorno = cont+"";

                break;
        }

        return retorno;
    }


    @SuppressWarnings("static-access")
	public String generarLocacion(Reduction reduccion){
        
        String retorno="",id,t1,t2,t3;
        tabla.Simbolo simbolo;
        int tam;
        String a[];

        switch(reduccion.getParentRule().getTableIndex()){
            case Main.RuleConstants.RULE_LOCATION_DOT:
                //<location> ::= <simpleLocation> '.' <location>
                //<simpleLocation> ::= id '[' <expression> ']'
                //<simpleLocation> ::= id
                Reduction simpleLoc = (Reduction)reduccion.getToken(0).getData();
                String base, despl_inicial;

                id = (String)simpleLoc.getToken(0).getData();
                simbolo = this.parser.darTablaSimbolos().darSimbolo(id, this.ambitoActual);
                a = this.darDesplazamiento(simbolo,parser,this.verificador);

                this.agregarACodigo(new Comentario(" <location> - structure ",true,'°',30));//°°°°°°
                elePlano.addContent(new Comment("locacion -> estructura"));

                if(simpleLoc.getParentRule().getTableIndex() == Main.RuleConstants.RULE_SIMPLELOCATION_ID){

                    //<simpleLocation> ::= id
                    //obtener el símbolo
                    //obtener el desplazamiento y la base de ese símbolo
                    //obtener el desplazamiento de la location
                    //sumarlos
                    //devolver base [despl_inicial+despl_loc]

                    base = a[0];
                    despl_inicial = a[1];


                }else{
                    //<simpleLocation> ::= id '[' <expression> ']'

                    base = a[0];
                    
                    t1 = this.generarExpresion((Reduction)simpleLoc.getToken(2).getData());
                    t2 = this.getTemporal();

                    tam = simbolo.darTipo().darTamanioTipoSimple();
                    this.agregarACodigo(new OperacionBinaria(t2,tam+"","*",t1));
                    op = new Element("OB");
                    op.setAttribute("t1", t2);
                    op.setAttribute("t2",tam+"");
                    op.setAttribute("operando","*");
                    op.setAttribute("t3",t1);
                    elePlano.addContent(op);
                    
                    this.agregarACodigo(new OperacionBinaria(t2,t2,"+",a[1]));
                    op = new Element("OB");
                    op.setAttribute("t1", t2);
                    op.setAttribute("t2",t2);
                    op.setAttribute("operando","+");
                    op.setAttribute("t3",a[1]);
                    elePlano.addContent(op);
                    despl_inicial = t2;

                    this.sacarTemp(t1);

                }

                t1 = this.darDesplazarEstructura((Reduction)reduccion.getToken(2).getData(), simbolo.darTipo().darListaVars());

                t3 = this.getTemporal();

                this.agregarACodigo(new OperacionBinaria(t3,despl_inicial,"+",t1));
                op = new Element("OB");
                op.setAttribute("t1", t3);
                op.setAttribute("t2",despl_inicial);
                op.setAttribute("operando","+");
                op.setAttribute("t3",t1);
                elePlano.addContent(op);

                retorno = base+"["+t3+"]";


                this.sacarTemp(t1);
                this.sacarTemp(despl_inicial);
                this.sacarTemp(t3);

                this.agregarACodigo(new Comentario("",true,'°',30));//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
                
                break;
            case Main.RuleConstants.RULE_LOCATION:
                //<location> ::= <simpleLocation>
                retorno = this.generarLocacion((Reduction)reduccion.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_SIMPLELOCATION_ID_LBRACKET_RBRACKET:
                //<simpleLocation> ::= id '[' <expression> ']'

                /*-------------------------------------------------------------------------
                <simpleLocation> ::= id '[' <expression> ']'
                Código:
                        cod(<expression>)-> t1
                       t2 = tam_ind * t1
                       t2 = t2 + despl_simbolo
                       base[t2]

                Resultado en: base[t2]
                -------------------------------------------------------------------------*/

                this.agregarACodigo(new Comentario(" <location> - array ",true,'°',30));//°°°°°°
                elePlano.addContent(new Comment("locacion -> arreglo"));

                id = (String)reduccion.getToken(0).getData();
                simbolo = this.parser.darTablaSimbolos().darSimbolo(id, this.ambitoActual);

                a = this.darDesplazamiento(simbolo,parser,this.verificador);
                //retorno[0] = base;
                //retorno[1] = despl+"";

                t1 = this.generarExpresion((Reduction)reduccion.getToken(2).getData());

                t2 = this.getTemporal();

                tam = simbolo.darTipo().darTamanioTipoSimple();

                this.agregarACodigo(new OperacionBinaria(t2,tam+"","*",t1));
                op = new Element("OB");
                op.setAttribute("t1", t2);
                op.setAttribute("t2",tam+"");
                op.setAttribute("operando","*");
                op.setAttribute("t3",t1);
                elePlano.addContent(op);
                
                this.agregarACodigo(new OperacionBinaria(t2,t2,"+",a[1]));
                op = new Element("OB");
                op.setAttribute("t1", t2);
                op.setAttribute("t2",t2);
                op.setAttribute("operando","+");
                op.setAttribute("t3",a[1]);
                elePlano.addContent(op);

                retorno = a[0]+"["+t2+"]";

                
                this.sacarTemp(t1);
                this.sacarTemp(t2);

                this.agregarACodigo(new Comentario("",true,'°',30));//°°°°°°°°°°°°°°°°°°°°°°°°°°°

                break;
            case Main.RuleConstants.RULE_SIMPLELOCATION_ID:
                //<simpleLocation> ::= id
                
                id = (String)reduccion.getToken(0).getData();
                simbolo = this.parser.darTablaSimbolos().darSimbolo(id, this.ambitoActual);

                a = this.darDesplazamiento(simbolo,parser,this.verificador);
                //retorno[0] = base;
                //retorno[1] = despl+"";
                retorno = a[0]+"["+a[1]+"]";


                break;
        }


        return retorno;
    }

    public static String[] darDesplazamiento(tabla.Simbolo symbol, main.Main parser, Verificador actual_ambit){

        String base;
        String retorno[] = new String[2];

        int despl = 0;


        //ver si es global
        if(symbol.darVerificador().darNombre() == 0){

            base = "global";

            LinkedList<tabla.Simbolo> simbGlobal = parser.darTablaSimbolos().darTodosSimbolosGlobales(actual_ambit);

            //System.out.println("Simbolos en ambitos hijos: \n"+simbGlobal);

            for(tabla.Simbolo a : simbGlobal){

                if(a == symbol){
                    break;
                }else{
                    despl+=a.darTipo().darTamanioTipo();
                }
            }


        }else{
            //está definida en el método actual

            base = "stack";

            LinkedList<tabla.Simbolo> simbLocal = parser.darTablaSimbolos().darTodoLosSimbolosEnAmbitoYSubSiguiente(actual_ambit);

            //System.out.println("Simbolos en ambitos hijos: \n"+simbLocal);

            for(tabla.Simbolo a : simbLocal){

                if(a == symbol){
                    break;
                }else{
                    despl+=a.darTipo().darTamanioTipo();
                }
            }


        }

        retorno[0] = base;
        retorno[1] = despl+"";
        
        return retorno;
    }

    public String generarLlamada(Reduction reduccion,String subname, int num){
        
        String retorno = "", t1, signature;
        Metodo llamado;

        switch(reduccion.getParentRule().getTableIndex()){
            case Main.RuleConstants.RULE_METHODCALL_ID_LPARAN_RPARAN:
                //<metodoCall> ::= id '(' <opt_arg_separated> ')'
                signature = this.darFirmaLlamada(reduccion);

                //System.out.println("Firma: "+signature+"------------------------------");

                llamado = parser.darTablaMetodos().darMetodo(signature);
                //System.out.println("--------------------------------"+ llamado.getMethodSignature()+"\n"+llamado.getSubname());

                this.agregarACodigo(new Comentario(" <metodoCall> ",true,'+',30));//++++++++++
                elePlano.addContent(new Comment("LlamadaMetodo"));

                if(  llamado.darRet().darNombreTipo().equals("void") ){

                    /*-------------------------------------------------------------------------
                    Si es void el tipo de retorno del método
                    Código:
                            cod(opt_arg_separated)
                            call metodo, cant_param
                    -------------------------------------------------------------------------*/
                    this.generarLlamada((Reduction)reduccion.getToken(2).getData(),llamado.darSubNombre(),1);

                    this.agregarACodigo(new Llamada(llamado.darSubNombre(),llamado.darParametros().size()+""));
                    llamada = new Element("Llamada");
                    llamada.setAttribute("dir2",llamado.darSubNombre());
                    llamada.setAttribute("dir3",llamado.darParametros().size()+"");
                    elePlano.addContent(llamada);

                    this.agregarACodigo(new Comentario("",true,'+',30));//++++++++++++++++++++++++
                    break;
                }

                /*-------------------------------------------------------------------------
                Si es void el tipo de retorno del método
                Código:
                        cod(opt_arg_separated)
                        t1 = call metodo, cant_param
                        retornar t1
                -------------------------------------------------------------------------*/

                t1 = this.getTemporal();

                this.generarLlamada((Reduction)reduccion.getToken(2).getData(),llamado.darSubNombre(),1);

                this.agregarACodigo(new Llamada(t1,llamado.darSubNombre(),llamado.darParametros().size()+""));
                llamada = new Element("Llamada");
                llamada.setAttribute("dir1",t1);
                llamada.setAttribute("dir2",llamado.darSubNombre());
                llamada.setAttribute("dir3",llamado.darParametros().size()+"");
                elePlano.addContent(llamada);

                retorno = t1;

                this.agregarACodigo(new Comentario("",true,'+',30));//++++++++++++++++++++++++

                break;
            case Main.RuleConstants.RULE_OPT_ARG_SEPARATED:
                 //<opt_arg_separated> ::= <arg list>
                this.generarLlamada((Reduction)reduccion.getToken(0).getData(),subname,num);
                break;
            case Main.RuleConstants.RULE_OPT_ARG_SEPARATED2:
                //<opt_arg_separated> ::=
                break;
            case Main.RuleConstants.RULE_ARGLIST:
                //<arg list> ::= <arg>
                this.generarLlamada((Reduction)reduccion.getToken(0).getData(),subname,num);
                break;
            case Main.RuleConstants.RULE_ARGLIST_COMMA:
                //<arg list> ::= <arg list> ',' <arg>
                this.generarLlamada((Reduction)reduccion.getToken(0).getData(),subname,num);
                this.generarLlamada((Reduction)reduccion.getToken(2).getData(),subname,num+1);
                break;
            case Main.RuleConstants.RULE_ARG:
                //<arg> ::= <expression>
                t1 = this.generarExpresion((Reduction)reduccion.getToken(0).getData());
                this.agregarACodigo(new Parametro(t1,subname,num));
                param = new Element("Parametro");
                param.setAttribute("param",t1);
                elePlano.addContent(param);

                this.sacarTemp(t1);

                break;
        }
        return retorno;
    }


    public String darFirmaLlamada(Reduction reduccion) {
        //<metodoCall> ::= id '(' <opt_arg_separated> ')'
        String signature = "";
        LinkedList<String> parameters = this.darParametros((Reduction)reduccion.getToken(2).getData());
        String id = (String)reduccion.getToken(0).getData();

        signature = id+"(";
        if(parameters.size()>0){
            signature+=parameters.get(0)+",";
            for(int i = 1;i<parameters.size(); i++){
                String a  = parameters.get(i);
                signature+=""+a+",";
            }
            signature = signature.substring(0, signature.length()-1);
        }
        signature+=")";

        return signature;
    }


    private LinkedList<String> darParametros(Reduction reduccion) {
        //<metodoCall> ::= id '(' <opt_arg_separated> ')'
        LinkedList<String> actual_param = new LinkedList<String>();

        switch(reduccion.getParentRule().getTableIndex())
        {
            case Main.RuleConstants.RULE_OPT_ARG_SEPARATED:
                //<opt_arg_separated> ::= <arg list>
                actual_param.addAll(this.darParametros((Reduction)reduccion.getToken(0).getData()));
                break;
            case Main.RuleConstants.RULE_OPT_ARG_SEPARATED2:
                //<opt_arg_separated> ::=
                break;
            case Main.RuleConstants.RULE_ARGLIST:
                //<arg list> ::= <arg>
                actual_param.addAll(this.darParametros((Reduction)reduccion.getToken(0).getData()));
                break;
            case Main.RuleConstants.RULE_ARGLIST_COMMA:
                //<arg list> ::= <arg list> ',' <arg>
                actual_param.addAll(this.darParametros((Reduction)reduccion.getToken(0).getData()));
                actual_param.addAll(this.darParametros((Reduction)reduccion.getToken(2).getData()));
                break;
            case Main.RuleConstants.RULE_ARG:
                //<arg> ::= <expression>
                String arg = this.obtenerTipo((Reduction)reduccion.getToken(0).getData());
                actual_param.add(arg);
                break;
        }
        return actual_param;
    }

    
    private String obtenerTipo(Reduction reduction){
        String retorno = "";

        
		String expression, simpleLocation;
        tabla.Simbolo simbolo;

        switch(reduction.getParentRule().getTableIndex())
        {
            case Main.RuleConstants.RULE_EXPRESSION:
                //<expression> ::= <Rel Exp> <conditionalop> <expression>

                retorno = "boolean";

                break;
            case Main.RuleConstants.RULE_EXPRESSION2:
                //<expression> ::= <Rel Exp>
                retorno = this.obtenerTipo((Reduction)reduction.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_RELEXP:
                //<Rel Exp> ::= <Add Exp> <relop> <Rel Exp>
                //<relop> ::= '<=' | '<' | '>' | '>=' | '==' | '!='
                retorno = "boolean";
                break;
            case Main.RuleConstants.RULE_RELEXP2:
                //<Rel Exp> ::= <Add Exp>
                retorno = this.obtenerTipo((Reduction)reduction.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_ADDEXP:
                //<Add Exp> ::= <Mult Exp> <addop> <Add Exp>
                retorno = "int";
                break;
            case Main.RuleConstants.RULE_ADDEXP2:
                //<Add Exp> ::= <Mult Exp>
                retorno = this.obtenerTipo((Reduction)reduction.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_MULTEXP:
                //<Mult Exp> ::= <Negate Exp> <mulop> <Mult Exp>
                retorno = "int";
                break;
            case Main.RuleConstants.RULE_MULTEXP2:
                //<Mult Exp> ::= <Negate Exp>
                retorno = this.obtenerTipo((Reduction)reduction.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_NEGATEEXP_MINUS:
                //<Negate Exp> ::= '-' <Value>
                retorno = "int";
                break;
            case Main.RuleConstants.RULE_NEGATEEXP_EXCLAM:
                //<Negate Exp> ::= '!' <Value>
                retorno = "boolean";
                break;
            case Main.RuleConstants.RULE_NEGATEEXP:
                //<Negate Exp> ::= <Value>
                retorno = this.obtenerTipo((Reduction)reduction.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_VALUE:
                //<Value> ::= <literal>
                retorno = this.obtenerTipo((Reduction)reduction.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_VALUE_LPARAN_RPARAN:
                //<Value> ::= '(' <expression> ')'
                retorno = this.obtenerTipo((Reduction)reduction.getToken(1).getData());
                break;
            case Main.RuleConstants.RULE_VALUE2:
                //<Value> ::= <metodoCall>
                retorno = this.obtenerTipo((Reduction)reduction.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_VALUE3:
                //<Value> ::= <location>
                retorno = this.obtenerTipo((Reduction)reduction.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_LOCATION_DOT:
                //<location> ::= <simpleLocation> '.' <location>

                //obtener a simpleLocation
                simpleLocation = this.obtenerTipo((Reduction)reduction.getToken(0).getData());

                if(simpleLocation.equals("error")){
                    retorno = "error";
                    break;
                }

                String idlocation1 = (String)((Reduction)reduction.getToken(0).getData()).getToken(0).getData();

                if(!simpleLocation.startsWith("struct:")){
                    retorno = "error";
                    break;
                }

                if(simpleLocation.endsWith("[]")){
                    retorno = "error";
                    break;
                }

                //existe??
                simbolo = this.parser.darTablaSimbolos().darSimbolo(idlocation1, ambitoActual);
                retorno = this.darTipoLocacion((Reduction)reduction.getToken(2).getData(), simbolo.darTipo().darListaVars());
                break;
            case Main.RuleConstants.RULE_LOCATION:
                //<location> ::= <simpleLocation>
                retorno = this.obtenerTipo((Reduction)reduction.getToken(0).getData());
                break;
            case Main.RuleConstants.RULE_SIMPLELOCATION_ID_LBRACKET_RBRACKET:
                //<simpleLocation> ::= id '[' <expression> ']'

                //tipo int'??
                expression = this.obtenerTipo((Reduction)reduction.getToken(2).getData());

                if(expression.equals("error")){
                    retorno = "error";
                    break;
                }else if(!expression.equals("int")){
                    retorno = "error";
                    System.out.println("tipo incompatible.... [!int]");
                }

                String id_simpleLocation_2 = (String)reduction.getToken(0).getData();
                simbolo = this.parser.darTablaSimbolos().darSimbolo(id_simpleLocation_2, ambitoActual);
                if(simbolo == null){
                    //no encontró en la tabla de símbolos
                    retorno = "error";
                }else{
                    //revisar si el símbolo es un arreglo.. si es quitar el final "[]" si no es -> error
                    if(simbolo.darTipo().esArreglo()){
                        retorno = simbolo.darTipo().darNombreTipo().substring(0, simbolo.darTipo().darNombreTipo().length()-2);
                    }else{
                        retorno = "error";
                    }
                }

                break;
            case Main.RuleConstants.RULE_SIMPLELOCATION_ID:
                //<simpleLocation> ::= id

                String id_simpleLocation_1 = (String)reduction.getToken(0).getData();
                simbolo = this.parser.darTablaSimbolos().darSimbolo(id_simpleLocation_1, ambitoActual);
                if(simbolo == null){
                    //no encontró en la tabla de símbolos
                    //cannot find symbol (Line 2): a
                    retorno = "error";
                }else{
                    retorno = simbolo.darTipo().darNombreTipo();
                }

                break;
            case Main.RuleConstants.RULE_METHODCALL_ID_LPARAN_RPARAN:
                //<metodoCall> ::= id '(' <opt_arg_separated> ')'

                //obtener la firma de esta llamada a método y buscarla en la tabla de métodos
                String signature = darFirmaLlamada(reduction);
                //por si los argumentos ya tienen un error...
                if(signature.contains("error") && !signature.startsWith("error")){
                    retorno = "error";
                    break;
                }

                Metodo correct_metodo = this.parser.darTablaMetodos().darMetodo(signature);
                if(correct_metodo!=null && !signature.isEmpty()){
                    retorno = correct_metodo.darRet().darNombreTipo();
                }else{
                    //cannot find symbol metodo (Line 2): metodo(boolean,int)
                    retorno = "error";
                }

                break;
            case Main.RuleConstants.RULE_LITERAL:
                //<literal> ::= <int_literal>//<int_literal> ::= num
                retorno = "int";
                break;
            case Main.RuleConstants.RULE_LITERAL2:
                //<literal> ::= <char_literal>//<char_literal> ::= character
                retorno = "char";
                break;
            case Main.RuleConstants.RULE_LITERAL3:
                //<literal> ::= <bool_literal>//<bool_literal> ::= true | false
                retorno = "boolean";
                break;
        }
        return retorno;
    }

    private String darTipoLocacion(Reduction reduccion, LinkedList<tabla.Simbolo> listaVars){
        String retorno = "";

        switch(reduccion.getParentRule().getTableIndex())
        {
            case Main.RuleConstants.RULE_LOCATION_DOT:
                //<location> ::= <simpleLocation> '.' <location>

                String simpleLocation = this.darTipoLocacion((Reduction)reduccion.getToken(0).getData(), listaVars);

                if(simpleLocation.equals("error")){
                    retorno = "error";
                    break;
                }

                String id_location_1 = (String)((Reduction)reduccion.getToken(0).getData()).getToken(0).getData();

                if(!simpleLocation.startsWith("struct:")){
                    retorno = "error";
                    break;
                }

                if(simpleLocation.endsWith("[]")){
                    retorno = "error";
                    break;
                }

                //buscarlo entre los miembros
                tabla.Simbolo simbolo0 = this.darLocacionDeListaVar(id_location_1, listaVars);
                retorno = this.darTipoLocacion((Reduction)reduccion.getToken(2).getData(), simbolo0.darTipo().darListaVars());
                break;
            case Main.RuleConstants.RULE_LOCATION:
                //<location> ::= <simpleLocation>
                retorno = this.darTipoLocacion((Reduction)reduccion.getToken(0).getData(), listaVars);
                break;
            case Main.RuleConstants.RULE_SIMPLELOCATION_ID_LBRACKET_RBRACKET:
                //<simpleLocation> ::= id '[' <expression> ']'

                //String expression = this.obtenerTipo((Reduction)reduccion.getToken(2).getData());
                String id_2 = (String)reduccion.getToken(0).getData();
                tabla.Simbolo symbol_2 = this.darLocacionDeListaVar(id_2, listaVars);
                if(symbol_2.darTipo().esArreglo()){
                    //quitar el "[]" del final...
                    retorno = symbol_2.darTipo().darNombreTipo().substring(0, symbol_2.darTipo().darNombreTipo().length()-2);
                }

                break;
            case Main.RuleConstants.RULE_SIMPLELOCATION_ID:
                //<simpleLocation> ::= id
                String id = (String)reduccion.getToken(0).getData();
                tabla.Simbolo symbol = this.darLocacionDeListaVar(id, listaVars);
                retorno = symbol.darTipo().darNombreTipo();
                break;
        }


        return retorno;
    }

    private tabla.Simbolo darLocacionDeListaVar(String locacion, LinkedList<tabla.Simbolo> listaVars) {

        for(tabla.Simbolo a : listaVars){
            if(locacion.equals(a.darId())){
                return a;
            }
        }

        return null;
    }

}
