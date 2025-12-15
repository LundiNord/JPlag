import java.io.*;
import java.util.*;

class Piramides {
	public static void main( String[] args ) {
		Scanner in = new Scanner( System.in );
		int n = in.nextInt(); 								// Numero de camadas da piramide
		int d = in.nextInt();								// Numero de pedras danificadas
		long[][] p = new long[n][n];
		
		for( int j = 0; j < n; j++ ) {						// Primeira camada toda a 1, e o nr de caminhos possiveis para cada pedra no inicio
			p[0][j] = 1;
		}
		
		while( d > 0 ) {									// Colocamos na posição das pedras danificadas o valor 0
			int i = in.nextInt();
			int j = in.nextInt();
			p[i-1][j-1] = -1;								// Retiramos 1 ao valo x e y da posiçao porque a matriz começa em 0
			d--;
		}
		
		
		
		for( int i = 0; i < n; i++ ) {
			for( int j = 0; j < n; j++ ) {
				
				if( i+1 < n && j+1 < n ) {
					
					if( p[i+1][j] != -1 ) {							// Se não for uma pedra danificada faz a soma
						
						if( p[i][j] == -1 )
							p[i+1][j] = p[i][j+1];
						else if( p[i][j+1] == -1 )
							p[i+1][j] = p[i][j];
						else
							p[i+1][j] = p[i][j] + p[i][j+1];
					}
				}
			}
		}
		if( p[n-1][0] == -1 )										// Verifica se a última posição é uma pedra danificada
			System.out.println("0");								// Se estiver danificada não existem caminhos possiveis para o topo da piramide
		else														
			System.out.println(p[n-1][0]);							// Se não estive danificada, o valor do topo da piramide e o nr de caminhos
																	// possiveis					
	}
}