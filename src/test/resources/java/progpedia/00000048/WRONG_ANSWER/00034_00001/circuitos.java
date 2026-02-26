import java.util.*;
import java.io.*;

class circuitos {
	public static  void main (String args[]){
	Scanner ler = new Scanner(System.in);
	int l = ler.nextInt();
	int n = ler.nextInt();
	int v [] = new int[l+1];
	for ( int i = 1 ; i <= l ; i++) {
		v[i] = 0  ;
	}
	int x ;
	int y ; 
	int max =  0 ; 
	for ( int i = 0 ; i < n ; i++) {
			x = ler.nextInt();
			y = ler.nextInt();
			v[x]++;
			v[y]++;
		if ( max < v[x] ) {
			max = v[x];
		}
		else {
			if ( max < v[y] ) {
				max = v[y];
			}
		}
	}
	
	System.out.println(max);	
}
}
