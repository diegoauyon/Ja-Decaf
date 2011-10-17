package tabla;

import java.util.LinkedList;

public class Tipo {

    private boolean simple;    
    private boolean estructura; 
    private boolean param;     
    private boolean arreglo;     
    private int tamanioArreglo;

    private LinkedList<Simbolo> listaVars = new LinkedList<Simbolo>();
    
    private String nombreTipo;

    private int tamanioTipo;
    
    
    public Tipo(String nombreTipo, int tamanio){
        this.nombreTipo = nombreTipo;
        this.simple = false;
        this.estructura = false;
        this.arreglo = false;
        this.param = false;
        this.tamanioTipo = tamanio;
    }


    public static int darTamanioBasico(String a){
        int tamano = 0;
        if(a.equals("void"))
            tamano = 0;
        else if(a.equals("int"))
            tamano = 4;
        else if(a.equals("char"))
            tamano = 2;
        else if(a.equals("boolean"))
            tamano = 1;
        return tamano;
    }


    public boolean esSimple() {
        return simple;
    }

    public void asigSimple(boolean simple) {
        this.simple = simple;
    }

    public boolean esEstructura() {
        return estructura;
    }

    public void asigEstructura(boolean arreglo) {
        this.estructura = arreglo;
    }

    public boolean esArreglo() {
        return arreglo;
    }

    public void asigArreglo(boolean arreglo, int tamanioArreglo) {
        this.arreglo = arreglo;
        this.tamanioArreglo = tamanioArreglo;
    }
    
    public void asigArreglo(boolean array) {
        this.arreglo = array;
    }


    public LinkedList<Simbolo> darListaVars() {
        return listaVars;
    }


    public void asigListaVar(LinkedList<Simbolo> listaVars) {
        this.listaVars = listaVars;
    }
    public String darNombreTipo() {
        String tipo = nombreTipo;
        if(this.esArreglo()){
            tipo = tipo+"[]";
        }
        if(this.esEstructura()){
            tipo = "struct:"+tipo;
        }
        return tipo;
    }

    public String darNombreTipoSinEstruc() {
        String tipo = nombreTipo;
        if(this.esArreglo()){
            tipo = tipo+"[]";
        }
        if(this.esEstructura()){
            tipo = ""+tipo;
        }
        return tipo;
    }


    public void asigNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }


    public boolean esParametro() {
        return param;
    }


    public void asigParametro(boolean param) {
        this.param = param;
    }

    public int darTamanioTipo() {
        if(this.arreglo)
            return this.tamanioTipo*this.tamanioArreglo;
        return this.tamanioTipo;
    }

    public int darTamanioTipoSimple() {
        return this.tamanioTipo;
    }
    
}
