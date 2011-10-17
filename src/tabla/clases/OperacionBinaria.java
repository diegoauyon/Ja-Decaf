package tabla.clases;
public class OperacionBinaria implements IGeneracionCodigo{

    String dir1 = "";   //id1
    String dir2 = "";   //id2
    String dir3 = "";   //id3
    String operador = "";

    public OperacionBinaria(String dir1, String dir2, String operador, String dir3){
        this.dir1 = dir1;
        this.dir2 = dir2;
        this.dir3 = dir3;
        this.operador = operador;
    }

    public String darId1() {
        return this.dir1;
    }

    public String darId2() {
        return this.dir2;
    }

    public String darId3() {
        return this.dir3;
    }

    public String darCodigo() {
        return "\t"+this.dir1+" = "+this.dir2+" "+this.operador+" "+this.dir3;
    }

}
