package tabla.clases;


public class Etiqueta implements IGeneracionCodigo{

    public String etiqueta = "";   //id1
                            //id2 -> ""
                            //id3 -> ""

    public Etiqueta(String etiqueta){
        this.etiqueta = etiqueta;
    }

    public String darId1() {
        return this.etiqueta;
    }

    public String darId2() {
        return "";
    }

    public String darId3() {
        return "";
    }

    public String darCodigo() {
        return this.etiqueta+":";
    }
    
}
