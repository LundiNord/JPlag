import java.io.*;
import java.util.*;

class prob5{
	public static void main(String[] args){
		Scanner stdin = new Scanner(System.in);
		
		int n = stdin.nextInt();
		int v[] = new int[n];
		int count = 0;
		
		for(int i = 0; i<n; i++){
			v[i] = stdin.nextInt();
		}
		count = mergesort(v,0,n-1);
		
		System.out.println(count);
	}
	
    static int merge(int v[], int inicio, int meio, int fim) {
    	int i, p1, p2;
    	int count1 = 0;
    	int a[] = new int[fim+1];

    	p1=inicio;     
    	p2=meio+1;  
    	i = inicio;    
    	
    	while (p1<=meio && p2<=fim) {           
    		if (v[p1] <= v[p2]) 
    			a[i++] = v[p1++];  
    		else if(v[p1] > v[p2]){
    			a[i++] = v[p2++];
    			count1 += meio + 1 -p1;
    		}
    	}
    	while (p1<=meio) 
    		a[i++] = v[p1++];    
    	while (p2<=fim)   
    		a[i++] = v[p2++];
	
    	for (i=inicio; i<=fim; i++) 
    		v[i] = a[i];  
    	
    	return count1;
    }
    
    static int mergesort(int v[], int inicio, int fim) {
    	int meio;
    	int count2 = 0;
    	if (inicio<fim) {                 
    		meio = (inicio+fim)/2;        
	    	count2 += mergesort(v, inicio, meio);   
	    	count2 += mergesort(v, meio+1, fim);   
	    	count2 += merge(v, inicio, meio, fim);  
    	}
    	return count2;
    }
}