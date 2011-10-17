package tabla.clases;


public class Jmp implements IGeneracionCodigo {

    String etiqueta = "";   //id1
                            //id2 -> ""
                            //id3 -> ""

    public Jmp(String etiqueta){
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
        return "\t"+"goto "+this.etiqueta;
    }

}
