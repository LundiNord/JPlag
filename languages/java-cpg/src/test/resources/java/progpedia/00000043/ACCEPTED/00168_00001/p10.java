//Exercicio feito em conjunto pelos alunos
// Carlos Bernardes up201303743
// Célio Rodrigues up201303171
import java.util.*;
import java.io.*;

class p10{
	public static void main(String [] args){
			Scanner in = new Scanner(System.in);
			int n = in.nextInt();

			// o racio entre nr de dias e multa ira dar a solução greedy ótima
				
			double [] racio = new double[n]; 
			
			for( int i = 0; i < n; i++ ){
				int dias = in.nextInt();
				int multa = in.nextInt();
				racio[i] = dias/(multa+0.00);
			}
			
			for( int i = 0; i < n; i++ ){
				double min = 999999;
				int index = 0; // o index da permutação do nr do sapato

				for( int j = 0; j < n ; j++ )
					if( min > racio[j] && racio[j] > 0.00 ){
						min = racio[j];
						index = j;
					}
				
					System.out.print( (index+1)+" ");

				racio[index] = 0.00;
			}
			System.out.println();		
	}
}
