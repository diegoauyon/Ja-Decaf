package tabla.clases;

public class Asignacion extends Declaracion
{
	
	public String nombre;
	public Expresion valor;
	public Expresion indice;
	
	public Asignacion(Expresion valor, Expresion indice, String nombre)
	{
		this.nombre = nombre;
		this.valor = valor;
		this.indice = indice;
	}

}
