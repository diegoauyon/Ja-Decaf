package tabla.clases;


public class OperacionUnaria implements IGeneracionCodigo{

    String dir1 = "";   //id1
    String dir2 = "";   //id2
    String operador = "";

    public OperacionUnaria(String dir1, String operador, String dir2){
        this.dir1 = dir1;
        this.dir2 = dir2;
        this.operador = operador;
    }

    public String darDir1() {
        return this.dir1;
    }

    public String darDir2() {
        return this.dir2;
    }

    public String darOperador() {
        return operador;
    }

    public String darCodigoString() {
        return "\t"+this.dir1+" = "+this.operador+" "+this.dir2;
    }

}