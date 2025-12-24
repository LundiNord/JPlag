import java.util.*;

public class ex6 {
	public static void bsearch(int somas[], int low, int high, int key){
		while ( low < high) {
			int middle = low + (high-low)/2;
			if (somas[middle]>key)
				high = middle;
			else
				low = middle+1;
		}
		if( Math.abs(somas[low]-key) == Math.abs(somas[low-1]-key))
			System.out.println(somas[low-1] + " " + somas[low]);
		
		else if ( Math.abs(somas [low]-key) < Math.abs(somas[low-1]-key))
			System.out.println( somas[low]);
		
		else
			System.out.println(somas[low-1]);
	}
	
public static void main (String args[]){
	Scanner stdin = new Scanner (System.in);
	
	int x = stdin.nextInt();
	int v[] = new int[x];
	
	for (int i=0; i<x; i++)
		v[i]=stdin.nextInt();
	
	int p = stdin.nextInt();
	int np[] = new int[p];
	
	for (int i=0; i<p; i++)
		np[i]=stdin.nextInt();
	
	int tam = x*(x-1)/2;
	int somas[]= new int[tam];
	
	int k=0;
	for (int i=0; i<x;i++)
		for (int j=i+1;j<x;j++){
			somas[k]=v[i]+v[j];
			k++;
		}
	Arrays.sort(somas);
	
	for(int i=0; i<p; i++)
		bsearch(somas, 1, tam-1, np[i]);
	}
}
