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
			k = bsearch(somas, 0, n_pares-1, val);
         if(k!= -1)
            System.out.println(somas[k]);
         else 
            System.out.println(k);
		}
	}

	static int bsearch(int v[], int low, int high, int key){
      int middle = 0;
      while(low < high){
         middle = low + (high - low)/2;
         if(key == v[middle])
            return middle;
         else if(key < v[middle])
            high = middle;
         else low = middle + 1;
      }
      
      middle = low;
      if(middle > 0){
         if(key - v[middle-1] < v[middle] - key)
            return middle-1;
         if(key - v[middle-1] == v[middle] - key){
            System.out.print(v[middle-1] + " " );
            return middle;
         }
         if(v[middle]-key < key - v[middle-1])
            return middle;
      }
      return middle;  
   }
}