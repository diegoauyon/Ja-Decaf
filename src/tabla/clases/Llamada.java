package tabla.clases;


public class Llamada implements IGeneracionCodigo{

    String temp_retorno = "";   //id1 //temp1
    String nombre_funcion = "";   //id2 //funcion
    String cant_params = "";   //id3 //parametros
    

    boolean void_type;

    public Llamada(String temp_retorno, String nombre_funcion, String cant_params){
        this.temp_retorno = temp_retorno;
        this.nombre_funcion = nombre_funcion;
        this.cant_params = cant_params;

        void_type = false;
    }

    public Llamada(String nombre_funcion, String cant_params){
        this.nombre_funcion = nombre_funcion;
        this.cant_params = cant_params;
        void_type = true;
    }

    public String darTemp_retorno() {
        return this.temp_retorno;
    }

    public String darNombre_funcion() {
        return this.nombre_funcion;
    }

    public String darCant_params() {
        return this.cant_params;
    }
    
    public boolean esTipoVoid()
    {
    	return void_type;
    }

    public String darCodigoString() {
        if(void_type){
            return "\t"+"call "+this.nombre_funcion+", "+this.cant_params;
        }
        return "\t"+this.darTemp_retorno()+" = call "+this.darNombre_funcion()+", "+this.darCant_params();
    }

}