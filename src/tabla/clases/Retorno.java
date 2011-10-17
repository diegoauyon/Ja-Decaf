package tabla.clases;


public class Retorno implements IGeneracionCodigo{

    String retorno = "";  //id1
                        //id2 -> ""
                        //id3 -> ""

   public Retorno(String retorno){
        this.retorno = retorno;
    }

    public String darId1() {
        return this.retorno;
    }

    public String darId2() {
        return "";
    }

    public String darId3() {
        return "";
    }

    public String darCodigo() {
        return "\t"+"return "+this.retorno+"";
    }

}

