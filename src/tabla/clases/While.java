package tabla.clases;

public class While extends Declaracion 
{
	public Expresion condicion = null;
	public Cuerpo cuerpo = null;
	
	public While(Cuerpo cuerpo, Expresion expresion)
	{
		this.cuerpo = cuerpo; this.condicion = expresion;
	}
	

}
