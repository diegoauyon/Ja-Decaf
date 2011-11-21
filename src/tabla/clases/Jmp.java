package tabla.clases;


public class Jmp implements IGeneracionCodigo {

    String etiqueta = "";   //id1
                            //id2 -> ""
                            //id3 -> ""

    public Jmp(String etiqueta){
        this.etiqueta = etiqueta;
    }

    public String darEtiqueta() {
        return this.etiqueta;
    }

    public String darCodigoString() {
        return "\t"+"goto "+this.etiqueta;
    }

}
