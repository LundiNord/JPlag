//tive ajuda a resolver este problema de um colega que nao frequenta a cadeira

import java.io.*;
import java.util.*;

public class Prob007{
    public static void main(String args[]) {
	Scanner stdin = new Scanner(System.in);

	int n = stdin.nextInt();	
	int s[] = new int[n]; 
	int count=0;
	
	for (int i = 0; i<n; i++){
	    s[i] = stdin.nextInt(); 
	    count+=i;
	 }
	 
	 //somas
	 int sum[] = new int[count];
	 int k=0;
	 
	 for(int i=0; i<n; i++){
		 for(int j=0; j<i; j++){
			 sum[k]=s[i]+s[j];
			 k++;
		 }
	}
      
    Arrays.sort(sum);
   
    int p = stdin.nextInt();
     
    for (int i = 0; i<p; i++){
		int per=stdin.nextInt();
		int proc=pesquisabinaria(sum, 0, count-1, per);
		if(proc!=-1)
			System.out.println(sum[proc]);
		else
			System.out.println(proc);
    }
}
    
    static int pesquisabinaria(int v[], int low, int high, int k){
		int middle=0;
		
		while(low < high){
			middle= low+(high-low)/2;
			if(k==v[middle])
				return middle;
			else if(k<v[middle])
				high = middle;
			else
				low=middle+1;
		}
		
		middle=low;
		if(middle > 0){
			if(k-v[middle-1] < v[middle]-k)
				return middle-1;
			if(k-v[middle-1] == v[middle]-k){
				System.out.print(v[middle-1] + " ");
				return middle;
			}
			if(v[middle]-k < k-v[middle-1])
				return middle;
		}
		return middle;
	}
}
