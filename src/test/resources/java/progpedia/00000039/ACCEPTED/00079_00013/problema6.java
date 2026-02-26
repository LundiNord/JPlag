import java.io.*;
import java.util.*;


class problema6 {
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

	int n = in.nextInt();
	int numeros[] = new int[n];

	for(int i = 0; i < n; i++) {
	    numeros[i] = in.nextInt();
	}

	int p = in.nextInt();
	int perguntas[] = new int[p];

	
	for(int j = 0; j < p; j++) {
	    perguntas[j] = in.nextInt();
	}
	int total = (n * (n-1)) / 2;  //progressoes aritmeticas
	int cont = 0;
	int somas[] = new int[total];
	for(int i=0; i < n ; i++) {
	    for(int j = i+1; j < n  ; j++) {
		somas[cont] = numeros[i] + numeros[j];	       
		cont++;
	    }
	}
	
	
	Arrays.sort(somas);

	
	
	for(int i = 0; i < p; i++) {
	    pesquisa(somas, perguntas[i], 0,total-1);
	    
	}
    }
    
    public static void pesquisa(int[] a, int b, int inicio, int fim) {
	
        while(inicio <=fim) {	    
	    int meio = (inicio + ((fim - inicio) / 2));
	    if(b == a[meio]) {
		System.out.println(a[meio]);
		return;
	    }
	    else
		if(inicio == fim) {
		    System.out.println(a[inicio]);
		    return;
		}
	    
		else 
		    {
			if(b < a[meio]) {
			    if(meio-1 >= inicio) 
				if(b > a[meio-1]) {
				    verifica(b, meio, a);
				    return;
				}
			    fim = meio;
			}
			else {
			    if(meio+1 <= fim)
				if(b > a[meio] && b < a[meio+1]) {
				    verifica(b, meio, a);
				    return;
				}
			inicio = meio+1;
			}
		    }
	}
    }
    
    public static void verifica(int b, int meio, int[] c) {
	
	if(c[meio] > b) {
	    int p1 = Math.abs(c[(meio - 1)] - b);
	    int p2 = Math.abs(c[meio] - b);
	    
	    if(p1 == p2 && c[meio-1] != c[meio] )
		System.out.println(c[meio-1] + " " + c[meio]);
	    else
		if(p1 >= p2) 
		    System.out.println(c[meio]);
		   
		else 
		    System.out.println(c[meio-1]);
	}		    
	
	else 
	    {
		if(c[meio] < b) {
		    int p1 = Math.abs(c[meio] - b);
		    int p2 = Math.abs(c[(meio+1)]-b);
		    
		    if(p1 == p2 && c[meio] != c[meio+1])
			System.out.println(c[meio] + " " + c[meio+1]);
		    else {
			if(p1 >= p2) 
			    System.out.println(c[meio+1]);
			
			else 
			    System.out.println(c[meio]);
			
		    }
		}	 
	    }
    }
}    

 

