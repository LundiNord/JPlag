import java.io.*;
import java.util.*;



class Mergesort{
	
	private int n[];
	private int h[];
		
	public void inserir(int[] v){
		
		n = v;
		h = new int[v.length];
	}
	
	public int ninversoes(int[] v){
		
		return mergesort(0,v.length-1);
	}
	
	private int mergesort(int a, int b){
		
		if(a<b){
			int m = a + (b-a)/2;
			
			return mergesort(a,m) + mergesort(m+1,b) + merge(a,m,b);
		}
		else return 0;
		
		
	}
	
	private int merge(int a, int m, int b){
		
		
		for (int i = a; i <= m; i++) {
		      h[i] = n[i];
		}
		
		for (int i = m+1; i <= b; i++) {
		      h[b+m+1-i] = n[i];
		}

		int i = a;
		int j = b;
		int c = 0;
	
		for(int k=a;k<=b;k++){
		    
			if (h[i] <= h[j]) {
		        n[k] = h[i];
		        i++;
		      } else {
		        n[k] = h[j];
		        j--;
		        
		        c+=m-i+1;
		      }	
		}
		return c;
	}

}


class prob3{
	
	public static void main(String[] args){
		
		Scanner ler = new Scanner(System.in);
		
		Mergesort mg = new Mergesort();
				
		int n = ler.nextInt();
		
		int v[] = new int[n];
		
		for(int i=0;i<n;i++){
			
			v[i] = ler.nextInt();
		}
		
		mg.inserir(v);
				
		System.out.println(mg.ninversoes(v));
		
	}
}