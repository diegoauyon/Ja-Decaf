package tabla;



public class Simbolo
{
	public String nombre;
	public int tipo = Const.TipoSimbolo.none;	
	public Object syntaxObject;
	public Object codeObject;
	
	public Simbolo(String nombre, int tipo, Object syntaxObject, Object codeObject)
	{
		this.nombre = nombre;
		this.tipo = tipo;
		this.syntaxObject = syntaxObject;
		this.codeObject = codeObject;
	}
	
}