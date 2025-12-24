import java.io.*;
import java.util.*;

public class Prob03 {

    /*
    
    
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);

	int N = stdin.nextInt();
	int v [] = new int [N];

	for (int i=0; i<N; i++)
	    v[i] = stdin.nextInt();

	Arrays.sort(v);
	
	for (int i=0; i<N-1; i++)
	  System.out.print(v[i] + " ");
       	System.out.println(v[N-1]);

    }

    */

    // COM MERGE
    static void merge (int v[], int inicio, int meio, int fim) {
	int i = inicio;
	int p1 = inicio;
	int p2 = meio + 1;
	
	int aux[] = new int[fim+1];
	
	while (p1<=meio && p2<=fim) {
	    if (v[p1] <= v[p2]) aux[i++] = v[p1++];
	    else aux[i++] = v[p2++];
	}
	
	while (p1<=meio) aux[i++] = v[p1++];
	while (p2<=fim) aux[i++] = v[p2++];

	for (i=inicio; i<=fim; i++)
	    v[i] = aux[i]; 
    }

    static void mergesort(int v[], int inicio, int fim) {
	int meio;
	if (inicio<fim) {
	    meio = (inicio + fim)/2;
	    mergesort(v, inicio, meio);
	    mergesort(v, meio+1, fim);
	    merge(v, inicio, meio, fim);
	}
    }

    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);
		
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();

	mergesort(v, 0, n-1);

	for (int i=0; i<n-1; i++)
	    System.out.print(v[i] + " ");
	System.out.println(v[n-1]);
    }
}
