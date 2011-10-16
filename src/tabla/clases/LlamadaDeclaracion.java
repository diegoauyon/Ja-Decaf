package tabla.clases;

import java.util.LinkedList;

public class LlamadaDeclaracion extends Declaracion
{
	public String nombre;
	public LinkedList<Argumento> argumentos;
	
	public LlamadaDeclaracion(LinkedList<Argumento> argumentos, String nombre)
	{
		this.argumentos = argumentos;
		this.nombre = nombre;
	}

}
