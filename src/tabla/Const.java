package tabla;

public class Const 
{

	
	
	public interface TipoVariables
	{
		final int primitivo = 0;
		final int estructura = 1;
		final int arreglo = 2;
		final int arreglo_Estructura = 31;
	}
	
	public interface TipoPrimitivo
	{
		final int bool = 3;
		final int integer = 4;
		final int character = 5;
		final int void_ = 6;
	}
	
	public interface PasoMetodo
	{
		final int porValor = 7;
		final int porReferencia = 8;
	}
	
	public interface TipoOperadorBinario
	{
		final int none = 9;
		final int mayor = 10;
		final int menor = 11;
		final int mayorIgual = 12;
		final int menorIgual = 13;
		final int igual = 14;
		final int noIgual = 15;
		final int suma = 16;
		final int resta = 17;
		final int multiplicacion = 18;
		final int division = 19;
		final int modulo = 20;
		final int and = 21;
		final int or = 22;
		
	}
	
	public interface TipoOperadorUnario
	{
		final int none = 23;
		final int positivo = 24;
		final int negativo = 25;
		final int negacion = 26;
		final int indice = 27;
	}
	
	public interface TipoLiteral
	{
		final int  bool = 28;
		final int  integer = 29;
		final int character = 30;
		final int void_ = 32;
	}
	
	//31 es estructura arreglo
	
	public interface TipoElemento
	{
		
		final int coleccion = 33;
		final int expresion = 34;
	}
	
	public interface TipoSimbolo
	{
		public final int none = 35;
		public final int funcion = 36;
		public final int estructura = 37;
		public final int variable = 38;	
	}


	
	public Const()
	{
		
	}


}
