import java.util.*;
import java.io.*;
import java.lang.*;

class prob06teste{

    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	int[] S = new int[N];
	for(int i=0;i<N;i++){
	    S[i] = in.nextInt();
	}
	int M=in.nextInt();
	int[] P = new int[M];
	for(int i=0;i<M;i++){
	    P[i] = in.nextInt();
	}
	int n_somas=(N*(N-1)/2);
	int[] somas= new int[n_somas];
	int c=0;
	for (int i=0; i<N;i++){
	    for(int j=i+1;j<N;j++){
		somas[c]=S[i]+S[j];
		c++;
	    }
	}
	c=0;
	Arrays.sort(somas);
	for(int i=0;i<M;i++){
	    if(somas[0]>=P[i]){
		System.out.println(somas[0]);
	    }
	    else if(somas[n_somas-1]<=P[i]){
		System.out.println(somas[n_somas-1]);
	    }
	    else
		pesq_numero(0,n_somas,somas,i,P);
	}
    }
	
    public static void pesq_numero(int min, int max, int somas[], int a, int P[]) {
	int low=min;
	int high=max;
	while ( low < high ) {
	    int half = low + ( high - low ) / 2;
	    if(somas[half]>=P[a])
		high=half;
	    else
		low=half+1;
	}
	int dist1=Math.abs(somas[low]-P[a]);
	int dist2=Math.abs(somas[low-1]-P[a]);
	if(dist1==dist2){
	    System.out.println(somas[low-1] + " " + somas[low]);
	}
	else if(dist1<dist2){
	    System.out.println(somas[low]);
	}
	else
	    System.out.println(somas[low-1]);
    }
}
	   
