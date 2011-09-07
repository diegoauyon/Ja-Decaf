package tabla.clases;

import tabla.Const;

public class ExpresionUnaria extends Expresion
{
	public Expresion valor = null;
	public Expresion indice = null;
	public int operador = Const.TipoOperadorUnario.none;
	
	public ExpresionUnaria(Expresion indice,Expresion valor, int operador)
	{
		this.indice = indice; this.valor = valor; this.operador = operador;
	}

}
