package tabla.clases;

import tabla.Const;

public class ExpresionUnaria extends Expresion
{
	public Expresion valor = null;
	public int operador = Const.TipoOperadorUnario.none;
	
	public ExpresionUnaria(Expresion valor, int operador)
	{
		this.valor = valor; this.operador = operador;
	}

}
