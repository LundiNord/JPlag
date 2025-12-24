//-----------------------------------------------------------------------------------------------
//DAA_010 - Problema do Sapateiro
//Bruna Madeira, juntamente com Federica Murillo  
//com ajuda da resolução do problema DAA_006.java :)
//-----------------------------------------------------------------------------------------------

import java.io.*;
import java.util.*;

// Classe para guardar o trabalho
class Trabalho implements Comparable<Trabalho> {
    public int dias;
    public int multa;
    public int indice;
    public float x;

    Trabalho(int d, int m, int i) {
	dias=d;
	multa=m;
	indice=i;

	x=(float)multa/dias;

    }

    //definir como comparar elementos da classe Trabalho
    @Override    
    public int compareTo(Trabalho t) {
	if(x < t.x) return 1;
	if(x > t.x) return -1;

       //em caso de empate ordenar pelo menor indice
	else{
	    if(indice < t.indice) return -1;
	    else 
		return 1;
	}
    }

}
public class DAA_010 {
    public static void main (String args[]) {
	
	Scanner stdin = new Scanner(System.in);

	int n = stdin.nextInt(); //ler o numero de encomendas
	Trabalho t[]= new Trabalho[n];
	

	for (int i=0; i<n; i++)
	    t[i]= new Trabalho (stdin.nextInt(), stdin.nextInt(), i+1);

	//chamada ao sort padrao da Linguagem Java
	// usa o comparador padrao do tipo do array
	Arrays.sort(t);

	for (int i=0; i<n-1; i++)
	    System.out.print(t[i].indice + " ");

    	    System.out.print(t[n-1].indice);

	System.out.println();
    }

}
