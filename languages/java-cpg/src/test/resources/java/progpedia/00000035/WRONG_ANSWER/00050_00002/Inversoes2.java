import java.util.*;
import java.io.*;

class Inversoes2{

    public static void main(String[] args){

	Scanner hello = new Scanner(System.in);
	int n = hello.nextInt();
	int contador = 0;
	int[] lista = new int[50000];
	
	for(int i=0;i<n;i++){
	    lista[i]=hello.nextInt();
	}
	
	int[] listaord = new int[50000];
	Arrays.sort(listaord);

	for(int i=0; i<lista.length; i++){
	    if(lista[i]!=listaord[i]) contador++;
	}
	System.out.println(contador+1);
    }
}
