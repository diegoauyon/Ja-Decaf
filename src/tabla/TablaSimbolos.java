package tabla;

import java.util.HashMap;
import tabla.clases.*;

public class TablaSimbolos 
{

	
	private TablaSimbolos padre = null;
	@SuppressWarnings("rawtypes")
	private HashMap tablahash = new HashMap();
	
	
	public TablaSimbolos()
	{
		
	}
	
	public TablaSimbolos(TablaSimbolos padre)
	{
		this.padre = padre;
	}
	
	public Simbolo agregar(Variable variable)
	{
		return agregar(variable.nombre,Const.TipoSimbolo.variable,variable,null);
	}
	
	public Simbolo agregar(Funcion funcion)
	{
		return agregar(funcion.nombre,Const.TipoSimbolo.funcion,funcion,null);
	}
	
	public Simbolo agregar(Estructura estructura)
	{
		return agregar(estructura.nombre,Const.TipoSimbolo.estructura,estructura,null);
	}
	
	@SuppressWarnings("unchecked")
	public Simbolo agregar(String nombre, int tipo, Object syntaxObject, Object codeObject) //throws SimboloException
	{
		String prefix = prefixDeTipo(tipo);
		
		if (tablahash.containsKey(prefix+nombre)){
			System.out.println("Simbolo ya existe en la tabla de simbolos: "+ prefix+nombre);
			try {
				throw new SimboloException("Simbolo ya existe en la tabla de simbolos: "+ prefix+nombre);
			} catch (SimboloException e) {
				//  Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Simbolo simbolo = new Simbolo(nombre,tipo,syntaxObject,codeObject);
		tablahash.put(prefix+nombre,simbolo);
		return simbolo;
	}
	
	public boolean contiene(String nombre, int tipo)
	{
		return encontrar(nombre,tipo) != null;
	}
	
	public Simbolo encontrar(String nombre, int tipo)
	{
		String prefix = prefixDeTipo(tipo);
		
		if (tablahash.containsKey(prefix+nombre))
		{
			return (Simbolo) tablahash.get(prefix+nombre);
		}
		else if (padre!= null)
		{
			return padre.encontrar(nombre, tipo);
		}
		else
			return null;
		
		
	}
	
	public String prefixDeTipo(int tipo)
	{
		if (tipo == Const.TipoSimbolo.funcion)
		{
			return "f_";
		}
		else if (tipo == Const.TipoSimbolo.estructura)
		{
			return "e_";
		}
		else if (tipo == Const.TipoSimbolo.variable)
		{
			return "v_";
		}
		return "";
			
	}
	
	
	
	
	
	

}

