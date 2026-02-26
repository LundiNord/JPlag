import java.util.*;
import java.io.*;

public class prob6{

    public static int pesquisaBinaria(int somas[],int low,int high,int key){
	int midle = (low + high)/2;

	
	while(low < high){	
	    midle = (low + high)/2;

	    
	    
	    if(key > somas[midle]) return pesquisaBinaria(somas,midle+1,high,key);
	    else if (key < somas[midle]) return pesquisaBinaria(somas,low,midle,key);
	    else if (key == somas[midle]) return midle;
	}

	return midle;
    }
	
    
    public static void main(String args[]){

	Scanner in = new Scanner(System.in);

	int n = in.nextInt();

	
	int size = ((n*n)-n)/2;

	int somas[] = new int[size];
	int v[] = new int[n];

	for(int i=0;i<n;i++)   //Construir array v
	    v[i] = in.nextInt();

	int p = in.nextInt();
	int k[] = new int[p];

	for(int i=0;i<p;i++)   //Construir array k
	    k[i]=in.nextInt();

	int flag = 0;
	
	for(int i=0;i<n;i++){
	    for(int j=i+1;j<n;j++){
		somas[flag] = v[i] + v[j];
		flag++;
	    }
	}

	Arrays.sort(somas);

	
	

	int m;
	
	for(int i=0;i<p;i++){
	    if (k[i] < somas[0]) System.out.println(somas[0]);
	    else if (k[i] > somas[size-1]) System.out.println(somas[size-1]);
	    else {
		m = pesquisaBinaria(somas,0,size,k[i]);
			
		if(k[i] == somas[m]) System.out.println(somas[m]);
		else{
		    int difone = Math.abs(k[i] - somas[m-1]);
		    int diftwo = Math.abs(k[i] - somas[m]);

		    if(difone > diftwo) System.out.println(somas[m]);
		    else if(difone < diftwo) System.out.println(somas[m-1]);
		    else System.out.println(somas[m-1]  + " " + somas[m]);
	    }
	    }
	}

	
    }
}
