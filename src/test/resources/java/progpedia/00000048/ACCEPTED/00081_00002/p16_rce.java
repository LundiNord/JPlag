import java.util.*;
import java.io.*;


// nao tinha o java instalado (atualizei para o novo min).
// ja instalei, deve dar 100% agora. se nao der meh :( 

class p16_rce {
	
	static boolean [] visited;
	static boolean [][] edges;
	static int nv, ne;

	static void dfs (int v) {
		visited[v] = true;
		for(int i=0; i<nv; i++)
			if(!visited[i] && edges[v][i])
				dfs(i);
	}


	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);

		nv = stdin.nextInt()+1;
		ne = stdin.nextInt();

		visited = new boolean[nv];
		edges = new boolean[nv][nv];
		
		for (int i=0; i<ne; i++){
			int a = stdin.nextInt();	
			int b = stdin.nextInt();
			edges[a][b] = edges[b][a] = true;
		}

		int	contador = 0;

		for (int i=0; i<nv; i++) {
			if(!visited[i]) {
				dfs(i);
				contador++;
			}
		}
		System.out.println(contador-1);
	}
}