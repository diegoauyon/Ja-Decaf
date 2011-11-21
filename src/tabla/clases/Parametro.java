package tabla.clases;


public class Parametro implements IGeneracionCodigo{

    String param = "";  //id1
                        //id2 -> ""
                        //id3 -> ""
    private String from_function ="";
    private int num_of_parameter = 0;

    public Parametro(String param,String from_function,int numOfParameter){
        this.param = param;
        this.from_function= from_function;
        this.num_of_parameter= numOfParameter;
        
    }

    public String darParam() {
        return this.param;
    }

    public String darFrom_function() {
        return from_function;
    }

    public int darNum_of_parameter() {
        return num_of_parameter;
    }

    public String darCodigoString() {
    	return "\t"+"param "+this.param+", "+this.darFrom_function()+", "+this.darNum_of_parameter();
    }

}
