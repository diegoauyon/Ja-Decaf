package tabla.clases;

import tabla.Tipo;


public class Variable extends Declaracion
{
	public String nombre;
	public Tipo tipo;
	public Object valor;
	
	public Variable(Object valor, Tipo tipo, String nombre)
	{
		this.nombre = nombre;
		this.valor = valor;
		this.tipo = tipo;
	}
	
}
