package tabla.clases;


public class Plano implements IGeneracionCodigo{

    String plane = ""; //id1
                            //id2 -> ""
                            //id3 -> ""

    public Plano(String plane){
        this.plane = plane;
    }

    public String darPlano() {
        return this.plane;
    }


    public String darCodigoString() {
        return ""+this.plane+"";
    }

}
