package tabla.clases;

import java.util.LinkedList;

import tabla.Tipo;

public class Funcion extends Declaracion
{
	public String nombre;
	public Cuerpo cuerpo;
	public Tipo tipo;
	public LinkedList<Parametro> parametros;

	
	public Funcion(Cuerpo cuerpo, LinkedList<Parametro> parametros, Tipo tipo,String nombre)
	{
		//if (variables!= null)
		//{
			//for (int)
		//}
		this.cuerpo = cuerpo; this.parametros = parametros; this.nombre = nombre;
		this.tipo = tipo;
	}
	
}