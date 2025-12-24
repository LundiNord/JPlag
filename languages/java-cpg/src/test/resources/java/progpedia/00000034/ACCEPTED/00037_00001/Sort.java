// Exemplo de ordenacao de 10 numeros inteiros
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);
	int n;
	n = stdin.nextInt();
	
	int v[] = new int[n];  
	
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();
	
	// Chamada ao sort padrao da linguagem Java
	Arrays.sort(v);
	
	//System.out.print("Depois de ordenar: ");
	for (int i=0; i<n-1; i++)
	    System.out.print(v[i] + " ");
	System.out.println(v[n-1]);

    }
}
