package tabla.clases;

import java.util.LinkedList;

import tabla.TablaSimbolos;


public class Cuerpo
{
	public LinkedList<Funcion> funciones = null;
	public LinkedList<Estructura> estructura = null;
	public LinkedList<Declaracion> declaraciones = null;
	public LinkedList<Variable> variables = null;
	
	public TablaSimbolos  tablaSimbolos = null;
	
	public Cuerpo(LinkedList<Declaracion> declaraciones)
	{
		if (declaraciones  == null)
		{
			return;
		}
		funciones = new LinkedList<Funcion>();
		estructura = new LinkedList<Estructura>();
		this.declaraciones = new LinkedList<Declaracion>();
		variables = new LinkedList<Variable>();
		
		for (int cont = 0; cont<declaraciones.size(); cont++)
		{
			Object declaracion = declaraciones.get(cont);
			if (declaracion.getClass() == Funcion.class)
			{
				funciones.add((Funcion)declaracion);
				System.out.println(""+cont+". Es una funcion");
			}
			else if (declaracion.getClass() == Estructura.class)
			{
				estructura.add((Estructura)declaracion);
				System.out.println(""+cont+". Es una estructura");
			}
			else if (declaracion.getClass() == Variable.class)
			{
				variables.add((Variable)declaracion);
				System.out.println(""+cont+". Es una variable");
			}
			else
			{
				System.out.println(""+cont+". Es una declaracion");
				this.declaraciones.add((Declaracion) declaracion);
			}
			
			
			
		}
	}
	
	
}
