package tabla.clases;


public class Retorno implements IGeneracionCodigo{

    String retorno = "";  //id1
                        //id2 -> ""
                        //id3 -> ""

   public Retorno(String retorno){
        this.retorno = retorno;
    }

    public String darRetorno() {
        return this.retorno;
    }

   

    public String darCodigoString() {
        return "\t"+"return "+this.retorno+"";
    }

}

