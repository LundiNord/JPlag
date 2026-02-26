import java.util.*;
import java.lang.Math;
class prob6{
    public static int binarySearch(int[] somas,int l, int h, int k){
	if(h-l ==1){
	    int diff1 = Math.abs(somas[l]-k);
	    int diff2 = Math.abs(somas[h]-k);
	    if(diff1<diff2)
		return somas[l]; 
	    return somas[h];
	}
	int half=(h+l)/2;
	if (k<=somas[half])
	    return binarySearch(somas,l, half, k);
	return binarySearch(somas,half,h, k);
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
	int[] pp = new int[P];
	System.out.println("Insira P valores:");
	for(int i=0;i<P;i++){
	    int valor=in.nextInt();
	    System.out.println(binarySearch(somas,0, size-1,valor));
	}
    }
    
}
