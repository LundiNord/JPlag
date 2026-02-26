import java.util.*;
import java.io.*;

public class Prob05 {
   static int cont = 0;

    public static int merge (int v[], int inicio, int meio, int fim) {
	int i = inicio;
	int p1 = inicio;
	int p2 = meio + 1;
	
	int aux[] = new int[fim+1];
	
	while (p1<=meio && p2<=fim) {
	    if (v[p1] <= v[p2]) {
		aux[i++] = v[p1++];
		cont++;
	    }
	    else {
		aux[i++] = v[p2++];
			cont++;
	    }
	}
	
	while (p1<=meio) {
	    aux[i++] = v[p1++];
	}

  	while (p2<=fim){
	    aux[i++] = v[p2++];
	}

	for (i=inicio; i<=fim; i++){
	    //	    if (v[i] != aux[i]) cont++;
	    v[i] = aux[i];
	}
 
	return cont;
    }

   public static int mergesort(int v[], int inicio, int fim) {
	int meio;
	
	if (inicio<fim) {
	    meio = (inicio + fim)/2;
	    mergesort(v, inicio, meio);
	    mergesort(v, meio+1, fim);
	    merge(v, inicio, meio, fim);
	    cont++;
	}

	return cont;
    }


    public static void main(String args[]) {
	
	Scanner stdin = new Scanner (System.in);
	
	int N    = stdin.nextInt();
	int v [] = new int[N];

	for (int i=0; i<N; i++)
	    v[i] = stdin.nextInt();

	/*	int cont = 0;
	int i,j;

	for (i=0; i<N; i++) 
	    for (j=i+1; j<N; j++) 
		if (v[i] > v[j])
		    cont += 1;
	*/

	System.out.println(mergesort(v, 0, N-1));

	    
		
    }
}