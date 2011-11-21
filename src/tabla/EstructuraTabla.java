package tabla;

import java.util.LinkedList;

public class EstructuraTabla {

    public LinkedList<Estructura> tabla = new LinkedList<Estructura>();

    public EstructuraTabla(){

    }

    public boolean agregarEstructura(String id, Verificador verificador, LinkedList<Simbolo> listaVars){
        return tabla.add(new Estructura(id, verificador, listaVars));
    }

    public boolean agregarEstructura(Estructura struct){
        Estructura st = this.darEstructura(struct.darId(), struct.darVerificador());
        if(st == null){
            return tabla.add(struct);
        }
        return false;
    }

    public Estructura darUltimaEstructura(){
        return this.tabla.getLast();
    }
    
    public Estructura darEstructura(String id, Verificador ambit){
        Verificador local_verificador = ambit;

        while(local_verificador != null){
            LinkedList<Estructura> list = darTodasEstructurasEnVerificador(local_verificador);

            for(int i=0;i<list.size();i++){
                String name = list.get(i).darId();
                if(name.equals(id)==true){
                    return list.get(i);
                }
            }
            local_verificador = local_verificador.darVerificadorPrevio();
        }
        return null;
    }

    public LinkedList<Estructura> darTodasEstructurasEnVerificador(Verificador ambit){
        LinkedList<Estructura> list = new LinkedList<Estructura>();
        for(Estructura a : this.tabla){
            if(a.darVerificador().darNombre()==ambit.darNombre()){
                list.add(a);
            }
        }
        return list;
    }


    public String[][] darInfo(){
    	//nombre estructura, verificador, listavars
        String[][] a = new String[this.tabla.size()][4];
        for(int i=0;i<a.length;i++){
            String[] b = this.tabla.get(i).darInfo();
            a[i][0]= b[0];
            a[i][1]= b[1];
            a[i][2]= b[2];
            a[i][3]= b[3];
        }
        return a;
    }


    public String[][] darInfoDefault(){
        //nombre estructura, verificador, listavars
        String[][] a = new String[1][4];
        a[0][0]= "";
        a[0][1]= "";
        a[0][2]= "";
        a[0][3]= "";
        return a;
    }

    public static String[] darNombreColumnas(){
        String columNames[] = {"Nombre Estructura", "Ambito", "Tamaño Tipo", "Lista de Variables"};
        return columNames;
    }

    public String toString(){
        String temp  = "";
        for(Estructura a: this.tabla){
            temp +=a.toString()+"\n";
        }
        return temp;
    }

}