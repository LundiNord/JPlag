import java.util.*;
class prob6{
    public static int binarySearch(int[] somas,int l, int h, int k){
	
	int half = l+(h-l)/2;
	if(h>l){
	    if(somas[half]==k){
		System.out.println(somas[half]);
		
		return somas[half];
	    }
	    else if(k<somas[half])
		return binarySearch(somas, l,half, k);
	    else
		return binarySearch(somas, half+1,h, k);
	}
	else{
	    if(half>0){
		if((somas[half]-k)<(k-somas[half-1]))
		    System.out.println(somas[half]);
		else if((somas[half]-k)>(k-somas[half-1]))
		    System.out.println(somas[half-1]);
		else if((somas[half]-k)==(k-somas[half-1]))
		    System.out.println(somas[half-1]+" "+somas[half]);
	    }
	    else if(half==0)
		System.out.println(somas[half]);
	    return -1;
	}
    }
    
    
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	System.out.println("Insira N:");
	int n = in.nextInt();
	int size = (n*(n-1)/2);
	
	System.out.println("Insira N valores:");
	int[] vetor= new int[n];
	int[] somas = new int[size];
	for(int i=0;i<n;i++){
	    vetor[i]=in.nextInt();
	}
	
	int k=0;
	for(int i=0;i<n;i++){
	    for(int j=i+1;j<n;j++){
		somas[k]=vetor[i]+vetor[j];
		k++;
	    }
	}
	Arrays.sort(somas);
	System.out.println("Insira P:");
	int P = in.nextInt();
	int[] p = new int[P];
	System.out.println("Insira P valores:");
	for(int i=0;i<P;i++){
	    p[i]=in.nextInt();
	}
	
	for(int i=0;i<P;i++){
	    int val=binarySearch(somas, 0, somas.length-1,p[i]);
	}
    }
    
}
