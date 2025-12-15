import java.util.*;

import java.util.*;
class Prob_15{
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int nNos = in.nextInt();	
		int nLigacoes = in.nextInt();
		int arvore[][] = new int[nNos+1][nNos+1];

		for (int i=0; i<nLigacoes; i++) {
			int ptx = in.nextInt();
			int pty = in.nextInt();
			arvore[ptx][pty] = 1;
			arvore[pty][ptx] = 1;
		}

		int nos_visitados[]= new int[nNos+1];
		for (int i=0; i<nNos; i++)
			nos_visitados[i] = 0;


		int count=0;
		for (int i=1; i<nNos+1; i++) {
			if (nos_visitados[i] == 0) {
				count +=1;
				DFS(arvore, nos_visitados, nNos, i);
			}
		}


		System.out.println(count);
	}

	public  static void DFS(int arvore[][], int nos_visitados[], int nNos, int no){
		nos_visitados[no] = 1;
		for ( int j=1;  j<nNos+1; j++) 
			if( arvore[no][j] ==1 && nos_visitados[j] == 0)
				DFS(arvore, nos_visitados, nNos, j);

	}
}