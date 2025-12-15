import java.util.*;
import java.io.*;
class Prob3{
    public static void main(String args[]){
	Scanner in = new Scanner(System.in);
	int N = in.nextInt();
	int[] v = new int[N]; 
	for(int i=0; i< N;i++){
	    v[i]=in.nextInt();
	}
	/*	for(int i=0;i<N-1;i++){
	    for(int j=0;j<N-1-i;j++){
		if(v[j]>v[j+1]){
		    int temp = v[j+1];
		    v[j+1] = v[j];
		    v[j] = temp;
		}
	    }
	    
	    }*/
	Arrays.sort(v);
	for(int i=0;i<N;i++){
	    
	    if(i>0){
		System.out.print(" ");
		System.out.print(v[i]);}
	    else
		System.out.print(v[i]);
	    
	}
	System.out.println();
    }
    
}
