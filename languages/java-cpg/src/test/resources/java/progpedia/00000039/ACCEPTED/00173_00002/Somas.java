import java.io.*;
import java.util.*;

class Somas{
    public static void main(String []args){
	Scanner in = new Scanner (System.in);
	int n = in.nextInt();
	int []v = new int[n];
	for(int i=0;i<n;i++){
	    v[i]=in.nextInt();}

	//fazer todas as somas possiveis
	int tam = n*(n-1)/2;
	int []somas= new int[tam];
	int contador=0;
	for(int i=0;i<n;i++){
	    for(int j=i+1; j<n;j++){
		somas[contador]=v[i]+v[j];
		contador++;
	    }
	}

	//ordenar somas possiveis
	Arrays.sort(somas);

	int m = in.nextInt();
	for(int i=0;i<m;i++){
	    int p = in.nextInt();
	    if(p<=somas[0])
		System.out.println(somas[0]);
	    else if(p>=somas[tam-1])
		System.out.println(somas[tam-1]);
	    else
		bSearch(somas,0,tam-1,p);
		
	}

    }
    public static void  bSearch(int[] v,int low,int high,int key){
    
    while(low < high) {
	
	int middle = low + (high-low)/2;
	if(v[middle] >= key){
	    high = middle;}
	else{
	    low = middle + 1;}        
    }
    
    if(Math.abs(v[low] - key) == Math.abs(v[low-1] - key))
	System.out.println(v[low-1] + " " + v[low]);
    
    if(Math.abs(v[low] - key) < Math.abs(v[low-1] - key))
	System.out.println(v[low]);
    
    if(Math.abs(v[low] - key) > Math.abs(v[low-1] - key)) 
	System.out.println(v[low-1]);
    
    }
}
