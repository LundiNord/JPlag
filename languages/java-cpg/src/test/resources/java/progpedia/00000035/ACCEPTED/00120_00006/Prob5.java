// pesquisa auxiliar http://www.vogella.com/tutorials/JavaAlgorithmsMergesort/article.html
import java.util.*;
import java.io.*;

public class Prob5 {
	static int n;
	static int v[]; 
	//static int inverso;
	 
	static int merge(int v[], int start, int middle, int end) {
		int i, p1, p2;
		int aux[] = new int[end+1];
		int count = 0;

		p1=start;     // metade esquerda
		p2=middle+1;  // "metade direita
		i = start;    // array aux[] a conter juncao
		while (p1<=middle && p2<=end) {           
		    if (v[p1] <= v[p2]) 
		    	aux[i++] = v[p1++]; 
		    else if(v[p1] > v[p2]){            
		    	aux[i++] = v[p2++];
		    	count = count + middle + 1 - p1;
		    }
		}
		while (p1<=middle) 
			aux[i++] = v[p1++];    
		while (p2<=end)    
			aux[i++] = v[p2++];
		
		for (i=start; i<=end; i++) 
			v[i] = aux[i];  
		return count;
		
	    }
	
	static int mergesort(int v[], int start, int end) {
			int middle;
			int contador = 0;
			if (start<end) {                 
			    middle = (start+end)/2;     
			    contador += mergesort(v, start, middle);   
			    contador += mergesort(v, middle+1, end);   
			    contador += merge(v, start, middle, end);  
			}
			return contador;
		    }
	
	/*static int inverso(){
		
		int count = 0;
		for(int i = 0; i < n-1; i++){
			for(int j = 1+i; j< n-1; j++){
				if(v[i] > v[j])
					count += 1; 
			}
		}
		return inverso;
	}*/
	
	public static void main(String args[]){
		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();
		v = new int[n];
		for(int i = 0; i < n; i++){
			v[i] = stdin.nextInt();
		}
		int resp = mergesort(v, 0, n-1);
		System.out.println(resp);
	}

}
