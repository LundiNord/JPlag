import java.io.*;
import java.util.*;

class Ponto implements Comparable<Ponto> {
	public int x1;
	public int x2;
	Ponto( int x1, int x2 ) {
		this.x1 = x1;
		this.x2 = x2;
	}
	@Override
	public int compareTo( Ponto v ) {
		if( x1 == v.x1 )
			return v.x2 - x2;
		else
			return x1 - v.x1;
	}
}

class Cover {
	public static void main( String[ ] args ) {
		Scanner in = new Scanner( System.in );
	
		int len = in.nextInt();    			/* Tamanho do segmento de reta */
		int n   = in.nextInt();				/* Numero de segmentos */
		Ponto[ ] array = new Ponto[ n ]; 	/* Vetor que guarda em cada posição um segmento de reta */
		int end = 0;						/* Valor da posição final do ultimo segmento */
		
		for( int i = 0; i < n; i++ ) {		/* Ciclo que lê os segmentos e guarda no vetor */
			array[ i ] = new Ponto(in.nextInt(), in.nextInt());
		}
		Arrays.sort( array );
		
		/*System.out.println();
		for(int i = 0; i < len; i++ )
			System.out.println( array[ i ].x1+" "+array[ i ].x2 );
		System.out.println();
		*/
		int count = 0;
		int i = 0;
		int max_size = 0;
		int max_pos = 0;
	
		while( end < len && i < n ) {
			if( array[i].x1 == end && array[i].x2 > end ) {
				count++;			
				end = array[i].x2;
			}
			i++;
		}
		System.out.println( count );
	}
	
	
}
