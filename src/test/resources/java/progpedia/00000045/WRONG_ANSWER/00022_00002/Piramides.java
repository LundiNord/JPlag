import java.io.*;
import java.util.*;

public class Piramides {
	
	static int N;	//nivel de camadas da piramide e numero de pedras na primeira camada
	
	static int contaPercurso(int m[][]){
		for(int i = 1, k = N; i < N; i++,k--){
			for(int j = 0; j < k; j++){
				if(m[i][j] == -1) m[i][j] = 0;
				else m[i][j] = m[i-1][j] + m[i-1][j+1];
			}
		}
		return m[N-1][0];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner stdin = new Scanner(System.in);
		int D; //numero de pedras em falta ou danificadas
		int C[], P[]; //C representa a camada e P representa a posicao da pedra nessa camada
		
		N = stdin.nextInt();
		D = stdin.nextInt();
		C = new int[D];
		P = new int[D];
		
		for(int  i = 0; i < D; i++){
			C[i] = stdin.nextInt();
			P[i] = stdin.nextInt();
		}
		
		int m[][] = new int[N][2*N];
		
		//criacao da matriz padrao
		for(int i = 0; i < 1; i++){
			for(int j = 0; j < N; j++){
				m[i][j] = 1;
				//System.out.print(m[i][j] + " ");
			}
			//System.out.println();
		}
		
		//colocar na piramide as pedras danificadas
		for(int i = 0; i < D; i++){
			if(C[i] == 1) m[C[i] -1][P[i]-1] = 0;
			else m[C[i]-1][P[i]-1] = -1;
		}
		
		System.out.println(contaPercurso(m));
		
		//debug
		/*
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		*/
		
		
		
	}

}
