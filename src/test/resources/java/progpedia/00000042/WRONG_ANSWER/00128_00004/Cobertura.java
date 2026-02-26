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

	    }
	    return conta;
	}
	return conta;
    }
    
    
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
	

	System.out.println(Solve(v));
	
	
    }
}
