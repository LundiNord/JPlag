// Exemplo de ordenacao de 10 numeros inteiros
// ----------------------------------
// Pedro Ribeiro (DCC/FCUP) - 17/10/2015
// ----------------------------------

import java.io.*;
import java.util.*;

public class Sort {
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	int[] numeros = new int[n];
	
	for (int i=0; i<n; i++)
	    numeros[i]=stdin.nextInt();
	
	Arrays.sort(numeros);
	
	for (int i=0; i<n; i++)
	    System.out.print(numeros[i] + " ");
	System.out.println();

    }
}
