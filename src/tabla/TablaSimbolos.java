package tabla;

import java.util.LinkedList;

public class TablaSimbolos {

    LinkedList<Simbolo> tabla;

  
    public TablaSimbolos(){
        tabla = new LinkedList<Simbolo>();
    }

    public boolean agregarSimbolo(Simbolo simbolo){
        return tabla.add(simbolo);
    }

    public boolean agregarTodosLosSimbolos(LinkedList<Simbolo> simbolo){
        return tabla.addAll(simbolo);
    }

    public boolean existeSimboloEnVerificador(String id, Verificador verificador){

        LinkedList<Simbolo> list = this.darTodosLosSimbolosEnVerificador(verificador);
        for(int i=0;i<list.size();i++){
            String name = list.get(i).darId();
            if(name.equals(id)==true){
                return true;
            }
        }
        return false;
    }

    public boolean existeTipoEnVerificador(String nombreTipo, Verificador verificador){

        LinkedList<Simbolo> list = this.darTodosLosSimbolosEnVerificador(verificador);
        for(int i=0;i<list.size();i++){
            String name = list.get(i).darTipo().darNombreTipo();
            if(name.equals(nombreTipo)==true){
                return true;
            }
        }
        return false;
    }

    public LinkedList<Simbolo> darTodosLosSimbolosEnVerificador(Verificador verificador){
        LinkedList<Simbolo> list = new LinkedList<Simbolo>();

        for(Simbolo a : this.tabla){
            if(a.darVerificador().darNombre()==verificador.darNombre()){
                list.add(a);
            }
        }
        return list;
    }

    public LinkedList<Simbolo> darTodoLosSimbolosEnAmbitoYSubSiguiente(Verificador veri){

        LinkedList<Simbolo> list = new LinkedList<Simbolo>();

        list.addAll(this.darTodosLosSimbolosEnVerificador(veri));

        for(Verificador a : veri.darProximoAmbito()){
            list.addAll(this.darTodoLosSimbolosEnAmbitoYSubSiguiente(a));
        }

        return list;
    }

    public LinkedList<Simbolo> darTodosSimbolosGlobales(Verificador veri){

        return this.darTodosLosSimbolosEnVerificador(veri.darPadre());

    }

    public Simbolo darSimbolo(String id, Verificador ambito){
        Verificador local = ambito;

        while(local != null){
            LinkedList<Simbolo> list = darTodosLosSimbolosEnVerificador(local);

            for(int i=0;i<list.size();i++){
                String name = list.get(i).darId();
                if(name.equals(id)==true){
                    return list.get(i);
                }
            }
            local = local.darVerificadorPrevio();
        }
        return null;
    }

    public boolean existeTipoEstructura(String id, Verificador ambit){
        Verificador local = ambit;

        while(local != null){
            LinkedList<Simbolo> list = darTodosLosSimbolosEnVerificador(local);

            for(int i=0;i<list.size();i++){
                String nombreTipo = list.get(i).darTipo().darNombreTipo();
                boolean esEstructura = list.get(i).darTipo().esEstructura();
                if(nombreTipo.equals("struct:"+id)==true && esEstructura){
                    return true;
                }
            }
            local = local.darVerificadorPrevio();
        }
        return false;
    }

    public Tipo darTipoEstructura(String id, Verificador amb){
        Verificador local = amb;

        while(local != null){
            LinkedList<Simbolo> list = darTodosLosSimbolosEnVerificador(local);

            for(int i=0;i<list.size();i++){
                String nombreTipo = list.get(i).darTipo().darNombreTipo();
                boolean isStruct = list.get(i).darTipo().esEstructura();
                if(nombreTipo.equals("struct:"+id)==true && isStruct){
                    return list.get(i).darTipo();
                }
            }
            local = local.darVerificadorPrevio();
        }
        return null;
    }


    public String[][] darInfo(){
    	//id,ambito,tipo,simple,estructura,parametro,array,listavars
        String[][] a = new String[this.tabla.size()][9];
        for(int i=0;i<a.length;i++){
            String[] b = this.tabla.get(i).darData();
            a[i][0]= b[0];
            a[i][1]= b[1];
            a[i][2]= b[2];
            a[i][3]= b[3];
            a[i][4]= b[4];
            a[i][5]= b[5];
            a[i][6]= b[6];
            a[i][7]= b[7];
            a[i][8]= b[8];
        }
        return a;
    }

    public String[][] darInfoDefault(){
    	//return,nombre,parametros,firma
        String[][] a = new String[1][9];
        a[0][0]= "";
        a[0][1]= "";
        a[0][2]= "";
        a[0][3]= "";
        a[0][4]= "";
        a[0][5]= "";
        a[0][6]= "";
        a[0][7]= "";
        a[0][8]= "";
        return a;
    }

    public static String[] darNombreColumnas(){
        String columNames[] = {"id", "Ambito", "Tipo", "Tamaño Tipo", "es Simple","es Estructura","es Param","es Arreglo","Lista Vars"};
        return columNames;
    }



    
}
