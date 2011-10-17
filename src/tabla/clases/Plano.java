package tabla.clases;


public class Plano implements IGeneracionCodigo{

    String plane = ""; //id1
                            //id2 -> ""
                            //id3 -> ""

    public Plano(String plane){
        this.plane = plane;
    }

    public String darId1() {
        return this.plane;
    }

    public String darId2() {
        return "";
    }

    public String darId3() {
        return "";
    }

    public String darCodigo() {
        return ""+this.plane+"";
    }

}
