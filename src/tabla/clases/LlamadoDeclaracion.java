package tabla.clases;

import java.util.LinkedList;


public class LlamadoDeclaracion extends Declaracion
{
	public String nombre;
	public LinkedList<Argumento> argumentos;
	
	public LlamadoDeclaracion(LinkedList<Argumento> argumentos, String nombre)
	{
		this.nombre = nombre;
		this.argumentos = argumentos;
	}
	
}