// Exemplo de ordenacao de n numeros inteiros
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {
	
	Scanner input = new Scanner(System.in);
	
	int n= input.nextInt();
	// Um array exemplo 
	int v[]= new int[n] ;
  
	//System.out.print(" Antes de ordenar: "); 
	//for (int i=0; i<10; i++)
	//    System.out.print(v[i] + " ");
	//System.out.println();
	
	
	// Chamada ao sort padrao da linguagem Java
	for(int i=0; i<n; i++){
		v[i]=input.nextInt();
	}
	
	Arrays.sort(v);
	
	//System.out.print("Depois de ordenar: ");
	for (int i=0; i<n; i++)
	    System.out.print(v[i] + " ");
	System.out.println();

    }
}
