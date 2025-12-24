import java.util.*;
import java.io.*;

public class SomasProx{
    public static void somar(int v[], int v2[],  int n){
	int k = 0;
	for(int i=0; i<n; i++)
	    for(int j=i+1; j<n; j++){
		v[k] = v2[i]+v2[j];
		k++;
	    }
    }

    public static void bSearch(int v[], int start, int end, int cond){
	int mid=start;
	int f = end;
	int direita;
	int esquerda;
	int centro;
	if(start == end)
	    System.out.println(v[start]);
	else{
	    if(cond >= v[end])
		System.out.println(v[end]);
	    else if(cond <= v[start])
		System.out.println(v[0]);
	    else{
		while(start<end-1){
		    mid = start + (end-start)/2;
		    if(v[mid] > cond) end = mid;
		    else start = mid+1;
		}   
		if(v[mid] == cond) System.out.println(v[mid]);
		else{
		    esquerda = Math.abs(v[mid-1] - cond);
		    direita = Math.abs(v[mid+1] - cond);
		    centro = Math.abs(v[mid] - cond);
		    if(esquerda == centro && v[mid-1] != v[mid])
			System.out.println(v[mid-1]+" "+v[mid]);
		    else if(centro == direita && v[mid] != v[mid+1])
			System.out.println(v[mid]+" "+v[mid+1]);
		    else if(esquerda < Math.min(centro,direita))
			System.out.println(v[mid-1]);
		    else if(centro < Math.min(esquerda,direita))
			System.out.println(v[mid]);
		    else if(direita < Math.min(esquerda,centro))
			System.out.println(v[mid+1]);
		    else
			System.out.println(v[mid]);
		}
	    }
	}
    }

    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	if(n==1){
	    int c = stdin.nextInt();
	    int d = stdin.nextInt();
	    for(int i=0; i<d; i++)
		stdin.nextInt();
	    System.out.println(c);
	}
	    else{
	    int size = ((n-1)*n)/2;
	    int v[] = new int[size];
	    int guarda[] = new int[n];
	    int save=0;
	    
	    for(int i=0; i<n; i++)
		guarda[i] = stdin.nextInt();
	    
	    somar(v, guarda, n);
	    
	    int n2 = stdin.nextInt();
	    guarda = new int[n2];
	    
	    for(int i=0; i<n2; i++)
		guarda[i] = stdin.nextInt();
	    
	    Arrays.sort(v);
	    
	    for(int i=0; i<n2; i++){
		save = guarda[i];
		bSearch(v, 0, size-1,save);       
	    }
	}
    }
}
