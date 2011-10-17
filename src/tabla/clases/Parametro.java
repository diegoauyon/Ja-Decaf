package tabla.clases;


public class Parametro implements IGeneracionCodigo{

    String param = "";  //id1
                        //id2 -> ""
                        //id3 -> ""

    public Parametro(String param){
        this.param = param;
    }

    public String darId1() {
        return this.param;
    }

    public String darId2() {
        return "";
    }

    public String darId3() {
        return "";
    }

    public String darCodigo() {
        return "\t"+"param "+this.param+"";
    }

}
