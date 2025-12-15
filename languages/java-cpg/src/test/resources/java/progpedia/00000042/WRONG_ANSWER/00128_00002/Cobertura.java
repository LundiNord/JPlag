import java.io.*;
import java.util.*;

class Cobertura {
    static int m,n;

    static int Solve(int v[],int w[]){
	int end,conta=0; 
	end = 0;
	while(end <= m){
	    for(int i=0;i<n;i++){
		while(v[i] == end)
		    if(w[i]>end) {
			end = w[i];
			conta++;
		    }
		
	    }
	    return conta;
	}
	return conta;
    }
    
    static void merge(int v[], int w[], int start, int middle, int end) {
	int i, p1, p2;
	int aux[] = new int[n+1];
	int aux2[] = new int[n+1];
	
	p1=start;     
	p2=middle+1;  
	i = start;    
	while (p1<=middle && p2<=end) {
	    
	    if (v[p1] <= v[p2]) {
		aux[i] = v[p1];
		aux2[i] = w[p1];
		i++;
		p1++;
	    }
	    else {
		aux[i] = v[p2];
		aux2[i] = w[p2];
		i++;
		p2++;
	    }
	}
	while (p1<=middle) {
	    
	    aux[i] = v[p1];
	    aux2[i] = w[p1];
	    i++;
	    p1++;
	}
	while (p2<=end) {
	    
	    aux[i] = v[p2];
	    aux2[i] = w[p2];
	    i++;
	    p2++;
	}
	for (i=start; i<=end; i++) {
	    v[i] = aux[i];
	    w[i] = aux2[i];
	    
	}
    }

    
    static void mergesort(int v[],int w[], int start, int end) {
	int middle;
	if (start<end) {                 
	    middle = (start+end)/2;        
	    mergesort(v, w, start, middle);   
	    mergesort(v, w, middle+1, end);   
	    merge(v, w, start, middle, end);  
	}
    }

    
    public static void main(String args[]){
	int L[],R[];
	Scanner stdin = new Scanner(System.in);

	m = stdin.nextInt();
	n = stdin.nextInt();
	L = new int[n];
	R = new int[n];
	for(int i=0;i<n;i++){
	    L[i] = stdin.nextInt();
	    R[i] = stdin.nextInt();
	}
	
	mergesort(L,R,0,n-1);
	
	System.out.println(Solve(L,R));
    }
}
