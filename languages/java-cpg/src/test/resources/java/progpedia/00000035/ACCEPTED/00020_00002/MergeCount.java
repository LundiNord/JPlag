import java.io.*;
import java.util.*;

public class MergeCount {
    static int contador;
    
    // Assumir que os arrays sao v[start..middle] e v[middle+1..end]
    static int merge(int v[], int start, int middle, int end) {
	int i, p1, p2;
	int aux[] = new int[end+1];
	contador = 0;

	p1=start;    
	p2=middle+1;  
	i = start;
       
	if(start >= end) return 0;

	else{
	while (p1<=middle && p2<=end){            // Enquanto der para comparar
	    if (v[p1] <= v[p2]) 	{
		aux[i++] = v[p1++];
	    }                         // Escolher o menor e adicionar 
	    else{              
		aux[i++] = v[p2++];
		contador += (middle + 1) - p1;
   	    }
	}

	while (p1<=middle) aux[i++] = v[p1++];     // Adicionar o que resta
	while (p2<=end)    aux[i++] = v[p2++];
	
	for (i=start; i<=end; i++) v[i] = aux[i];  // Copiar array aux[] para v[]

	return contador;
	}
    }

    static int mergesort(int v[], int start, int end) {
	int middle;
	contador = 0;
	if (start<end) {     // Parar quando tamanho do array < 2
	    middle = (start+end)/2;        
	    contador = mergesort(v, start, middle) + mergesort(v, middle+1, end) + merge(v, start, middle, end);
	    }
	 
	return contador;
    }
 
    public static void main(String args[]) {

	Scanner stdin = new Scanner(System.in);
	
	int n   = stdin.nextInt();
	int v[] = new int[n];
	for (int i=0; i<n; i++)
	    v[i] = stdin.nextInt();

	System.out.println(mergesort(v, 0, n-1));

    }
}
