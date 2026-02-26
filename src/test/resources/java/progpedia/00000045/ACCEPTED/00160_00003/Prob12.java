import java.io.*;
import java.util.*;


class Prob12{
	static long caminhos(long [][] piramide, int n){
		for(int i = n-2; i>=0; i--){
			for(int j = 0; j<=i; j++){
				if(piramide[i][j]!=0)
					piramide[i][j] = piramide[i+1][j] + piramide[i+1][j+1]; //a soma dos deus inferiores
			}
		}
		return piramide[0][0];
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int c,p;
		int n = in.nextInt(); //número de pedras na primeira camada
		int d = in.nextInt(); //pedras em falta ou muito deterioradas
		
		long [][] piramide = new long[n][n]; //para guardar as posições da piramide
		
		//Put the matrix all at 1's
		
		for(int i = n-1; i>=0; i--){ //baixo para cima
			for(int j = 0; j<=i; j++){ //esquerda para a direita
				piramide[i][j]=1; // atribuir 1 em todas as posiçoes
			}
		}
		
		
		//reads the rotten rock 
		for(int i = 0; i < d; i++){
			c = in.nextInt(); //C representa a camada
			p = in.nextInt(); //P representa a posição da pedra nessa camada
			piramide[n-c][p-1] = 0; //atribuir 0 às pedras danificadas
		}
		
		System.out.println(caminhos(piramide, n));
	}
}
