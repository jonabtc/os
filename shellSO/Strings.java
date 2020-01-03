

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.*;

public class Strings{
		 // Texto
	    
	    // Texto que vamos a buscar

	    // Contador de ocurrencias 
	    //static 

	public static String[] palabras(String str){
		String sTextoBuscado = "^ ";
		int contador = 0;
		String [] aux = new String [100];
	    while (str.indexOf(sTextoBuscado) > -1) {
		String a  = str.substring(0, str.indexOf(sTextoBuscado));
	      str = str.substring(str.indexOf(
	        sTextoBuscado)+sTextoBuscado.length(),str.length());
		aux[contador]=a;
	      contador++; 
	    }
		String [] respuesta = new String[contador];
		for(int i=0; i<contador; i++)
			respuesta[i]=aux[i];

	    System.out.println (contador);
	return respuesta;
	}
	public static void main(String args[]){
		String sTexto = "!1,!2,!3,!4,!5";
		//String a = sTexto.split("!");
		
		
		//String [] a = palabras (sTexto);
		//for(int i=0; i< a.length; i++)
			if(sTexto.charAt(0) == '!')
			System.out.println("lalala");
		//String comando = leer.nextLine();

	}
}

/*

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.lang.ProcessBuilder;

public class Shell {
	int contador = 0;
	

public void ingresarDato(String[] lista, String cmd){
	
	if(contador < 10){
		lista[contador]=cmd;
		contador++;
	}else{
		String[] aux = new String[9];
		for(int i=0; i<9;i++)
			aux[i] = lista[9-i];
		aux[10] = cmd;
		lista = aux;
		
	}
}
public void imprimir(){
	
}
	
public static void main(String[] args) throws IOException {
	Stack <String> listaCmd = new Stack<String>();
	String divisorComandos = "^ ";
	
    while (true) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
	System.out.print("shell:> ");
        command = in.readLine();
        
        String [] arregloComandos = command.split("^");
	System.out.println(arregloComandos.length);
        for(int i = 0; i<arregloComandos.length; i++){
		String []a = arregloComandos[i].split(" ");

		for(int j=0; j<a.length; j++)
			a[j]=a[j].trim();
		ProcessBuilder pb = new ProcessBuilder(a);
		try {

            Process prs = pb.start();
            BufferedReader out = new BufferedReader(new InputStreamReader(prs.getInputStream()));
			String readline;
			while ((readline = out.readLine()) != null){
				System.out.println(readline);
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
		}
        
       
       
    }
}
}

*/
