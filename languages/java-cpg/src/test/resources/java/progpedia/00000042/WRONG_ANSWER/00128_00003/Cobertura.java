import java.io.*;
import java.util.*;

class Vetor implements Comparable<Vetor> {
    public int start,end;

    Vetor(int s, int e) {
	start = s;
	end = e;
	
    }
    
    @Override
    public int compareTo(Vetor p) {
	if (start < p.start) return -1;
	if (start > p.start) return +1;
	if (end < p.end) return -1;
	return +1;
    }
}

class Cobertura {
    static int m,n;

    static int Solve(Vetor v[]){
	int fim,conta=0,inicio=0,f=0; 

	fim = 0;
	while(fim < m){
	    for(int i=0;i<n;i++){
		if(fim > m) return conta;
		if(fim > 0){
		    if((inicio == v[i].start && fim >= v[i].end) ) conta = conta;
		    if(inicio == v[i].start && fim < v[i].end ) {
			//System.out.println("ola");
			fim = v[i].end;
		    }
		    if(v[i].start <= f && v[i].end > fim) fim = v[i].end;
		}
		if(v[i].start <= fim && v[i].end>fim ){
		    inicio = v[i].start;
		    f = fim;
		    fim = v[i].end;
		    conta++;
		}
		System.out.println("??" + fim);
	    }
	    return conta;
	}
	return conta;
    }
    
    /*static void merge(int v[], int w[], int start, int middle, int end) {
	int i, p1, p2;
	int aux[] = new int[n+1];
	int aux2[] = new int[n+1];
	//System.out.println("??" + end);
	p1=start;     
	p2=middle+1;  
	i = start;    
	while (p1<=middle && p2<=end) {
	    //System.out.println("!!" + middle);
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
	    //System.out.println("!!!" + middle);
	    aux[i] = v[p1];
	    aux2[i] = w[p1];
	    i++;
	    p1++;
	}
	while (p2<=end) {
	    //System.out.println("!!!!" + middle);
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
    */
    
    public static void main(String args[]){
	Vetor v[];
	Scanner stdin = new Scanner(System.in);

	m = stdin.nextInt();
	n = stdin.nextInt();
	v = new Vetor[n];
	
	for(int i=0;i<n;i++){
	    v[i] = new Vetor(stdin.nextInt(),stdin.nextInt());
	}
	
	Arrays.sort(v);
	
	/*for(int i=0;i<n;i++)
	    System.out.println(v[i].start + " " + v[i].end);
	*/
	System.out.println(Solve(v));
	
	
    }
}
