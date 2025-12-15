// Exemplo de ordenacao de 10 numeros inteiros
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]){
    Scanner input = new Scanner(System.in);
    int j;
    int n = input.nextInt();
    int [] v = new int[n];

	// Um array exemplo
	/*int v[] = {33, 4, 28, 18, 15, 2, 8, 17, 42, 39};

	System.out.print(" Antes de ordenar: ");
	for (int i=0; i<10; i++)
	    System.out.print(v[i] + " ");
	System.out.println();*/

	for(j=0;j<n;j++)
	{
        v[j]=input.nextInt();
	}


	// Chamada ao sort padrao da linguagem Java
	Arrays.sort(v);

	//System.out.print("Depois de ordenar: ");
	for (int i=0; i<n; i++)
	    System.out.print(v[i] + " ");
	//System.out.println();

    }
}
