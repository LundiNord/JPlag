// Exemplo de ordenacao de 10 numeros inteiros
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

class Ordenar {
    public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
   
    int n = in.nextInt();
    int array [] = new int[n];
    for (int i =0; i< n ; i++){
    	array [i] = in.nextInt();
    }

    /*
    // Um array exemplo 
    int v[] = {33, 4, 28, 18, 15, 2, 8, 17, 42, 39};
  
	System.out.print(" Antes de ordenar: "); 
	for (int i=0; i<10; i++)
	    System.out.print(v[i] + " ");
	System.out.println();
*/	
	// Chamada ao sort padrao da linguagem Java
	Arrays.sort(array);
	
	//System.out.print("Depois de ordenar: ");
	for (int i=0; i<n; i++)
	    System.out.print(array[i] + " ");
	System.out.println();

    }
}