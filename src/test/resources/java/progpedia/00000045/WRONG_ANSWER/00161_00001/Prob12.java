import java.util.*;
import java.io.*;
import java.util.Arrays;

class Prob12{
		static int caminhos( int [][] piramide , int pedras ) {
			for ( int i = pedras -2 ; i >= 0 ; i--) {
				for ( int j = 0  ; j <= i ; j++) {
					if ( piramide[i][j] != 0 ) {
						piramide[i][j] = piramide[i+1][j] + piramide[i+1][j+1];
					}
				}
			}
			return piramide[0][0];
		}
			
	public static void main ( String[] args){
	Scanner ler = new Scanner ( System.in);
	int pedras = ler.nextInt();
	int d = ler.nextInt();
	int piramide[][] = new int [pedras][pedras];
	int c , l ; 
	for ( int i = pedras-1 ; i >= 0 ; i--) {
		for ( int j = 0 ; j <= i ; j++) {
		piramide[i][j] = 1 ; 
		}
	}
	
	
	for (int i = 0 ; i < d ; i++) {
		c = ler.nextInt();
		l = ler.nextInt();
			piramide[pedras-c][l-1] = 0 ; 
		}
	System.out.println(caminhos(piramide , pedras)) ;
	}
}
	
	
