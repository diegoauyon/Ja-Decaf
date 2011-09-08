package tabla.clases;

import java.util.LinkedList;

import tabla.Tipo;

public class Funcion extends Declaracion
{
	public String nombre;
	public Cuerpo cuerpo;
	public Tipo tipo;
	public LinkedList<Parametro> parametros;
	public LinkedList<Variable> variables = null;
	
	public Funcion(Cuerpo cuerpo,LinkedList<Variable> variables, LinkedList<Parametro> parametros, Tipo tipo,String nombre)
	{
		//if (variables!= null)
		//{
			//for (int)
		//}
		this.cuerpo = cuerpo; this.parametros = parametros; this.nombre = nombre;
		this.tipo = tipo; this.variables = variables;
	}
	
}