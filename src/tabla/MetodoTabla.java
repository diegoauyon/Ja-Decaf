package tabla;


import java.util.LinkedList;

public class MetodoTabla {

    LinkedList<Metodo> tablaMetodo = new LinkedList<Metodo>();

    public MetodoTabla(){}

    public boolean agregarMetodo(String nombre, Tipo ret, LinkedList<Simbolo> parametro){
        Metodo met = new Metodo(nombre, ret, parametro);
        for(Metodo a : this.tablaMetodo){
            if(a.darFirmaMetodo().equals(met.darFirmaMetodo())){
                return false;
            }
        }
        met.asigSubNombre(generarSubNombre(met.darNombre()));
        this.tablaMetodo.add(met);
        return true;
    }

    public boolean agregarMetodo(Metodo met){
        for(Metodo a : this.tablaMetodo){
            if(a.darFirmaMetodo().compareTo(met.darFirmaMetodo())==0){
                return false;
            }
        }
        met.asigSubNombre(generarSubNombre(met.darNombre()));
        this.tablaMetodo.add(met);
        return true;
    }

    public String generarSubNombre(String nombre){
        String sub = nombre;
        int contador = 0;
        boolean repetir = true;
        while(repetir){
            repetir = false;
            for(Metodo a : this.tablaMetodo){
                String sub2 = a.darSubNombre();
                if(sub2.equals(sub)){
                    sub = nombre+"|"+contador;
                    contador++;
                    repetir = true;
                    break;
                }
            }
        }
        //System.out.println("El sub-Nombre del nuevo método es: "+sub);
        return sub;
    }

    public LinkedList<Metodo> darListaMetodo(){
        return this.tablaMetodo;
    }


    public Metodo darMetodo(String signature){
        for(Metodo a : this.tablaMetodo){
            if(a.darFirmaMetodo().compareTo(signature)==0){
                return a;
            }
        }
        return null;
    }

    public String[][] darInfo(){
    	//Retorno, Nombre, Parametros
        String[][] a = new String[this.tablaMetodo.size()][5];
        for(int i=0;i<a.length;i++){
            String[] b = this.tablaMetodo.get(i).getData();
            a[i][0]= b[0];
            a[i][1]= b[1];
            a[i][2]= b[2];
            a[i][3]= b[3];
            a[i][4]= b[4];
        }
        return a;
    }


    public String[][] darInfoDefault(){
    	//return, nombre, parametros,  firma
        String[][] a = new String[1][5];
        a[0][0]= "";
        a[0][1]= "";
        a[0][2]= "";
        a[0][3]= "";
        a[0][4]= "";
        return a;
    }


    public static String[] darNombreColumnas(){
        String columNames[] = {"Retorno", "Nombre", "Parametros", "Firma", "Sub nombre"};
        return columNames;
    }

    public String toString(){
        String temp  = "";
        for(Metodo a: this.tablaMetodo){
            temp +=a.toString()+"\n";
        }
        return temp;
    }


    
}
