package tabla;

import java.util.LinkedList;

public class Verificador {
    
    private int nombre;
    private Verificador verificador;
    private LinkedList<Verificador> sigVerificador = new LinkedList<Verificador>();
    private int cHijo = 0;

    public static int cont = 0;

    public Verificador(){
        nombre = (cont++);
        this.verificador = null;
    }

    public Verificador(Verificador verificadorPrevio){
        nombre = (cont++);
        this.verificador = verificadorPrevio;
        verificadorPrevio.agregarHijo(this);
    }

    public int darNombre() {
        return nombre;
    }

    public void setName(int name) {
        this.nombre = name;
    }

    public Verificador darVerificadorPrevio() {
        return verificador;
    }

    public void asigVerificadorPrevio(Verificador previo) {
        this.verificador = previo;
    }

    
    public void agregarHijo(Verificador hijo) {
        this.sigVerificador.add(hijo);
    }

    
    public Verificador darHijo() {
        if(cHijo<this.sigVerificador.size()){
            return this.sigVerificador.get(cHijo++);
        }
        return null;
    }

    public Verificador darPadre(){
        if(this.verificador==null){
            return this;
        }
        return this.verificador.darPadre();
    }


    //TODO DAR ESPIRITU SANTO
    
    public void resetearAHijo() {
        cHijo = 0;
        for(Verificador a : this.sigVerificador){
            a.resetearAHijo();
        }
    }

    public LinkedList<Verificador> darProximoAmbito() {
        return sigVerificador;
    }

    
    public void asigSigVerificador(LinkedList<Verificador> next_scope) {
        this.sigVerificador = next_scope;
    }


}
