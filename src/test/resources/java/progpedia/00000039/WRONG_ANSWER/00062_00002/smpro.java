import java.io.*;
import java.util.*;
import java.lang.*;

 

class smpro{

    static int bs(int k, int soma[], int low, int high){
	
	while(low<high){
	    int mid= low +  (high-low)/2;
	    if(soma[mid]==k)
		return mid;
	    else if(soma[mid]>k)
		return bs(k, soma, low, mid);
	    else
		return bs(k, soma, mid+1, high);
	    
	}
       
	return low;
    }

    static int nsomas;
    static boolean u;
    
    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	int N= stdin.nextInt();
	int n[] = new int[N];
	for(int i=0;i<N;i++)
	    n[i]= stdin.nextInt();

	int P = stdin.nextInt();
	//int p[]= new int [P];
	//for(int i=0;i<P;i++)
	//  p[i]= stdin.nextInt();

	nsomas = N*(N-1)/2;
	int soma[] = new int[nsomas];

	int pos = 0;
	for(int i=0; i<N; i++)
	    for(int j=i+1; j<N; j++) {
		soma[pos] = n[i] + n[j];
		pos++;
	    }

	Arrays.sort(soma);
	
	//for(int i=0; i<nsomas; i++)
	//  System.out.println(soma[i]);
	
	/*	int c=0;
	boolean primeiro;
	int dif;
	while(c<P){
	    int k= stdin.nextInt();
	    int melhor = Math.abs(k-soma[0]);
	    for(int i=0; i<nsomas;i++) {		
		dif = Math.abs(k-soma[i]);
		if (dif < melhor) melhor = dif;
	    }
	    primeiro = true;
	    for(int i=0; i<nsomas;i++) {
		dif = Math.abs(k-soma[i]);
		if (dif == melhor && (i==0 || soma[i]!=soma[i-1])) {
		    if (primeiro) primeiro = false;
		    else System.out.print(" ");
		    System.out.print(soma[i]);
		}
	    }
	    System.out.println();
	    c++;			    	    	    
	}	
	*/
	
	
	
	int c=0;
	int low=0;
	int high=nsomas-1;
	u=false;
	while(c<P){
	    if(c==P-1)
		u=true;
	    int k=stdin.nextInt();
	    
	    int num=bs(k, soma, low, high);

	     
	    
	    if(num==0){
		//if(u)
		System.out.println(soma[num]);
		//else
		//  System.out.println(soma[num]);
		
	    }
	    else if( (Math.abs(k-soma[num])) ==  (Math.abs(k-soma[num-1]))  ){
		//if(u)
		//  System.out.println(soma[num-1] + " " + soma[num]);
		//else
		System.out.println(soma[num-1] + " " + soma[num]);
		
	    }
	    else if( (Math.abs(k-soma[num])) <  (Math.abs(k-soma[num-1]))  ) {
		//if(u)
		System.out.println(soma[num]);
		//else
		//System.out.println(soma[num]);
	    }

	    else{
		//if(u)
		//  System.out.println(soma[num-1]);
		//else
		System.out.println(soma[num-1]);
		
		
	    }
		
		
	    
	    
	    c++;
	}

	
	
	
	
    }
}
