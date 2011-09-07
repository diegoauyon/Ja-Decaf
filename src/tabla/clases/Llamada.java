package tabla.clases;

import java.util.LinkedList;

public class Llamada extends Expresion
{
	public String nombre;
	public LinkedList<Argumento> argumentos;
	
	public Llamada(LinkedList<Argumento> argumentos, String nombre)
	{
		this.argumentos = argumentos;
		this.nombre = nombre;
	}
}

