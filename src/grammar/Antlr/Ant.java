package grammar.Antlr;

import java.io.*;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ANTLRFileStream;
/*
El nombre Principal es arbitrario, escoge el que prefieras
*/
public class Ant {
public static void main(String args[]){
try{
/*
Atención reemplaza XXXX por el nombre de tu gramática
Este programa funcionaría para una gramáica llamada XXXX.g
*/
// Crear el objeto correspondiente al analizador léxico
DecafLexer analex = new DecafLexer();
// Asignar el fichero de entrada al analizador léxico
analex.setCharStream(new ANTLRFileStream("Pruebas/input.txt"));
System.out.println("Paso!");
CommonTokenStream tokens = new CommonTokenStream();
System.out.println("Paso!");
// Identificar al analizador léxico como fuente de tokens para el
// sintactico
tokens.setTokenSource(analex);
System.out.println("Paso!");
// Crear el objeto correspondiente al analizador sintáctico
DecafParser anasint = new DecafParser(tokens);
System.out.println("Paso!");
/*
Comenzar el análisis llamando al axioma de la gramática
Atención, sustituye AXIOMA por el nombre del axioma de tu gramática
*/
anasint.program();
System.out.println("Paso!");
}catch (java.io.IOException fnfe){
System.err.println("No se encontró el fichero");
}catch (org.antlr.runtime.RecognitionException re){
System.err.println(re.getMessage());
}
}
}