package tabla.clases;


public class Asignacion implements IGeneracionCodigo{

    String dir1 = "";   //id1
    String dir2 = "";   //id2
                        //id3 -> ""

    public Asignacion(String dir1, String dir2){
        this.dir1 = dir1;
        this.dir2 = dir2;
    }

    public String darId1() {
        return this.dir1;
    }

    public String darId2() {
        return this.dir2;
    }

    public String darId3() {
        return "";
    }

    public String darCodigo() {
        return "\t"+this.dir1+" = "+this.dir2;
    }

}