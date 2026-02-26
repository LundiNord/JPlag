import java.io.*;
import java.util.*;

class sumProx{

    static int binarySearch(int[] values , int val ) {
	return binarySearch(values , val , 0, values.length  - 1);
    }

    static int binarySearch(int[] values , int val , int low , int high) {
	int half = low + ( high - low ) / 2;

	if( high <= low ){

	    if(half != 0){
		int d1=values[half]-val;
		int d2=val-values[half-1];
		if(d1==d2){
		    System.out.println(values[half-1]+" "+values[half]);
		}
		else if(d1<d2)
		    System.out.println(values[half]);
		else
		    System.out.println(values[half-1]);
	    }

	    else{
		System.out.println(values[half]);
	    }

	    return -1;

	    
	}
	else{

	    if(val == values[half]){
		System.out.println(values[half]);
		return -1;
	    }
	    else if( val < values[half] ) 
		return binarySearch(values , val , low , half);
	    else
		return binarySearch(values , val , half + 1, high);
	}
    }

    
    

    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();

	int[] v = new int[n];
	int size = (n-1)*n/2;
	int[] somas = new int[size];
	
	for(int i=0; i<n; i++)
	    v[i]=stdin.nextInt();

	int pos = 0;
	
	for(int i=0; i<n;i++)
	    for(int j=i+1; j<n; j++)
		somas[pos++]= v[i]+v[j];

	Arrays.sort(somas);

	//n casos
	int n_casos = stdin.nextInt();

	for(int i=0; i<n_casos;i++){
	    int pesquisa=stdin.nextInt();
	    binarySearch(somas, pesquisa);
	    
	}
	
	//	for(int i=0; i< size; i++)
	//  System.out.print(somas[i]+" ");
    }
}
