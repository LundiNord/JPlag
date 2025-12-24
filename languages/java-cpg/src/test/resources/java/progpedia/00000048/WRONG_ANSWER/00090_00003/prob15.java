//package Exercicios;
import java.util.Scanner;

public class prob15 {

	static int n;                 // Numero de nos do grafo
	static boolean adj[][]; 	  // Matriz de adjacencias
	static boolean visited[];     // Que nos ja foram visitados?
	static int count=0;
	
	static void dfs(int v) 
	{   	
		visited[v] = true;
		for (int i=1; i<=n; i++)
			if (adj[v][i] && !visited[i])
			{
				count++;
				dfs(i);
			}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		adj     = new boolean[n+1][n+1];
		visited = new boolean[n+1];
		int edges = in.nextInt();	
		for (int i=0; i<edges; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			adj[a][b] = adj[b][a] = true;
			
		}
	
		dfs(1); // Pesquisa em profundidade a partir do no 1
		System.out.println(count);    
		in.close();
	}
}