package tabla.clases;


public class Etiqueta implements IGeneracionCodigo{

    public String etiqueta = "";   //id1
                            //id2 -> ""
                            //id3 -> ""

    public Etiqueta(String etiqueta){
        this.etiqueta = etiqueta;
    }

    public String darEtiqueta() {
        return this.etiqueta;
    }


    public String darCodigoString() {
        return this.etiqueta+":";
    }
    
}
