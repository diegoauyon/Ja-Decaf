package tabla.clases;


public class Llamada implements IGeneracionCodigo{

    String dir1 = "";   //id1 //temp1
    String dir2 = "";   //id2 //funcion
    String dir3 = "";   //id3 //parametros

    boolean void_type;

    public Llamada(String dir1, String dir2, String dir3){
        this.dir1 = dir1;
        this.dir2 = dir2;
        this.dir3 = dir3;

        void_type = false;
    }

    public Llamada(String dir2, String dir3){
        this.dir2 = dir2;
        this.dir3 = dir3;
        void_type = true;
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
        if(void_type){
            return "\t"+"call "+this.dir2+", "+this.dir3;
        }
        return "\t"+this.dir1+" = call "+this.dir2+", "+this.dir3;
    }

}