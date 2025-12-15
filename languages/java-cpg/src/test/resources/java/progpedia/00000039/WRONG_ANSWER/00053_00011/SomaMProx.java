import java.io.*;
import java.util.*;
class BSearch{
    public static void bsearch(int soma[], int low, int high, int op){

	while (low < high ){ 
	    int middle = low + (high - low)/2;
	    
	    if (soma[middle] >= op) {
	    	high = middle;
	    }
	    else
	    	low = middle + 1;
	}
	
	if(Math.abs(soma[low] - op) == Math.abs(soma[low-1] - op) )
		System.out.println(soma[low] + " " + soma[low-1]);

	else if(Math.abs(soma[low] - op) < Math.abs(soma[low-1] - op))
		System.out.println(soma[low]);

	else
		System.out.println(soma[low-1]);
    }
}

    
class SomaMProx{
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);

	int n,p;
	n = in.nextInt();
	int num[] = new int[n];
	for(int i=0; i<n ; i++)
	    num[i] = in.nextInt();
	
	p = in.nextInt();
	int op[] = new int[p];
	for( int i=0 ; i < p; i++)
	    op[i] = in.nextInt();
	
	int size = (n*(n-1))/2; 
	int soma[] = new int[size];
	int k = 0;
	for(int i=0; i<n-1; i++)
	    for(int j = i+1; j < n; j++){
		soma[k] = num[i] + num[j];
		k++;
	    }
	
	Arrays.sort(soma);
	
	for(int i = 0; i<p ; i++) {
	    if(op[i] <= soma[0])
		System.out.println(soma[0]);

	    else if(op[i] >= soma[size-1])
		System.out.println(soma[size-1]);

	    else
		BSearch.bsearch(soma, 0, size-1, op[i]);
	}
    }
}
