import java.util.*;
import java.io.*;

class Redes{
	static int n;
	static boolean adj[][]; 
	static boolean visited[];
	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		n = input.nextInt();
		adj     = new boolean[n+1][n+1];
		visited = new boolean[n+1];	
		int edges = input.nextInt();
	
		for (int i=0; i<edges; i++) {
			int a = input.nextInt();
		   int b = input.nextInt();
	      adj[a][b] = adj[b][a] = true;
		}

		int contador = 0;
		for (int i=1; i<=n; i++)
			if (!visited[i]) {
				contador++;
//				System.out.println("NO INICIAL: " + i);
				dfs(i);
			}	

		System.out.println(contador);

	}

	static void dfs(int v){
//		System.out.println("dfs: " + v);
		visited[v] = true;
		for (int i=1; i<=n; i++)
	   	if (adj[v][i] && !visited[i])
				dfs(i);
	}

}
