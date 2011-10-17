package tabla;

public class Simbolo {

    private String id;
    private Verificador  verificador;
    private Tipo   tipo;

    public Simbolo(){
        this.id = "";
    }

    public Simbolo(String id, Tipo tipo, Verificador verificador){
        this.id = id;
        this.verificador = verificador;
        this.tipo = tipo;
    }

    public String darId() {
        return id;
    }

    public void asigId(String id) {
        this.id = id;
    }

    public Verificador darVerificador() {
        return verificador;
    }

    public void asigVerificador(Verificador verificador) {
        this.verificador = verificador;
    }

    public Tipo darTipo() {
        return tipo;
    }

    public void asigTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String[] darData() {
    	//id,ambito,tipo,tamaño tipo,simple,estrucutra,parametro,arreglo,listavars
        String[] a = new String[9];
        a[0] = this.id;
        a[1] = this.verificador.darNombre()+"";
        a[2] = this.darTipo().darNombreTipo();
        a[3] = this.darTipo().darTamanioTipo()+"";
        a[4] = this.darTipo().esSimple()+"";
        a[5] = this.darTipo().esEstructura()+"";
        a[6] = this.darTipo().esParametro()+"";
        a[7] = this.darTipo().esArreglo()+"";
        a[8] = this.darTipo().darListaVars().toString();
        return a;
    }

    public String toString(){
        String temp = "";
        temp+=id+": "+tipo.darNombreTipo();
        return temp;
    }
    
}
