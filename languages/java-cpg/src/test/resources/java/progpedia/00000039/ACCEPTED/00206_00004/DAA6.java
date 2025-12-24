import java.util.*;
import java.io.*;

class DAA6{
	public static void main(String args[]){
		Scanner scan= new Scanner(System.in);
		int i, j, k=0, n_pares, val, m;

		int n=scan.nextInt();
		n_pares=(n*n-n)/2;
		int v[]=new int[n];
		for(i=0; i<n; i++)
			v[i]=scan.nextInt();
		
		int somas[]=new int[n_pares];

		for(i=0; i<n; i++){
			for(j=i+1; j<n; j++){
				somas[k]=v[i]+v[j];
				k++;
			}
		}

		Arrays.sort(somas);

		int p=scan.nextInt();

		for(i=0; i<p; i++){
			val=scan.nextInt();
			bsearch(somas, 0, n_pares-1, val);
		}
	}

	static void bsearch(int v[], int low, int high, int key){
		int right=-1, left, middle, center;

		if(key >= v[high])
			System.out.println(v[high]);
		else if(key <= v[low])
			System.out.println(v[0]);
   	    else { //comparacoes para fazer a pesquisa
   	    	while(low < high){
   	    		middle = low + (high - low)/2;
   	    		if(v[middle] >= key)
   	    			high = middle;
   	    		else 
   	    			low = middle + 1;
   	    	}
   	    	left = Math.abs(v[low-1] - key);
   	    	if(low + 1 <= high)
   	    		right = Math.abs(v[low+1] - key);
   	    	center = Math.abs(v[low] - key);
   	    	if(left == center && v[low-1] != v[low]) {
   	    		System.out.println(v[low-1] + " " + v[low]);
   	    		return ;
   	    	}
   	    	else if(right != -1 && center == right && v[low] != v[low-1]) {
   	    		System.out.println(v[low] + " " + v[low+1]);
   	    		return;
   	    	}
   	    	else if(left < center) {
   	    		System.out.println(v[low-1]);
   	    		return;
   	    	}
   	    	else if(right != -1 && right < center) {
   	    		System.out.println(v[low+1]);
   	    		return;
   	    	}
   	    	else {
   	    		System.out.println(v[low]);
   	    		return;
   	    	}
   	    }
   	}
}