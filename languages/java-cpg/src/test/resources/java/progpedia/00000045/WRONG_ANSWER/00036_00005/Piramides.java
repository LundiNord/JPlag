import java.util.*;

//	MUDAR A MATRIZ PARA UM LONG POR CAUSA D MAQUINA DO MOOSHAK


class Piramides{
	static int n;
	static int piramide[][] = new int[n][n];
	static int contador = 0;

	static int calcular(){
		for(int i=0;i<n-1;i++){
			for(int j=0;j<=i;j++){
				if(piramide[i][j] != -1){
					if(piramide[i+1][j] != -1 && i<n-1)
						piramide[i+1][j] += piramide[i][j];
					if(piramide[i+1][j+1] != -1 && i<n-1)
						piramide[i+1][j+1] += piramide[i][j];
				}
			}
		}
		for(int i=0;i<=n-1;i++){
			if(piramide[n-1][i] != -1)
				contador += piramide[n-1][i];
		}
		return contador;
	}
/*
	static boolean pedra_partida(int i,int j,String posicao){
		int linha,coluna;
		if(posicao == "Esquerda" && j-1 >= 0){
			linha = i - 1;
			coluna = j - 1;
			if(piramide[linha][coluna] == -1)
				return true;
			else
				return false;
		}	
		if(posicao == "Cima" && i-1 >= j){
			linha = i - 1;
			coluna = j;
			if(piramide[linha][coluna] == -1)
				return true;
			else
				return false;
		}	
		return true;
	}
 
*/


	public static void main(String args[]){
		Scanner input = new Scanner(System.in);

		n = input.nextInt();
		int c = input.nextInt();
		piramide = new int[n][n];
										
		piramide[0][0] = 1;
					
		int lm=0,cm=0,aux1=0;		// BLOCOS PARTIDOS COM -1
		for(int i=0;i<c;i++){
			lm = input.nextInt();
			aux1 += (n-lm); 
			cm = input.nextInt();
			cm--;
			piramide[aux1][cm] = -1;
			aux1 = 0;
		}

		System.out.println(calcular());

		/*
		//IMPRIMIR A MATRIZ
		for(int i=0;i<n;i++){
			System.out.println();
			for(int j=0;j<n;j++)
				System.out.print(piramide[i][j] + " ");
		}
		System.out.println();
			*/	

	}
}