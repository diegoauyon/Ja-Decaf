package tabla;

import tabla.clases.*;

public class Verificador 
{
	public Clase clase = null;
	
	public Verificador (Clase clase)
	{
		this.clase = clase;
	}
	
	public void construirTablaSimbolos(TablaSimbolos padre, Cuerpo cuerpo)
	{
		if (cuerpo.estructura != null)
		{
			for (int con= 0; con<cuerpo.estructura.size(); con++)
			{
				Estructura estructura = cuerpo.estructura.get(con);
				padre.agregar(estructura).syntaxObject = estructura;
			}
		}
		if (cuerpo.funciones != null)
		{
			for (int con= 0; con<cuerpo.funciones.size(); con++)
			{
				Funcion funcion = cuerpo.funciones.get(con);
				padre.agregar(funcion).syntaxObject = funcion;
				funcion.cuerpo.tablaSimbolos = new TablaSimbolos();
				construirTablaSimbolos(funcion.cuerpo.tablaSimbolos, funcion.cuerpo);
			}
		}
		
		if (cuerpo.declaraciones != null)
		{
			for (int con= 0; con<cuerpo.declaraciones.size(); con++)
			{
				Declaracion declaracion = cuerpo.declaraciones.get(con);
				if (declaracion.getClass() == Variable.class)
				{
					padre.agregar((Variable) declaracion).syntaxObject = declaracion;
				}
			}
		}
	}
	
	public boolean verificarExpresion(TablaSimbolos tablaSimbolo, Expresion expresion)
	{
		try
		{
			darTipoExpresion(tablaSimbolo,expresion);
			return true;
		}
		catch(VerificadorException ex)
		{
			System.out.println(" Oh - oh ");
			ex.printStackTrace();
			return false;
		}
	}
	
	public Tipo  darTipoExpresion(TablaSimbolos tabla, Expresion expresion) throws VerificadorException
	{
		if (expresion.getClass() == ExpresionUnaria.class)
		{
			ExpresionUnaria uno = (ExpresionUnaria) expresion;
			return encontrarTipo(darTipoExpresion(tabla,uno.valor),uno.operador);
		}
		else if (expresion.getClass() == ExpresionBinaria.class)
		{
			ExpresionBinaria bi = (ExpresionBinaria) expresion;
			return encontrarTipo(darTipoExpresion(tabla,bi.izquierda),darTipoExpresion(tabla,bi.derecha),bi.operador);
		}
		else if (expresion.getClass() == Literal.class )
		{
			Literal literal = (Literal) expresion;
			
			if (literal.tipo == Const.TipoLiteral.bool)
			{
				return new Tipo(Const.TipoPrimitivo.bool);
			}
			if (literal.tipo == Const.TipoLiteral.character)
			{
				return new Tipo(Const.TipoPrimitivo.character);
			}
			if (literal.tipo == Const.TipoLiteral.integer)
			{
				return new Tipo(Const.TipoPrimitivo.integer);
			}
			if (literal.tipo == Const.TipoLiteral.void_)
			{
				return new Tipo(Const.TipoPrimitivo.void_);
			}
			
		}
		else if (expresion.getClass() == Location.class)
		{
			return  ((Variable)tabla.encontrar(((Location)expresion).valor, Const.TipoSimbolo.variable).syntaxObject).tipo;
		}
		else if (expresion.getClass() == Llamada.class)
		{
			return ((Llamada)expresion).tipo;
		}
		return null;
	}
	
	public Tipo encontrarTipo(Tipo izTipo, Tipo deTipo, int operadorBinario) throws VerificadorException
	{
		if ((izTipo.tipoVariable != Const.TipoVariables.primitivo) || (deTipo.tipoVariable != Const.TipoVariables.primitivo))
		{
			throw new VerificadorException("Operaciones binarias solo se pueden hacer en tipo primitivos");
		}
		
		if (izTipo.tipoPrimitivo == Const.TipoPrimitivo.bool && deTipo.tipoPrimitivo == Const.TipoPrimitivo.bool)
		{
			switch(operadorBinario)
			{
				case Const.TipoOperadorBinario.and:
				case Const.TipoOperadorBinario.or:
				case Const.TipoOperadorBinario.noIgual:
					return new Tipo(Const.TipoPrimitivo.bool);
					//break;
				default:
					throw new VerificadorException("El operador usado no puede ser utilizado como tipo booleano");
					//break;
			}
		}
		else if (izTipo.tipoPrimitivo == Const.TipoPrimitivo.integer && deTipo.tipoPrimitivo == Const.TipoPrimitivo.integer)
		{
			switch(operadorBinario)
			{
			case Const.TipoOperadorBinario.and:
			case Const.TipoOperadorBinario.or:
				throw new VerificadorException("Operador dado no puede ser aplicado a tipo integer");
			case Const.TipoOperadorBinario.mayor:
			case Const.TipoOperadorBinario.menor:
			case Const.TipoOperadorBinario.mayorIgual:
			case Const.TipoOperadorBinario.menorIgual:
			case Const.TipoOperadorBinario.igual:
			case Const.TipoOperadorBinario.noIgual:
				return new Tipo(Const.TipoPrimitivo.bool);
			default:
				return new Tipo(Const.TipoPrimitivo.integer);
			}
		}
		
		else if (izTipo.tipoPrimitivo == Const.TipoPrimitivo.character && deTipo.tipoPrimitivo == Const.TipoPrimitivo.character)
		{
			switch(operadorBinario)
			{
				case Const.TipoOperadorBinario.and:
				case Const.TipoOperadorBinario.or:
					throw new VerificadorException("Operador dado no puede ser aplicado a tipo char");
				default:
					return new Tipo(Const.TipoPrimitivo.character);
					
			}
		}
		
		throw new VerificadorException("Tipos incompatibles para la operacion");
	}
	
	public Tipo encontrarTipo(Tipo tipo, int operador) throws VerificadorException
	{
		switch (operador)
		{
		case Const.TipoOperadorUnario.indice:
				if (tipo.tipoVariable == Const.TipoVariables.arreglo)
					return new Tipo(tipo.tipoPrimitivo);
				else if (tipo.tipoVariable == Const.TipoVariables.arreglo_Estructura)
					return new Tipo(tipo.nombre);
				else 
					throw new VerificadorException("El operador indice no puede ser aplicado al tipo especifico ");
		case Const.TipoOperadorUnario.negacion:
				if (tipo.tipoPrimitivo == Const.TipoPrimitivo.bool)
				{
					return new Tipo(tipo.tipoPrimitivo);
				}
				else
					throw new VerificadorException("El opearador NOT (!) no puede ser aplciado al tipo especifico");
		default:
			if (tipo.tipoVariable == Const.TipoVariables.primitivo)
			{
				return new Tipo(tipo.tipoPrimitivo);
			}
			else
				throw new VerificadorException("Los operadores +/- no puede ser aplicado al tipo espeficifico ");
		}
	}
	
	
	public boolean verificarCuerpo(Cuerpo cuerpo)
	{
		for (int con = 0; con<cuerpo.declaraciones.size(); con++)
		{
			Declaracion declaracion = cuerpo.declaraciones.get(con);
			if (declaracion.getClass() == Variable.class)
			{
				Variable variable = (Variable)declaracion;
				if (verificarExpresion(cuerpo.tablaSimbolos,(Expresion)variable.valor))
				{
					System.out.println("Si funciono");
				}
				else
					System.out.println("No funciono");
			}
		}
		return false;
	}
	

}
