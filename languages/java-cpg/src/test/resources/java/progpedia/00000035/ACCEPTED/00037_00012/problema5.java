
import java.util.*;


class problema5 {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	int n = in. nextInt();
	int vetor[] = new int[n];
	
	for(int i = 0; i <n ; i++) {
	    vetor[i] = in.nextInt();
	}
	
	int cont = 0;
	for(int i = 0; i <= n-1; i++) {
	    for(int j = i+1; j <= n-1; j++) {
		if(vetor[i] > vetor[j])
		    cont += 1;
	    }
	}
	int print = mergesort(vetor, 0 ,n-1);
	System.out.println(print);
    }
    
    
    static int mergesort(int v[], int inicio, int fim) {
	int meio;
	int cont = 0;
	if(inicio < fim) {
	    meio = (inicio+fim)/ 2;
	    cont = mergesort(v, inicio, meio) + mergesort(v, meio+1, fim) + merge(v, inicio, meio, fim);
	   
	}
	return cont;
    }
    
    static int merge(int v[], int inicio, int meio, int fim) {
	int i, p1, p2;
	int aux[] = new int[fim+1];
	int cont = 0;
	p1 = inicio;
	p2 = meio +1;
	i = inicio;
	
	if(inicio >= fim) return 0;
	
	else {
	    
	    while(p1 <= meio && p2 <= fim) {
		
		if(v[p1] <= v[p2]) {
		    
		    aux[i++] = v[p1++];
		}
		else {
		    
		    cont += (meio+1) - p1;
		    aux[i++] = v[p2++];
		}
	    }
	}
	while(p1 <= meio) {
	    aux[i++] = v[p1++];
	    
	}
	
	while(p2 <= fim) {
	    aux[i++] = v[p2++];
	    
	}
	for(i = inicio; i <= fim; i++) {
	    v[i] = aux[i];
	}
	return cont;
    }
}
