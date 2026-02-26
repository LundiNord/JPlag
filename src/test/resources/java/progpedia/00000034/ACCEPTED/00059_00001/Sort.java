// Exemplo de ordenacao de 10 numeros inteiros
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	int v[] = new int[n];
	// Um array exemplo 
	for(int i = 0; i<n; i++) { 
	    v[i] = in.nextInt();
	}
	/*System.out.print(" Antes de ordenar: "); 
	for (int i=0; i<10; i++)
	    System.out.print(v[i] + " ");
	System.out.println();
	*/
	// Chamada ao sort padrao da linguagem Java
	Arrays.sort(v);
	
	
	for (int i=0; i<n; i++)
	    if(i==n-1)
		System.out.print(v[i]);
	    else
		System.out.print(v[i] + " ");
	System.out.println();

    }
}

//mesmo computador que Rui Carvalho
