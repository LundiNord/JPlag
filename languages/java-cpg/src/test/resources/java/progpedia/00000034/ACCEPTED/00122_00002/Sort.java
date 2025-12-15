// Exemplo de ordenacao de 10 numeros inteiros
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {
	Scanner stdin = new Scanner (System.in);
	
	int n= stdin.nextInt();
	int v[] = new int[n];
	for(int i=0;i<n;i++)
	    v[i]=stdin.nextInt();

	// Um array exemplo 
	//int v[] = {33, 4, 28, 18, 15, 2, 8, 17, 42, 39};
  
	//System.out.print(" Antes de ordenar: "); 
	//for (int i=0; i<n; i++)
	    //  System.out.print(v[i] + " ");
	    //	System.out.println();
	
	// Chamada ao sort padrao da linguagem Java
	Arrays.sort(v);
	
	//	System.out.print("Depois de ordenar: ");
	for (int i=0; i<n; i++)
	    System.out.print(v[i] + " ");
	System.out.println();

    }
}
