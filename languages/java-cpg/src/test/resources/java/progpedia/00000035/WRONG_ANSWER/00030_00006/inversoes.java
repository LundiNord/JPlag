import java.util.*;
import java.io.*;

public class inversoes {
	
	static int merge(int v[] , int start , int middle , int end ) {
		int i , p1 , p2 ; 
		int aux[] = new int [end+1] ; 
		int cont = 0 ; 
		p1 = start ; 
		p2 = middle +1 ;
		i = start ; 
		while ( p1 <= middle && p2 <= end ) {
			if ( v[p1] <= v[p2] )  {
				aux[i++] = v[p1++];
				cont++;
			}
			else {
				aux[i++] = v[p2++];
				cont++;
			}
		}
			while ( p1<= middle) {
				aux[i++] = v[p1++];
			}
			while (p2 <= end ) {
				aux[i++] = v[p2++] ;
			}
			for ( i = start ; i <= end ; i++) {
				v[i] = aux[i] ; 
			}
			return cont ; 
		}
	
	static int mergesort ( int v[] , int start , int end ) {
		int middle ;
		int contador = 0  ; 
		if ( start < end ) {
			middle = (start+end)/2;
			contador = contador + mergesort(v,start,middle);
			contador = contador + mergesort(v , middle+1 , end) ;
			contador = contador + merge( v ,start ,middle , end ) ; 
		}
		return contador ; 
	}

	public static void main(String[] args) {
		Scanner ler  = new Scanner(System.in);
		int n = ler.nextInt();
		int v[] = new int[n];
		int cont=0  ; 
		int max = 0 ; 
		for ( int i = 0 ; i < n ; i++) {
			v[i] = ler.nextInt();
		}
			cont = mergesort(v,0,n-1) ;
		System.out.println(cont);
	}

}



