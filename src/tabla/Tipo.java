package tabla;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Tipo 
{
	
	public String nombre;
	public int tipoVariable = Const.TipoVariables.primitivo;
	public int tipoPrimitivo = Const.TipoPrimitivo.void_;
	public boolean esRef = false;
	
//----------------------------------------------------------------------------------	
	

	
//----------------------------------------------------------------------------------	
	
	//Crea tipo estructura
	public Tipo()
	{
		
	}
	
	public Tipo(int tipoPrimitivo)
	{
		tipoVariable = Const.TipoVariables.primitivo;
		this.tipoPrimitivo =tipoPrimitivo; 
	}
	
	//estructura
	public Tipo(String nombre)
	{
		tipoVariable = Const.TipoVariables.estructura;
		this.nombre = nombre;
	}
	
	public Tipo crearArregloDesdeTipo(Tipo tipo)
	{
		if (tipo.tipoVariable == Const.TipoVariables.primitivo)
		{
			tipo.tipoVariable = Const.TipoVariables.arreglo;
		}
		else if (tipo.tipoVariable == Const.TipoVariables.estructura)
		{
			tipo.tipoVariable = Const.TipoVariables.arreglo_Estructura;
		}
		return tipo;
	}
	
	public Tipo crearArregloTipo(int tipo)
	{
		Tipo tip = new Tipo();
		tip.tipoVariable = Const.TipoVariables.arreglo;
		tip.tipoPrimitivo = tipo;
		return tip;
	}
	
	public Tipo crearArregloTipo(String nombre)
	{
		Tipo tip = new Tipo();
		tip.tipoVariable = Const.TipoVariables.arreglo_Estructura;
		tip.nombre = nombre;
		return tip;
	}
	
	public String tipo()
	{
		String tipo = "";
		if (!esRef)
		{
			if (tipoVariable == Const.TipoVariables.primitivo)
			{
				if (tipoPrimitivo == Const.TipoPrimitivo.integer)
				{
					return "integer"; 
				}
				else if (tipoPrimitivo == Const.TipoPrimitivo.bool)
				{
					return "booleano"; 
				}
				else if (tipoPrimitivo == Const.TipoPrimitivo.character)
				{
					return "character"; 
				}
				else if (tipoPrimitivo == Const.TipoPrimitivo.void_)
				{
					return "void"; 
				}
			}
			else if (tipoVariable == Const.TipoVariables.arreglo)
			{
				if (tipoPrimitivo == Const.TipoPrimitivo.integer)
				{
					return "A_integer"; 
				}
				else if (tipoPrimitivo == Const.TipoPrimitivo.bool)
				{
					return "A_booleano"; 
				}
				else if (tipoPrimitivo == Const.TipoPrimitivo.character)
				{
					return "A_character"; 
				}
				
			}
		}
	 return null;	
	}
}
