//package aulas;

import java.io.*;
import java.util.*;

/* Gera-se a matriz da piramide
 * inicializa-se  a 1 e depois marca-se os "roadblocks" a 0
 * percorre-se a piramide contando o numero de caminhos (sempre que 
 * é possivel chegar de um bloco a outro conta como um novo caminho)
 * uma piramide de 4 camadas tem base 4 etc (n camadas = n pedras base)
 * i camada j posicao na camada 
 */
class Problema12{
	static long ncaminhos(long [][] piramide, int n){
		for(int i = n-2; i>=0; i--){
			for(int j = 0; j<=i; j++){
				if(piramide[i][j]!=0)
					piramide[i][j] = piramide[i+1][j] + piramide[i+1][j+1];
			}
		}
		return piramide[0][0];
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int c,p;
		int n = in.nextInt();
		int d = in.nextInt();
		
		long [][] piramide = new long[n][n];
		
		
		for(int i = n-1; i>=0; i--){ //inicializar matriz
			for(int j = 0; j<=i; j++){
				piramide[i][j]=1;
			}
		}
		
		for(int i = 0; i < d; i++){ //ler input de roadblocks e colocar na matriz
			c = in.nextInt();
			p = in.nextInt();
			piramide[n-c][p-1] = 0;
		}
		
		System.out.println(ncaminhos(piramide, n));
	}
}
