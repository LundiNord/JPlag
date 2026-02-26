//package Exercicios;
import java.util.*;
public class prob06 {
	static int high,low;

	 static int BinarySearch(int[] somas ,int key)
	{

		int low = 0;
	    int high = somas.length - 1;

	    if (high < 0)
	        throw new IllegalArgumentException("The array cannot be empty");

	    while (low < high) {
	        int mid = (low + high) / 2;
	        assert(mid < high);
	        int d1 = Math.abs(somas[mid  ] - key);
	        int d2 = Math.abs(somas[mid+1] - key);
	        if (d2 <= d1)
	        {
	            low = mid+1;
	        }
	        else
	        {
	            high = mid;
	        }
	    }
	    return somas[high];
	}
	
	public static void main(String[] args) {

		Scanner in = new Scanner (System.in);
		int n = in.nextInt();
		int v[] = new int[n];
		
		for (int a=0;a<n;a++)
			v[a]=in.nextInt();
		int aux=0;	
		int p =in.nextInt();
		int v2[] = new int[p];
		
		for (int b=0;b<p;b++)
			v2[b]=in.nextInt();
		int[] somas = new int[n*((n-1)/2)];
		
	    for (int i=0;i<v.length-2;i++)
	    	for (int j=i+1;j<v.length-1;j++)
	    	{
	    		somas[aux]=v[i]+v[j];
	    		aux++;	     		
	    	}		
	  
	    Arrays.sort(somas);	    

	 //   for (int i=0;i<n*((n-1)/2);i++)
	    //	System.out.print(somas[i] + " ");
	    for (int i=0;i<p;i++)
	    {
	    	int key=v2[i];
	    	System.out.println("a soma mais proxima de " +key + " e " + BinarySearch(somas,key));
	    }	    	  		
	}
}
