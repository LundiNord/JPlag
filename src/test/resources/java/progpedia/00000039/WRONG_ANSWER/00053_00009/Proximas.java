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
	    for(int j=i+1 ; j<
		    N ; j++){
		somas[pos] = n[i] + n[j];
		pos++;
	    }
       
	Arrays.sort(somas);
	int P = stdin.nextInt();

	
	for(int i = 0 ; i < P ; i++){
	
	    // p = perguntas
	    int p = stdin.nextInt();
	    bsearch(somas, 0, size-1, p, size);
	}
	
    }
    public static void  bsearch(int[] v,int low,int high,int value,int size){
	
	
	while(low < high) {
	    
	    
	    int mid = low + (high-low)/2;

       
	    if(v[mid] >= value) {
		high = mid;
	    }
	    else{
		low = mid + 1;
	    }
		
	}

	if(Math.abs(v[low]) == value)
	    System.out.println(v[low]);
	
	else if((low-1 > 0) && Math.abs(v[low] - value) == Math.abs(v[low-1] - value))
	    System.out.println(v[low-1] + " " + v[low]);

	else if((low+1 < size) && Math.abs(v[low] - value) == Math.abs(v[low+1] - value))
	    System.out.println(v[low] + " " + v[low+1]);
	
	else if(low-1 < 0) System.out.println(v[low]);
	
	else if(low+1 > size-1) System.out.println(v[low]);
	
	else if(low == 0){ if(value < v[low]) System.out.println(v[low]);
	    else System.out.println(v[low+1]); }

	else if(low == size - 1){ if(value > v[low]) System.out.println(v[low]);
	    else System.out.println(v[low-1]);	   }
	
	else  if(Math.abs(v[low] - value) < Math.abs(v[low-1] - value))
	    System.out.println(v[low]);

	else System.out.println(v[low-1]);
	    
	       
    }
}

