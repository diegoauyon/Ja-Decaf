package tabla;

import java.util.LinkedList;

public class Estructura {
    private LinkedList<Simbolo> listaVar = new LinkedList<Simbolo>();
    private Verificador verificador;
    private String id;

    public Estructura(){
        this.id = "";
        verificador = null;
    }
    
    public Estructura(String id, Verificador verificador, LinkedList<Simbolo> listaVar){
        this.id = id;
        this.verificador = verificador;
        this.listaVar = listaVar;
    }

    public LinkedList<Simbolo> darListaVar() {
        return listaVar;
    }

    public Verificador darVerificador() {
        return verificador;
    }

    public String darId() {
        return id;
    }

    public Tipo darTipo() {
        int tamano = 0;
        for(Simbolo a : listaVar){
            tamano += a.darTipo().darTamanioTipo();
        }
        Tipo tipo = new Tipo(this.id,tamano);
        tipo.asigListaVar(listaVar);
        tipo.asigEstructura(true);
        return tipo;
    }


    public String[] darInfo() {
    	//Nombre estructura, verificador, tamaño, listavars
        String[] a = new String[4];
        a[0] = this.id;
        a[1] = this.verificador.darNombre()+"";
        a[2] = this.darTipo().darTamanioTipo()+"";
        a[3] = this.listaVar.toString();
        return a;
    }

    public String toString(){
        String temp = "";
        temp += "\t"+id+" - scope: "+verificador.darNombre()+"\n";
        for(Simbolo a : this.listaVar){
            temp+="\t * "+a.darTipo().darNombreTipo()+" "+a.darId()+"\n";
        }
        return temp;
    }



}
