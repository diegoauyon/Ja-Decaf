package tabla.clases;

public class If extends Declaracion
{
	public Expresion condicion = null;
	public Cuerpo cuerpoif = null;
	public Cuerpo cuerpoelse = null;
	
	public If(Cuerpo cuerpoelse, Cuerpo cuerpoif, Expresion condicion)
	{
		this.cuerpoelse = cuerpoelse;
		this.cuerpoif = cuerpoif;
		this.condicion = condicion;
	}

}
