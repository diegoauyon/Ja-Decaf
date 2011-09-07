package tabla.clases;

import java.util.LinkedList;

import tabla.Const;




public class Elemento
{
	public int tipoElemento = Const.TipoElemento.expresion;
	public LinkedList<Elemento> elementos = null;
	public Expresion expresion = null;
	
	public Elemento(LinkedList<Elemento> elementos)
	{
		this.tipoElemento = Const.TipoElemento.coleccion;
		this.elementos = elementos;
	}
	
	public Elemento(Expresion expresion)
	{
		this.tipoElemento = Const.TipoElemento.expresion;
		this.expresion = expresion;
	}
	
}