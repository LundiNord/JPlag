//Trabalho realizado com Marco Regueiras up201305333

import java.util.*;
import java.io.*;

class piramide{
	public static long contaCaminho (int n, boolean solta[][]){
		long count [][] = new long [n][n];

		for (int i=n-1; i>=0; i--){
			for (int j=0; j<=i; j++) {
				if(solta[i][j]==true)
					count[i][j]=0;
				else if(i==n-1 && solta[i][j]==false)
					count[i][j]=1;
				else{
					count[i][j] = count[i+1][j]+count[i+1][j+1];
				}
			}
		}

		return count[0][0]; //retorna o valor no topo da piramide
	}

	public static void main(String args[]) {
		Scanner inp = new Scanner (System.in);

		int n = inp.nextInt();
		int d = inp.nextInt();
		int camada;
		int pos;
		int p[][]=new int[n][n];
		boolean solta[][]=new boolean[n][n];
		int ncaminhos;


		//Posição das pedras soltas
		for(int i=0; i<d; i++){
			camada = inp.nextInt();
			pos = inp.nextInt();
			solta[n-camada][pos-1] = true;
		}
	
		System.out.println(contaCaminho(n, solta));
	}
}
