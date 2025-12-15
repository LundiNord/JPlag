import java.io.*;
import java.util.*;




public class Soma {
    
    
    static void binary(int []v,int low , int high, int key)
    {
	int middle,aux;
	aux= high;
        
	while(low < high)
	    {
		middle = low + (high - low)/2;
		if (v[middle] >= key )
		    {
			high=middle;
			
			
		    }
		else 
		    {
			low = middle+1;
		    }
		
		
	    }
	
	
	if((low-1)>= 0)
	    {
		
		if(key-v[low-1] == v[low]-key)
		    {
			System.out.print(v[low-1]+" "+ v[low]+"\n");
		    }
		else if((key - v[low-1]) < (v[low]-key))
		    {
			System.out.print(v[low-1]+"\n");	
		    }
		else if ((key - v[low-1]) > (v[low]-key))
		    {
			System.out.print(v[low]+"\n");
		    }
	    }
	else
	    {
		
		System.out.print(v[low]+"\n");	
	    }
	
		
	
	    
    }

    



    
    public static void main(String args[])
    {
	int k, n,p,s[],p1[],somas[];
	
	
	
	Scanner stdin = new Scanner(System.in);
	
	n    = stdin.nextInt();
	s = new int[n];
	for(int i = 0 ; i<n; i++)
	    {
		s[i] = stdin.nextInt();
	    }
	p = stdin.nextInt();
	p1 = new int[p];
	for(int i = 0; i<p; i++)
	    {
		p1[i] = stdin.nextInt();
	    }
	
	
	k=0;
	
	somas = new int[((n-1)*n)/2];
	for(int j = 0; j<n; j++)
	    {
		for(int i = j+1; i<n; i++)
		    {
			
			
			somas[k]= s[j]+s[i];
			k++;
		    }
	    }
	Arrays.sort(somas);
	
	for(int i=0 ; i<p; i++)
	    {
		
		//	System.out.println()
		binary(somas,0,k-1,p1[i]);
		 
	    }
	
	
	
    }
    
}
