import java.io.*;
import java.util.*;

class Proximas{
    public static void main(String args[]){

	Scanner stdin = new Scanner(System.in);
	
	int N = stdin.nextInt();
	int n[] = new int[N]; // n = numeros
	for(int i=0 ; i<N ; i++)	    
	    n[i] = stdin.nextInt();

	//guardar todas as somas de pares no array somas
	int pos = 0; // posições no array somas
	int size = N*(N-1)/2 ;
	int somas[] = new int[size];
	for(int i=0 ; i<N ; i++)
	    for(int j=i+1 ; j<N ; j++){
		somas[pos] = n[i] + n[j];
		pos++;
	    }
       
	Arrays.sort(somas);
	int P = stdin.nextInt();

	
	for(int i = 0 ; i < P ; i++){
	
	    // p = perguntas
	    int p = stdin.nextInt();

	    if(p <= somas[0])
		System.out.println(somas[0]);
	    
	    else
		if(p >= somas[size-1])
		    System.out.println(somas[size-1]);
	    
		else
		    bsearch(somas, 0, size-1, p);
	}
	
    }
    public static void  bsearch(int[] v,int low,int high,int value){

	
	while(low < high) {
	    
	    
	    int mid = low + (high-low)/2;

       
	    if(v[mid] >= value) {
		high = mid;
	    }
	    else{
		low = mid + 1;
	    }
		
	}

	if(Math.abs(v[low] - value) == Math.abs(v[low-1] - value))
	    System.out.println(v[low-1] + " " + v[low]);

	if(Math.abs(v[low] - value) < Math.abs(v[low-1] - value))
	    System.out.println(v[low]);

	if(Math.abs(v[low] - value) > Math.abs(v[low-1] - value))
	    System.out.println(v[low-1]);

    }
}

	
	


