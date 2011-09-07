package tabla.clases;

import java.util.LinkedList;

public class Estructura extends Declaracion
{
	public String nombre;
	public LinkedList<Variable> variables = null;
	
	public Estructura(LinkedList<Variable> variables, String nombre)
	{
		this.variables = variables;
		this.nombre = nombre;
	}
}