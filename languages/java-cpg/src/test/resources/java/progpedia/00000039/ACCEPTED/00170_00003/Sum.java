import java.io.*;
import java.util.*;


class Sum {
	
	public static int BinarySearch( int[] v, int high, int low, int key) {
		int half = low + ( high - low )/2;
		if( high > low ) {
			
			if( v[half] == key ) {
				System.out.println( v[half] );							// Retorna o valor da key se a encontrar ( v[half] é igual a key )
				return v[half];
			}
			else if( key < v[half] )
				return BinarySearch( v, half, low, key );
			else
				return BinarySearch( v, high, half+1, key );
			
		}
		else {
			if( half>0) {
				if( ( v[half] - key ) < ( key - v[half-1] ) )
					System.out.println( v[half] );
				else if( ( v[half] - key ) > ( key - v[half-1]) )
					System.out.println( v[half-1] );
				else if( ( v[half]  - key ) == ( key - v[half-1] ) )
					System.out.println( v[half-1]+" "+v[half] );
				}
			else if( half == 0)
				System.out.println( v[half] );
				
				
				return -1;
			}
	}
	
	public static void main( String[] args ) {
		Scanner in = new Scanner( System.in );
		
		int n = in.nextInt();
		int[] values = new int[n];
		for( int i = 0; i < n; i++ ) {
			values[i] = in.nextInt();						// Vetor com os valores para as somas 
		}
		
		int q = in.nextInt();
		int[] query = new int[q];
		for( int i = 0; i < q; i++ ) {
			query[i] = in.nextInt();						// Vetor das perguntas
		}
		
		int maxsize = ((n*n)-n)/2;							// Tamanho maximo para o vetor das somas 
		int[] sum = new int[maxsize];
		
		int count = 0;
		for( int i = 0; i < n; i++ ) {
			for( int j = i+1; j < n; j++ ) {
				sum[count] = values[i] + values[j];			// Vetor com todas as somas
				count++;
			}
		}
		
		Arrays.sort( sum );
		
		
		for( int i = 0; i < q; i++ ) {
			int val = BinarySearch( sum, sum.length-1, 0, query[i] );
		}
		
		/*for( int i = 0; i < sum.length-1; i++ )
			System.out.print(sum[i]+" ");
		System.out.println();*/
	}
}