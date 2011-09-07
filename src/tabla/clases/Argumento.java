package tabla.clases;

import tabla.Const;


public class Argumento
{
	public Expresion valor;
	public int metodoPaso = Const.PasoMetodo.porValor;
	
	public Argumento(Expresion valor, int pasoMetodo)
	{
		this.valor = valor; this.metodoPaso = pasoMetodo;
	}
}