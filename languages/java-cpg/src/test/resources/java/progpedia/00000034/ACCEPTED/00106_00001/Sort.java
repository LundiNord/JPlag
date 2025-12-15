// Exemplo de ordenacao de 10 numeros inteiros
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {

	int a,b;
	
	Scanner input = new Scanner(System.in);

	a = input.nextInt();

	int v[] = new int[a];

	for(int i=0; i<a; i++)
	    {
		v[i] = input.nextInt();
	    }
	/*
	
	System.out.print(" Antes de ordenar: "); 
	for (int i=0; i<a; i++)
	    System.out.print(v[i] + " ");
	System.out.println();
	
	*/
	// Chamada ao sort padrao da linguagem Java
	Arrays.sort(v);
	
	//System.out.print("Depois de ordenar: ");
	for (int i=0; i<a; i++)
	    System.out.print(v[i] + " ");
	System.out.println();

    }
}
