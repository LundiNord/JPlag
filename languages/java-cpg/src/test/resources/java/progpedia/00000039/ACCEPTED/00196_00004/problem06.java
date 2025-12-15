//package Exercicios;
import java.util.*;


public class problem06 {
	
	 static int BinarySearch(int[] soma ,int low, int high, int key)
	{
		int mid=0;
	
	    while (low < high) {
	        mid = low + (high-low) / 2;
	       if (key == soma[mid])
	    	   return mid;
	       else if (key < soma[mid])
	    	   high=mid;
	       else
	    	   low=mid+1;
	    }
	    mid=low;	    
	    if(mid > 0){
			if(key - soma[mid-1] < soma[mid] - key)
				return mid-1;
			if(key - soma[mid-1] == soma[mid] - key){
				System.out.print(soma[mid-1] + " " );
				return mid;
			}
			if(soma[mid]-key < key - soma[mid-1])
				return mid;
		}
		return mid;    	    
	}
	
	public static void main(String[] args) {

		Scanner in = new Scanner (System.in);
		int n = in.nextInt();
		
		int v[] = new int[n];
		
		int quickscope=0;
		
		for (int a=0;a<n;a++)
		{
			v[a]=in.nextInt();
			quickscope +=a;
		}
		int aux=0;	
				
		int[] somas = new int[quickscope];		
	    for (int i=0;i<n;i++)
	    	for (int j=0;j<i;j++)
	    	{
	    		somas[aux]=v[i]+v[j];
	    		aux++;	     		
	    	}		
	    
	    Arrays.sort(somas);	 
	    int p =in.nextInt();

	    int backstreetboys;
	    for (int i=0;i<p;i++)
	    {
	    	int key = in.nextInt();
	    	backstreetboys = BinarySearch(somas,0,quickscope-1,key);
	    	if (backstreetboys != -1)
	    	{
	    		System.out.println(somas[backstreetboys]);
	    	}
	    	else
	    		System.out.println(backstreetboys);
	    }	 
	    in.close();
	}
}
