package tabla.clases;

import tabla.Const;

public class ExpresionBinaria extends Expresion 
{
	public Expresion derecha = null;
	public Expresion izquierda = null;
	public int operador = Const.TipoOperadorBinario.none;

	public ExpresionBinaria(Expresion derecha, Expresion izquierda, int operador)
	{
		this.derecha = derecha; this.izquierda = izquierda; this.operador = operador;
	}
}
