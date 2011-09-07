package tabla.clases;

public class Literal extends Expresion
{
	public String valor;
	public int tipo;
	
	public Literal(String valor, int tipoLiteral)
	{
		this.valor= valor; this.tipo = tipoLiteral;
	}

}
