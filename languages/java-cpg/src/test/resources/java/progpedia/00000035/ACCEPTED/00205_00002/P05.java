import java.io.*;
import java.util.*;

class P05 {
    public static void main (String args[]) {
	Scanner in = new Scanner (System.in);
	int N = in.nextInt();
	int v [] = new int [N];
	for(int i=0; i<N; i++) {
	    v[i] = in.nextInt();
	}
	int valor = mergesort(v, 0, N-1);
	System.out.println(valor);
    }
    static int merge(int v[], int start, int middle, int end) {
	int i, p1, p2;
	int aux[] = new int[end+1];
	int contador = 0;
	p1=start;    
	p2=middle+1;  
	i = start;   
	while (p1<=middle && p2<=end) {
	    if (v[p1] <= v[p2]) aux[i++] = v[p1++];  
	    else {
		aux[i++] = v[p2++];
		contador += (middle-p1+1);
	    }
	}
	while (p1<=middle) aux[i++] = v[p1++];
	while (p2<=end)	    aux[i++] = v[p2++];

	for (i=start; i<=end; i++) 
	    v[i] = aux[i];
	
	return contador;
    }
    
    
    static int mergesort(int v[], int start, int end) {
	int middle;
	int contador = 0;
	if (start<end) {                 
	    middle = (start+end)/2;        
	    contador = contador + mergesort(v, start, middle);   
	    contador = contador + mergesort(v, middle+1, end);  
	    contador = contador + merge(v, start, middle, end);  
	}
	return contador;
    }
}
