package tabla;

import java.util.LinkedList;

public class Metodo {
    private LinkedList<Simbolo> parametros = new LinkedList<Simbolo>();
    private Tipo ret;
    private String nombre;
    private String subnombre;

    public Metodo(String nombre, String subnombre, Tipo ret, LinkedList<Simbolo> parametros){
        this.parametros = parametros;
        this.ret = ret;
        this.nombre = nombre;
        this.subnombre = subnombre;
    }

    public Metodo(String nombre, Tipo ret, LinkedList<Simbolo> parametros){
        this.parametros = parametros;
        this.ret = ret;
        this.nombre = nombre;
        this.subnombre = nombre;
    }

    public String darFirmaMetodo(){
        String retorno = darNombre()+"(";
        if(darParametros().size()>0){
            retorno+=darParametros().get(0).darTipo().darNombreTipo()+",";
            for(int i = 1;i<this.darParametros().size(); i++){
                Simbolo a  = darParametros().get(i);
                retorno+=""+a.darTipo().darNombreTipo()+",";
            }
            retorno = retorno.substring(0, retorno.length()-1);
        }
        retorno+=")";
        //System.out.println("FIRMA METODO -----> "+retorno);
        return retorno;
    }

    public String[] getData() {
    	//retorno, nombre, parametros
        String[] a = new String[5];
        a[0] = this.darRet().darNombreTipo();
        a[1] = this.darNombre()+"";
        a[2] = this.darParametros().toString();
        a[3] = this.darFirmaMetodo();
        a[4] = this.darSubNombre();
        return a;
    }

    public String toString(){
        String temp  = "";
        temp += "\t"+this.darFirmaMetodo();
        return temp;
    }

    public String darSubNombre() {
        return subnombre;
    }

    public void asigSubNombre(String subnombre) {
        this.subnombre = subnombre;
    }

    public LinkedList<Simbolo> darParametros() {
        return parametros;
    }
    
    public void asigParametros(LinkedList<Simbolo> parametros) {
        this.parametros = parametros;
    }

    public Tipo darRet() {
        return ret;
    }

    public void asigRet(Tipo ret) {
        this.ret = ret;
    }

    public String darNombre() {
        return nombre;
    }

    public void asigNombre(String nombre) {
        this.nombre = nombre;
    }
    
}