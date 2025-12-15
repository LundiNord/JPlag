import java.util.*;
import java.io.*;

class p12{
	public static void main(String args[]){
		Scanner input = new Scanner(System.in);

		int nPedras = input.nextInt();
		int[][] matrix = new int[nPedras][nPedras];
		int pedrasDet = input.nextInt();

		for(int i=0; i < nPedras; i++){
			for(int j=0; j < nPedras; j++){
				matrix[i][j] = 1;
				//System.out.print(matrix[i][j]);
			}
			//System.out.println();
		}

		for(int i=0; i < pedrasDet ;i++){
			int x = input.nextInt(); int y = input.nextInt();

			matrix[Math.abs(nPedras-x)][Math.abs(y-1)] = 0;
		}
		/*----------------------------PROGRAMACAO DINAMICA -------------------------*/

		//COMECAR DO FIM DA MATRIX
		int colunas = nPedras-1;

		for(int i= (nPedras-2); i>=0 ; i--){

			for(int j = 0; j <colunas; j++){

				if(matrix[i][j] == 0) {
					matrix[i][j] = 0;
				}

				else if(matrix[i][j] != 0) {
					matrix[i][j] = matrix[i+1][j] + matrix[i+1][j+1];
				}
			}
		colunas --;
		}

		System.out.println(matrix[0][0]);
	}
}
