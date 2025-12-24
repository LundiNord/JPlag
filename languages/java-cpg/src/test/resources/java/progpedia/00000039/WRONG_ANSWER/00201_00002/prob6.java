

import java.util.*;


public class prob6 {

	
	public static void binarysearch (int v[], int low, int high, int key) {
		while ( low < high) {
			int midle = low + (high-low)/2;
			if (v[midle]>key)
				high=midle;
			else
				low=midle+1;
			
		}
		
		if( Math.abs(v[low]-key)==Math.abs(v[low-1]-key))
			System.out.println(v[low] + " " + v[low-1]);
		else if ( Math.abs(v[low]-key)<Math.abs(v[low-1]-key))
			System.out.println( v[low]);
		else
			System.out.println(v[low-1]);
	}
	
	
	public static void main (String args[]) {
		Scanner le= new Scanner(System.in);
		int n= le.nextInt();
		int num[] = new int[n];
		
		for(int i=0; i<n; i++)
			num[i]= le.nextInt();
		
		int p= le.nextInt();
		int per[] = new int[p];
		
		for(int i=0; i<p; i++)
			per[i] = le.nextInt();
		int max = n*(n-1)/2;
		int soma[] = new int [max];
		int k=0;
		
		for(int i=0; i<max; i++){
			for(int j=i+1; j<n; j++){
				soma[k]= num[i]+num[j];
				k++;
			}
				
		}
		
		Arrays.sort(soma);
		
		for(int i=0; i<p; i++)
		binarysearch(soma,1,soma.length-1,per[i]);
		
		}
}
