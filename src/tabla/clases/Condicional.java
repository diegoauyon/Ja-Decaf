package tabla.clases;


public class Condicional implements IGeneracionCodigo {

   
    String dir1 = "";       //id1
    String dir2 = "";       //id2
    String etiqueta = "";   //id3
    String operador = "";

    public Condicional(String dir1, String operador, String dir2, String etiqueta){
        this.dir1 = dir1;
        this.dir2 = dir2;
        this.etiqueta = etiqueta;
        this.operador = operador;
    }

    public String darDir1() {
        return this.dir1;
    }

    public String darDir2() {
        return this.dir2;
    }

    public String darEtiqueta() {
        return this.etiqueta;
    }
    
    public String darOperador()
    {
    	return operador;
    }

    public String darCodigoString() {
        return "\t"+"if "+this.dir1+" "+this.operador+" "+this.dir2+" goto "+this.etiqueta;
    }
    
}
