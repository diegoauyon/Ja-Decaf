package tabla.clases;

import tabla.Const;
import tabla.Tipo;


public class Parametro
{
	public String nombre;
	public Tipo tipo;
	public int metodoPaso = Const.PasoMetodo.porValor;
	
	public Parametro(String nombre, Tipo tipo, int metodoPaso)
	{
		this.nombre = nombre; this.tipo = tipo; this.metodoPaso = metodoPaso;
	}
}