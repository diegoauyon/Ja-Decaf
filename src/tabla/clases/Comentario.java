package tabla.clases;


public class Comentario implements IGeneracionCodigo{

    String comentario = "";   //id1
                            //id2 -> ""
                            //id3 -> ""
    boolean tab = false;

    public Comentario(String comentario){
        this.comentario = comentario;
    }

    public Comentario(String comentario, boolean tab, char completar, int tam){

        this.comentario = completar+""+completar+""+completar+comentario;

        while(this.comentario.length() <= tam){
            this.comentario+=""+completar;
        }

        this.tab = tab;
    }

    public String darComentario() {
        return this.comentario;
    }

    public boolean darTab() {
        return this.tab;
    }

    public String darCodigoString() {
        return ((tab)?"\t":"")+"//"+this.comentario+"";
    }

}
