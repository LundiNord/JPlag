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
	while(start<end){
	    mid = start + (end-start)/2;
	    if(v[mid] > cond) end = mid;
	    else start = mid+1;
	    }
	if(mid<=0 || mid>=f) System.out.println(v[mid]);
	else if(v[mid] == cond) System.out.println(v[mid]);
	else{
	    if((Math.abs(v[mid-1]-cond) == Math.abs(v[mid]-cond)) 
	       && v[mid-1] != v[mid])
		System.out.println(v[mid-1]+" "+v[mid]);
	    else if(Math.abs(v[mid-1]-cond) < Math.abs(v[mid]-cond))
		System.out.println(v[mid-1]);
	    else
		System.out.println(v[mid]);
	}
    }

    public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	int n = stdin.nextInt();
	int size = ((n-1)*n)/2;
	int v[] = new int[((n-1)*n)/2];
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
	    bSearch(v, 0, size, save);       
	}
    }
}
