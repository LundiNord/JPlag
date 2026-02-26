//package daa;

import java.util.Scanner;

public class redes {
static Scanner in = new Scanner(System.in);

	public static void dfs(int a, int n, boolean adj[][], boolean vis[]){
		//marcar visitado
		vis[a] = true;
		for (int i=1; i<=n; i++)
			if (adj[a][i] && !vis[i])
				dfs(i,n,adj,vis);
	}
	
	public static void main(String[] args) {
		int n=in.nextInt();
		int l=in.nextInt();
		//Matriz de Adjacencia que recebe o input
		boolean adj[][] = new boolean [n+1][n+1];
		for(int i = 1; i <= l ; i++){
			int a = in.nextInt();
			int b = in.nextInt();
			adj[a][b] = adj[b][a] = true;
		}
		int contador = 0;
		//array de  visitados
		boolean vis[] = new boolean [n+1];
		for(int i = 1; i <= n; i++) {			
				if(!vis[i]){
					contador ++;
					dfs(i, n, adj, vis);
				}
		}
		System.out.println(contador);
	}
}
